package join_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ui.RoomView;

import static org.mockito.Mockito.*;

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
    @Test
    public void testPrepareRoomView(){
        presenter.prepareRoomView("");
        verify(mockView, times(1)).prepareGUI();
    }

    // omitted testing the prepareFailView

}