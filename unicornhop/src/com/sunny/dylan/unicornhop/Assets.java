/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.sunny.dylan.unicornhop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture background;
	public static TextureRegion backgroundStart;
	public static Animation backgroundRegion;

	public static Texture items;
	public static Texture sparkles;
	public static Texture star;
	public static Texture superstar;
	public static Texture dragon;
	public static Texture explosion;
	public static Texture shootingStar;
	public static Texture logos;
	public static TextureRegion mainMenu;
	public static TextureRegion pauseMenu;
	public static TextureRegion ready;
	public static TextureRegion gameOver;
	public static TextureRegion highScoresRegion;
	public static TextureRegion logo;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;
	public static Animation starAnim;
	public static Animation dragonAnim;
	public static Animation superstarAnim;
	public static Animation unicornJump;
	public static Animation unicornFall;
	public static TextureRegion unicornHit;
	public static Animation shootingstarFly;
	public static Animation distractionFly;
	public static BitmapFont font;

	public static Music music;
	public static Sound jumpSound;
	public static Sound highJumpSound;
	public static Sound hitSound;
	public static Sound coinSound;
	public static Sound clickSound;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void load () {
		background = loadTexture("data/backgrounds.png");
		backgroundStart = new TextureRegion(background, 0, 0, 320, 480);
		
		backgroundRegion = new Animation(0.15f, new TextureRegion(background, 0, 0, 320, 480), new TextureRegion(background, 330, 0, 320, 480), new TextureRegion(background, 660, 0, 320, 480), new TextureRegion(background, 0, 496, 320, 480),
		 new TextureRegion(background, 330, 496, 320, 480), new TextureRegion(background, 660, 496, 320, 480), new TextureRegion(background, 0, 989, 320, 480), new TextureRegion(background, 330, 989, 320, 480), 
		 new TextureRegion(background, 660, 989, 320, 480), new TextureRegion(background, 0, 1483, 320, 480), new TextureRegion(background, 330, 1483, 320, 480), new TextureRegion(background, 660, 1483, 320, 480), new TextureRegion(background, 0, 1974, 320, 480), 
		 new TextureRegion(background, 330, 1974, 320, 480), new TextureRegion(background, 660, 1974, 320, 480), /*cat*/ new TextureRegion(background, 0, 2478, 320, 480), new TextureRegion(background, 330, 2478, 320, 480), new TextureRegion(background, 660, 2478, 320, 480), 
		 new TextureRegion(background, 0, 2980, 320, 480), new TextureRegion(background, 330, 2980, 320, 480), /*spin*/ new TextureRegion(background, 330, 2980, 320, 480), 
		 new TextureRegion(background, 0, 2980, 320, 480), new TextureRegion(background, 0, 2478, 320, 480), new TextureRegion(background, 660, 2980, 320, 480), 
		 new TextureRegion(background, 0, 3487, 320, 480), new TextureRegion(background, 330, 3487, 320, 480), new TextureRegion(background, 660, 3487, 320, 480), new TextureRegion(background, 1002, 2980, 320, 480), new TextureRegion(background, 1340, 2980, 320, 480), 
		 new TextureRegion(background, 1682, 2980, 320, 480), new TextureRegion(background, 1002, 3487, 320, 480), new TextureRegion(background, 1340, 3487, 320, 480), new TextureRegion(background, 1682, 3487, 320, 480), new TextureRegion(background, 0, 2478, 320, 480)/*end of spin, start of lasers*/,
		 new TextureRegion(background, 1004, 0, 320, 480), new TextureRegion(background, 0, 0, 320, 480), new TextureRegion(background, 1347, 0, 320, 480), new TextureRegion(background, 0, 0, 320, 480), new TextureRegion(background, 1700, 0, 320, 480), 
		 new TextureRegion(background, 0, 0, 320, 480), new TextureRegion(background, 1004, 496, 320, 480), new TextureRegion(background, 0, 0, 320, 480), new TextureRegion(background, 1347, 496, 320, 480));

		items = loadTexture("data/items.png");
		shootingStar = loadTexture("data/estrella.png");
		sparkles = loadTexture("data/sparkles.png");
		star = loadTexture("data/star.png");
		dragon = loadTexture("data/dragon.png");
		explosion = loadTexture("data/explosion.png");
		superstar = loadTexture("data/superstar.png");
		logos = loadTexture("data/logo.png");
		
		mainMenu = new TextureRegion(items, 0, 224, 300, 110);
		pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
		ready = new TextureRegion(items, 320, 224, 192, 32);
		gameOver = new TextureRegion(items, 352, 256, 160, 96);
		highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
		logo = new TextureRegion(logos, 0, 0, 274, 142);
		soundOff = new TextureRegion(items, 0, 0, 64, 64);
		soundOn = new TextureRegion(items, 64, 0, 64, 64);
		arrow = new TextureRegion(items, 0, 64, 64, 64);
		pause = new TextureRegion(items, 64, 64, 64, 64);
		
		starAnim = new Animation(0.2f, new TextureRegion(star, 0, 0, 300, 280), new TextureRegion(star, 300, 0, 535, 500));
		dragonAnim = new Animation(0.2f, new TextureRegion(dragon, 15, 10, 173, 150), new TextureRegion(dragon, 230, 10, 173, 150),new TextureRegion(dragon, 445, 10, 173, 150), new TextureRegion(dragon, 230, 10, 173, 150));
		superstarAnim = new Animation(0.1f, new TextureRegion(superstar, 0, 0, 300, 280), new TextureRegion(superstar, 300, 0, 300, 280));
		unicornJump = new Animation(0.2f, new TextureRegion(sparkles, 0, 0, 64, 64), new TextureRegion(sparkles, 0, 0, 64, 64));
		unicornFall = new Animation(0.2f, new TextureRegion(sparkles, 0, 0, 64, 64), new TextureRegion(sparkles, 0, 0, 64, 64));
		unicornHit = new TextureRegion(explosion, 210, 236, 476, 368);
		shootingstarFly = new Animation(0.2f, new TextureRegion(shootingStar, 0, 0, 582, 270), new TextureRegion(shootingStar, 0, 0, 582, 270));

		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);

		music = Gdx.audio.newMusic(Gdx.files.internal("data/soundtrack.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		if (Settings.soundEnabled) music.play();
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("data/jump.wav"));
		highJumpSound = Gdx.audio.newSound(Gdx.files.internal("data/highjump.wav"));
		coinSound = Gdx.audio.newSound(Gdx.files.internal("data/hit.wav"));
		hitSound = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
	}

	public static void playSound (Sound sound) {
		if (Settings.soundEnabled)
			{
			sound.play(0.65f);
			}
	}
}
