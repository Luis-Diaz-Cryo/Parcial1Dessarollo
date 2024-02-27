package com.example.parcial1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parcial1.viewmodels.Contact

class ContactRepository {
    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> get() = _contacts

    fun updateContact(newContact: List<Contact>) {
        _contacts.value = newContact
    }

    fun addContact(newContact: Contact) {
        val currentContacts = _contacts.value.orEmpty().toMutableList()
        currentContacts.add(newContact)
        _contacts.value = currentContacts
    }

    fun getContactByName(contactName: String): Contact? {
        return _contacts.value?.find { it.name == contactName }
    }
}
