package com.peter.blowvoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by peter on 2/17/16.
 */
public class Fonts {

    public BitmapFont font24;
    public BitmapFont font48;
    public BitmapFont font64;
    public BitmapFont font36;

    public Fonts(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sawasdee.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        font24 = generator.generateFont(parameter);
        parameter.size = 48;
        font48 = generator.generateFont(parameter);
        parameter.size = 64;
        font64 = generator.generateFont(parameter);
        parameter.size = 36;
        font36 = generator.generateFont(parameter);
        generator.dispose();

        font24.setColor(Color.BLACK);
        font48.setColor(Color.BLACK);
        font64.setColor(Color.BLACK);
        font36.setColor(Color.WHITE);

    }

    public void dispose(){
        font24.dispose();
        font48.dispose();
        font64.dispose();
        font36.dispose();
    }
}
