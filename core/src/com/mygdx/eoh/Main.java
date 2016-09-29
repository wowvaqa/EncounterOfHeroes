package com.mygdx.eoh;

import com.badlogic.gdx.Game;
import com.mygdx.eoh.screens.ScreenManager;
import com.mygdx.eoh.screens.Screens;

public class Main extends Game {

    @Override
    public void create() {
        ScreenManager.getInstance().initalize(this);
        ScreenManager.getInstance().setScreen(Screens.ScreenMainMenu);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
