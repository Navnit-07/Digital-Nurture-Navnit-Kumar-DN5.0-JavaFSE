import org.junit.Test;
import org.mockito.Mockito;
import service.ExternalApi;
import service.MyService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

// Exercise 1: Mocking and Stubbing with Mockito
public class MyServiceMockTest {

    @Test
    public void testExternalApi() {
        // Create mock object for external API
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub the method to return predefined value
        when(mockApi.getData()).thenReturn("Mock Data");

        // Use mock in service
        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }
}
