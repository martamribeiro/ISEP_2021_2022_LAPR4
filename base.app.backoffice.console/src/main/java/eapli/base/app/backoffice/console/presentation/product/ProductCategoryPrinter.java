package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.visitor.Visitor;

public class ProductCategoryPrinter implements Visitor<ProductCategory> {

    @Override
    public void visit(final ProductCategory visitee){
        System.out.printf("%-10s%-30s%-4s", visitee.identity(), visitee.getAlphanumericCode(),
                String.valueOf(visitee.getDescription()));
    }

}
