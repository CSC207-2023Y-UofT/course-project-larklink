package views;

import javax.swing.*;
import java.awt.*;

/**
 * Tests if specific ui element exists within frame
 */

public class ViewTestUtility {
    // adapted from competitive programming template DFS
    protected static boolean hasElement(Component element, Class<?> type) {
        if (type.isInstance(element)) {
            return true;
        }
        if (element instanceof Container) {
            for (Component child : ((Container) element).getComponents()) {
                if (hasElement(child, type)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected static <T extends JComponent> T findElement(Container container, Class<T> type) {
        for (Component component : container.getComponents()) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
            // recurse
            if (component instanceof Container) {
                T result = findElement((Container) component, type);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
