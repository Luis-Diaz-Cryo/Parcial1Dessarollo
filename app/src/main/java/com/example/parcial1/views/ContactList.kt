package com.example.parcial1.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial1.viewmodels.ContactDetailViewModel
import com.example.parcial1.viewmodels.ContactListViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.parcial1.navigation.NavDestinations
import com.example.parcial1.viewmodels.Contact


@Composable
fun ContactList(
    viewModel: ContactListViewModel,
    navController: NavController,
    contactDetailsViewModel: ContactDetailViewModel
) {
    val contacts by viewModel.contacts.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ContactListHeader()
            AddContactButton(navController)
        }

        Spacer(modifier = Modifier.height(16.dp))


        ContactListItems(contacts, navController, contactDetailsViewModel)
    }
}

@Composable
fun ContactListHeader() {
    Text(text = "Contact list", fontSize = 20.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun AddContactButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate(NavDestinations.AddContact.route)
        }
    ) {
        Text("Add Contact space")
    }
}

@Composable
fun ContactListItems(
    contacts: List<Contact>,
    navController: NavController,
    contactDetailsViewModel: ContactDetailViewModel
) {
    LazyColumn {
        items(contacts) { contact ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("${NavDestinations.ContactDetails.route}?contactName=${contact.name}")
                        contactDetailsViewModel.updateSelectedContact(contact)
                    }
                    .padding(8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Name: ${contact.name}")
                    Text(text = "Phone Number: ${contact.phoneNumber}")
                }
            }
        }
    }
}

