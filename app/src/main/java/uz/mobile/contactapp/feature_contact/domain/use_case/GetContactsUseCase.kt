package uz.mobile.contactapp.feature_contact.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.domain.repository.ContactRepository
import uz.mobile.contactapp.feature_contact.domain.util.ContactOrder
import uz.mobile.contactapp.feature_contact.domain.util.OrderType

class GetContactsUseCase(
    private val repository: ContactRepository
) {

    operator fun invoke(
        contactOrder: ContactOrder = ContactOrder.Date(OrderType.Descending)
    ): Flow<List<Contact>> {
        return repository.getContacts().map { contacts ->
            when (contactOrder.orderType) {
                is OrderType.Ascending -> {
                    when (contactOrder) {
                        is ContactOrder.Name -> contacts.sortedBy { it.name.lowercase() }
                        is ContactOrder.Date -> contacts.sortedBy { it.timestamp }
                        is ContactOrder.Color -> contacts.sortedBy { it.color }
                    }
                }

                is OrderType.Descending -> {
                    when (contactOrder) {
                        is ContactOrder.Name -> contacts.sortedByDescending { it.name.lowercase() }
                        is ContactOrder.Date -> contacts.sortedByDescending { it.timestamp }
                        is ContactOrder.Color -> contacts.sortedByDescending { it.color }
                    }
                }
            }
        }
    }

}