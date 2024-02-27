package com.example.parcial1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial1.data.ContactRepository

class ContactListViewModel(private val contactRepository: ContactRepository) : ViewModel() {
    val contacts: LiveData<List<Contact>> get() = contactRepository.contacts

    fun updateContact(newContact: List<Contact>) {
        contactRepository.updateContact(newContact)
    }

    fun addContact(newContact: Contact) {
        contactRepository.addContact(newContact)
    }
}
