package com.mygdx.eoh.classes;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.eoh.mapEditor.MapEditor;

/**
 * Created by v on 2016-09-27.
 */
public class DefaultGestureListener implements GestureDetector.GestureListener {

    private Stage stage;
    private MapEditor mapEditor;


    public DefaultGestureListener(Stage stage, MapEditor mapEditor) {
        this.stage = stage;
        this.mapEditor = mapEditor;
    }

    public DefaultGestureListener() {

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        if (stage.getCamera().position.x < 350) {
            stage.getCamera().position.x = 350;
        }

        if (stage.getCamera().position.x > mapEditor.mapColumns * 100) {
            stage.getCamera().position.x = mapEditor.mapColumns * 100;
        }

        if (stage.getCamera().position.y > mapEditor.mapRows * 100) {
            stage.getCamera().position.y = mapEditor.mapRows * 100;
        }

        if (stage.getCamera().position.y < 50) {
            stage.getCamera().position.y = 50;
        }

        stage.getCamera().translate(-deltaX, deltaY, 0);
        stage.getCamera().update();

        if (stage.getCamera().position.x > 350
                && stage.getCamera().position.x < mapEditor.mapColumns * 100
                && stage.getCamera().position.y > 50
                && stage.getCamera().position.y < mapEditor.mapRows * 100) {

            //backgroundStage.getCamera().translate(-deltaX / 10, deltaY / 10, 0);
            //backgroundStage.getCamera().update();
        }

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
