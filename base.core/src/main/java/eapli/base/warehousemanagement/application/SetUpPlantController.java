package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.*;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SetUpPlantController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final PlantRepository repositoryPlant = PersistenceContext.repositories().plants();
    private final AisleRepository repositoryAisle = PersistenceContext.repositories().aisles();
    private final RowRepository repositoryRow = PersistenceContext.repositories().rows();
    private final ShelfRepository repositoryShelf = PersistenceContext.repositories().shelfs();
    private final AgvDockRepository repositoryDock = PersistenceContext.repositories().agvDocks();
    private final SquareRepository squareRepository = PersistenceContext.repositories().squares();
    private Square beginSquare=null;
    private Square endSquare=null;
    private Square depthSquare=null;


    @SuppressWarnings("unchecked")
    public boolean setUpPlant(File json){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(json)) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            /*
            warehouse Plant characteristics
             */
            String warehouseName = (String) jsonObject.get("Warehouse");
            WarehouseName name = new WarehouseName(warehouseName);

            Long warehouseLength = (Long) jsonObject.get("Length");
            Length length = new Length(warehouseLength);

            Long warehouseWidth = (Long) jsonObject.get("Width");
            Width width = new Width(warehouseWidth);

            Long warehouseSquare = (Long) jsonObject.get("Square");
            SquareSize squareSize = new SquareSize(warehouseSquare);

            String warehouseUnit = (String) jsonObject.get("Unit");
            Unit unit = new Unit(warehouseUnit);

            final var newPlant= new PlantBuilder().
                    hasName(name).
                    hasLength(length).
                    hasWidth(width).
                    hasSquareSize(squareSize).
                    hasUnit(unit).
                    build();
            repositoryPlant.save(newPlant);

            /*
            aisle characteristics
             */
            JSONArray aisles = (JSONArray) jsonObject.get("Aisles");

            for (JSONObject aisle : (Iterable<JSONObject>) aisles) {
                Long id = (Long) aisle.get("Id");

                if(aisle.get("begin")!=null){
                    JSONObject warehouseBegin = (JSONObject) aisle.get("begin");

                    Long lsquare = (Long) warehouseBegin.get("lsquare");
                    Long wsquare = (Long) warehouseBegin.get("wsquare");

                    beginSquare = new Square(lsquare, wsquare);
                    squareRepository.save(beginSquare);

                }
                if(aisle.get("end")!=null){
                    JSONObject warehouseEnd = (JSONObject) aisle.get("end");

                    Long lsquare = (Long) warehouseEnd.get("lsquare");
                    Long wsquare = (Long) warehouseEnd.get("wsquare");

                    endSquare = new Square(lsquare, wsquare);
                    squareRepository.save(endSquare);
                }
                if(aisle.get("depth")!=null){
                    JSONObject warehouseDepth = (JSONObject) aisle.get("depth");

                    Long lsquare = (Long) warehouseDepth.get("lsquare");
                    Long wsquare = (Long) warehouseDepth.get("wsquare");

                    depthSquare = new Square(lsquare, wsquare);
                    squareRepository.save(depthSquare);
                }
                String warehouseAccessibility = (String) aisle.get("accessibility");
                Accessibility accessibility = new Accessibility(warehouseAccessibility);

                final var newAisle = new AisleBuilder()
                        .hasID(id).hasBegin(beginSquare)
                        .hasEnd(endSquare)
                        .hasDepth(depthSquare)
                        .hasAccessibility(accessibility)
                        .build();
                repositoryAisle.save(newAisle);

                /*
                Row characteristics
                 */
                JSONArray rows = (JSONArray) aisle.get("rows");
                for (JSONObject row : (Iterable<JSONObject>) rows) {
                    Long idRow = (Long) row.get("Id");


                    if (row.get("begin") != null) {
                        JSONObject warehouseBegin = (JSONObject) row.get("begin");

                        Long lsquare = (Long) warehouseBegin.get("lsquare");
                        Long wsquare = (Long) warehouseBegin.get("wsquare");

                        beginSquare = new Square(lsquare, wsquare);
                        squareRepository.save(beginSquare);
                    }
                    if (row.get("end") != null) {
                        JSONObject warehouseEnd = (JSONObject) row.get("end");

                        Long lsquare = (Long) warehouseEnd.get("lsquare");
                        Long wsquare = (Long) warehouseEnd.get("wsquare");

                        endSquare = new Square(lsquare, wsquare);
                        squareRepository.save(endSquare);
                    }
                    final var newRow = new RowBuilder()
                            .hasID(idRow)
                            .hasBegin(beginSquare)
                            .hasEnd(endSquare)
                            .hasAisle(newAisle)
                            .build();
                    repositoryRow.save(newRow);

                    /*
                    Shelf characteristics
                     */
                    Long numShelfs = (Long) row.get("shelves");

                    for (int i=0; i<Math.toIntExact(numShelfs); i++){
                        final var newShelf = new ShelfBuilder().hasRow(newRow).build();
                        repositoryShelf.save(newShelf);
                    }

                }

            }

            /*
            AGV Dcok characteristics
             */
            JSONArray agvDocks = (JSONArray) jsonObject.get("AGVDocks");
            for (JSONObject dock : (Iterable<JSONObject>) agvDocks){
                String dockID = (String) dock.get("Id");

                if(dock.get("begin")!=null){
                    JSONObject warehouseBegin = (JSONObject) dock.get("begin");

                    Long lsquare = (Long) warehouseBegin.get("lsquare");
                    Long wsquare = (Long) warehouseBegin.get("wsquare");

                    beginSquare = new Square(lsquare, wsquare);
                    squareRepository.save(beginSquare);
                }
                if(dock.get("end")!=null){
                    JSONObject warehouseEnd = (JSONObject) dock.get("end");

                    Long lsquare = (Long) warehouseEnd.get("lsquare");
                    Long wsquare = (Long) warehouseEnd.get("wsquare");

                    endSquare = new Square(lsquare, wsquare);
                    squareRepository.save(endSquare);
                }
                if(dock.get("depth")!=null){
                    JSONObject warehouseDepth = (JSONObject) dock.get("depth");

                    Long lsquare = (Long) warehouseDepth.get("lsquare");
                    Long wsquare = (Long) warehouseDepth.get("wsquare");

                    depthSquare = new Square(lsquare, wsquare);
                    squareRepository.save(depthSquare);
                }
                String fileAccessibility = (String) dock.get("accessibility");
                Accessibility accessibility = new Accessibility(fileAccessibility);
                final var newAGVDock = new AgvDockBuilder()
                        .hasDockID(dockID)
                        .hasBegin(beginSquare)
                        .hasEnd(endSquare)
                        .hasDepth(depthSquare)
                        .hasAccessibility(accessibility)
                        .build();

                repositoryDock.save(newAGVDock);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return true;

    }
}
