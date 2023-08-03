package uz.mobile.contactapp.feature_contact.presentation.add_edit_contact

sealed class UIEvent {
    data class ShowSnackbar(val message: String) : UIEvent()
    object SaveContact : UIEvent()
}
