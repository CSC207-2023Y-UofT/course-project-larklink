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

    @Test
    void testPrepareRoomView() {
        String messageHistory = "Old message\\nNew message";

        messagePresenter.prepareRoomView(messageHistory);
        verify(mockView, times(1)).prepareGUI();
    }
    // omitted testing the prepareMessageErrorView method because it relies on a static method from Swing
    // omitted testing the setView method because it is just a setter

}
