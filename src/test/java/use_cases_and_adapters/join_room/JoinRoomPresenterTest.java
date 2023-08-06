package use_cases_and_adapters.join_room;

import use_cases_and_adapters.Viewable;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests JoinRoomPresenter.
 */
public class JoinRoomPresenterTest {
    private JoinRoomPresenter presenter;
    @Mock
    private Viewable mockView;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        presenter = new JoinRoomPresenter();
        presenter.setView(mockView);
    }

    /**
     * Tests prepareRoomView with given message history.
     */
    @Test
    public void testPrepareRoomView(){
        presenter.prepareRoomView("[15:38:42] nadine: sup\n");
        Mockito.verify(mockView, Mockito.times(1)).prepareGUI("[15:38:42] nadine: sup\n");
    }

    @Test
    void testPrepareFailView() {
        presenter.prepareFailView();
        Mockito.verify(mockView, Mockito.times(1)).displayPopUpMessage("No Such Room Found!");
    }
}