package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {getSharedPreferences("LittleLemon", MODE_PRIVATE)}
    private val client= HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("text","plain"))
        }
    }

    private val database by lazy { Room.databaseBuilder(applicationContext,MenuDataBase::class.java,"database").build()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LittleLemonTheme {
                val navController= rememberNavController()
                NavigationComposable(navController = navController, sharedPreferences = sharedPreferences ,applicationContext)
            }
        }

        lifecycleScope.launch(Dispatchers.IO){
            if(database.menuItemDao().isEmpty()){
                NetworkMenuItemsToDatabase(fetchMenu())
            }
        }
    }


    private suspend fun fetchMenu(): MenuNetworkData {
        return client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()
    }

    private suspend fun NetworkMenuItemsToDatabase(menuNetworkData: MenuNetworkData){
        val menuItems:List<MenuItem> = menuNetworkData.menuItems.map { it.toMenuItems() }
        menuItems.forEach {
            database.menuItemDao().insertAll(it)
        }

    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}