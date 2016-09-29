package com.mygdx.eoh.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.eoh.screens.ScreenManager;

/**
 * Defines default screen
 * Created by v on 2016-09-27.
 */
public class DefaultScreen implements Screen {

    private InputMultiplexer inputMultiplexer;

    private Stage mainStage;

    private Table mainTable;

    public DefaultScreen() {

        Camera camera = new OrthographicCamera();
        Viewport viewport = new FitViewport(ScreenManager.WIDTH, ScreenManager.HEIGHT, camera);

        inputMultiplexer = new InputMultiplexer();

        mainStage = new Stage();
        mainStage.setViewport(viewport);

        mainTable = new Table();
        mainTable.setFillParent(true);

        mainStage.addActor(mainTable);
    }

    @Override
    public void show() {
        inputMultiplexer.addProcessor(mainStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.act();
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
        //mainStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mainStage.dispose();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Table getMainTable() {
        return mainTable;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public void setInputMultiplexer(InputMultiplexer inputMultiplexer) {
        this.inputMultiplexer = inputMultiplexer;
    }
}
