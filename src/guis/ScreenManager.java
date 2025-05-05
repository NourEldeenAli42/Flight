package guis;

import java.util.Stack;

public class ScreenManager {
    private static ScreenManager instance;
    private final Stack<Class<? extends Form>> screenHistory;

    private ScreenManager() {
        screenHistory = new Stack<>();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void pushScreen(Class<? extends Form> screenClass) {
        screenHistory.push(screenClass);
    }

    public void goBack() {
        if (!screenHistory.isEmpty()) {
            screenHistory.pop(); // Remove the current screen
            if (!screenHistory.isEmpty()) {
                Class<? extends Form> previousScreen = screenHistory.pop();
                try {
                    Form screen = previousScreen.getDeclaredConstructor().newInstance();
                    screen.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
