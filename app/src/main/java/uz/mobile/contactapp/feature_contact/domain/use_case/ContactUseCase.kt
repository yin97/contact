package uz.mobile.contactapp.feature_contact.domain.use_case

data class ContactUseCase(
    val getContacts: GetContactsUseCase,
    val deleteContact: DeleteContactUseCase,
    val addContact:AddContactUseCase,
    val getContact:GetContactUseCase
)