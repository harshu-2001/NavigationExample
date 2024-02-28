package com.onedeveloper.navigationexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.onedeveloper.navigationexample.ui.theme.NavigationExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    //Navigation Framework

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        //Notes
        composable(route = "registration") {
            RegistrationScreen(navController)
        }
        //2nd Approch to navigate
        composable(route = "login") {
            LoginScreen {
                navController.navigate("main/${it}")
            }
        }
        //Pass the arguments between the screens and navigate to that.
        composable(route = "main/{email}",arguments= listOf(
            navArgument("email"){
                type = NavType.StringType   //to define the argument dataType and name property helps to identify the value
            }
        )) {
            val email = it.arguments!!.getString("email").toString()   //to extract the argument value
            MainScreen(email)
        }

    }

}

@Composable
fun RegistrationScreen(navController: NavController) {
    Text(text = "Registration Screen", modifier = Modifier.clickable {
        navController.navigate("main")
    }, style = MaterialTheme.typography.headlineMedium)
}


@Composable
fun LoginScreen(onClick: (email:String)->Unit) {
    Text(
        text = "Login Screen",
        modifier = Modifier.clickable { onClick("hello@gmail.com") },
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun MainScreen(email: String) {
    Text(text = "Main Screen \n ${email}", style = MaterialTheme.typography.headlineMedium)
}