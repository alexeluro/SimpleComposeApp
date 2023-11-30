package com.example.simplecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplecomposeapp.ui.theme.SimpleComposeAppTheme

class MainActivity : ComponentActivity() {


//    composable("contact_details_screen"
////                            arguments = listOf(
////                                navArgument("contact") { type = NavType.StringType }
////                            )
//    ) {
//        ContactsDetails()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleComposeAppTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "contact_list_screen"
                    ) {
                        composable("contact_list_screen") {
                            ContactScreen(navController)
                        }
                        composable("contact_details_screen") {
                            ContactsDetails(navController)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleComposeAppTheme {
    }
}