package com.example.parcial1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial1.data.ContactRepository

class ContactDetailViewModel(private val contactRepository: ContactRepository) : ViewModel() {
    private val _selectedContact = MutableLiveData<Contact>()
    val selectedContact: LiveData<Contact> get() = _selectedContact

    fun updateSelectedContact(contact: Contact) {
        _selectedContact.value = contact
    }
}
