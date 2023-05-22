package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavHostController,
    applicationContext: Context
) {
        var searchPhrase by rememberSaveable {
            mutableStateOf("")
        }
        Scaffold(topBar = { HomeHeader(navController) }) {
            Column(modifier = Modifier.padding(it)) {
                Hero(searchPhrase, onChange = {searchPhrase=it})
            }
        }
    }


@Composable
fun HomeHeader(navController:NavHostController){
    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(contentColor = Color.White, containerColor = Color.White),
        shape = RoundedCornerShape(1.dp)
    ) {
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription ="logo.png",
                modifier= Modifier
                    .size(width = 200.dp, height = 70.dp)
                    .padding(end = 30.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .clickable { navController.navigate(Profile.route) }
                    .size(100.dp, 60.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero(
    searchPhrase: String,
    onChange:(String)->Unit
) {
    Surface(
        color = LittleLemonColor.green,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier=Modifier.padding(10.dp)) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(bottom=15.dp)
            ) {
                Column() {
                    Text(
                        text = stringResource(id = R.string.title),
                        style = MaterialTheme.typography.headlineSmall,
                        color = LittleLemonColor.yellow,
                        fontSize = 36.sp
                    )
                    Text(
                        text = stringResource(id = R.string.location),
                        modifier = Modifier.padding(bottom = 15.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = LittleLemonColor.yellow,
                        fontSize = 24.sp,
                    )
                    Text(
                        text = stringResource(id = R.string.description),
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth(0.60f)
                            .padding(top = 15.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.heroimage),
                    contentDescription = "hero Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(140.dp, 190.dp)
                        .clip(RoundedCornerShape(10.dp))

                )
            }

            TextField(
                value = searchPhrase,
                onValueChange = {onChange(it)},
                modifier=Modifier.fillMaxWidth().padding(10.dp),
                label = {
                    Text(text = "Enter Search Phrase")
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)

            )


        }
    }
}



