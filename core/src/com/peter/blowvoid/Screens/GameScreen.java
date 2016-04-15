package com.peter.blowvoid.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.peter.blowvoid.BVoid;
import com.peter.blowvoid.Managers.GameManager;
import com.peter.blowvoid.Managers.SequenceManager;
import com.peter.blowvoid.entities.Level;

/**
 * Created by peter on 2/15/16.
 */
public class GameScreen implements Screen {

    BVoid game;

    public OrthographicCamera camera;
    Viewport viewport;
    GameManager gameManager;





    public GameScreen(BVoid game){

        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT, camera);
        camera.setToOrtho(false, game.GAME_WIDTH,game.GAME_HEIGHT);
        gameManager = new GameManager(game, this);



    }

    public void update(float delta){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touch_point = new Vector3(x,y,0);
                camera.unproject(touch_point);


                return true;
            }

            @Override
            public boolean touchUp (int x, int y, int pointer, int button) {


                return true;
            }
        });

        gameManager.update(delta);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
           gameManager.draw(game.batch);

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
