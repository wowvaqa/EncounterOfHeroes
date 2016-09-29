package com.mygdx.eoh.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Class is used for loading and managing resoursces.
 * Created by v on 2016-09-28.
 */
public class AssetsMapEditor {

    private static AssetsMapEditor instance;
    private AssetManager manager;

    private AssetsMapEditor() {
        createAssets();
    }

    public static AssetsMapEditor getInstance() {
        if (instance == null) {
            instance = new AssetsMapEditor();
        }
        return instance;
    }

    private void createAssets() {
        manager = new AssetManager();

        TextureLoader.TextureParameter param = new TextureLoader.TextureParameter();
        param.minFilter = Texture.TextureFilter.Linear;

        manager.load("styles/uiskin.json", Skin.class);

        manager.load("mapEditor/interface/newButtonUp.png", Texture.class, param);
        manager.load("mapEditor/interface/newButtonDown.png", Texture.class, param);
        manager.load("mapEditor/interface/loadButtonUp.png", Texture.class, param);
        manager.load("mapEditor/interface/loadButtonDown.png", Texture.class, param);
        manager.load("mapEditor/interface/saveButtonUp.png", Texture.class, param);
        manager.load("mapEditor/interface/saveButtonDown.png", Texture.class, param);
        manager.load("mapEditor/interface/exitButtonUp.png", Texture.class, param);
        manager.load("mapEditor/interface/exitButtonDown.png", Texture.class, param);

        manager.load("mapEditor/terrains/grass.png", Texture.class, param);
        manager.load("mapEditor/terrains/forestC.png", Texture.class, param);
        manager.load("mapEditor/terrains/mountainC.png", Texture.class, param);
        manager.load("mapEditor/terrains/river.png", Texture.class, param);

        manager.load("mapEditor/players/player1.png", Texture.class, param);
        manager.load("mapEditor/players/player2.png", Texture.class, param);

        manager.load("mapEditor/boxes/texTresureBoxLvl1.png", Texture.class, param);
        manager.load("mapEditor/boxes/texTresureBoxLvl2.png", Texture.class, param);

        manager.load("mapEditor/buldings/towerOfMagic.png", Texture.class, param);
        manager.load("mapEditor/buldings/towerOfWisdom.png", Texture.class, param);
        manager.load("mapEditor/buldings/towerOfDefence.png", Texture.class, param);
        manager.load("mapEditor/buldings/towerOfSpeed.png", Texture.class, param);
        manager.load("mapEditor/buldings/towerOfAttack.png", Texture.class, param);
        manager.load("mapEditor/buldings/towerOfHp.png", Texture.class, param);
        manager.load("mapEditor/buldings/well.png", Texture.class, param);
        manager.load("mapEditor/buldings/hospital.png", Texture.class, param);

        manager.load("mapEditor/mobs/mobSpider.png", Texture.class, param);
        manager.load("mapEditor/mobs/mobSkeleton.png", Texture.class, param);
        manager.load("mapEditor/mobs/mobZombie.png", Texture.class, param);
        manager.load("mapEditor/mobs/mobRandomLevel1.png", Texture.class, param);
        manager.load("mapEditor/mobs/mobRandomLevel2.png", Texture.class, param);

        manager.finishLoading();
    }

    public AssetManager getManager() {
        return manager;
    }

    public void dispose() {
        instance = null;
        manager.dispose();
    }
}
