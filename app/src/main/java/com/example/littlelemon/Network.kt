package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id:Int,
    @SerialName("title")
    val title:String,
    @SerialName("description")
    val description:String,
    @SerialName("price")
    val price:String,
    @SerialName("image")
    val image:String,
    @SerialName("category")
    val category:String
){
    fun toMenuItems()=MenuItem(id,title,description,price,image,category)
}

@Serializable
class MenuNetworkData(
    @SerialName("menu")
    val menuItems:List<MenuItemNetwork>
)

