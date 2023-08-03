package uz.mobile.contactapp.feature_contact.presentation.contacts

import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.domain.util.ContactOrder
import uz.mobile.contactapp.feature_contact.domain.util.OrderType

data class ContactsState(
    val contacts: List<Contact> = emptyList(),
    val contactOrder: ContactOrder = ContactOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)