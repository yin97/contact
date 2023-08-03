package uz.mobile.contactapp.feature_contact.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.mobile.contactapp.feature_contact.domain.model.Contact

interface ContactRepository {

    fun getContacts(): Flow<List<Contact>>

    suspend fun getContactById(id:Int):Contact?

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)

}