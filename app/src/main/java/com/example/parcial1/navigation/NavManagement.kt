package com.example.parcial1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial1.data.ContactRepository
import com.example.parcial1.viewmodels.ContactDetailViewModel
import com.example.parcial1.viewmodels.ContactEditViewModel
import com.example.parcial1.viewmodels.ContactListViewModel
import com.example.parcial1.views.AddContact
import com.example.parcial1.views.ContactDetails
import com.example.parcial1.views.ContactList
import com.example.parcial1.views.EditContactDetails

@Composable
fun NavManagement(
    contactListViewModel: ContactListViewModel,
    contactDetailsViewModel: ContactDetailViewModel,
    contactEditViewModel: ContactEditViewModel,
    contactRepository: ContactRepository
) {
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavDestinations.ContactList.route) {
        composable(NavDestinations.ContactList.route) {
            ContactList(contactListViewModel, navController, contactDetailsViewModel)
        }
        composable(NavDestinations.ContactDetails.route+"/{contactName}") { backStackEntry ->
            val contactName = backStackEntry.arguments?.getString("contactName")
            requireNotNull(contactName,{""})

            val contact = contactRepository.getContactByName(contactName)

            if (contact != null) {
                ContactDetails(contactDetailsViewModel, navController, contactEditViewModel, contact)
            }
        }


        composable(NavDestinations.EditContact.route) { backStackEntry ->
            val contactName = backStackEntry.arguments?.getString("contactName")
            val contact = contactName?.let { contactRepository.getContactByName(it) }
            if (contact != null) {
                EditContactDetails(contactEditViewModel, navController, contact)
            }


       }
        composable(NavDestinations.AddContact.route) {
            AddContact(contactListViewModel, navController)
        }
    }
}


sealed class NavDestinations(val route: String) {
    object ContactList : NavDestinations("ContactList")
    object ContactDetails : NavDestinations("ContactDetails")
    object EditContact : NavDestinations("EditContact")
    object AddContact : NavDestinations("AddContact")
}
