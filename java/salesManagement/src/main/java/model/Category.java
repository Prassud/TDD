package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Category {
    Books("BOOK"), Foods("CHOCOLATE", "BAR", "PIE"), Medicines("PILL"), Others;
    private List<String> namesThatBelongToCategory;

    Category(String... names) {
        namesThatBelongToCategory = new ArrayList<>(10);
        namesThatBelongToCategory.addAll(Arrays.asList(names));
    }

    public boolean isBelongToThisCategory(String itemName) {
        return namesThatBelongToCategory.stream().filter(eachName -> itemName.toUpperCase().contains(eachName)).findFirst().isPresent();
    }
}
