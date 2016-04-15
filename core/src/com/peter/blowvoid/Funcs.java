package com.peter.blowvoid;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by peter on 2/17/16.
 */
public class Funcs {

    public void centerSprite(Sprite sprite){
        sprite.setX(BVoid.GAME_WIDTH/2 - sprite.getWidth()/2);

    }

    public float centerRegion(TextureRegion region){
        return BVoid.GAME_WIDTH/2 - region.getRegionWidth()/2;
    }

}
