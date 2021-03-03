/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlin.math.abs
import kotlin.random.Random

class PuppyPageViewModel : ViewModel() {
    private var puppyId = ""

    var puppyImage: LiveData<String>? = null

    private var loveLevel: Int = -1

    fun withId(puppy: String): PuppyPageViewModel {
        puppyId = puppy
        return this
    }

    fun withImage(puppyImage: LiveData<String>): PuppyPageViewModel {
        this.puppyImage = puppyImage
        return this
    }

    fun getLoveLevel(): Int {
        if (loveLevel == -1) {
            loveLevel = abs(Random.nextInt() % 5)
        }
        return loveLevel
    }

    fun weight(): Float {
        return abs(((Random.nextInt() % 197) + 10) / 10f)
    }
}
