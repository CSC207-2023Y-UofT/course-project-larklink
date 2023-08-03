package use_cases_and_adapters.messaging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testhandleSendMessage() {
        String testContent = "hello";

        // check that we call handleSendMessage with the correct content
        messageController.handleSendMessage(testContent);

        verify(inputBoundary).handleSendMessage(contentCaptor.capture());

        String capturedContent = contentCaptor.getValue(); // get the argument that was passed to handleSendMessage
        assertEquals(testContent, capturedContent); // check that we passed in the right content
    }

    @Test
    public void testHandleRetrieveMessages() {
        // check that we call handleRetrieveMessages
        messageController.handleRetrieveMessages();

        verify(inputBoundary).handleRetrieveMessages();
    }
}