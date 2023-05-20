package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonHero
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.LittleLemonYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoarding(){

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    Scaffold(topBar = { Header()}) {
        Column(
            modifier= Modifier.padding(it)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = LittleLemonHero),
                shape = RoundedCornerShape(1.dp)
            ) {
                Text(
                    text = "Let's get to know you",
                    fontSize = 25.sp,
                    modifier= Modifier
                        .padding(top = 40.dp, bottom = 40.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Card(colors =CardDefaults.cardColors(containerColor = Color.White),shape= RoundedCornerShape(1.dp)) {
                Text(
                    text = "Personal Information",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 60.dp, bottom = 40.dp, start = 10.dp)
                )

                OutlinedTextField(
                    value = firstName,
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                    onValueChange = {
                        firstName = it
                    },
                    label = { Text(text = "First Name",color = Color.Black) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 30.dp, top = 20.dp)
                )

                OutlinedTextField(
                    value = lastName,
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                    onValueChange = {
                        lastName = it
                    },
                    label = { Text(text = "Last Name",color = Color.Black) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 30.dp)
                )

                OutlinedTextField(
                    value = email,
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                    onValueChange = {
                        email = it
                    },
                    label = { Text(text = "Email",color = Color.Black) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 30.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonYellow),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 30.dp)
                    ) {
                        Text(text = "Register", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun Header(){
    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(contentColor = Color.White, containerColor = Color.White),
        shape = RoundedCornerShape(1.dp)
    ) {
        Column(
            modifier=Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription ="logo.png",
                modifier=Modifier.size(width = 200.dp, height = 80.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview(){
 LittleLemonTheme {
     OnBoarding()
 }
}