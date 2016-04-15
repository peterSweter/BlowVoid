package com.peter.blowvoid.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.peter.blowvoid.BVoid;
import com.peter.blowvoid.Screens.GameScreen;
import com.peter.blowvoid.Screens.MainMenuScreen;
import com.peter.blowvoid.entities.Bomb;
import com.peter.blowvoid.entities.Level;

/**
 * Created by peter on 2/17/16.
 */
public class GameManager {

    public BVoid game;
    public GameScreen gameScreen;
    public LevelManager levelManager;
    public Level level;
    public BombManager bombManager;
    public int current_lvl;

    public GameManager(BVoid game, GameScreen gameScreen){
        this.game = game;
        this.gameScreen = gameScreen;

        levelManager = new LevelManager();
        current_lvl = game.dataManager.getCurrent_lvl();

        level = levelManager.levels[current_lvl];
        level.start();


        bombManager = new BombManager(game, level);

    }

    public void update(float delta){

        if(level.update()==Level.GAME_OVER){
            gameOver();
        }

        if(level.points_left <= 0){
            levelComplete();
        }

        bombManager.update();

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                Vector3 touch_point = new Vector3(x, y, 0);
                gameScreen.camera.unproject(touch_point);
                bombManager.touchUpdate(touch_point.x, touch_point.y);


                return true;
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {


                return true;
            }
        });



    }

    public void draw(SpriteBatch spriteBatch){
        bombManager.draw(spriteBatch);
        game.fonts.font64.draw(spriteBatch, "" + level.points_left, 16, BVoid.GAME_HEIGHT - 44);


    }

    public void gameOver(){
        game.setScreen(new MainMenuScreen(game,1));
        int old_hscore = game.dataManager.getHigh_score();
        if(old_hscore < level.points_to_achieve - level.points_left){
            game.dataManager.setHigh_score(level.points_to_achieve - level.points_left);
        }
    }

    public void levelComplete(){
        game.setScreen(new MainMenuScreen(game,2));

        game.dataManager.setHigh_score(0);
        game.dataManager.setCurrent_lvl(game.dataManager.getCurrent_lvl()+1);
    }
}
