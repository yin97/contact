package uz.mobile.contactapp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import uz.mobile.contactapp.feature_contact.domain.model.Contact
import uz.mobile.contactapp.feature_contact.presentation.contacts.components.ContactItem
import uz.mobile.contactapp.ui.theme.ContactAppTheme

class MyComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        composeTestRule.setContent {

            ContactAppTheme {
                ContactItem(
                    Contact(
                        name = "Jone Done", phoneNumber = "+998903212299", 1500L,
                        Color.DarkGray.toArgb(), 1
                    ),
                    onDeleteClick = {

                    },
                    onUpdateClick = {

                    }
                )
            }
        }
    }
}