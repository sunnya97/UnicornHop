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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.badlogic.gdx.math.Vector2;

public class World {
	public interface WorldListener {
		public void jump ();

		public void highJump ();

		public void hit ();

		public void star ();
		
		public void superstar ();
	}

	public static final float WORLD_WIDTH = 10;
	public static final float WORLD_HEIGHT = 15 * 20;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static int starnum=0;
	public static final Vector2 gravity = new Vector2(0, -16);
	float lastY = 45f;
	float y = Star.STAR_HEIGHT / 2;
	float maxJumpHeight = Unicorn.UNICORN_JUMP_VELOCITY * Unicorn.UNICORN_JUMP_VELOCITY / (2 * -gravity.y);
	public int timesGenerated=0;
	
	public final Unicorn unicorn;
	public final List<ShootingStar> shootingstars;
	public final List<Star> stars;
	public final List<Dragon> dragons;
	public final WorldListener listener;
	public final Random rand;
	
	public float heightSoFar;
	public long score = 0;
	public int state;
	
	//set variables
	public World (WorldListener listener) {
		this.unicorn = new Unicorn(5, 1);
		this.shootingstars = new ArrayList<ShootingStar>();
		this.stars = new ArrayList<Star>();
		this.dragons = new ArrayList<Dragon>();
		this.listener = listener;
		rand = new Random();
		generateLevel();
		this.heightSoFar = 0;
		this.score = 0;
		this.state = WORLD_STATE_RUNNING;
	}
	
	//reset variables for new game
	private void generateLevel () {
		score=0;
		Star.STAR_WIDTH=1f;
		Star.STAR_HEIGHT=1f;
		Star.STAR_SCORE=10;
		float y = Star.STAR_HEIGHT / 2;
		float maxJumpHeight = Unicorn.UNICORN_JUMP_VELOCITY * Unicorn.UNICORN_JUMP_VELOCITY / (2 * -gravity.y);
		timesGenerated=0;
		starnum=0;
	}
	
	//add 50 stars and shooting stars
	public void generateMore(){
		timesGenerated+=1;
		if(timesGenerated==2)
			shrink();
		else if(timesGenerated==3)
			shrink();
		else if(timesGenerated==4)
			shrink();
		while(stars.size()<50){
			lastY+=35f;
			float x = rand.nextFloat() * (WORLD_WIDTH - Star.STAR_WIDTH) + Star.STAR_WIDTH / 2;
			Star star = new Star(x, y);
			stars.add(star);
			y += (maxJumpHeight - 0.5f);
			y -= rand.nextFloat() * (maxJumpHeight / 3);
			int sometimes = rand.nextInt(3);
			if(sometimes == 0){
			ShootingStar shootingstar = new ShootingStar(star.position.x + rand.nextFloat(), lastY + rand.nextInt(30));
			shootingstars.add(shootingstar);
			}
			Dragon dragon = new Dragon(star.position.x + rand.nextFloat(), lastY + rand.nextInt(30) + 0.5f);
			dragons.add(dragon);
		}
	}
	
	//make stars smaller
	public void shrink(){
		Star.STAR_WIDTH-=0.1f;
		Star.STAR_HEIGHT-=0.1f;
		System.out.println("shrunk");
	}
	
	//call update functions && make sure there are at least 19 stars
	public void update (float deltaTime, float accelX) {
		updateUnicorn(deltaTime, accelX);
		updateShootingStars(deltaTime);
		updateStars(deltaTime);
		updateDragons(deltaTime);
		if (unicorn.state != Unicorn.UNICORN_STATE_HIT) 
			checkCollisions();
		checkGameOver();
		if(stars.size()<20)
			generateMore();
		
	}

	private void updateUnicorn (float deltaTime, float accelX) {
		if (unicorn.state != Unicorn.UNICORN_STATE_HIT && unicorn.position.y <= 0.5f) unicorn.hitStar();
		if (unicorn.state != Unicorn.UNICORN_STATE_HIT) unicorn.velocity.x = -accelX / 10 * Unicorn.UNICORN_MOVE_VELOCITY;
		unicorn.update(deltaTime);
		heightSoFar = Math.max(unicorn.position.y, heightSoFar);
	}
	
	private void updateShootingStars (float deltaTime) {
		int len = shootingstars.size();
		for (int i = 0; i < len; i++) {
			ShootingStar shootingstar = shootingstars.get(i);
			shootingstar.update(deltaTime);
		}
	}
	
	private void updateDragons (float deltaTime) {
		int len = dragons.size();
		for (int i = 0; i < len; i++) {
			Dragon dragon = dragons.get(i);
			dragon.update(deltaTime);
		}
	}

	private void updateStars (float deltaTime) {
		int len = stars.size();
		for (int i = 0; i < len; i++) {
			Star star = stars.get(i);
			star.update(deltaTime);
		}
	}
	
	//Call collision checkers
	private void checkCollisions () {
		checkShootingStarCollisions();
		checkStarCollisions();
		checkDragonCollisions();
	}

	//Double Score & bounce	/
	private void checkShootingStarCollisions () {
		int len = shootingstars.size();
		for (int i = 0; i < len; i++) {
			ShootingStar shootingstar = shootingstars.get(i);
			if (OverlapTester.overlapRectangles(shootingstar.bounds, unicorn.bounds)) {
				score*=2;
				shootingstars.remove(shootingstar);
				len = shootingstars.size();
				unicorn.hitStar();
				listener.jump();
			}
		}
	}
	
	private void checkDragonCollisions () {
		int len = dragons.size();
		for (int i = 0; i < len; i++) {
			Dragon dragon = dragons.get(i);
			if (OverlapTester.overlapRectangles(dragon.bounds, unicorn.bounds)) {
				unicorn.hitDragon();
			}
		}
	}
	
	//check for collision with Star and then jump
	private void checkStarCollisions () {
		int len = stars.size();
		for (int i = 0; i < len; i++) {
			Star star = stars.get(i);
			if (OverlapTester.overlapRectangles(star.bounds, unicorn.bounds)) {
				stars.remove(star);
				len = stars.size();
				listener.star();
				score+=Star.STAR_SCORE;
				Star.STAR_SCORE=Star.STAR_SCORE+10;
				unicorn.hitStar();
				listener.jump();
				if(starnum==0)
					unicorn.hitSuperStar();
				starnum++;
			}

		}
		if (unicorn.velocity.y > 0) return;
	}
	
	//check if unicorn hits bottom
	private void checkGameOver () {
		if (heightSoFar - 7.5f > unicorn.position.y) {
			state = WORLD_STATE_GAME_OVER;
		}
	}
}
