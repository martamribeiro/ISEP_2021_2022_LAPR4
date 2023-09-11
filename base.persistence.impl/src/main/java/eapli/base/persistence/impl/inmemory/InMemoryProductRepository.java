package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository extends InMemoryDomainRepository<Product, Code>
implements ProductRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Product> findAll(){
        ProductRepository repository = PersistenceContext.repositories().products();

        List<Product> list = new LinkedList<>();

        for(Product product : repository.findAll()){
            list.add(product);
        }

        return list;
    }

    @Override
    public Optional<Product> ofIdentity() {

        return Optional.empty();
    }

    @Override
    public Iterable<BrandName> findAllBrandName() {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
    }

    @Override
    public Iterable<ExtendedDescription> findAllDescription() {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
    }

    @Override
    public boolean containsOfIdentity(Code id) {
        return super.containsOfIdentity(id);
    }

    @Override
    public boolean contains(Product entity) {
        return super.contains(entity);
    }

    @Override
    public Iterable<Product> findByBrandName(BrandName brandName){
        return match(e -> e.brandName().equals(brandName));
    }

    @Override
    public Iterable<Product> findByExtendedDescription(ExtendedDescription description){
        return match(e -> e.getExtendedDescription().equals(description));
    }

    @Override
    public Iterable<Product> findByCategory(ProductCategory category){
        return match(e -> e.getProductCategory().equals(category));
    }
}
