package com.mygdx.eoh.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eoh.classes.DefaultScreen;

/**
 * Created by v on 2016-09-19.
 */
public class ScreenMainMenu extends DefaultScreen {

    private Skin skin;

    private TextButton button00;
    private TextButton button01;
    private TextButton button02;
    private TextButton button03;
    private TextButton button04;

    public ScreenMainMenu() {
        skin = new Skin(Gdx.files.local("styles/uiskin.json"));

        button00 = new TextButton("Single", skin);
        button01 = new TextButton("Hot Seat", skin);
        button02 = new TextButton("Map Editor", skin);
        button03 = new TextButton("Options", skin);
        button04 = new TextButton("Exit", skin);

        button00.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(Screens.ScreenSingleGame);
            }
        });

        button02.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(Screens.ScreenMapEditor);
            }
        });

        super.getMainTable().add(button00);
        super.getMainTable().row();
        super.getMainTable().add(button01);
        super.getMainTable().row();
        super.getMainTable().add(button02);
        super.getMainTable().row();
        super.getMainTable().add(button03);
        super.getMainTable().row();
        super.getMainTable().add(button04);
        super.getMainTable().row();
    }

    @Override
    public void dispose() {
        skin.dispose();
        super.dispose();
    }
}
