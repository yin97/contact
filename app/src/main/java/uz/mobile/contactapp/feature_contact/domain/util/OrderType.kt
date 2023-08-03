package uz.mobile.contactapp.feature_contact.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
