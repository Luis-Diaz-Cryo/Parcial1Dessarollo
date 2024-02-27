package com.example.parcial1.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parcial1.viewmodels.ContactDetailViewModel
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.parcial1.viewmodels.Contact
import com.example.parcial1.viewmodels.ContactEditViewModel


@Composable
fun ContactDetails(
    viewModel: ContactDetailViewModel,
    navController: NavController,
    contactEditViewModel: ContactEditViewModel,
    contact: Contact
) {

    Log.d("ContactDetails", "Contact: $contact")
    val selectedContact by viewModel.selectedContact.observeAsState(contact)

    selectedContact.let { observedContact ->
        ContactDetailsContent(observedContact, navController, contactEditViewModel)
        BackButton(navController)
    }
}




@Composable
fun ContactDetailsContent(
    contact: Contact,
    navController: NavController,
    contactEditViewModel: ContactEditViewModel?
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Name: ${contact.name}")
        Text(text = "Phone Number: ${contact.phoneNumber}")
        Text(text = "Email Address: ${contact.emailAddress}")

        EditContactButton(navController, contact, contactEditViewModel)
    }
}

@Composable
fun EditContactButton(navController: NavController, contact: Contact, contactEditViewModel: ContactEditViewModel?) {
    Button(
        onClick = {
            navController.navigate("EditContact/${contact.name}")
            contactEditViewModel?.updateEditedContact(contact)
        },
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text("Edit Contact")
    }
}




@Composable
fun BackButton(navController: NavController) {
    Button(
        onClick = { navController.popBackStack() },
        modifier = Modifier
                  .padding(8.dp)
    ) {
        Text("Back")
    }
}



