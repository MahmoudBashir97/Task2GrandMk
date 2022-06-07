package com.mahmoud_bashir.task2grand.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahmoud_bashir.task2grand.data.local.model.PhotosModel

@Database(entities = [PhotosModel::class], version = 1, exportSchema = false)
abstract class PhotosDatabase : RoomDatabase(){
    abstract fun dao():PhotosDao

    companion object{
        val DATABASE_NAME: String = "photos_db"
    }
}