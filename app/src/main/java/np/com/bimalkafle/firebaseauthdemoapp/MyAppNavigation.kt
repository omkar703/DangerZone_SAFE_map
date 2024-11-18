package np.com.bimalkafle.firebaseauthdemoapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import np.com.bimalkafle.firebaseauthdemoapp.pages.HomePage
import np.com.bimalkafle.firebaseauthdemoapp.pages.LoginPage
import np.com.bimalkafle.firebaseauthdemoapp.pages.SignupPage
import np.com.bimalkafle.firebaseauthdemoapp.pages.StartUp

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "startUp", builder = {
        composable("startUp"){
            StartUp(navController)
        }
        composable("login"){
            LoginPage(modifier,navController,authViewModel)
        }
        composable("signup"){
            SignupPage(modifier,navController,authViewModel)
        }
        composable("home"){
            HomePage(modifier,navController,authViewModel)
        }
    })
}