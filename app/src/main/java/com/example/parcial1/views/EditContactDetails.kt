package com.example.parcial1.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial1.viewmodels.Contact
import com.example.parcial1.viewmodels.ContactEditViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditContactDetails(
    viewModel: ContactEditViewModel,
    navController: NavController,
    contact: Contact
) {
    val editedContact by viewModel.editedContact.observeAsState(contact.copy())
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = editedContact.name,
            onValueChange = { newName -> viewModel.updateEditedContact(editedContact.copy(name = newName)) },
            label = { Text("Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = editedContact.phoneNumber,
            onValueChange = { newPhoneNumber -> viewModel.updateEditedContact(editedContact.copy(phoneNumber = newPhoneNumber)) },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = editedContact.emailAddress,
            onValueChange = { newEmailAddress -> viewModel.updateEditedContact(editedContact.copy(emailAddress = newEmailAddress)) },
            label = { Text("Email Address") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                viewModel.updateEditedContact(editedContact)
                keyboardController?.hide()
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text("Done")
        }
    }
}
