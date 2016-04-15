package com.peter.blowvoid.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.peter.blowvoid.BVoid;

/**
 * Created by peter on 2/15/16.
 */
public class Level {

    public static final int GAME = 0;
    public static final int EXPLOSION =1;
    public static final int GAME_OVER = 2;

    public int min_bomb_amount;
    public int max_bomb_amount;

    public int c_min_b_a;
    public int c_max_b_a;

    public int final_min_bomb_amount;
    public int final_max_bomb_amount;

    public int points_to_achieve;
    public int points_left;

    public float width,height;
    public float x,y;

    public int sequence_count;
    public int game_state;

    int needed_amount_min;
    int needed_amount_max;
    int last_amount_min;
    int last_amount_max;



    public int[] numbers_of_sequences;

    public Level(int min_bomb_amount, int max_bomb_amount, int final_min_bomb_amount, int final_max_bomb_amount, int[] numbers_of_sequences, float width, float height, int points_to_achieve){

        this.numbers_of_sequences = numbers_of_sequences;
        this.min_bomb_amount = min_bomb_amount;
        this.max_bomb_amount = max_bomb_amount;
        this.final_min_bomb_amount = final_min_bomb_amount;
        this.final_max_bomb_amount = final_max_bomb_amount;
        this.height = height;
        this.width = width;
        this.points_to_achieve = points_to_achieve;
        x = BVoid.GAME_WIDTH/2 - width/2;
        y = 2 * BVoid.BOMB_RADIOUS * 2;
        sequence_count = this.numbers_of_sequences.length;

    }

    public void setPosition(float x, float y){
        this.x=x;
        this.y=y;
    }

    public int update(){


        return game_state;
    }

    public void start(){

        c_min_b_a = min_bomb_amount;
        c_max_b_a = max_bomb_amount;
        points_left = points_to_achieve;
        game_state = 0;
        countAmount();
    }

    public void addPoint(){
        points_left--;
        int curren_points = (points_to_achieve-points_left);

        if(needed_amount_min!=0 &&  curren_points % needed_amount_min == 0  && last_amount_min !=curren_points){
            last_amount_min = curren_points;
            c_min_b_a = Math.min(c_min_b_a + 1, final_min_bomb_amount);
        }

        if(needed_amount_max!=0 && curren_points % needed_amount_max == 0  && last_amount_max !=curren_points ){
            last_amount_max = curren_points;
            c_max_b_a = Math.min(c_max_b_a + 1, final_max_bomb_amount);
        }

        System.out.println("c_min "+ c_min_b_a+ " c_max "+ c_max_b_a);

    }

    void countAmount(){
        needed_amount_min=0;
        needed_amount_max=0;
        if((final_min_bomb_amount - min_bomb_amount) !=0)needed_amount_min =Math.max(1, (points_to_achieve-10)/(final_min_bomb_amount - min_bomb_amount));
        if((final_max_bomb_amount - max_bomb_amount) !=0)needed_amount_max =Math.max(1, (points_to_achieve-10)/(final_max_bomb_amount - max_bomb_amount));

        System.out.println("needed_min "+ needed_amount_min+" needed_max "+needed_amount_max);

    }


}
