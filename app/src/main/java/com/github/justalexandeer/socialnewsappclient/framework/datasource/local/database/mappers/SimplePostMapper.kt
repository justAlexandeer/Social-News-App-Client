package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.mappers

import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.SimplePostEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimplePostMapper @Inject constructor() {
    fun fromListOfSimplePostToListOfSimplePostEntity(listOfSimplePost: List<SimplePost>): List<SimplePostEntity> {
        return listOfSimplePost.map {
            val arrayListOfTag = ArrayList<Tag>()
            arrayListOfTag.addAll(it.tags)
            SimplePostEntity(
                it.id,
                it.name,
                it.date,
                it.appUser,
                it.category,
                arrayListOfTag,
                it.content,
                it.commentCount
            )
        }
    }

    fun fromListOfSimplePostEntityToListOfPost(listOfSimplePostEntity: List<SimplePostEntity>): List<SimplePost> {
        return listOfSimplePostEntity.map {
            val listOfTag = mutableListOf<Tag>()
            listOfTag.addAll(it.tags)
            SimplePost(
                it.id,
                it.name,
                it.date,
                it.appUser,
                it.category,
                listOfTag,
                it.content,
                it.commentCount
            )
        }
    }
}