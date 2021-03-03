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