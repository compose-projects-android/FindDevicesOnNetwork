/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.compose_projects.find_devices_on_network

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.compose_projects.find_devices_on_netkork.find_devices_on_network.FindDevicesManager
import org.compose_projects.find_devices_on_netkork.find_devices_on_network.services.WifiP2pConnection

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val findDevicesManager = FindDevicesManager(this)
            val devicesWifi = findDevicesManager.devicesWifi()
            val devicesHotsPot = findDevicesManager.devicesHotsPot()

            val intent = Intent(this, WifiP2pConnection::class.java)

            Row {

                LazyColumn {
                    itemsIndexed(devicesWifi) { index, ip ->
                        Text(text = "(${index + 1}) $ip ")
                    }
                }
                Column {
                    Button(onClick = {startService(intent)}) {
                        Text(text = "Iniciar servicio")
                    }

                    Button(onClick = {stopService(intent)}) {
                        Text(text = "Detener servicio")
                    }
                }

            }

                /*LazyColumn {
                    itemsIndexed(devicesHotsPot) { index, ip ->
                        Text(text = "(${index + 1}) $ip ")
                    }
                }

                 */

            /*
                        AnimeList(
                animes = listOf(
                    TopAnimes(
                        category = Categories.category1,
                        imageAnime = R.drawable.ic_launcher_background,
                        nameAnime = NameAnimes.name1
                    ),
                    TopAnimes(
                        category = Categories.category3,
                        imageAnime = R.drawable.ic_launcher_background,
                        nameAnime = NameAnimes.name7
                    ),
                    TopAnimes(
                        category = Categories.category2,
                        imageAnime = R.drawable.ic_launcher_background,
                        nameAnime = NameAnimes.name6
                    ),
                    TopAnimes(
                        category = Categories.category1,
                        imageAnime = R.drawable.ic_launcher_background,
                        nameAnime = NameAnimes.name3
                    ),
                    TopAnimes(
                        category = Categories.category3,
                        imageAnime = R.drawable.ic_launcher_background,
                        nameAnime = NameAnimes.name1
                    ),
                    TopAnimes(
                        category = Categories.category3,
                        imageAnime = R.drawable.ic_launcher_background,
                        nameAnime = NameAnimes.name4
                    ),
                    TopAnimes(
                        category = Categories.category2,
                        imageAnime = R.drawable.ic_launcher_background,
                        nameAnime = NameAnimes.name5
                    ),
                    TopAnimes(
                        category = Categories.category1,
                        imageAnime = R.drawable.ic_launcher_background,
                        nameAnime = NameAnimes.name7
                    ),
                )
            )
             */

        }
    }
}


data class TopAnimes(
    val category: String,
    val imageAnime: Int,
    val nameAnime: String
)

data object Categories {
    const val category1 = "Top Anime Series"
    const val category2 = "Top Airing Anime"
    const val category3 = "Top Chavo del 8"
    //const val category4 = ""
}

data object NameAnimes {
    const val name1 = "One Punch Man 3"
    const val name3 = "Oshi no Ko Season 2"
    const val name4 = "Re Zero K ..."
    const val name5 = "Chavo del 8"
    const val name6 = "Pedro el escamoso"
    const val name7 = "Juanita la del barrio"
}


@Composable
fun AnimeList(animes: List<TopAnimes>) {
    val groupedAnimes = animes.groupBy { it.category }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn {
            groupedAnimes.forEach { (category, animesInCategory) ->
                item {
                    Text(
                        text = category,
                        style = TextStyle(
                            color = Color.Black.copy(0.8F),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                item {
                    LazyRow {
                        items(animesInCategory) { anime ->
                            AnimeItem(anime)
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun AnimeItem(anime: TopAnimes) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(7.dp)
    ) {
        Image(
            painter = painterResource(id = anime.imageAnime),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = anime.nameAnime, color = Color.Blue)
    }
}

