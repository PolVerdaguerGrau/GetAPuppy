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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ComposeNavigation()
                }
            }
        }
    }
}

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    val puppies = listOf(
        "Bobby",
        "Light",
        "Jessie",
        "Aisó",
        "Bangarang",
        "Siff",
        "Gatox",
        "Firulais",
        "Cucatrempada",
        "Oscar"
    )
    val puppyListViewModel = PuppyListViewModel().withPuppies(puppies)
    NavHost(navController, startDestination = "greetings") {
        composable("greetings") { Greeting(navController) }
        composable("puppyList") {
            ChoosePuppy(navController, puppyListViewModel)
        }
        composable("puppyPage/{puppy}") { backStackEntry ->
            backStackEntry.arguments?.getString("puppy")?.let { puppyId ->
                val puppyPageViewModel = PuppyPageViewModel().withId(puppyId)
                    .withImage(puppyListViewModel.getPuppyImage(puppyId))
                PuppyPage(backStackEntry.arguments?.getString("puppy") ?: "", puppyPageViewModel)
            }
        }
    }
}

@Composable
fun Greeting(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Get a puppy!",
            style = typography.h6,
            modifier = Modifier
                .padding(20.dp)
        )
        Button(
            onClick = { navController.navigate("puppyList") },
            modifier = Modifier
                .height(60.dp)
                .width(100.dp)
        ) {
            Text(text = "Start!")
        }
    }
}
