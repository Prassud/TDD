package service;

import common.TestUtils;
import model.Category;
import model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.testng.AssertJUnit.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SalesManagementServiceTest {

    private SalesManagementService service;

    @Before
    public void setUp() {
        List<Category> excludedTaxItems = new ArrayList<>(10);
        excludedTaxItems.add(Category.Books);
        excludedTaxItems.add(Category.Foods);
        excludedTaxItems.add(Category.Medicines);
        service = new SalesManagementService(excludedTaxItems);
    }


    @Test
    public void shouldVerifyServiceProcessAndReturnTheExpectedItemType() {
        List<Item> items = TestUtils.createNonImportSampleItems();
        Item processedItem = items.get(0);
        service.process(processedItem);
        assertEquals(processedItem.getTypes().get(0), Item.Type.excluded);
        assertEquals(processedItem.getTypes().get(1), Item.Type.imported);

        processedItem = items.get(1);
        service.process(processedItem);
        assertEquals(processedItem.getTypes().get(0), Item.Type.excluded);

        processedItem = items.get(2);
        service.process(processedItem);
        assertEquals(processedItem.getTypes().get(0), Item.Type.normal);

    }


    @Test
    public void shouldVerifyServiceProcessAndReturnTheExpectedCost() {
        List<Item> items = TestUtils.createNonImportSampleItems();
        Item processedItem = items.get(0);
        service.process(processedItem);
        assertEquals(processedItem.getTypes().get(0), Item.Type.excluded);
        processedItem.computeSalesTax();
        assertEquals(12.49, processedItem.getTotalCost());

        processedItem = items.get(1);
        service.process(processedItem);
        assertEquals(processedItem.getTypes().get(0), Item.Type.normal);
        assertEquals(16.49, processedItem.getTotalCost());

        processedItem = items.get(2);
        service.process(processedItem);
        assertEquals(processedItem.getTypes().get(0), Item.Type.excluded);
        assertEquals(0.85, processedItem.getTotalCost());

    }


    @Test
    public void shouldVerifyServiceProcessAndReturnTheExpectedCostForImportedProducts() {
        List<Item> items = TestUtils.createImportSampleItems();
        Item processedItem = items.get(0);
        service.process(processedItem);
        assertEquals(processedItem.getTypes().get(0), Item.Type.excluded);
        assertEquals(processedItem.getTypes().get(1), Item.Type.imported);
        assertEquals(10.50, processedItem.getTotalCost());

        processedItem = items.get(1);
        service.process(processedItem);
        assertEquals(processedItem.getTypes().get(0), Item.Type.normal);
        assertEquals(processedItem.getTypes().get(1), Item.Type.imported);
        assertEquals(32.19, processedItem.getTotalCost());

    }


    @Test
    public void shouldVerifyServiceProcessAndReturnItemCategory() {
        List<Item> items = TestUtils.createNonImportSampleItems();
        Item processedItem = items.get(0);
        service.process(processedItem);
        assertEquals(processedItem.getCategory(), Category.Books);
    }
}
