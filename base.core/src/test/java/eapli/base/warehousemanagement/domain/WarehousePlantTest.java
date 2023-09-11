package eapli.base.warehousemanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WarehousePlantTest {
    @Test
    public void ensurePlantIsCreated(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf("Warehouse Test"))
                .hasLength(Length.valueOf(400L))
                .hasWidth(Width.valueOf(300L))
                .hasSquareSize(SquareSize.valueOf(5L))
                .hasUnit(Unit.valueOf("cm"))
                .build();
        assertNotNull(plant);
    }

    @Test(expected = IllegalStateException.class)
    public void ensurePlantFailsNameNull(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(null)
                .hasLength(Length.valueOf(400L))
                .hasWidth(Width.valueOf(300L))
                .hasSquareSize(SquareSize.valueOf(5L))
                .hasUnit(Unit.valueOf("cm"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensurePlantFailsLengthNull(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf("Warehouse Test"))
                .hasLength(null)
                .hasWidth(Width.valueOf(300L))
                .hasSquareSize(SquareSize.valueOf(5L))
                .hasUnit(Unit.valueOf("cm"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensurePlantFailsWidthNull(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf("Warehouse Test"))
                .hasLength(Length.valueOf(400L))
                .hasWidth(null)
                .hasSquareSize(SquareSize.valueOf(5L))
                .hasUnit(Unit.valueOf("cm"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensurePlantFailsSquareSizeNull(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf("Warehouse Test"))
                .hasLength(Length.valueOf(400L))
                .hasWidth(Width.valueOf(300L))
                .hasSquareSize(null)
                .hasUnit(Unit.valueOf("cm"))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensurePlantFailsUnitNul(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf("Warehouse Test"))
                .hasLength(Length.valueOf(400L))
                .hasWidth(Width.valueOf(300L))
                .hasSquareSize(SquareSize.valueOf(5L))
                .hasUnit(null)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePlantFailsNameEmpty(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf(""))
                .hasLength(Length.valueOf(400L))
                .hasWidth(Width.valueOf(300L))
                .hasSquareSize(SquareSize.valueOf(5L))
                .hasUnit(Unit.valueOf("cm"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePlantFailsLengthNotPositive(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf("Warehouse Test"))
                .hasLength(Length.valueOf(0L))
                .hasWidth(Width.valueOf(300L))
                .hasSquareSize(SquareSize.valueOf(5L))
                .hasUnit(Unit.valueOf("cm"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePlantFailsWidthNotPositive(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf("Warehouse Test"))
                .hasLength(Length.valueOf(400L))
                .hasWidth(Width.valueOf(0L))
                .hasSquareSize(SquareSize.valueOf(5L))
                .hasUnit(Unit.valueOf("cm"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePlantFailsSquareSizeNotPositive(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf("Warehouse Test"))
                .hasLength(Length.valueOf(400L))
                .hasWidth(Width.valueOf(300L))
                .hasSquareSize(SquareSize.valueOf(0L))
                .hasUnit(Unit.valueOf("cm"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePlantFailsUnitEmpty(){
        final WarehousePlant plant = new PlantBuilder()
                .hasName(WarehouseName.valueOf("Warehouse Test"))
                .hasLength(Length.valueOf(400L))
                .hasWidth(Width.valueOf(300L))
                .hasSquareSize(SquareSize.valueOf(5L))
                .hasUnit(Unit.valueOf(""))
                .build();
    }
}
