package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.ExtendedDescription;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;

import javax.persistence.TypedQuery;
import java.util.*;

public class JpaProductCategoryRepository extends BaseJpaRepositoryBase<ProductCategory, AlphaNumericCode, AlphaNumericCode>
implements ProductCategoryRepository {
    //comentario
    JpaProductCategoryRepository() {
        super("alphanumericCode");
    }

    @Override
    public Optional<ProductCategory> findByAlphanumericCode(AlphaNumericCode code) {
        final Map<String, Object> params = new HashMap<>();
        params.put("code",code);
        return matchOne("e.productcategory.alphanumericcode=:code", params);
    }

    @Override
    public Iterable<ProductCategory> findAllCategory(){
        final TypedQuery<Product> query = entityManager().createQuery(
                "SELECT p FROM Product p",
                Product.class);

        Iterable<Product> products = query.getResultList();
        List<ProductCategory> filteredByCategoryList = new LinkedList<>();

        for (Product p : products){
            if (!filteredByCategoryList.contains(p.getProductCategory())){
                filteredByCategoryList.add(p.getProductCategory());
            }
        }
        return filteredByCategoryList;
    }
}
