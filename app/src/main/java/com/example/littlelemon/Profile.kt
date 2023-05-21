package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonHero
import com.example.littlelemon.ui.theme.LittleLemonYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    edit: SharedPreferences,
    navController: NavHostController,
) {


    val firstName=edit.getString("firstName","")
    val lastName=edit.getString("lastName","")
    val email=edit.getString("email","")


    Scaffold(topBar = { Header()}) {
        Column(
            modifier= Modifier.padding(it)
        ) {


            Card(colors = CardDefaults.cardColors(containerColor = Color.White),shape= RoundedCornerShape(1.dp)) {
                Text(
                    text = "Personal Information",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 60.dp, bottom = 40.dp, start = 10.dp)
                )

                OutlinedTextField(
                    readOnly = true,
                    value = firstName!!,
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                    onValueChange = {
                    },
                    label = { Text(text = "First Name",color = Color.Black) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 30.dp, top = 20.dp)
                )

                OutlinedTextField(
                    readOnly = true,
                    value = lastName!!,
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                    onValueChange = {

                    },
                    label = { Text(text = "Last Name",color = Color.Black) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 30.dp)
                )

                OutlinedTextField(
                    readOnly = true,
                    value = email!!,
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                    onValueChange = {

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
                        onClick = {
                           edit.edit().clear().commit()

                            navController.navigate(OnBoarding.route){
                                popUpTo(navController.graph.id){
                                    inclusive=true
                                }
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonYellow),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 30.dp)
                    ) {
                        Text(text = "Log Out", color = Color.Black)
                    }
                }
            }
        }
    }
}

