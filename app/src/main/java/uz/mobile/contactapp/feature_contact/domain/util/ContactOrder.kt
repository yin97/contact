package uz.mobile.contactapp.feature_contact.domain.util

import android.icu.text.CaseMap.Title

sealed class ContactOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : ContactOrder(orderType)
    class Date(orderType: OrderType) : ContactOrder(orderType)
    class Color(orderType: OrderType) : ContactOrder(orderType)

    fun copy(orderType: OrderType): ContactOrder {
        return when (this) {
            is Name -> Name(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
