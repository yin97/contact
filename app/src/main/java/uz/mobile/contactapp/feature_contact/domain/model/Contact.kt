package uz.mobile.contactapp.feature_contact.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.mobile.contactapp.ui.theme.BabyBlue
import uz.mobile.contactapp.ui.theme.LightGreen
import uz.mobile.contactapp.ui.theme.RedOrange
import uz.mobile.contactapp.ui.theme.RedPink
import uz.mobile.contactapp.ui.theme.Violet

@Entity
data class Contact(
    val name: String,
    val phoneNumber: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val contactColors = listOf(LightGreen, Violet, RedOrange, RedPink, BabyBlue)
    }
}

class InvalidContactException(message: String) : Exception(message)
