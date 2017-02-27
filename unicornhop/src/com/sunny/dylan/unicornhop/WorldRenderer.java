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
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorldRenderer{
	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	World world;
	OrthographicCamera cam;
	SpriteBatch batch;
	TextureRegion background;
	float stateTime;
	TextureRegion                   currentFrame;
	
	public WorldRenderer (SpriteBatch batch, World world) {
		this.world = world;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batch = batch;
	}

	public void render () {
		if (world.unicorn.position.y > cam.position.y) cam.position.y = world.unicorn.position.y;
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
		
	}

	public void renderBackground () {
		stateTime += Gdx.graphics.getDeltaTime();
      currentFrame = Assets.backgroundRegion.getKeyFrame(stateTime, 0);
      batch.begin();
      batch.draw(currentFrame, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
      batch.end();
	}
	
	public void renderObjects () {
		batch.enableBlending();
		batch.begin();
		renderUnicorn();
		renderStars();
		renderDragons();
		//renderSuperStar();
		renderShootingStars();
		batch.end();
	}
	
	private void renderUnicorn () {
		TextureRegion keyFrame;
		switch (world.unicorn.state) {
		case Unicorn.UNICORN_STATE_FALL:
			keyFrame = Assets.unicornFall.getKeyFrame(world.unicorn.stateTime, Animation.ANIMATION_LOOPING);
			break;
		case Unicorn.UNICORN_STATE_JUMP:
			keyFrame = Assets.unicornJump.getKeyFrame(world.unicorn.stateTime, Animation.ANIMATION_LOOPING);
			break;
		case Unicorn.UNICORN_STATE_HIT:
		default:
			keyFrame = Assets.unicornHit;
		}
		//david says "butt"
		float side = world.unicorn.velocity.x < 0 ? -1 : 1;
		if (side < 0)
			batch.draw(keyFrame, world.unicorn.position.x + 0.5f, world.unicorn.position.y - 0.5f, side * Unicorn.UNICORN_WIDTH, Unicorn.UNICORN_HEIGHT);
		else
			batch.draw(keyFrame, world.unicorn.position.x - 0.5f, world.unicorn.position.y - 0.5f, side * Unicorn.UNICORN_WIDTH, Unicorn.UNICORN_HEIGHT);
	}

	private void renderStars () {
		int len = world.stars.size();
		for (int i = 0; i < len; i++) {
			Star star = world.stars.get(i);
			TextureRegion keyFrame = Assets.starAnim.getKeyFrame(star.stateTime, Animation.ANIMATION_LOOPING);
			batch.draw(keyFrame, star.position.x - 0.5f, star.position.y - 0.5f, Star.STAR_WIDTH, Star.STAR_HEIGHT);
		}
	}
	
	/*private void renderSuperStar()
	{
		int len = world.stars.size();
		for (int i = 0; i < len; i++) {
			Star superstar = world.stars.get(i);
			TextureRegion keyFrame = Assets.superstarAnim.getKeyFrame(superstar.stateTime, Animation.ANIMATION_LOOPING);
			batch.draw(keyFrame, 5, 3.5f, Star.STAR_WIDTH, Star.STAR_HEIGHT);
		}
	}*/

	private void renderShootingStars () {
		int len = world.shootingstars.size();
		for (int i = 0; i < len; i++) {
			ShootingStar squirrel = world.shootingstars.get(i);
			TextureRegion keyFrame = Assets.shootingstarFly.getKeyFrame(squirrel.stateTime, Animation.ANIMATION_LOOPING);
			float side = squirrel.velocity.x < 0 ? -1 : 1;
			if (side < 0)
				batch.draw(keyFrame, squirrel.position.x + 0.5f, squirrel.position.y - 0.5f, side * ShootingStar.SQUIRREL_WIDTH, ShootingStar.SQUIRREL_HEIGHT);
			else
				batch.draw(keyFrame, squirrel.position.x - 0.5f, squirrel.position.y - 0.5f, side * ShootingStar.SQUIRREL_WIDTH, ShootingStar.SQUIRREL_HEIGHT);
		}
	}
	
	private void renderDragons () {
		int len = world.dragons.size();
		for (int i = 0; i < len; i++) {
			Dragon dragon = world.dragons.get(i);
			TextureRegion keyFrame = Assets.dragonAnim.getKeyFrame(dragon.stateTime, Animation.ANIMATION_LOOPING); 
			float side = dragon.velocity.x < 0 ? -1 : 1;
			if (side < 0)
				batch.draw(keyFrame, dragon.position.x + 0.5f, dragon.position.y - 0.5f, side * Dragon.DRAGON_WIDTH, Dragon.DRAGON_HEIGHT);
			else
				batch.draw(keyFrame, dragon.position.x - 0.5f, dragon.position.y - 0.5f, side * Dragon.DRAGON_WIDTH, Dragon.DRAGON_HEIGHT);
		}
	}
}
