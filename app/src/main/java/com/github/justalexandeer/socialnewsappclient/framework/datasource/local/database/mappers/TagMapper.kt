package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.mappers

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.TagEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TagMapper @Inject constructor() {

    fun fromListOfTagToListEntityTag(listOfTag: List<Tag>): List<TagEntity> {
        return listOfTag.map {
            TagEntity(
                it.id,
                it.name,
                it.amountOfUse
            )
        }
    }

    fun fromListOfEntityTagToListTag(listOfEntity: List<TagEntity>): List<Tag> {
        return listOfEntity.map {
            Tag(
                it.id,
                it.name,
                it.amountOfUse
            )
        }
    }
}