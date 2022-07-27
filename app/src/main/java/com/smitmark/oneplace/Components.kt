package com.smitmark.oneplace.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState


@Composable
fun AddWebSite(url:String){
    val webView = rememberWebViewState(url = url)
    WebView(
        state = webView,
        onCreated = { it.settings.javaScriptEnabled = true }
    )
}
@Composable
fun CustomTextInput(title:String,modifier: Modifier,textState:String,onClick:()->Unit ,onValueChange:(String)->Unit){
    Column(modifier =  modifier
    ) {
        val maxLength = 2000
        val lightBlue = Color(0xffd8e6ff)
        val black = Color.Black
        Text(
            text = title,
            modifier = Modifier
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = black
        )
        TextField(
            value = textState,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = onValueChange,
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                if (textState.isNotEmpty()) {
                    IconButton(onClick = onClick) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        )
        Text(
            text = "${textState.length} / $maxLength",
            modifier = Modifier
                .padding(top = 4.dp),
            textAlign = TextAlign.End,
            color = black
        )
    }
}