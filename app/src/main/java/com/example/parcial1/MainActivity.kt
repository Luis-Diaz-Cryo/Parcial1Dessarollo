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
    import  com.example.parcial1.views.AddContact
    import  com.example.parcial1.views.ContactDetails
    import  com.example.parcial1.views.ContactList
    import  com.example.parcial1.views.EditContactDetails

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
                        composable(NavDestinations.ContactDetails.route) { backStackEntry ->
                            val contactName = backStackEntry.arguments?.getString("contactName")
                            val contact = contactName?.let { contactRepository.getContactByName(it) }

                            if (contact != null) {
                                ContactDetails(
                                    viewModel = ContactDetailViewModel(contactRepository),
                                    navController = navController,
                                    contactEditViewModel = ContactEditViewModel(contactRepository),
                                    contact = contact
                                )
                            }
                        }

                        composable(NavDestinations.EditContact.route) { backStackEntry ->
                            val contactName = backStackEntry.arguments?.getString("contactName")
                            val contact = contactName?.let { contactRepository.getContactByName(it) }
                            if (contact != null) {
                                EditContactDetails(
                                    viewModel = ContactEditViewModel(contactRepository),
                                    navController = navController,
                                    contact = contact
                                )
                            }
                        }

                        composable(NavDestinations.AddContact.route) {
                            AddContact(
                                viewModel = ContactListViewModel(contactRepository),
                                navController = navController
                            )
                        }
                    }

                }
            }
        }
    }

