package eapli.base.productmanagement.domain;

import org.junit.Test;

public class CategoryDescriptionTest {
    @Test
    public void ensureDescriptionIsCreated(){
        CategoryDescription description = new CategoryDescription("isto é uma descrição teste");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionStringFailsEmpty(){
        CategoryDescription description = new CategoryDescription("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionLengthFailsWithLess(){
        CategoryDescription description = new CategoryDescription("isto falha");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionLengthFailsWithMore(){
        CategoryDescription description = new CategoryDescription("isto certamente e decerteza vai falhar porque é garantido que tem mais de 50 caracteres");
    }
}
