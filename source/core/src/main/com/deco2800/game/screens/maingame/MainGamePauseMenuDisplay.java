package com.deco2800.game.screens.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.deco2800.game.generic.ServiceLocator;
import com.deco2800.game.ui.components.UIComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainGamePauseMenuDisplay extends UIComponent {
    private static final Logger logger = LoggerFactory.getLogger(MainGamePauseMenuDisplay.class);
    private Table table;
    private static final float Z_INDEX = 2f;

    @Override
    public void create() {
        super.create();
        addActors();
    }

    private void addActors() {
        table.setFillParent(true);
        TextButton resumeBtn = new TextButton("Resume", new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json")));
        TextButton restartBtn = new TextButton("Restart from Start", new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json")));
        TextButton mainMenuBtn = new TextButton("Main Menu", new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json")));
        TextButton settingsBtn = new TextButton("Settings", new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json")));

        // Trigger resume game
        resumeBtn.addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        logger.debug("Resume button clicked");
                        entity.getEvents().trigger("resume");
                    }
                });

        // Trigger restart game
        restartBtn.addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        logger.debug("Restart button clicked");
                        entity.getEvents().trigger("restart");
                    }
                });

        // Trigger to go to settings menu
        settingsBtn.addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        logger.debug("Settings button clicked");
                        entity.getEvents().trigger("settings");
                    }
                });

        // Trigger to go to main menu
        mainMenuBtn.addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        logger.debug("Main menu button clicked");
                        entity.getEvents().trigger("main_menu");
                    }
                });

        table.add(resumeBtn).padTop(50f);
        table.row();
        table.add(restartBtn).padTop(15f);
        table.row();
        table.add(settingsBtn).padTop(15f);
        table.row();
        table.add(mainMenuBtn).padTop(15f);
        table.setName("Pause Menu");
        ServiceLocator.getRenderService().getStage().addActor(table);
        ServiceLocator.getRenderService().getStage().draw();
    }

    @Override
    public void draw(SpriteBatch batch) {
        // draw is handled by the stage
    }

    @Override
    public float getZIndex() {
        return Z_INDEX;
    }

    @Override
    public void dispose() {
        table.clear();
        super.dispose();
    }
}
