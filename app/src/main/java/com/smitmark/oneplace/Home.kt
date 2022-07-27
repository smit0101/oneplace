package com.smitmark.oneplace

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.smitmark.oneplace.model.WebSite
import com.smitmark.oneplace.ui.theme.CustomTextInput
import com.smitmark.oneplace.viewmodel.WebSiteViewModel

@Composable
fun Home(navController: NavHostController, webSiteViewModel: WebSiteViewModel) {

    MainBookMark(navController, webSiteViewModel)
}

@Composable
private fun MainBookMark(
    navController: NavHostController,
    webSiteViewModel: WebSiteViewModel
) {
    Column(modifier = Modifier.padding(5.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var title by rememberSaveable {
                mutableStateOf("")
            }
            CustomTextInput(
                title = "Add Title",
                textState = title,
                onClick = { title = "" },
                onValueChange = {
                    title = it
                },
                modifier = Modifier.weight(3f)
            )
            var url by rememberSaveable {
                mutableStateOf("")
            }
            CustomTextInput(
                title = "Add WebSite",
                textState = url,
                onClick = { url = "" },
                onValueChange = {
                    url = it
                },
                modifier = Modifier.weight(3f)
            )



            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
                    .clickable {
                        if (url.isEmpty() || title.isEmpty()) {
                            navController.previousBackStackEntry
                        } else {
                            webSiteViewModel.addWebSite(WebSite(title, url))
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "+", color = Color.White, fontSize = 30.sp)

            }


        }
        Spacer(modifier = Modifier.size(10.dp))
        val lazyListState = rememberLazyListState()
        LazyColumn(state = lazyListState) {

            itemsIndexed(webSiteViewModel.webSites) { index, item ->
                BookMark(navController, index, item, webSiteViewModel)
            }
        }


    }
}

@Composable
private fun BookMark(
    navController: NavHostController,
    index: Int,
    item: WebSite,
    webSiteViewModel: WebSiteViewModel
) {
    Row {
        Box(
            modifier = Modifier
                .weight(3f)
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black)
                .clickable {
                    navController.navigate("website/${index}")

                }, contentAlignment = Alignment.Center
        ) {
            Text(text = item.title, color = Color.White, fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black)
                .clickable {
                    webSiteViewModel.removeWebSite(title = item.title, index)
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = "-", color = Color.White, fontSize = 30.sp)
        }
    }
    Spacer(modifier = Modifier.size(10.dp))
}



