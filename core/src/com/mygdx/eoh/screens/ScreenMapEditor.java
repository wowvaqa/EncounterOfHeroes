package com.mygdx.eoh.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.eoh.assets.AssetsMapEditor;
import com.mygdx.eoh.classes.DefaultGestureDetector;
import com.mygdx.eoh.classes.DefaultGestureListener;
import com.mygdx.eoh.classes.DefaultScreen;
import com.mygdx.eoh.mapEditor.MapEditor;

/**
 * Represents visualisation (screen)
 * Created by v on 2016-09-28.
 */
public class ScreenMapEditor extends DefaultScreen {

    private Interface interfaceManager;

    private FitViewport viewport;
    private FitViewport mapStageViewport;

    private Camera mapStageCamera;

    private MapEditor mapEditor;

    private Stage mapStage;
    private Stage mainStage;

    private boolean interfaceStageFlag = false;
    private boolean mapStageFlag = false;

    public ScreenMapEditor() {

        mapStageCamera = new OrthographicCamera();
        mapStageViewport = new FitViewport(ScreenManager.WIDTH, ScreenManager.HEIGHT, mapStageCamera);

        viewport = (FitViewport) super.getMainStage().getViewport();

        mainStage = super.getMainStage();

        mapEditor = new MapEditor();

        interfaceManager = new Interface();

        super.getMainTable().add(interfaceManager.imageButtonNew).align(Align.topLeft).size(50, 50);
        super.getMainTable().add(interfaceManager.imageButtonLoad).align(Align.topLeft).size(50, 50);
        super.getMainTable().add(new Table()).expandX();
        super.getMainTable().row();
        super.getMainTable().add(interfaceManager.imageButtonSave).align(Align.topLeft).size(50, 50);
        super.getMainTable().add(interfaceManager.imageButtonExit).align(Align.topLeft).size(50, 50);
        super.getMainTable().add(new Table()).expandX();
        super.getMainTable().row();
        super.getMainTable().add(new Table()).expandY();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (mapStage != null) {
            mapStage.act();
            mapStage.draw();
        }

        super.getMainStage().act();
        super.getMainStage().draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (mapStage != null){
            mapStage.dispose();
        }
    }

    @Override
    public void show() {
        super.show();
        if (mapStage != null) {
            super.getInputMultiplexer().addProcessor(mapStage);
        }
    }

    private class Interface {

        public ImageButton imageButtonNew;
        public ImageButton imageButtonLoad;
        public ImageButton imageButtonSave;
        public ImageButton imageButtonExit;

        public Interface() {
            createButtons();
        }

