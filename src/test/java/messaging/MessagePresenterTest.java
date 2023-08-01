package messaging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ui.View;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
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

    @Test
    void testPrepareMessageErrorView() {
        messagePresenter.prepareMessageErrorView();
        verify(mockView, times(1)).prepareGUI();
    }


}
