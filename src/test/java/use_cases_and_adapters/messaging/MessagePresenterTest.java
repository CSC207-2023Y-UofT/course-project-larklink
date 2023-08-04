package use_cases_and_adapters.messaging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import views.View;
import static org.mockito.Mockito.*;

public class MessagePresenterTest {
    private MessagePresenter messagePresenter;
    private View mockView;

    @BeforeEach
    void setUp() {
        mockView = Mockito.mock(View.class);
        messagePresenter = new MessagePresenter();
        messagePresenter.setView(mockView);
    }
    // Test method for the prepareRoomView() function of the MessagePresenter
    @Test
    void testPrepareRoomView() {
        String messageHistory = "Old message\\nNew message";
        messagePresenter.prepareRoomView(messageHistory);
        // Verify that the prepareGUI() method of the mock view is called once with the expected argument
        verify(mockView, times(1)).prepareGUI(messageHistory);
    }
    // Test method for the prepareMessageErrorView() function of the MessagePresenter
    @Test
    void testPrepareMessageErrorView() {
        messagePresenter.prepareMessageErrorView();
        // Verify that the displayPopUpMessage() method of the mock view is called once with the expected error message
        verify(mockView, times(1)).displayPopUpMessage("You cannot send an empty message!");
    }
}
