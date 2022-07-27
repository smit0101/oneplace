package com.smitmark.oneplace.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.smitmark.oneplace.client.client
import com.smitmark.oneplace.model.WebSite
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class WebSiteViewModel: ViewModel(){
    var webSites:SnapshotStateList<WebSite> = mutableStateListOf()

    init {
        load()
    }

    fun load()= runBlocking {
        async {
            val result:MutableList<WebSite> = client.get("https://oneplaceupdated-pgokju5beq-el.a.run.app/websites") {

            }.body()
            webSites.addAll(result)
        }
    }
    fun addWebSite(webSite:WebSite) {
        runBlocking {
            async {
                val result:WebSite = client.post("https://oneplaceupdated-pgokju5beq-el.a.run.app/add/websites") {
                    contentType(ContentType.Application.Json)
                    setBody(webSite)
                }.body()
                webSites.add(result)
            }
        }
    }
    fun removeWebSite(title:String,index:Int){
        runBlocking {
            async {
                val result:WebSite  =
                    client.delete("https://oneplaceupdated-pgokju5beq-el.a.run.app/remove/website/title?") {
                        parameter(key = "title", value = title)
                    }.body()

                webSites.removeAt(index)

            }
        }
    }
}