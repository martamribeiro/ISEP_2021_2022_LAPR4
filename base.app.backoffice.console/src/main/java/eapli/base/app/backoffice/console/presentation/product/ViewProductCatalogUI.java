package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.application.ViewProductCatalogController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class ViewProductCatalogUI extends AbstractUI {
    private final ViewProductCatalogController controller = new ViewProductCatalogController();
    private Scanner read = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        String useFilters = "";
        String moreFilters = "";
        List<String> catalog = new ArrayList<>();
        boolean isValidYesOrNo1 = false;
        boolean isValidYesOrNo2 = false;
        boolean isValidNumber = false;
        Integer filterOption;
        int numSelectedFilters = 0;

        Map<Integer, String> filters = new HashMap<>();
        Map<Integer, String> selectedFilters = new HashMap<>();
        filters.put(1, "Category");
        filters.put(2, "Brand");
        filters.put(3, "Description");
        filters.put(4, "All above");

        int i=0, j, m;
        System.out.println("Do you want to use filters? \n Yes - Y | No - N");
        do{
            if(i > 0){
                System.out.println("Please enter a valid answer. (Yes - Y | No - N");
            }
            useFilters = read.nextLine();

            if(useFilters.equalsIgnoreCase("yes") || useFilters.equalsIgnoreCase("y") ||
                    useFilters.equalsIgnoreCase("no") || useFilters.equalsIgnoreCase("n")){
                isValidYesOrNo1 = true;
            }
            i++;
        }while(!isValidYesOrNo1);

        if(useFilters.equalsIgnoreCase("no") || useFilters.equalsIgnoreCase("n")){
            catalog = controller.showProductCatalog();

            for(String product : catalog){
                System.out.println(product);
            }
        }else{
            do {
                m = 0;
                j = 0;

                System.out.println("Which filter do you want to use?");
                for (Integer filterNum : filters.keySet()) {
                    System.out.println(filterNum + " - " + filters.get(filterNum));
                }

                do {
                    if (j > 0) {
                        System.out.println("Please enter a valid number.");
                    }

                    filterOption = read.nextInt();

                    if(filterOption == 1 || filterOption == 2 || filterOption == 3 || filterOption == 4){
                        isValidNumber = true;
                        selectedFilters.put(filterOption, filters.get(filterOption));
                        numSelectedFilters++;
                    }

                    j++;
                } while(!isValidNumber);



                if(filterOption == 4) {
                    moreFilters = "no";
                }else {

                    filters.remove(filterOption, filters.get(filterOption));
                    filters.remove(4, filters.get(4));

                    do {
                        if (m > 0) {
                            System.out.println("Please enter a valid answer. (Yes - Y | No - N)");
                        }

                        if(numSelectedFilters < 3) {
                            moreFilters = Console.readLine("Do you want to use any more filters? \n Yes - Y | No - N");

                            if(moreFilters.equalsIgnoreCase("yes") || moreFilters.equalsIgnoreCase("y")
                                    || moreFilters.equalsIgnoreCase("no") || moreFilters.equalsIgnoreCase("n")){
                                isValidYesOrNo2 = true;
                            }

                            m++;
                        }else{
                            moreFilters = "no";
                        }
                    } while(!isValidYesOrNo2);
                }
            }while (!moreFilters.equalsIgnoreCase("no") && !moreFilters.equalsIgnoreCase("n"));
        }

        for(Integer selectedOption : selectedFilters.keySet()) {
            switch (selectedOption) {
                case 1:
                    catalog = controller.showProductCatalogFilteredByCategory();

                    for (String product : catalog) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    catalog = controller.showProductCatalogFilteredByBrand();

                    for (String product : catalog) {
                        System.out.println(product);
                    }
                    break;
                case 3:
                    catalog = controller.showProductCatalogFilteredByDescription();

                    for (String product : catalog) {
                        System.out.println(product);
                    }
                    break;
                case 4:
                    catalog = controller.showProductCatalogFilteredByAll();

                    for(String product : catalog){
                        System.out.println(product);
                    }
                    break;
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "View Catalog";
    }
}
