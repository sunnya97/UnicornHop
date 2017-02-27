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

public class Dragon extends DynamicGameObject {
	public static final float DRAGON_WIDTH = 2.0f;
	public static final float DRAGON_HEIGHT = 2.0f;
	public static final float DRAGON_VELOCITY = 4f;

	float stateTime = 0;

	public Dragon (float x, float y) {
		super(x, y, DRAGON_WIDTH, DRAGON_HEIGHT);
		velocity.set(DRAGON_VELOCITY, 0);
	}

	public void update (float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - DRAGON_WIDTH / 2;
		bounds.y = position.y - DRAGON_HEIGHT / 2;

		if (position.x < DRAGON_WIDTH / 2) {
			position.x = DRAGON_WIDTH / 2;
			velocity.x = DRAGON_VELOCITY;
		}
		if (position.x > World.WORLD_WIDTH - DRAGON_WIDTH / 2) {
			position.x = World.WORLD_WIDTH - DRAGON_WIDTH / 2;
			velocity.x = -DRAGON_VELOCITY;
		}
		stateTime += deltaTime;
	}
}
