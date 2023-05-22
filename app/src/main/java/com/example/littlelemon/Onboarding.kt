package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonHero
import com.example.littlelemon.ui.theme.LittleLemonYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoarding(
    edit: SharedPreferences.Editor,
    navController: NavHostController,
    applicationContext: Context
) {

val focusManager= LocalFocusManager.current
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
                LazyColumn() {
                    item {
                        OutlinedTextField(
                            value = firstName,
                            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                            onValueChange = {
                                firstName = it
                            },
                            label = { Text(text = "First Name", color = Color.Black) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, bottom = 30.dp, top = 20.dp),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            )
                        )

                        OutlinedTextField(
                            value = lastName,
                            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                            onValueChange = {
                                lastName = it
                            },
                            label = { Text(text = "Last Name", color = Color.Black) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                        )

                        OutlinedTextField(
                            value = email,
                            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                            onValueChange = {
                                email = it
                            },
                            label = { Text(text = "Email", color = Color.Black) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            if(!(firstName.isBlank()||lastName.isBlank()||email.isBlank())) {
                                edit.putString("firstName", firstName)
                                    .putString("lastName", lastName)
                                    .putString("email", email)
                                    .commit()
                                Toast.makeText(applicationContext,"Successfully Registered!",Toast.LENGTH_LONG).show()

                                navController.navigate(Home.route){
                                    popUpTo(navController.graph.id){
                                        inclusive=true
                                    }
                                }

                            }
                            else
                                Toast.makeText(applicationContext,"Registration Unsuccessful,Please enter all data",Toast.LENGTH_LONG).show()
                        },
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

