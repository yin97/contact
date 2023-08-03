package uz.mobile.contactapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.mobile.contactapp.feature_contact.data.data_source.ContactDatabase
import uz.mobile.contactapp.feature_contact.data.repository.ContactRepositoryImpl
import uz.mobile.contactapp.feature_contact.domain.repository.ContactRepository
import uz.mobile.contactapp.feature_contact.domain.use_case.AddContactUseCase
import uz.mobile.contactapp.feature_contact.domain.use_case.ContactUseCase
import uz.mobile.contactapp.feature_contact.domain.use_case.DeleteContactUseCase
import uz.mobile.contactapp.feature_contact.domain.use_case.GetContactUseCase
import uz.mobile.contactapp.feature_contact.domain.use_case.GetContactsUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactDatabase(app: Application): ContactDatabase {
        return Room.databaseBuilder(
            app,
            ContactDatabase::class.java,
            ContactDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactRepository(db: ContactDatabase): ContactRepository {
        return ContactRepositoryImpl(db.contactDao)
    }

    @Provides
    @Singleton
    fun provideContactUseCases(repository: ContactRepository): ContactUseCase {
        return ContactUseCase(
            getContacts = GetContactsUseCase(repository),
            deleteContact = DeleteContactUseCase(repository),
            addContact = AddContactUseCase(repository),
            getContact = GetContactUseCase(repository)
        )
    }
}