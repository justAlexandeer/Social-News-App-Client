package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.convertor

import androidx.room.TypeConverter
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun listToJson(value: ArrayList<Tag>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String):ArrayList<Tag> {
        val listType = object: TypeToken<ArrayList<Tag>>(){}.type
        return Gson().fromJson(value, listType)
    }
}

