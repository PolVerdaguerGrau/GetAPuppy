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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PuppyPage(puppy: String, puppyPageViewModel: PuppyPageViewModel) {
    Column(Modifier.padding(5.dp)) {

        puppyPageViewModel.puppyImage?.observeAsState()?.value?.let {
            CoilImage(
                data = it, contentDescription = "A  dog",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(0.dp, 5.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray),
                loading = {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                    )
                }
            )
        }

        Column(Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    style = MaterialTheme.typography.h6, text = "name: ", modifier = Modifier
                )
                Text(
                    style = MaterialTheme.typography.h4, text = puppy, textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = MaterialTheme.typography.h6, text = "love: "
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    for (i in 0..4) {
                        if (i <= puppyPageViewModel.getLoveLevel()) {
                            Image(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(id = R.drawable.ic_heart),
                                contentDescription = "Heart"
                            )
                        } else {
                            val lastHeart =
                                if (puppy == "Aisó") R.drawable.ic_heart else R.drawable.ic_empty_heart
                            Image(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(id = lastHeart),
                                contentDescription = "Heart"
                            )
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = MaterialTheme.typography.h6,
                    text = "weight: ",
                    modifier = Modifier.padding(0.dp, 5.dp)
                )
                val weight = puppyPageViewModel.weight()
                Text(
                    style = MaterialTheme.typography.h5,
                    text = "$weight Kg",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun Preview() {
    PuppyPage(puppy = "Bangarang", puppyPageViewModel = PuppyPageViewModel().withId("Aisó"))
}
