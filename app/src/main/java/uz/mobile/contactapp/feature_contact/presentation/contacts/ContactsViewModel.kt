package uz.mobile.contactapp.feature_contact.presentation.contacts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.domain.use_case.ContactUseCase
import uz.mobile.contactapp.feature_contact.domain.util.ContactOrder
import uz.mobile.contactapp.feature_contact.domain.util.OrderType
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactUseCase: ContactUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ContactsState())
    val state: State<ContactsState> = _state

    private var recentlyDeleteNote: Contact? = null

    private var getContactJob: Job? = null

    init {
        getContacts(ContactOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: ContactsEvent) {
        when (event) {
            is ContactsEvent.Order -> {
                if (state.value.contactOrder::class == event.contactOrder::class &&
                    state.value.contactOrder.orderType == event.contactOrder.orderType
                ) {
                    return
                }
                getContacts(event.contactOrder)
            }

            is ContactsEvent.DeleteContact -> {
                viewModelScope.launch {
                    contactUseCase.deleteContact(event.contact)
                    recentlyDeleteNote = event.contact
                }
            }

            is ContactsEvent.RestoreContact -> {
                viewModelScope.launch {
                    contactUseCase.addContact.invoke(recentlyDeleteNote ?: return@launch)
                    recentlyDeleteNote = null
                }
            }

            is ContactsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getContacts(contactOrder: ContactOrder) {
        getContactJob?.cancel()

        getContactJob = contactUseCase.getContacts(contactOrder)
            .onEach { contacts ->
                _state.value = state.value.copy(
                    contacts = contacts,
                    contactOrder = contactOrder
                )
            }
            .launchIn(viewModelScope)
    }


}