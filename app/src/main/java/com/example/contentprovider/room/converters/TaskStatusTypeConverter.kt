package com.example.contentprovider.room.converters

import androidx.room.TypeConverter

object TaskStatusTypeConverter {

    @TypeConverter
    @JvmStatic
    fun taskStatusToInt(enum: TaskStatusEnum): Int = enum.ordinal

    @TypeConverter
    @JvmStatic
    fun intToTaskStatus(i: Int): TaskStatusEnum = TaskStatusEnum.values()[i]

}