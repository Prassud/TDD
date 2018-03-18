package service;

import model.Category;
import model.Item;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SalesManagementService {

    private List<Category> excludedTaxItems;


    public SalesManagementService(List<Category> excludedTaxItems) {
        this.excludedTaxItems = excludedTaxItems;

    }

    public void process(Item item) {
        categorizeItems(item);
        item.computeSalesTax();
    }

    private void categorizeItems(Item item) {
        Optional<Category> itemCategory = Arrays.stream(Category.values()).filter(
                eacCategory -> eacCategory.isBelongToThisCategory(item.getName()
                )).findFirst();
        if (itemCategory.isPresent()) {
            item.setCategory(itemCategory.get());
        }
        categorizeInToTaxTypes(item);
    }

    private void categorizeInToTaxTypes(Item item) {
        if (this.excludedTaxItems.contains(item.getCategory())) {
            item.addType(Item.Type.excluded);
        } else {
            item.addType(Item.Type.normal);
        }
        if (item.isImported()) {
            item.addType(Item.Type.imported);
        }
    }
}
