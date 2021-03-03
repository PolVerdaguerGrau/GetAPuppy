package com.example.androiddevchallenge


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PuppyListViewModel : ViewModel() {

    private var puppiesWithImages = HashMap<String, String?>()

    fun getPuppies(): List<String> {
        val puppies = mutableListOf<String>()
        puppiesWithImages.keys.forEach {
            puppies.add(it)
        }
        return puppies
    }

    fun getPuppyImage(puppy: String): LiveData<String> {
        return puppiesWithImages[puppy]?.let { puppyImage ->
            liveData {
                emit(puppyImage)
            }
        } ?: run {
            liveData {
                val puppyImage = getPuppyImage()
                puppiesWithImages[puppy] = puppyImage
                emit(puppyImage)
            }
        }
    }

    private suspend fun getPuppyImage(): String {
        return getRandomPuppy()
    }

    suspend fun getRandomPuppy(): String {
        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()

                    val url = request.url().newBuilder()
                        .build()

                    val newRequest = request.newBuilder()
                        .url(url)
                        .build()

                    chain.proceed(newRequest)
                }
                .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://dog.ceo/")
            .client(okHttpClient)
            .build()
        return retrofit.create(RandomPuppyService::class.java).run {
            getRandom().message
        }
    }

    fun withPuppies(puppies: List<String>): PuppyListViewModel {
        puppies.forEach {
            puppiesWithImages[it] = null
        }
        return this
    }
}