package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.mappers

import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.AppUserEntity
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.TagEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppUserMapper @Inject constructor() {

    fun fromListOfAppUserToListEntityAppUser(listOfAppUser: List<AppUser>): List<AppUserEntity> {
        return listOfAppUser.map {
            AppUserEntity(
                it.name,
                it.username,
                it.amountOfPosts
            )
        }
    }

    fun fromListOfEntityAppUserToListAppUser(listOfAppUserEntity: List<AppUserEntity>): List<AppUser> {
        return listOfAppUserEntity.map {
            AppUser(
                it.name,
                it.username,
                it.amountOfPosts
            )
        }
    }
}