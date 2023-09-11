package eapli.base.productmanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductCategoryTest {

    @Test
    public void ensureCategoryIsCreated(){
        final ProductCategory test = new CategoryBuilder()
                .hasCode(AlphaNumericCode.valueOf("12346578"))
                .hasDescription(CategoryDescription.valueOf("isto é uma descrição teste"))
                .build();
        assertNotNull(test);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCategoryIsntCreatedWithCodeNull(){
        final ProductCategory test = new CategoryBuilder()
                .hasCode(null)
                .hasDescription(CategoryDescription.valueOf("isto é uma descrição teste"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCategoryIsntCreatedWithDescriptionNull(){
        final ProductCategory test = new CategoryBuilder()
                .hasCode(AlphaNumericCode.valueOf("12346578"))
                .hasDescription(null)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCategoryCodeLengthFails(){
        final ProductCategory test = new CategoryBuilder()
                .hasCode(AlphaNumericCode.valueOf("1234567891011"))
                .hasDescription( CategoryDescription.valueOf("isto é uma descrição teste"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCategoryDescriptionLengthFailsWithLess(){
        final ProductCategory test = new CategoryBuilder()
                .hasCode(AlphaNumericCode.valueOf("12346578"))
                .hasDescription( CategoryDescription.valueOf("isto é um fail"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCategoryDescriptionLengthFailsWithMore(){
        final ProductCategory test = new CategoryBuilder()
                .hasCode(AlphaNumericCode.valueOf("12346578"))
                .hasDescription( CategoryDescription.valueOf("isto é um fail que vai falhar certamente porque é demasiado extremamente muito mais grande"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCategoryCodeFailsEmptyString(){
        final ProductCategory test = new CategoryBuilder()
                .hasCode(AlphaNumericCode.valueOf(""))
                .hasDescription( CategoryDescription.valueOf("isto é uma descrição teste"))
                .build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCategoryDescriptionFailsEmptyString(){
        final ProductCategory test = new CategoryBuilder()
                .hasCode(AlphaNumericCode.valueOf("12346578"))
                .hasDescription( CategoryDescription.valueOf(""))
                .build();

    }
}
