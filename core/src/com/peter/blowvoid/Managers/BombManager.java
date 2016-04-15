package com.peter.blowvoid.Managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.peter.blowvoid.BVoid;
import com.peter.blowvoid.entities.Bomb;
import com.peter.blowvoid.entities.Level;
import com.peter.blowvoid.entities.Sequence;

import java.util.Iterator;

/**
 * Created by peter on 2/15/16.
 */
public class BombManager {


    public long spawn_time = 3500;
    public long max_spawn_time = 3500;
    public long min_spawn_time  = 0;
    public long last_spawn=0;

    public Array<Bomb> bombs;
    public BVoid game;
    public Level level;
    public SequenceManager sequenceManager;

    public BombManager(BVoid game, Level level){
        sequenceManager = new SequenceManager(game);
        this.game = game;
        this.level = level;



        bombs = new Array<Bomb>(256);


    }

    public int spawn(int to_spwan){



        int max_sequence_id=0;

        while(max_sequence_id < level.numbers_of_sequences.length && to_spwan >= sequenceManager.sequences[level.numbers_of_sequences[max_sequence_id]].bomb_amount){
            max_sequence_id++;
            if(max_sequence_id == level.numbers_of_sequences.length)break;
        }
        max_sequence_id--;
        max_sequence_id =Math.max(0, max_sequence_id);

        int sequence_id = level.numbers_of_sequences[MathUtils.random(0, max_sequence_id)];
        Sequence tmp_sequence = sequenceManager.sequences[sequence_id];
        //System.out.println("sequence_i "+sequence_id);

        // TODO narazie spawn jest ile sie uda na sekwie
        return tmp_sequence.spawn(level.x, level.y, level.width, level.height, MathUtils.random(min_spawn_time, max_spawn_time), bombs);




    }

    public void update(){


        int bomb_number=0;
        Iterator<Bomb> iter = bombs.iterator();
        while(iter.hasNext()){
            bomb_number++;
            Bomb bomb = iter.next();
            switch(level.game_state){
                case Level.GAME:
                    switch(bomb.update()){
                        case 1:
                            level.addPoint();
                            iter.remove();
                            break;
                        case -1:
                            level.game_state = level.EXPLOSION;
                            break;

                    }
                    break;
                case Level.EXPLOSION:
                    if(bomb.update() == 3){
                        level.game_state = level.GAME_OVER;
                    }
                    break;
            }

        }
        System.out.println("bomb_number "+bomb_number);

        if( TimeUtils.millis() - last_spawn > spawn_time && level.game_state == level.GAME){
            last_spawn =TimeUtils.millis();
            int to_spwan = MathUtils.random(level.c_min_b_a, level.c_max_b_a);
            int last_to_spawn =-1;
            int try_count=0;

            while(to_spwan>0){

                to_spwan-=spawn(to_spwan);
                last_to_spawn = to_spwan;
                if(to_spwan == last_to_spawn)try_count++;
                if(try_count >=10)break;
            }
            System.out.print("spawn_end");
        }

    }

    public  void touchUpdate(float x, float y){
        switch(level.game_state) {
            case Level.GAME:
                Iterator<Bomb> iter = bombs.iterator();
                while (iter.hasNext()) {
                    Bomb bomb = iter.next();
                    switch (bomb.touchUpdate(x, y)) {
                        case 1:
                            iter.remove();
                            level.addPoint();
                            break;
                        case -1:
                            level.game_state = level.EXPLOSION;
                            break;
                    }
                }
                break;
        }
    }

    public void draw(SpriteBatch spriteBatch){

        Iterator<Bomb> iter = bombs.iterator();
        while(iter.hasNext()){
            Bomb bomb = iter.next();
            bomb.draw(spriteBatch);
        }



    }

}
