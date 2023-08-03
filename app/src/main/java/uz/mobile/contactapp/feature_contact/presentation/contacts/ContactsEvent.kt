package uz.mobile.contactapp.feature_contact.presentation.contacts

import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.domain.util.ContactOrder

sealed class ContactsEvent {
    data class Order(val contactOrder: ContactOrder) : ContactsEvent()
    data class DeleteContact(val contact: Contact) : ContactsEvent()
    object RestoreContact : ContactsEvent()
    object ToggleOrderSection : ContactsEvent()
}