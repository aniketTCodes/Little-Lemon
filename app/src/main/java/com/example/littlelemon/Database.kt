package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.serialization.SerialName

@Entity
class MenuItem(
    @PrimaryKey val id:Int,
    val title:String,
    val description:String,
    val price:String,
    val image:String,
    val category:String
)

@Dao
interface MenuItemDao{
    @Query("Select * from MenuItem")
    fun getAll():LiveData<List<MenuItem>>

    @Insert
    fun insertAll(vararg menuItems:MenuItem)

    @Query("Select (Select Count(*) from MenuItem) == 0 ")
    fun isEmpty():Boolean

}

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDataBase:RoomDatabase(){
    abstract fun menuItemDao():MenuItemDao
}