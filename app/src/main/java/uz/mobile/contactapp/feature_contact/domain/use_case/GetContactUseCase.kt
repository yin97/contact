package uz.mobile.contactapp.feature_contact.domain.use_case

import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.domain.repository.ContactRepository

class GetContactUseCase(
    private val repository: ContactRepository
) {

    suspend operator fun invoke(id:Int):Contact?{
        return repository.getContactById(id)
    }
}