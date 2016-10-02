package com.mygdx.eoh.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * To access assets used in Main Menu.
 * Created by l.wawrzyniak on 2016-10-02.
 */

public class AssetsMainMenu {

    private static AssetsMainMenu instance;
    private AssetManager manager;

    private AssetsMainMenu() {
        createAssets();
    }

    public static AssetsMainMenu getInstance() {
        if (instance == null) {
            instance = new AssetsMainMenu();
        }
        return instance;
    }

    public AssetManager getManager() {
        return manager;
    }

    private void createAssets(){
        manager = new AssetManager();

        TextureLoader.TextureParameter param = new TextureLoader.TextureParameter();
        param.minFilter = Texture.TextureFilter.Linear;
        param.magFilter = Texture.TextureFilter.Nearest;
        //param.magFilter = Texture.TextureFilter.MipMapLinearNearest;


        manager.load("styles/uiskin.json", Skin.class);
        manager.load("mainMenu/interface/buttonSinglePlayerUp.png", Texture.class, param);
        manager.load("mainMenu/interface/buttonSinglePlayerDown.png", Texture.class, param);
        manager.load("mainMenu/interface/buttonHotSeatUp.png", Texture.class, param);
        manager.load("mainMenu/interface/buttonHotSeatDown.png", Texture.class, param);

        manager.finishLoading();
    }

    public void dispose() {
        instance = null;
        manager.dispose();
    }

}
