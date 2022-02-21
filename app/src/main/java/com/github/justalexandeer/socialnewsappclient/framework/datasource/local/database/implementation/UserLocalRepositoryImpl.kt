package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.UserLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.AppUserDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.mappers.AppUserMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalRepositoryImpl @Inject constructor(
    private val appUserDao: AppUserDao,
    private val appUserMapper: AppUserMapper
): UserLocalRepository {
    override suspend fun getTopUsers(limit: Int): List<AppUser> {
        return appUserMapper.fromListOfEntityAppUserToListAppUser(appUserDao.getTopAppUserEntity(limit))
    }

    override suspend fun saveUsers(listOfAppUser: List<AppUser>) {
        appUserDao.saveListOfAppUserEntity(appUserMapper.fromListOfAppUserToListEntityAppUser(listOfAppUser))
    }

}