        private void createButtons() {
            ImageButton.ImageButtonStyle imageButtonStyleNew = new ImageButton.ImageButtonStyle();
            ImageButton.ImageButtonStyle imageButtonStyleLoad = new ImageButton.ImageButtonStyle();
            ImageButton.ImageButtonStyle imageButtonStyleSave = new ImageButton.ImageButtonStyle();
            ImageButton.ImageButtonStyle imageButtonStyleExit = new ImageButton.ImageButtonStyle();

            imageButtonStyleNew.imageUp = new TextureRegionDrawable(new TextureRegion(
                    AssetsMapEditor.getInstance().getManager().get("mapEditor/interface/newButtonUp.png", Texture.class)
            ));
            imageButtonStyleNew.imageDown = new TextureRegionDrawable(new TextureRegion(
                    AssetsMapEditor.getInstance().getManager().get("mapEditor/interface/newButtonDown.png", Texture.class)
            ));
            imageButtonStyleLoad.imageUp = new TextureRegionDrawable(new TextureRegion(
                    AssetsMapEditor.getInstance().getManager().get("mapEditor/interface/loadButtonUp.png", Texture.class)
            ));
            imageButtonStyleLoad.imageDown = new TextureRegionDrawable(new TextureRegion(
                    AssetsMapEditor.getInstance().getManager().get("mapEditor/interface/loadButtonDown.png", Texture.class)
            ));
            imageButtonStyleSave.imageUp = new TextureRegionDrawable(new TextureRegion(
                    AssetsMapEditor.getInstance().getManager().get("mapEditor/interface/saveButtonUp.png", Texture.class)
            ));
            imageButtonStyleSave.imageDown = new TextureRegionDrawable(new TextureRegion(
                    AssetsMapEditor.getInstance().getManager().get("mapEditor/interface/saveButtonDown.png", Texture.class)
            ));
            imageButtonStyleExit.imageUp = new TextureRegionDrawable(new TextureRegion(
                    AssetsMapEditor.getInstance().getManager().get("mapEditor/interface/exitButtonUp.png", Texture.class)
            ));
            imageButtonStyleExit.imageDown = new TextureRegionDrawable(new TextureRegion(
                    AssetsMapEditor.getInstance().getManager().get("mapEditor/interface/exitButtonDown.png", Texture.class)
            ));

            imageButtonNew = new ImageButton(imageButtonStyleNew);
            imageButtonLoad = new ImageButton(imageButtonStyleLoad);
            imageButtonSave = new ImageButton(imageButtonStyleSave);
            imageButtonExit = new ImageButton(imageButtonStyleExit);

            addListeners();
        }

        private void addListeners() {
            imageButtonExit.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    AssetsMapEditor.getInstance().dispose();
                    ScreenManager.getInstance().setScreen(Screens.ScreenMainMenu);
                }
            });

            imageButtonNew.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    Window window = getNewMapWindow(viewport);
                    window.setMovable(false);
                    window.setModal(true);

                    window.setPosition(
                            ScreenManager.WIDTH - ScreenManager.WIDTH / 2 - window.getWidth() / 2,
                            ScreenManager.HEIGHT - ScreenManager.HEIGHT / 2 - window.getHeight() / 2
                    );

                    mainStage.addActor(window);
                }
            });
        }

        public Window getNewMapWindow(final FitViewport viewPort) {
            final Skin skin = AssetsMapEditor.getInstance().getManager().get("styles/uiskin.json");
            final Window window = new Window("Nowa mapa", skin);
            window.setSize(600, 400);
            Label lblAmountOfXfields = new Label("ilosc pol X: ", skin);
            Label lblAmountOfYfields = new Label("ilosc pol Y: ", skin);
            TextField tfNameofMap = new TextField("", skin);
            final TextField tfAmountOfXflilds = new TextField("10", skin);
            final TextField tfAmountOfYflilds = new TextField("10", skin);
            TextButton tBmakeMap = new TextButton("OK", skin);
            tBmakeMap.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    boolean isMapCreated = true;

                    if (mapStage != null) {
                        mapStage.clear();
                        isMapCreated = false;
                    }

                    mapStage = mapEditor.createStage(
                            Integer.parseInt(tfAmountOfXflilds.getText()),
                            Integer.parseInt(tfAmountOfYflilds.getText()),
                            mapStageViewport
                    );
                    //createFreameAroudMap();

                    if (isMapCreated) {
                        DefaultGestureListener myGL = new DefaultGestureListener(mapStage, mapEditor);
                        DefaultGestureDetector myGD = new DefaultGestureDetector(myGL);

                        getInputMultiplexer().addProcessor(myGD);
                    }

                    window.remove();
                }
            });

            window.add(new Label("Nazwa mapy:", skin));
            window.add(tfNameofMap).pad(5);
            window.row();
            window.add(lblAmountOfXfields).pad(5);
            window.add(tfAmountOfXflilds).pad(5);
            window.row();
            window.add(lblAmountOfYfields).pad(5);
            window.add(tfAmountOfYflilds).pad(5);
            window.row();
            window.add(tBmakeMap).pad(5).size(100, 50).colspan(2);
            window.row();

            return window;
        }
    }
}
