package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductRepository;

import javax.persistence.TypedQuery;
import java.util.*;

public class JpaProductRepository extends BaseJpaRepositoryBase<Product, Code, Code>
    implements ProductRepository {
    JpaProductRepository() {
        super("uniqueInternalCode");
    }

    @Override
    public Optional<Product> ofIdentity() {
        return Optional.empty();
    }

    @Override
    public Iterable<BrandName> findAllBrandName() {
        final TypedQuery<Product> query = entityManager().createQuery(
                "SELECT p FROM Product p",
                Product.class);

        Iterable<Product> products = query.getResultList();
        List<BrandName> filteredByBrandNameList = new LinkedList<>();

        for (Product p : products){
            if ((p.getBrandName().isPresent()) && (!filteredByBrandNameList.contains(p.getBrandName().get()))){
                filteredByBrandNameList.add(p.getBrandName().get());
            }
        }
        return filteredByBrandNameList;
    }

    @Override
    public Iterable<ExtendedDescription> findAllDescription(){
        final TypedQuery<Product> query = entityManager().createQuery(
                "SELECT p FROM Product p",
                Product.class);

        Iterable<Product> products = query.getResultList();
        List<ExtendedDescription> filteredByDescriptionList = new LinkedList<>();

        for (Product p : products){
            if (!filteredByDescriptionList.contains(p.getExtendedDescription())){
                filteredByDescriptionList.add(p.getExtendedDescription());
            }
        }
        return filteredByDescriptionList;
    }

    @Override
    public Iterable<Product> findByBrandName(BrandName brandName){
        final Map<String, Object> params = new HashMap<>();
        params.put("brandName", brandName);
        return match("e.brandName=:brandName", params);
    }

    @Override
    public Iterable<Product> findByExtendedDescription(ExtendedDescription description){
        final Map<String, Object> params = new HashMap<>();
        params.put("description", description);
        return match("e.extendedDescription=:description", params);
    }

    @Override
    public Iterable<Product> findByCategory(ProductCategory category){
        final Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        return match("e.productCategory=:category", params);
    }

}
