package use_cases_and_adapters.messaging;

import org.junit.jupiter.api.*;
import org.mockito.*;
import views.View;

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
        Mockito.verify(mockView, Mockito.times(1)).prepareGUI(messageHistory);
    }
    // Test method for the prepareMessageErrorView() function of the MessagePresenter
    @Test
    void testPrepareMessageErrorView() {
        messagePresenter.prepareMessageErrorView();
        Mockito.verify(mockView, Mockito.times(1)).displayPopUpMessage("You cannot send an empty message!");
    }
}
