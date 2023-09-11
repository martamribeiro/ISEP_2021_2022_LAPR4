package eapli.base.productmanagement.domain;

public class CategoryBuilder {
    private ProductCategory category;

    private AlphaNumericCode code;
    private CategoryDescription description;

    public CategoryBuilder hasCode(final AlphaNumericCode code){
        this.code=code;
        return this;
    }

    public CategoryBuilder hasDescription(final CategoryDescription description){
        this.description=description;
        return this;
    }

    private ProductCategory buildOrThrow(){
        if(category!=null) {
            return category;
        }else if(code!=null && description!=null){
            category = new ProductCategory(code, description);
            return category;
        } else {
            throw new IllegalStateException();
        }
    }

    public ProductCategory build() {
        final ProductCategory ret = buildOrThrow();
        category = null;
        return ret;
    }

}
