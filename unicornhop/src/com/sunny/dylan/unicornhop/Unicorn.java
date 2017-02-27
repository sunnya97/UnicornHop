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

public class Unicorn extends DynamicGameObject {
	public static final int UNICORN_STATE_JUMP = 0;
	public static final int UNICORN_STATE_FALL = 1;
	public static final int UNICORN_STATE_HIT = 2;
	public static final float UNICORN_JUMP_VELOCITY = 11;
	public static final float UNICORN_MOVE_VELOCITY = 40;
	public static final float UNICORN_WIDTH = 1.2f;
	public static final float UNICORN_HEIGHT = 1.2f;

	int state;
	float stateTime;

	public Unicorn (float x, float y) {
		super(x, y, UNICORN_WIDTH, UNICORN_HEIGHT);
		state = UNICORN_STATE_FALL;
		stateTime = 0;
	}

	public void update (float deltaTime) {
		velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;

		if (velocity.y > 0 && state != UNICORN_STATE_HIT) {
			if (state != UNICORN_STATE_JUMP) {
				state = UNICORN_STATE_JUMP;
				stateTime = 0;
			}
		}

		if (velocity.y < 0 && state != UNICORN_STATE_HIT) {
			if (state != UNICORN_STATE_FALL) {
				state = UNICORN_STATE_FALL;
				stateTime = 0;
			}
		}

		//if (position.x < 0) position.x = World.WORLD_WIDTH;
		//if (position.x > World.WORLD_WIDTH) position.x = 0;

		if (position.x < 0) position.x = 0;
		if (position.x > World.WORLD_WIDTH) position.x = World.WORLD_WIDTH;

		
		stateTime += deltaTime;
	}

	public void hitShootingStar () {
		velocity.y = UNICORN_JUMP_VELOCITY;
		state = UNICORN_STATE_JUMP;
		stateTime = 0;
		
	}

	public void hitStar () {
		velocity.y = UNICORN_JUMP_VELOCITY;
		state = UNICORN_STATE_JUMP;
		stateTime = 0;
	}
	public void hitSuperStar () {
		velocity.y = UNICORN_JUMP_VELOCITY*3;
		state = UNICORN_STATE_JUMP;
		stateTime = 0;
	}
	public void hitDragon() {
		velocity.y = 0;
		state = UNICORN_STATE_HIT;
		stateTime = 0;
	}
}
