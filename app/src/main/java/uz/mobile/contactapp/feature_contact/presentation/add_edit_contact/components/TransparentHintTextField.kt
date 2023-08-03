package uz.mobile.contactapp.feature_contact.presentation.add_edit_contact.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import uz.mobile.contactapp.feature_contact.presentation.add_edit_contact.AddEditContactEvent
import uz.mobile.contactapp.ui.theme.ContactAppTheme

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
    Box(modifier = modifier) {

        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) }
        )

        if (isHintVisible) {
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactAppTheme {
        TransparentHintTextField(
            text = "phoneNumberState.text",
            hint = "phoneNumberState.hint",
            onValueChange = {

            },
            onFocusChange = {

            },
            isHintVisible = false,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge
        )
    }
}