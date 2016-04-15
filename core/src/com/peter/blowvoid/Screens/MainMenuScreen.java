package com.peter.blowvoid.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.peter.blowvoid.BVoid;
import com.peter.blowvoid.Managers.DataManager;
import com.peter.blowvoid.entities.Bomb;

/**
 * Created by peter on 2/15/16.
 */
public class MainMenuScreen implements Screen {

    public BVoid game;

    OrthographicCamera camera;
    Viewport viewport;

    TextureRegion tittle_region;
    TextureRegion play_btn_region;
    Sprite tittle;
    Bomb play_btn;
    int game_message;


    public MainMenuScreen(BVoid game){

        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT, camera);
        camera.setToOrtho(false, game.GAME_WIDTH,game.GAME_HEIGHT);

        tittle_region = game.atlas.findRegion("main_screen_tittle");
        play_btn_region = game.atlas.findRegion("play_btn");

        tittle = new Sprite(tittle_region);
        game.funcs.centerSprite(tittle);
        tittle.setY(game.GAME_HEIGHT - 60 - tittle.getHeight());

        play_btn = new Bomb(play_btn_region,game.funcs.centerRegion(play_btn_region), tittle.getY() -80 - play_btn_region.getRegionHeight());
        game_message=0;



    }
    public MainMenuScreen(BVoid game, int game_message){

        this(game);
        this.game_message = game_message;

    }

    public void drawMessage(SpriteBatch spriteBatch){
        switch(game_message){
            case 1:
                //game_over

                game.fonts.font64.setColor(Color.RED);
                game.fonts.font64.draw(spriteBatch, "GAME OVER!", play_btn.sprite.getX() - 80, play_btn.sprite.getY() - 140);
                game.fonts.font64.setColor(Color.BLACK);
                break;
            case 2:
                //level accomleashed
                game.fonts.font48.setColor(Color.LIME);
                game.fonts.font48.draw(spriteBatch, "Level compleated!", play_btn.sprite.getX() - 100, play_btn.sprite.getY() - 140);
                game.fonts.font48.setColor(Color.BLACK);
                break;

        }
    }

    public void update(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touch_point = new Vector3(x,y,0);
                camera.unproject(touch_point);

                if(play_btn.circle.contains(touch_point.x, touch_point.y)){

                    game.setScreen(new GameScreen(game));

                }

                return true;
            }

            @Override
            public boolean touchUp (int x, int y, int pointer, int button) {


                return true;
            }
        });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
            tittle.draw(game.batch);
            play_btn.draw(game.batch);
            drawMessage(game.batch);
            game.fonts.font48.draw(game.batch, "highscore: " + game.dataManager.getHigh_score(), play_btn.sprite.getX() - 50, play_btn.sprite.getY() - 80);
            game.fonts.font36.draw(game.batch, "lvl "+game.dataManager.getCurrent_lvl(),play_btn.sprite.getX()+80,play_btn.sprite.getY() + 48);

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);

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

    }
}
