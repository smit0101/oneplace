package com.smitmark.oneplace

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.smitmark.oneplace.ui.theme.AddWebSite
import com.smitmark.oneplace.viewmodel.WebSiteViewModel

@Composable
fun Website(navController: NavHostController,index:Int,webSiteViewModel: WebSiteViewModel){
    Column(modifier=Modifier.fillMaxSize()) {
        AddWebSite(url = webSiteViewModel.webSites[index].url)

    }
}

