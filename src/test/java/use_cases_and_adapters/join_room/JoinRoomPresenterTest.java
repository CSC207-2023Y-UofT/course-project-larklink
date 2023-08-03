package use_cases_and_adapters.join_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import use_cases_and_adapters.Viewable;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Test class for JoinRoomPresenter.
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
     * Tests prepareRoomView method with given message history.
     */
    @Test
    public void testPrepareRoomView(){
        presenter.prepareRoomView("[15:38:42] nadine: sup\n");

        // checks that mockView calls prepareGUI method exactly once
        verify(mockView, times(1)).prepareGUI();
    }

    // omitted testing the prepareInvalidCredentialsView method because it relies on a static method from Swing
    // omitted testing the setView method because it is just a setter
}