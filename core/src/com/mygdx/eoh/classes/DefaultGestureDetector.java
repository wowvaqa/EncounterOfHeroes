package com.mygdx.eoh.classes;

import com.badlogic.gdx.input.GestureDetector;

/**
 * Created by v on 2016-09-27.
 */
public class DefaultGestureDetector extends GestureDetector {

    public DefaultGestureDetector(GestureListener listener) {
        super(listener);
    }

    @Override
    public boolean isPanning() {
        return super.isPanning();
    }
}
