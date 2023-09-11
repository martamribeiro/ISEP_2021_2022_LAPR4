package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.BrandName;
import eapli.base.productmanagement.domain.ExtendedDescription;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ViewProductCatalogController {
    //private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    private final ProductCategoryRepository productCategoryRepository = PersistenceContext.repositories().productCategories();

    public List<String> showProductCatalogFilteredByAll(){
        Iterable<String> filteredByBrand = new ArrayList<>();
        Iterable<String> filteredByDescription = new ArrayList<>();
        Iterable<String> filteredByCategory = new ArrayList<>();
        List<String> catalog = new LinkedList<>();

        filteredByBrand = showProductCatalogFilteredByBrand();

        for(String brand : filteredByBrand){
            catalog.add(brand);
        }

        filteredByDescription = showProductCatalogFilteredByDescription();

        for(String description : filteredByDescription){
            catalog.add(description);
        }

        filteredByCategory = showProductCatalogFilteredByCategory();

        for(String category : filteredByCategory){
            catalog.add(category);
        }

        return catalog;
    }

    public List<String> showProductCatalogFilteredByBrand(){
        Iterable<BrandName> filteredByBrand = new ArrayList<>();
        List<String> catalogByBrand = new LinkedList<>();
        Iterable<Product> productFilteredByBrand = new ArrayList<>();

        filteredByBrand = productRepository.findAllBrandName();

        for(BrandName brand : filteredByBrand){
            catalogByBrand.add(String.format("Products with the brand - '%s': \n", brand.brandName()));

            productFilteredByBrand = productRepository.findByBrandName(brand);

            for(Product product : productFilteredByBrand){
                catalogByBrand.add(String.format("> %s", product.toString()));
            }
        }

        return catalogByBrand;
    }

    public List<String> showProductCatalogFilteredByDescription(){
        Iterable<ExtendedDescription> filteredByDescription = new ArrayList<>();
        List<String> catalogByDescription = new LinkedList<>();
        Iterable<Product> productFilteredByDescription = new ArrayList<>();

        filteredByDescription = productRepository.findAllDescription();

        for(ExtendedDescription description : filteredByDescription){
            catalogByDescription.add(String.format("Products with the description - '%s': \n", description.extendedDescription()));

            productFilteredByDescription = productRepository.findByExtendedDescription(description);

            for(Product product : productFilteredByDescription){
                catalogByDescription.add(String.format("> %s", product.toString()));
            }
        }

        return catalogByDescription;
    }

    public List<String> showProductCatalogFilteredByCategory(){
        Iterable<ProductCategory> filteredByCategory = new ArrayList<>();
        List<String> catalogByCategory = new LinkedList<>();
        Iterable<Product> productFilteredByCategory = new ArrayList<>();

        filteredByCategory = productCategoryRepository.findAllCategory();

        for(ProductCategory category : filteredByCategory){
            catalogByCategory.add(String.format("Products with '%s': \n", category.getDescription()));

            productFilteredByCategory = productRepository.findByCategory(category);

            for(Product product : productFilteredByCategory){
                catalogByCategory.add(String.format("> %s", product.toString()));
            }
        }

        return catalogByCategory;
    }

    public List<String> showProductCatalog(){
        List<String> catalog = new LinkedList<>();

        for(Product product : productRepository.findAll()){
            catalog.add(product.toString());
        }

        return catalog;
    }
}
