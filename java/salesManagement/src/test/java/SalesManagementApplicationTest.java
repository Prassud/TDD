import SalesManagementApplication.SalesManagementApplication;
import common.TestUtils;
import model.Item;
import model.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import service.SalesManagementService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class SalesManagementApplicationTest {

    @Mock
    private SalesManagementService salesService;


    @Test
    public void shouldVerifyIfApplicationIsStartedSuccessFully() {
        SalesManagementApplication salesManagementApplication = new SalesManagementApplication(salesService);
        List<Stream> output = salesManagementApplication.run(getStreamOfInputLines());
        assertNotNull(output);
    }

    @Test
    public void shouldVerifyIfTheGivenInputIsProcessedToGetItem() {
        List<Item> sampleItems = TestUtils.createNonImportSampleItems();
        ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);
        SalesManagementApplication salesManagementApplication = spy(new SalesManagementApplication(salesService));
        salesManagementApplication.run(getStreamOfInputLines());
        Mockito.verify(salesService, times(3)).process(argument.capture());
        List<String> returnList = argument.getAllValues().stream().map(eachitem -> eachitem.getName()).collect(Collectors.toList());
        assertEquals(sampleItems.get(0).getName(), returnList.get(0));
        assertEquals(sampleItems.get(1).getName(), returnList.get(1));
        assertEquals(sampleItems.get(2).getName(), returnList.get(2));
    }


    @Test
    public void shouldVerifyIfTheGivenInputIsProcessedToGetImportedItem() {
       List<Item> sampleItems = TestUtils.createImportSampleItems();
        ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);
        SalesManagementApplication salesManagementApplication = spy(new SalesManagementApplication(salesService));
        salesManagementApplication.run(getStreamOfInputLinesForImportedItems());
        Mockito.verify(salesService, times(2)).process(argument.capture());
        List<String> returnList = argument.getAllValues().stream().map(eachitem -> eachitem.getName()).collect(Collectors.toList());
        assertEquals(sampleItems.get(0).getName(), returnList.get(0));
        assertEquals(sampleItems.get(1).getName(), returnList.get(1));
    }


    private List<Stream> getStreamOfInputLines() {
        List<Stream> inputList = new ArrayList<Stream>(10);
        inputList.add(new Stream("1 book at 12.49"));
        inputList.add(new Stream("1 music CD at 14.99"));
        inputList.add(new Stream("1 chocolate bar at 0.85"));
        return inputList;
    }


    private List<Stream> getStreamOfInputLinesForImportedItems() {
        List<Stream> inputList = new ArrayList<Stream>(10);
        inputList.add(new Stream("1 imported box of chocolates at 10.00"));
        inputList.add(new Stream("1 imported bottle of perfume at 47.50"));
        return inputList;
    }

}