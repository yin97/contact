package uz.mobile.contactapp.feature_contact.data.repository

import kotlinx.coroutines.flow.Flow
import uz.mobile.contactapp.feature_contact.data.data_source.ContactDao
import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val contactDao: ContactDao
) : ContactRepository {
    override fun getContacts(): Flow<List<Contact>> {
        return contactDao.getContacts()
    }

    override suspend fun getContactById(id: Int): Contact? {
        return contactDao.getContactById(id)
    }

    override suspend fun insertContact(contact: Contact) {
        contactDao.insertContact(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact)
    }
}