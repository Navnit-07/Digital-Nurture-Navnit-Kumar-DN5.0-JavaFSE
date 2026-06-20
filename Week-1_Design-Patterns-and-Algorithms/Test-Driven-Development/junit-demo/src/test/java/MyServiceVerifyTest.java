import org.junit.Test;
import org.mockito.Mockito;
import service.ExternalApi;
import service.MyService;

import static org.mockito.Mockito.verify;

// Exercise 2: Verifying interactions with Mockito
public class MyServiceVerifyTest {

    @Test
    public void testVerifyInteraction() {
        // Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        MyService service = new MyService(mockApi);

        // Call the method
        service.fetchData();

        // Verify that getData() was called on the mock
        verify(mockApi).getData();
    }
}
