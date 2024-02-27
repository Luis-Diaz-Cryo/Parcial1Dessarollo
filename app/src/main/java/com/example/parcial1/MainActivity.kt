package com.example.parcial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.parcial1.ui.theme.Parcial1Theme
import androidx.navigation.compose.*
import com.example.parcial1.navigation.NavDestinations
import com.example.parcial1.viewmodels.ContactDetailViewModel
import com.example.parcial1.viewmodels.ContactEditViewModel
import com.example.parcial1.viewmodels.ContactListViewModel
import com.example.parcial1.navigation.NavManagement
import com.example.parcial1.data.ContactRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial1Theme {
                val navController = rememberNavController()


                val contactRepository = ContactRepository()

                NavHost(
                    navController = navController,
                    startDestination = NavDestinations.ContactList.route
                ) {
                    composable(NavDestinations.ContactList.route) {
                        NavManagement(
                            contactListViewModel = ContactListViewModel(contactRepository),
                            contactDetailsViewModel = ContactDetailViewModel(contactRepository),
                            contactEditViewModel = ContactEditViewModel(contactRepository),
                            contactRepository = contactRepository
                        )
                    }
                }
            }
        }
    }
}

