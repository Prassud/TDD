package SalesManagementApplication;

import model.Item;
import model.Stream;
import service.SalesManagementService;

import java.util.ArrayList;
import java.util.List;

public class SalesManagementApplication {

    private SalesManagementService salesManagementService;

    public SalesManagementApplication(SalesManagementService salsesService) {
        this.salesManagementService = salsesService;
    }

    public List<Stream> run(List<Stream> streamOfinputLines) {
        streamOfinputLines.forEach(eachInputLine -> {
            this.salesManagementService.process(createItemFromInputString(eachInputLine.getContent()));
        });
        return new ArrayList<Stream>();
    }


    private Item createItemFromInputString(String streamContent) {
        String[] contents = streamContent.split(" ");
        int index = 1;
        StringBuffer buffer = new StringBuffer(10);
        boolean isImported = false;
        while (index < contents.length && !contents[index].equals("at")) {
            if ("imported".equals(contents[index])) {
                isImported = true;
            } else {
                buffer.append(contents[index]);
                buffer.append(" ");
            }
            index++;
        }
        buffer.deleteCharAt(buffer.length() - 1);
        String name = buffer.toString();
        double price = Double.valueOf(contents[++index]);
        int qty = Integer.valueOf(contents[0]);
        return new Item(price, name, qty, isImported);

    }
}
