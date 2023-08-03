package uz.mobile.contactapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import uz.mobile.contactapp.feature_contact.presentation.add_edit_contact.AddEditContactScreen
import uz.mobile.contactapp.feature_contact.presentation.contacts.ContactsScreen
import uz.mobile.contactapp.feature_contact.presentation.util.Screen
import uz.mobile.contactapp.ui.theme.ContactAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ContactAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.ContactsScreen.route
                    ) {
                        composable(route = Screen.ContactsScreen.route) {
                            ContactsScreen(navController = navController)
                        }
                        composable(
                            route = "${Screen.AddEditContactScreen.route}?contactId={contactId}&contactColor={contactColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "contactId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "contactColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("contactColor") ?: -1
                            AddEditContactScreen(
                                navController = navController,
                                contactColor = color
                            )

                        }
                    }
                }
            }
        }
    }
}




