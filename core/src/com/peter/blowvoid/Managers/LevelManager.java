package com.peter.blowvoid.Managers;

import com.peter.blowvoid.BVoid;
import com.peter.blowvoid.entities.Level;

/**
 * Created by peter on 2/15/16.
 */
public class LevelManager {
    public int lvl_count =11;
    public Level[] levels;

    public LevelManager(){
        levels = new Level[lvl_count];

        levels[0] = new Level(3,4,4,5,new int[]{0}, BVoid.GAME_WIDTH - 16, 600,25);
        levels[1] = new Level(4,5,5,6,new int[]{0,1}, BVoid.GAME_WIDTH - 16, 600,40);
        levels[2] = new Level(5,6,6,7,new int[]{0,1,2}, BVoid.GAME_WIDTH - 16, 600,50);
        levels[3] = new Level(6,7,7,8,new int[]{0,1,2}, BVoid.GAME_WIDTH - 16, 600,100);
        levels[4] = new Level(7,8,8,9,new int[]{0,1,2}, BVoid.GAME_WIDTH - 16, 600,200);
        levels[5] = new Level(9,13,10,14,new int[]{0,1,2,3}, BVoid.GAME_WIDTH - 16, 600,200);
        levels[6] = new Level(10,13,11,14,new int[]{0,1,2,3}, BVoid.GAME_WIDTH - 16, 600,300);
        levels[7] = new Level(11,13,12,14,new int[]{0,1,2,3,4}, BVoid.GAME_WIDTH - 16, 600,400);
        levels[8] = new Level(12,13,12,14,new int[]{0,1,2,3,4}, BVoid.GAME_WIDTH - 16, 600,600);
        levels[9] = new Level(12,14,12,15,new int[]{0,1,2,3,4}, BVoid.GAME_WIDTH - 16, 600,800);
        levels[10] = new Level(12,15,12,16,new int[]{0,1,2,3,4}, BVoid.GAME_WIDTH - 16, 600,1000);


    }
}
