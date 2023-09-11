package eapli.base.warehousemanagement.repositories;

import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface AgvDockRepository extends DomainRepository<String, AgvDock> {
    @Override
    Iterable<AgvDock> findAll();


}
