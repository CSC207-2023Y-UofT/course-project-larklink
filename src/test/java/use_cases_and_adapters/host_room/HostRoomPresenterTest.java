package use_cases_and_adapters.host_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import views_and_drivers.views.View;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * Testing the host room presenter (as the name suggests)
 */
public class HostRoomPresenterTest {
    private HostRoomPresenter hostRoomPresenter;
    private View mockView;

    @BeforeEach
    void setUp() {
        mockView = Mockito.mock(View.class);
        hostRoomPresenter = new HostRoomPresenter();
        hostRoomPresenter.setView(mockView);
    }

    /**
     * Ensure that prepareGUI called when room view prepared
     */
    @Test
    void testPrepareJoinOrHostView() {
        hostRoomPresenter.prepareRoomView("");
        // check prepareGUI called
        verify(mockView, times(1)).prepareGUI("");
    }


    /**
     * Ensure that the correct view is displayed when a user attempts
     * to host two rooms simultaneously
     */
    @Test
    void testPrepareMultipleHostingView() {
        hostRoomPresenter.prepareMultipleHostingView("11bestnumber");
        // string format same as message in presenter
        verify(mockView, times(1)).displayPopUpMessage(
                "You're already hosting a room: 11bestnumber");
    }


    /**
     * Ensure that the correct view is displayed when a user attempts
     * to host a room with a duplicate name (i.e. there already exists a room using that name)
     */
    @Test
    void testPrepareDuplicateNameView() {
        hostRoomPresenter.prepareDuplicateNameView();
        verify(mockView, times(1)).displayPopUpMessage(
                "A room with this name already exists.");

    }
}
