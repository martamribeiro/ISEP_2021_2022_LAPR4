package route_planner;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.Bin;
import positioning.AGVCurrentPositions;
import sensor.TheSensor;

import java.awt.geom.Point2D;
import java.util.*;



// A queue node used in BFS
class Node
{
    // (x, y) represents coordinates of a cell in the matrix
    int x, y;

    // maintain a parent node for printing the final path
    Node parent;

    Node(int x, int y, Node parent)
    {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}

public class AgvRoute {
    private AGV currentAGV;
    private AGVPosition currentAGVPosition;
    private TheTask agvTask;
    private String[][] warehousePlant;

    public AgvRoute(AGV currentAGV, AGVPosition currentAGVPosition, TheTask agvTask, String[][] warehousePlant) {
        this.currentAGV = currentAGV;
        this.currentAGVPosition = currentAGVPosition;
        this.agvTask = agvTask;
        this.warehousePlant = warehousePlant;
    }

    // Below arrays detail all four possible movements from a cell
    private static int[] row = { -1, 0, 0, 1 };
    private static int[] col = { 0, -1, 1, 0 };

    // The function returns false if (x, y) is not a valid position
    private static boolean isValid(int x, int y, int N, int M, String[][] matrix, String agvDockID) {
        if((x >= 0 && x < N) && (y >= 0 && y < M)) {
            if(matrix[x][y].equals("X") || (matrix[x][y].equals(agvDockID))) {
                return true;
            }
        }
        return false;
    }

    // Utility function to find path from source to destination
    private static void findPath(Node node, List<Point2D> path)
    {
        if (node != null) {
            findPath(node.parent, path);
            path.add(new Point2D.Double(node.x, node.y));
        }
    }

    // Find the shortest route in a matrix from source cell (x, y) to
    // destination cell (xDest, yDest)
    public static LinkedList<Point2D> findPath(String[][] matrix, int x, int y, int xDest, int yDest, String agvDockID)
    {
        // list to store shortest path
        LinkedList<Point2D> path = new LinkedList<>();

        // base case
        if (matrix == null || matrix.length == 0) {
            return path;
        }

        // `N × M` matrix
        int N = matrix.length;      //lines
        int M = matrix[0].length;   //columns


        // create a queue and enqueue the first node
        Queue<Node> q = new ArrayDeque<>();
        Node src = new Node(x, y, null);
        q.add(src);

        // set to check if the matrix cell is visited before or not
        Set<String> visited = new HashSet<>();

        String key = src.x + "," + src.y;
        visited.add(key);

        // loop till queue is empty
        while (!q.isEmpty())
        {
            // dequeue front node and process it
            Node curr = q.poll();
            int i = curr.x, j = curr.y;

            // return if the destination is found
            if (i == xDest && j == yDest) {
                findPath(curr, path);
                return path;
            }

            // value of the current cell
            //int n = matrix[i][j];

            // check all four possible movements from the current cell
            // and recur for each valid movement
            for (int k = 0; k < row.length; k++)
            {
                // get next position coordinates using the value of the current cell
                x = i + row[k];
                y = j + col[k];

                // check if it is possible to go to the next position
                // from the current position
                if (isValid(x, y, N, M, matrix, agvDockID))
                {
                    // construct the next cell node
                    Node next = new Node(x, y, curr);

                    key = next.x + "," + next.y;

                    // if it isn't visited yet
                    if (!visited.contains(key))
                    {
                        // enqueue it and mark it as visited
                        q.add(next);
                        visited.add(key);
                    }
                }
            }
        }

        // we reach here if the path is not possible
        return path;
    }

    public synchronized LinkedList<Point2D> computeFinalRoute() {


        // start position of route: current position of AGV
        int xSource = Math.toIntExact(this.currentAGVPosition.wSquare());
        int ySource = Math.toIntExact(this.currentAGVPosition.lSquare());

        List<Bin> bins = this.agvTask.bins();

        Bin firstBin = bins.get(0);
        int xBin = Math.toIntExact(firstBin.row().beginSquare().wSquare());
        int yBin = Math.toIntExact(firstBin.row().beginSquare().lSquare());
        int xNextBin, yNextBin, xDock, yDock;

        String agvDockID = this.currentAGV.getAgvDock().getAgvDockID();

        LinkedList<Point2D> finalRoute = findPath(warehousePlant,xSource,ySource,xBin, yBin, agvDockID);

        for (int i = 0; i < bins.size()-1; i++) {
            xBin = Math.toIntExact(bins.get(i).row().beginSquare().wSquare());
            yBin = Math.toIntExact(bins.get(i).row().beginSquare().lSquare());
            xNextBin = Math.toIntExact(bins.get(i+1).row().beginSquare().wSquare());
            yNextBin = Math.toIntExact(bins.get(i+1).row().beginSquare().lSquare());
            LinkedList<Point2D> route = findPath(warehousePlant,xBin, yBin, xNextBin, yNextBin, agvDockID);
            route.removeFirst();
            finalRoute.addAll(route);
        }

        //position of last product
        xBin = Math.toIntExact(bins.get(bins.size()-1).row().beginSquare().wSquare());
        yBin = Math.toIntExact(bins.get(bins.size()-1).row().beginSquare().lSquare());

        //final position of route: AGV Dock
        xDock = Math.toIntExact(this.currentAGV.getAgvDock().beginSquare().wSquare()-1);
        yDock = Math.toIntExact(this.currentAGV.getAgvDock().beginSquare().lSquare()-1);

        LinkedList<Point2D> comingBackToDock = findPath(warehousePlant, xBin, yBin, xDock, yDock, agvDockID);
        comingBackToDock.removeFirst();
        finalRoute.addAll(comingBackToDock);

        return finalRoute;
    }



    //only for test purposes
    public static void main(String[] args)
    {
        String[][] matrix =
                {
                        { "X", "X", "X", "X", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "D1", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "D5", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "D2", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "D3", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "D6", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "D4", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                };


        //fazer para na ultima route verificar que o destino é D?
        System.out.println(matrix[2][0]);
        System.out.println(matrix[15][0]);

        // Find a route in the matrix from source cell (0, 0) to
        // destination cell (N-1, N-1)
        LinkedList<Point2D> path = findPath(matrix, 2, 0, 10,29, "D4");


        if (path != null && path.size() > 0) {
            System.out.print("The shortest path is " + path);
        } else {
            System.out.println("Destination is not found");
        }
    }
}
