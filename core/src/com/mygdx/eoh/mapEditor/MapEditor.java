package com.mygdx.eoh.mapEditor;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.eoh.assets.AssetsMapEditor;
import com.mygdx.eoh.enums.Terrains;

import java.io.Serializable;

/**
 * Engine of Map Editor
 * Created by v on 2016-09-28.
 */
public class MapEditor {

    public FieldOfEditor[][] fields;

    public int mapColumns = 0;
    public int mapRows = 0;

    public DrawingType drawingType = DrawingType.none;

    public void fillField(FieldOfEditor field) {
        if (field.terrains.equals(Terrains.Grass)) {
            field.setDrawable(new TextureRegionDrawable(new TextureRegion(AssetsMapEditor.getInstance().getManager().get(
                    "mapEditor/terrains/grass.png", Texture.class))));
            field.setPosition(field.posX * 100, field.posY * 100);
        } else if (field.terrains.equals(Terrains.Forest)) {
            field.setDrawable(new TextureRegionDrawable(new TextureRegion(AssetsMapEditor.getInstance().getManager().get(
                    "mapEditor/terrains/forestC.png", Texture.class))));
            field.setPosition(field.posX * 100, field.posY * 100);
        } else if (field.terrains.equals(Terrains.Mountain)) {
            field.setDrawable(new TextureRegionDrawable(new TextureRegion(AssetsMapEditor.getInstance().getManager().get(
                    "mapEditor/terrains/mountainC.png", Texture.class))));
            field.setPosition(field.posX * 100, field.posY * 100);
        } else if (field.terrains.equals(Terrains.River)) {
            field.setDrawable(new TextureRegionDrawable(new TextureRegion(AssetsMapEditor.getInstance().getManager().get(
                    "mapEditor/terrains/river.png", Texture.class))));
            field.setPosition(field.posX * 100, field.posY * 100);
        }

        if (field.player1StartLocation) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/players/player1.png"));
        } else if (field.player2StartLocation) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/players/player2.png"));
        }

        if (field.tresureBoxLvl1) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/boxes/texTresureBoxLvl1.png"));
        } else if (field.tresureBoxLvl2) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/boxes/texTresureBoxLvl2.png"));
        }

        if (field.towerMagic) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/buldings/towerOfMagic.png"));
        } else if (field.towerWisdom) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/buldings/towerOfWisdom.png"));
        } else if (field.towerDefence) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/buldings/towerOfDefence.png"));
        } else if (field.towerSpeed) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/buldings/towerOfSpeed.png"));
        } else if (field.towerAttack) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/buldings/towerOfAttack.png"));
        } else if (field.towerHp) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/buldings/towerOfHp.png"));
        } else if (field.towerWell) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/buldings/well.png"));
        } else if (field.towerHospital) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/buldings/hospital.png"));
        }

        if (field.mobSkeletonLocation) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/mobs/mobSzkieletfTex.png"));
        } else if (field.mobWolfLocation) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/mobs/mobWolfTex.png"));
        } else if (field.mobRandomLevel1) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/mobs/mobRandomLevel1.png"));
        } else if (field.mobSpiderLocation) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/mobs/mobSpiderTex.png"));
        } else if (field.mobZombieLocation) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/mobs/mobZombieTex.png"));
        } else if (field.mobRandomLevel2) {
            field.setDrawable(mergeDrawable("mapEditor/terrains/grass.png", "mapEditor/mobs/mobRandomLevel2.png"));
        }

        field.setWidth(100);
        field.setHeight(100);
    }

    public Stage createStage(int sizeColumns, int sizeRow, Viewport viewport) {
        Stage stage = new Stage(viewport);

        mapColumns = sizeColumns;
        mapRows = sizeRow;

        fields = new FieldOfEditor[sizeColumns][sizeRow];

        for (int i = 0; i < sizeColumns; i++) {
            for (int j = 0; j < sizeRow; j++) {
                fields[i][j] = new FieldOfEditor();
                fields[i][j].terrains = Terrains.Grass;
                fields[i][j].terrainGrass = true;
                fields[i][j].posX = i;
                fields[i][j].posY = j;
                fillField(fields[i][j]);
            }
        }

        for (int i = 0; i < sizeColumns; i++) {
            for (int j = 0; j < sizeRow; j++) {
                stage.addActor(fields[i][j]);
            }
        }
        return stage;
    }

    private Drawable mergeDrawable(String pathOrginal, String pathToMerge) {
        Texture texture = AssetsMapEditor.getInstance().getManager().get(pathOrginal, Texture.class);
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
        Pixmap pixmap = texture.getTextureData().consumePixmap();

        Texture texture2 = AssetsMapEditor.getInstance().getManager().get(pathToMerge, Texture.class);
        if (!texture.getTextureData().isPrepared()) {
            texture2.getTextureData().prepare();
        }
        Pixmap pixmap2 = texture2.getTextureData().consumePixmap();

        pixmap.drawPixmap(pixmap2, 0, 0);

        return new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
    }

    /**
     * Types of drawing
     */
    public enum DrawingType {
        forestDraw,
        mountainDraw,
        riverDraw,
        rubberDraw,
        player1Draw,
        player2Draw,
        mobSkeletonDraw,
        mobWolfDraw,
        mobRandomLvl1Draw,
        mobSpiederDraw,
        mobZombieDraw,
        mobRandomLvl2Draw,
        tresureBox1Draw,
        tresureBox2Draw,
        towerMagicDraw,
        towerWisdomDraw,
        towerDefenceDraw,
        towerSpeedDraw,
        towerAttackDraw,
        towerHpDraw,
        towerWellDraw,
        towerHospitalDraw,
        none
    }

    public class FieldOfEditor extends Image implements Serializable {

        public boolean player1StartLocation = false;
        public boolean player2StartLocation = false;
        public boolean mobSkeletonLocation = false;
        public boolean mobWolfLocation = false;
        public boolean mobZombieLocation = false;
        public boolean mobSpiderLocation = false;
        public boolean mobRandomLevel1 = false;
        public boolean mobRandomLevel2 = false;
        public boolean terrainForest = false;
        public boolean terrainMountain = false;
        public boolean terrainRiver = false;
        public boolean terrainGrass = false;
        public boolean tresureBoxLvl1 = false;
        public boolean tresureBoxLvl2 = false;
        public boolean towerMagic = false;
        public boolean towerWisdom = false;
        public boolean towerDefence = false;
        public boolean towerSpeed = false;
        public boolean towerAttack = false;
        public boolean towerHp = false;
        public boolean towerWell = false;
        public boolean towerHospital = false;
        public Terrains terrains;
        public int posX;
        public int posY;

        public FieldOfEditor() {
            super(AssetsMapEditor.getInstance().getManager().get("mapEditor/terrains/grass.png", Texture.class));

            addListeners(this);
        }

        public void addListeners(final FieldOfEditor field) {
            this.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (drawingType != null) {
                        if (drawingType.equals(DrawingType.forestDraw)) {
                            field.terrainForest = true;
                            field.terrains = Terrains.Forest;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.mountainDraw)) {
                            field.terrainMountain = true;
                            field.terrains = Terrains.Mountain;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.riverDraw)) {
                            field.terrainRiver = true;
                            field.terrains = Terrains.River;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.rubberDraw)) {
                            clearField(field);
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.player1Draw)) {
                            for (int i = 0; i < mapColumns; i++) {
                                for (int j = 0; j < mapRows; j++) {
                                    if (fields[i][j].player1StartLocation) {
                                        fields[i][j].player1StartLocation = false;
                                        fillField(fields[i][j]);
                                    }
                                }
                            }
                            field.player1StartLocation = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.player2Draw)) {
                            for (int i = 0; i < mapColumns; i++) {
                                for (int j = 0; j < mapRows; j++) {
                                    if (fields[i][j].player2StartLocation) {
                                        fields[i][j].player2StartLocation = false;
                                        fillField(fields[i][j]);
                                    }
                                }
                            }
                            field.player2StartLocation = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.mobSkeletonDraw)) {
                            field.mobSkeletonLocation = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.mobWolfDraw)) {
                            field.mobWolfLocation = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.mobRandomLvl1Draw)) {
                            field.mobRandomLevel1 = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.mobSpiederDraw)) {
                            field.mobSpiderLocation = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.mobZombieDraw)) {
                            field.mobZombieLocation = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.mobRandomLvl2Draw)) {
                            field.mobRandomLevel2 = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.tresureBox1Draw)) {
                            field.tresureBoxLvl1 = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.tresureBox2Draw)) {
                            field.tresureBoxLvl2 = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.towerMagicDraw)) {
                            field.towerMagic = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.towerWisdomDraw)) {
                            field.towerWisdom = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.towerDefenceDraw)) {
                            field.towerDefence = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.towerSpeedDraw)) {
                            field.towerSpeed = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.towerAttackDraw)) {
                            field.towerAttack = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.towerHpDraw)) {
                            field.towerHp = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.towerWellDraw)) {
                            field.towerWell = true;
                            fillField(field);
                        } else if (drawingType.equals(DrawingType.towerHospitalDraw)) {
                            field.towerHospital = true;
                            fillField(field);
                        }
                    }

                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }

        /**
         * Clears field
         *
         * @param field Object of FieldOfEditor
         */
        private void clearField(FieldOfEditor field) {
            field.terrains = Terrains.Grass;
            field.terrainForest = false;
            field.terrainMountain = false;
            field.terrainRiver = false;
            field.terrainGrass = true;
            field.player1StartLocation = false;
            field.player2StartLocation = false;
            field.mobRandomLevel1 = false;
            field.mobSkeletonLocation = false;
            field.mobWolfLocation = false;
            field.mobSpiderLocation = false;
            field.mobZombieLocation = false;
            field.mobRandomLevel2 = false;
            field.tresureBoxLvl1 = false;
            field.tresureBoxLvl2 = false;
            field.towerMagic = false;
            field.towerWisdom = false;
            field.towerSpeed = false;
            field.towerDefence = false;
            field.towerAttack = false;
            field.towerHp = false;
            field.towerWell = false;
            field.towerHospital = false;
        }
    }

}
