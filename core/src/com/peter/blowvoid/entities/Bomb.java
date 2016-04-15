package com.peter.blowvoid.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.peter.blowvoid.BVoid;

import java.sql.Time;

/**
 * Created by peter on 2/15/16.
 */
public class Bomb {

    public BVoid game;

    public static long BOMB_MAX_DURATION = 3200;
    public static long BOMB_MIN_DURATION = 1200;
    public static final int NORMAL = 0;
    public static final int TRICKY = 1;


    public Sprite sprite;
    public Circle circle;
    float circle_scale=1;
    float width, height;
    public float scale;

    public int type;

    public long spawn_time;
    public long start_time;
    public long duration;
    public boolean spawned;
    public boolean exploded=false;
    public boolean defused=false;

    public long explosion_duration = 1500;
    public boolean explosion_end = false;

    public Bomb(TextureRegion textureRegion, float x, float y){

        sprite = new Sprite(textureRegion);
        sprite.setPosition(x, y);

        circle = new Circle(x + sprite.getRegionWidth()/2, y+ sprite.getRegionWidth()/2, textureRegion.getRegionWidth()/2 + 8);
        spawned = true;
    }

    public Bomb(Bomb bomb, BVoid game){
        //kontruktor który kopijue i wrzuca do array z bombami
        sprite = new Sprite(bomb.sprite);
        sprite.setPosition(bomb.getX(), bomb.getY());

        circle = new Circle(bomb.getX() + sprite.getRegionWidth()/2, bomb.getY()+ sprite.getRegionWidth()/2, bomb.sprite.getRegionWidth()/2 + 8);
        this.type = MathUtils.random(0,1); // 0 normal 1 tricky
        this.game =game;
        duration = MathUtils.random(BOMB_MIN_DURATION,BOMB_MAX_DURATION);
        this.spawn_time = bomb.spawn_time;
        this.spawn_time=0;
        spawned = false;


    }

    public int update(){
        if(!spawned){
            if(TimeUtils.millis() > spawn_time ){
                spawned=true;
                start_time = TimeUtils.millis();
            }
        }

        return setScale();

    }

    public int setScale(){
        if(!spawned)return 0;
        if(exploded){
            scale = (float) (TimeUtils.millis() - start_time) /explosion_duration;
            scale = Math.round(scale * 100.0f)/100.0f;
            sprite.setScale(10*scale);
            if(scale>1){
                explosion_end=true;
                return 3;
            }

        }else {
            switch (type) {
                case NORMAL:
                    scale = (float) 0.5f + 0.5f*(TimeUtils.millis() - start_time) / duration;
                     scale = Math.round(scale * 100.0f)/100.0f;

                    sprite.setScale(scale);
                    // circle.setRadius(radius * scale);
                    if (scale > 1) {
                        exploded = true;
                        proceedToExplode(game.atlas.findRegion("red_bomb"));
                        return -1;
                    }

                    break;
                case TRICKY:
                    scale = (float) 0.5f - 0.5f*(TimeUtils.millis() - start_time) / duration;
                    scale = Math.round(scale * 100.0f)/100.0f;
                    sprite.setScale(scale);
                    // circle.setRadius(radius * scale);
                    if (scale < 0) {
                        defused = true;
                        return 1;
                    }

                    break;
            }
        }

        return 0;
    }

    public void proceedToExplode(TextureRegion textureRegion){
        this.sprite.setRegion(textureRegion);
        start_time = TimeUtils.millis();

    }

    public int touchUpdate(float x, float y){
        if(!spawned)return 0;

        if(circle.contains(new Vector2(x, y))){
            switch(type){
                case NORMAL:
                    defused = true;
                    return 1;

                case TRICKY:
                    exploded = true;
                    proceedToExplode(game.atlas.findRegion("red_bomb"));
                    return -1;

            }

        }
        return 0;
    }

    public void draw(SpriteBatch spriteBatch){
        if(!spawned)return;
        sprite.draw(spriteBatch);
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x, y );
        circle.setPosition(sprite.getX() + sprite.getRegionWidth() / 2, sprite.getY() + sprite.getRegionWidth() / 2);


    }
    public void setSpawn_time(long spawn_time){
        this.spawn_time = TimeUtils.millis() + spawn_time;
        spawned =false;
    }
    public float getX(){
        return sprite.getX();
    }

    public float getY(){
        return sprite.getY();
    }

}
