package com.example.parcial1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial1.data.ContactRepository

class ContactEditViewModel(contactRepository: ContactRepository) : ViewModel() {
    private val _editedContact = MutableLiveData<Contact>()
    val editedContact: LiveData<Contact> get() = _editedContact

    fun updateEditedContact(contact: Contact) {
        _editedContact.value = contact
    }


    fun getCurrentEditedContact(): LiveData<Contact> {
        return editedContact
    }

}
