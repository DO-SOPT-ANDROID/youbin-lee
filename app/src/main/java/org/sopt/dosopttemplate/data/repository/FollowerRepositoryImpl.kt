package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.FollowerDataSource
import org.sopt.dosopttemplate.domain.entity.FollowerEntity
import org.sopt.dosopttemplate.domain.repository.FollowerRepository
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(private val followerDataSource: FollowerDataSource)
    : FollowerRepository{
    override suspend fun getFollowerList(page: Int) : Result<List<FollowerEntity>> =
        runCatching {
            followerDataSource.getFollower(page).toFollowerEntity()
        }
}