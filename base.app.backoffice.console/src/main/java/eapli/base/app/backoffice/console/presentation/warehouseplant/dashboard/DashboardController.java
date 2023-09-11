package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class DashboardController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public void showDashboard(String ipAddress){
        HTTPServerAGVS server = new HTTPServerAGVS(ipAddress);
        server.setController(this);
        server.start();
    }

}
