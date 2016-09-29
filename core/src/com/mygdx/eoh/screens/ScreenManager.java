package com.mygdx.eoh.screens;

import com.badlogic.gdx.Game;

/**
 * Managing screens
 * Created by v on 2016-09-19.
 */
public final class ScreenManager {

    public static float WIDTH = 800;
    public static float HEIGHT = 600;
    private static ScreenManager instance;
    private Game game;
    private Object currentScreen = null;

    private ScreenManager() {
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initalize(Game game) {
        this.game = game;
    }

    public void setScreen(Screens screen) {
        switch (screen) {
            case ScreenMainMenu:
                disposeCurrentScreen(currentScreen);
                currentScreen = new ScreenMainMenu();
                game.setScreen((ScreenMainMenu) currentScreen);
                break;
            case ScreenSingleGame:
                disposeCurrentScreen(currentScreen);
                currentScreen = new ScreenSingleGame();
                game.setScreen((ScreenSingleGame) currentScreen);
                break;
            case ScreenMapEditor:
                disposeCurrentScreen(currentScreen);
                currentScreen = new ScreenMapEditor();
                game.setScreen((ScreenMapEditor) currentScreen);
                break;
        }
    }

    private void disposeCurrentScreen(Object object) {
        if (object != null) {
            if (object.getClass().equals(ScreenMainMenu.class)) {
                ScreenMainMenu screen = (ScreenMainMenu) object;
                screen.dispose();
            } else if (object.getClass().equals(ScreenMapEditor.class)) {
                ScreenMapEditor screen = (ScreenMapEditor) object;
                screen.dispose();
            }
        }
    }
}
