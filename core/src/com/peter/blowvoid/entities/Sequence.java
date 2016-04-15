package com.peter.blowvoid.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.peter.blowvoid.BVoid;

import java.util.Iterator;

import javax.sound.midi.SysexMessage;

/**
 * Created by peter on 2/15/16.
 */
public class Sequence {

    BVoid game;

   public int bomb_amount;
    Array<Bomb>bombs;
    TextureRegion bomb_region;

    public float width;
    public float height;

    public Sequence(BVoid game){
        this.game = game;
        bomb_region = game.atlas.findRegion("black_bomb");
        bombs = new Array<Bomb>(16);
        bomb_amount=0;
    }

    public void addBomb(float x, float y){
        bombs.add(new Bomb(bomb_region,x,y));
        bomb_amount++;
    }

    public void setSize(float w, float h){
        width = w;
        height =h;
    }

    public void draw(SpriteBatch spriteBatch){
        Iterator<Bomb> iter = bombs.iterator();
        while(iter.hasNext()) {
            Bomb bomb = iter.next();
            bomb.draw(spriteBatch);
        }
    }

    public int spawn(float x,  float y, float lvl_w, float lvl_h, long spawn_time, Array<Bomb>game_bombs){
        int spawned =0;
        float s_x, s_y;
        s_x = MathUtils.random(x, lvl_w - width);
        s_y = MathUtils.random(y, lvl_h -height);

        int bombs_count=0;

        Iterator<Bomb> iter = bombs.iterator();
        while (iter.hasNext()) {

                Bomb bomb = iter.next();
                Bomb new_bomb = new Bomb(bomb, game);


                new_bomb.setPosition(new_bomb.getX() + s_x, new_bomb.getY() + s_y);

                //sprawdzam czy nową bombe można wstawić
                boolean can_put = true;

                Iterator<Bomb> iter_gb= game_bombs.iterator();
                while(iter_gb.hasNext()){
                    bombs_count++;
                    Bomb tmp_bomb = iter_gb.next();
                    if(new_bomb.circle.overlaps(tmp_bomb.circle)){
                        System.out.println("data "+new_bomb.circle.x+" "+new_bomb.circle.y+" "+tmp_bomb.circle.x+" "+tmp_bomb.circle.y+" "+tmp_bomb.circle.radius);
                        can_put= false;
                        break;
                    }
                }

                if(can_put){

                    new_bomb.setSpawn_time(spawn_time);
                    game_bombs.add(new_bomb);
                    spawned++;


                }

        }
        System.out.println("bombs_count " + bombs_count);
        return spawned;
    }

}
