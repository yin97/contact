package uz.mobile.contactapp.feature_contact.domain.use_case

import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.domain.repository.ContactRepository

class DeleteContactUseCase(
    private val repository: ContactRepository
) {
    suspend operator fun invoke(contact: Contact) {
        repository.deleteContact(contact)
    }
}