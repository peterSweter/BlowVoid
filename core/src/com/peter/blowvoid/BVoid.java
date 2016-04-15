package com.peter.blowvoid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.peter.blowvoid.Managers.DataManager;
import com.peter.blowvoid.Screens.MainMenuScreen;

public class BVoid extends Game {

	public static final  float GAME_WIDTH = 540;
	public static final float  GAME_HEIGHT = 890;
	public static final float BOMB_RADIOUS = 32;
	public static final float BOMB_SCALE=1;
	public static final int LVL_COUNT = 11;

	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	public TextureAtlas atlas;
	public Fonts fonts;
	public Funcs funcs;
	public DataManager dataManager;



	
	@Override
	public void create () {

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		atlas = new TextureAtlas(Gdx.files.internal("blow_void_textures.pack"));
		fonts = new Fonts();
		funcs = new Funcs();
		dataManager = new DataManager();

		setScreen(new MainMenuScreen(this));

	}


	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose() {
		super.dispose();
		fonts.dispose();
		atlas.dispose();
	}


}
