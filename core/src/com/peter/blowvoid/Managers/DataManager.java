package com.peter.blowvoid.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


/**
 * Created by peter on 2/15/16.
 */
public class DataManager {
    Preferences data;

    int current_lvl;
    int high_score;

    public DataManager(){
        data = Gdx.app.getPreferences("BlowVoidData");
        current_lvl = data.getInteger("current_lvl",0);
        high_score = data.getInteger("high_score",0);
    }

    public void setHigh_score(int high_score){
        this.high_score= high_score;
        data.putInteger("high_score", high_score);
        data.flush();
    }

    public void setCurrent_lvl(int current_lvl){
        this.current_lvl = current_lvl;
        data.putInteger("current_lvl", current_lvl);
        data.flush();
    }

    public int getCurrent_lvl() {
        return current_lvl;
    }

    public int getHigh_score(){
        return high_score;
    }
}
