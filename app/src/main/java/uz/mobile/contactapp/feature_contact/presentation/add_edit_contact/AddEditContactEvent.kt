package uz.mobile.contactapp.feature_contact.presentation.add_edit_contact

import androidx.compose.ui.focus.FocusState

sealed class AddEditContactEvent{
    data class EnteredName(val value:String):AddEditContactEvent()
    data class ChangeNameFocus(val focusState: FocusState):AddEditContactEvent()
    data class EnteredPhoneNumber(val value:String):AddEditContactEvent()
    data class ChangePhoneNumberFocus(val focusState: FocusState):AddEditContactEvent()
    data class ChangeColor(val color:Int):AddEditContactEvent()
    object SaveContact:AddEditContactEvent()
}
