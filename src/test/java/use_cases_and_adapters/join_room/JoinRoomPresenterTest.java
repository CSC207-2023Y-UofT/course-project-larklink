package use_cases_and_adapters.join_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import use_cases_and_adapters.Viewable;

import static org.mockito.Mockito.*;
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

        // checks that mockView calls prepareGUI exactly once with given message history
        verify(mockView, times(1)).prepareGUI("[15:38:42] nadine: sup\n");
    }

    @Test
    void testPrepareFailView() {
        presenter.prepareFailView();

        // checks that mockView calls displayPopUpMessage exactly once with the correct error message
        verify(mockView, times(1)).displayPopUpMessage("No Such Room Found!");
    }
}