package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavHostController,
    applicationContext: Context,
    menuItems: List<MenuItem>
) {
        var searchPhrase by rememberSaveable {
            mutableStateOf("")
        }
        var category by rememberSaveable {
            mutableStateOf(4)
        }
    
        Scaffold(topBar = { HomeHeader(navController) }) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Hero(searchPhrase) { searchPhrase = it }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(1.dp)
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Text(
                            text = "ORDER FOR DELIVERY!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            modifier = Modifier.padding(bottom=10.dp)
                        ){
                            itemsIndexed(categories){index, item ->
                                if(index==category){
                                    SelectedCategoryButtons(name = index, onClick = {category=index})
                                }
                                else{
                                    NotSelectedCategoryButtons(name = index,onClick={category=index})
                                }
                            }
                        }
                        Divider(color = Color.LightGray, modifier = Modifier.padding(bottom=10.dp))

                        LazyColumn{
                            if(category==4) {
                                if (searchPhrase.isEmpty()) {
                                    items(items = menuItems) {
                                        menuItems(dish = it)
                                    }
                                } else
                                    items(items = menuItems.filter {
                                        it.title.contains(
                                            searchPhrase,
                                            true
                                        )
                                    }) {
                                        menuItems(dish = it)
                                    }
                            }else{
                                if (searchPhrase.isEmpty()) {
                                    items(items = menuItems.filter { it.category.contains(categories[category],true) }) {
                                        menuItems(dish = it)
                                    }
                                } else
                                    items(items = menuItems.filter {
                                        it.title.contains(
                                            searchPhrase,
                                            true
                                        )&&it.category.contains(categories[category],true)
                                    }) {
                                        menuItems(dish = it)
                                    }
                            }
                        }

                    }
                }
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
                    .size(width = 200.dp, height = 55.dp)
                    .padding(end = 30.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .clickable { navController.navigate(Profile.route) }
                    .size(100.dp, 45.dp))
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
            Text(
                text = stringResource(id = R.string.title),
                style = MaterialTheme.typography.headlineLarge,
                color = LittleLemonColor.yellow,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(bottom=15.dp)
            ) {
                Column() {

                    Text(
                        text = stringResource(id = R.string.location),
                        modifier = Modifier.padding(bottom = 15.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        fontSize = 25.sp,
                    )
                    Text(
                        text = stringResource(id = R.string.description),
                        color = Color.White,
                        fontSize = 15.sp,
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
                        .size(150.dp, 150.dp)
                        .clip(RoundedCornerShape(10.dp))

                )
            }
            TextField(
                value = searchPhrase,
                onValueChange = {onChange(it)},
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                placeholder = {
                    Text(text = "Enter Search Phrase")
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White, textColor = Color.Black),
                leadingIcon = { Image(painter = painterResource(id = R.drawable.search_foreground), contentDescription = "Search", modifier = Modifier.size(50.dp))}
            )
        }
    }
}
@Composable
fun NotSelectedCategoryButtons(
     name:Int,
     onClick:()->Unit
){
    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
    ) {
        Text(text = categories[name], color = Color.DarkGray, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

@Composable
fun SelectedCategoryButtons(
    name:Int,
    onClick:()->Unit
){
    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
        border = BorderStroke(3.dp, LittleLemonColor.yellow)
    ) {
        Text(text = categories[name], color = LittleLemonColor.green, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun menuItems(
    dish:MenuItem
){
    Card (colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)){

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = dish.title, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
                Text(text = dish.description, modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(.75f),
                    color = Color.Gray,
                    fontSize = 15.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "$ ${dish.price}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            }
            GlideImage(
                model = dish.image,
                contentDescription = "Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(1.dp))
                    .padding(start = 5.dp)
            )
        }
    }
}




