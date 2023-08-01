package join_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ui.RoomView;

import static org.mockito.Mockito.*;

/**
 * Test class for JoinRoomPresenter.
 */
public class JoinRoomPresenterTest {
    private JoinRoomPresenter presenter;
    @Mock
    private RoomView mockView;

    @BeforeEach
    public void setUP() {
        mockView = mock(RoomView.class);
        presenter = new JoinRoomPresenter();
        presenter.setView(mockView);
    }

    /**
     * Test 'prepareRoomView' with given message history.
     * Verifies that mockView calls 'prepareGUI' exactly once.
     */
    @Test
    public void testPrepareRoomView(){
        presenter.prepareRoomView("[15:38:42] nadine: sup\n");
        verify(mockView, times(1)).prepareGUI();
    }

    // omitted testing the prepareInvalidCredentialsView method because it relies on a static method from Swing
    // omitted testing the setView method because it is just a setter

}