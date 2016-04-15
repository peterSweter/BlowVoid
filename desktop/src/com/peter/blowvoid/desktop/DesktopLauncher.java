package com.peter.blowvoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.peter.blowvoid.BVoid;

import java.math.BigInteger;

public class DesktopLauncher {
	BigInteger test;
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BVoid(), config);
	}

}
