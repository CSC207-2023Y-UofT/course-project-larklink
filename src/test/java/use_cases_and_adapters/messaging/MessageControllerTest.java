package use_cases_and_adapters.messaging;

import org.junit.jupiter.api.*;
import org.mockito.*;

public class MessageControllerTest {

    private MessageController messageController;

    @Mock
    private MessageInputBoundary inputBoundary;

    @Captor
    ArgumentCaptor<String> contentCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        messageController = new MessageController(inputBoundary);
    }

    @Test
    public void testHandleSendMessage() {
        String testContent = "hello";

        // check that we call handleSendMessage with the correct content
        messageController.handleSendMessage(testContent);

        Mockito.verify(inputBoundary).handleSendMessage(contentCaptor.capture());

        String capturedContent = contentCaptor.getValue(); // get the argument that was passed to handleSendMessage
        assert capturedContent.equals(testContent); // check that we passed in the right content
    }

    @Test
    public void testHandleRetrieveMessages() {
        // check that we call handleRetrieveMessages
        messageController.handleRetrieveMessages();

        Mockito.verify(inputBoundary).handleRetrieveMessages();
    }
}