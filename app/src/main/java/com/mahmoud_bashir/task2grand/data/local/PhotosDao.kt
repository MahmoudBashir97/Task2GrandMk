package com.mahmoud_bashir.task2grand.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahmoud_bashir.task2grand.data.local.model.PhotosModel

@Dao
interface PhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photo:List<PhotosModel>)

    @Query("SELECT * FROM photos_table")
    suspend fun getAllUsers(): List<PhotosModel>

    @Query("SELECT * FROM photos_table where title LIKE :searchQ")
    suspend fun searchOnPhotos(searchQ:String):List<PhotosModel>
}