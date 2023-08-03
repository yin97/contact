package uz.mobile.contactapp.feature_contact.domain.use_case

import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.domain.model.InvalidContactException
import uz.mobile.contactapp.feature_contact.domain.repository.ContactRepository
import kotlin.jvm.Throws

class AddContactUseCase(
    private val repository: ContactRepository
) {

    @Throws(InvalidContactException::class)
    suspend operator fun invoke(contact: Contact) {
        if (contact.phoneNumber.isBlank()) {
            throw InvalidContactException("The phone number of the contact can't be empty.")
        }
        if (contact.name.isBlank()) {
            throw InvalidContactException("The name of the contact can't be empty")
        }
        repository.insertContact(contact)
    }
}