package com.ezlo.testtask.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = REPLACE)
    fun insert(entity: T): Long?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun tryInsert(entity: T): Long

    @Insert(onConflict = REPLACE)
    fun insert(entities: List<T>)

    @Update
    fun update(entity: T): Int

    @Update
    fun update(entities: List<T>)

    @Delete
    fun delete(entity: T): Int?

    @Delete
    fun delete(entities: List<T>)

}