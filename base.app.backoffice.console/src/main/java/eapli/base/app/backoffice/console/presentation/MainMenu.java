/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation;


import eapli.base.app.backoffice.console.presentation.agv.AssignOrderToFreeAGVUI;
import eapli.base.app.backoffice.console.presentation.order.DispatchOrderUI;
import eapli.base.app.backoffice.console.presentation.order.RegisterClientOrderUI;
import eapli.base.app.backoffice.console.presentation.agv.ConfigureAvailableAGVUI;
import eapli.base.app.backoffice.console.presentation.agv.RegisterAGVUI;
import eapli.base.app.backoffice.console.presentation.client.RegisterClientUI;
import eapli.base.app.backoffice.console.presentation.order.ViewOrdersSentToCostumerUI;
import eapli.base.app.backoffice.console.presentation.product.CreateCategoryUI;
import eapli.base.app.backoffice.console.presentation.product.RegisterProductUI;
import eapli.base.app.backoffice.console.presentation.product.ViewProductCatalogUI;
import eapli.base.app.backoffice.console.presentation.questionnaire.CreateQuestionnaireUI;
import eapli.base.app.backoffice.console.presentation.questionnaire.GenerateReportUI;
import eapli.base.app.backoffice.console.presentation.warehouseplant.SetUpPlantUI;
import eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard.DashboardUI;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    // COSTUMERS
    private static final int REGISTER_COSTUMER_OPTION = 1;
    private static final int REGISTER_ORDER_FOR_CLIENT_OPTION = 2;

    //PRODUCT
    private static final int REGISTER_PRODUCT_OPTION = 3;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 4;

    // MAIN MENU SALES CLERK
    private static final int PRODUCT_OPTION = 1;
    private static final int COSTUMERS_OPTION = 2;
    private static final int CREATE_CATEGORY = 5;

    private static final int SHOW_CATALOG = 6;
    private static final int CHANGE_ORDER_STATUS_FROM_DISPATCHED_TO_DELIEVERED = 7;

    // WAREHOUSE EMPLOYEE
    private static final int REGISTER_AGV = 1;
    private static final int CONFIGURE_AVAILABLE_AGVS = 2;
    private static final int INSERT_AGV_INFORMATIONS = 2;
    private static final int SET_UP_PLANT=3;

    private static final int ASSIGN_AGV_TO_ORDER = 4;

    private static final int DISPATCH_ORDER = 5;

    private static final int DASHBOARD = 6;

    //private static final int AUTOMATICALLY_ASSIGN_AGV_TO_ORDER = 5;

    //SALES MANAGER
    private static final int CREATE_QUESTIONNAIRE = 1;

    private static final int SALES_MANAGER_OPTION = 2;

    private static final int SEE_QUESTIONNAIRES_REPORT = 2;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.SALES_CLERK)) {
            final Menu salesClerkMenu = buildSalesClerk();
            mainMenu.addSubMenu(COSTUMERS_OPTION, salesClerkMenu);
        }

        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.WAREHOUSE_EMPLOYEE)){
            final Menu warehouseEmployeeMenu = buildWarehouseEmployee();
            mainMenu.addSubMenu(INSERT_AGV_INFORMATIONS, warehouseEmployeeMenu);
        }

        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.SALES_MANAGER)){
            final Menu salesManagerMenu = buildSalesManagerMenu();
            mainMenu.addSubMenu(SALES_MANAGER_OPTION, salesManagerMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }



    private Menu buildSalesClerk() {
        final Menu menu = new Menu("Sales Clerk >");

        menu.addItem(REGISTER_COSTUMER_OPTION, "Register Client", new RegisterClientUI()::show);
        menu.addItem(REGISTER_ORDER_FOR_CLIENT_OPTION, "Register Order On Behalf Of A Costumer", new RegisterClientOrderUI()::show);
        menu.addItem(REGISTER_PRODUCT_OPTION, "Register Product", new RegisterProductUI()::show);
        menu.addItem(CREATE_CATEGORY, "Create Product Category", new CreateCategoryUI()::show);
        menu.addItem(SHOW_CATALOG, "Show Products Catalog", new ViewProductCatalogUI()::show);
        menu.addItem(CHANGE_ORDER_STATUS_FROM_DISPATCHED_TO_DELIEVERED, "Change Order Status from: Dispatched for Client to: Delievered", new ViewOrdersSentToCostumerUI()::show);
        return menu;
    }

    private Menu buildWarehouseEmployee(){
        final Menu menu = new Menu("Warehouse Employee >");
        menu.addItem(REGISTER_AGV, "Register AGV", new RegisterAGVUI()::show);
        menu.addItem(CONFIGURE_AVAILABLE_AGVS, "Configure Available AGV", new ConfigureAvailableAGVUI()::show);
        menu.addItem(SET_UP_PLANT, "Set the Warehouse Plant", new SetUpPlantUI()::show);
        menu.addItem(ASSIGN_AGV_TO_ORDER, "Assign AGV to Order ready to be prepared", new AssignOrderToFreeAGVUI()::show);
        //menu.addItem(AUTOMATICALLY_ASSIGN_AGV_TO_ORDER, "Automatically assign AGV to Order ready to be prepared", new AutomaticallyAssignOrderToFreeAGVUI()::show);
        menu.addItem(DISPATCH_ORDER, "Dispatch Order prepared by an AGV", new DispatchOrderUI()::show);
        //menu.addItem(AUTOMATICALLY_ASSIGN_AGV_TO_ORDER, "Automatically assign AGV to Order ready to be prepared", new TestAutomaticallyAssignOrderToFreeAGVUI()::show);
        menu.addItem(DASHBOARD, "Show Dashboard", new DashboardUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildSalesManagerMenu(){
        final Menu menu = new Menu("Sales Manager >");
        menu.addItem(CREATE_QUESTIONNAIRE, "Create Questionnaire", new CreateQuestionnaireUI()::show);
        menu.addItem(SEE_QUESTIONNAIRES_REPORT, "See Questionnaire's Report", new GenerateReportUI()::show);

        return menu;
    }
}
