package uz.mobile.contactapp.feature_contact.presentation.add_edit_contact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.domain.model.InvalidContactException
import uz.mobile.contactapp.feature_contact.domain.use_case.ContactUseCase
import javax.inject.Inject

@HiltViewModel
class AddEditContactViewModel @Inject constructor(
    private val contactUseCase: ContactUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _contactName = mutableStateOf(
        ContactTextFieldState(
            hint = "Enter name..."
        )
    )
    val contactName: State<ContactTextFieldState> = _contactName

    private val _contactPhoneNumber = mutableStateOf(
        ContactTextFieldState(
            hint = "+xxx xx xxx xx xx"
        )
    )
    val contactPhoneNumber: State<ContactTextFieldState> = _contactPhoneNumber

    private val _contactColor = mutableStateOf(Contact.contactColors.random().toArgb())
    val contactColor: State<Int> = _contactColor

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentContactId: Int? = null

    init {
        savedStateHandle.get<Int>("contactId")?.let { contactId ->
            if (contactId != -1) {
                viewModelScope.launch {
                    contactUseCase.getContact(contactId)?.also { contact ->
                        currentContactId = contact.id
                        _contactName.value = contactName.value.copy(
                            text = contact.name,
                            isHintVisible = false
                        )
                        _contactPhoneNumber.value = contactPhoneNumber.value.copy(
                            text = contact.phoneNumber,
                            isHintVisible = false
                        )

                        _contactColor.value = contact.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditContactEvent) {
        when (event) {
            is AddEditContactEvent.EnteredName -> {
                _contactName.value = contactName.value.copy(
                    text = event.value
                )
            }

            is AddEditContactEvent.ChangeNameFocus -> {
                _contactName.value = contactName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            contactName.value.text.isBlank()
                )
            }

            is AddEditContactEvent.EnteredPhoneNumber -> {
                _contactPhoneNumber.value = _contactPhoneNumber.value.copy(
                    text = event.value
                )
            }

            is AddEditContactEvent.ChangePhoneNumberFocus -> {
                _contactPhoneNumber.value = _contactPhoneNumber.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            contactName.value.text.isBlank()
                )
            }

            is AddEditContactEvent.ChangeColor -> {
                _contactColor.value = event.color
            }

            is AddEditContactEvent.SaveContact -> {
                viewModelScope.launch {
                    try {
                        contactUseCase.addContact(
                            Contact(
                                name = contactName.value.text,
                                phoneNumber = contactPhoneNumber.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = contactColor.value,
                                id = currentContactId
                            )
                        )
                        _eventFlow.emit(UIEvent.SaveContact)
                    } catch (e: InvalidContactException) {
                        _eventFlow.emit(
                            UIEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save contact!"
                            )
                        )
                    }
                }
            }
        }
    }


}