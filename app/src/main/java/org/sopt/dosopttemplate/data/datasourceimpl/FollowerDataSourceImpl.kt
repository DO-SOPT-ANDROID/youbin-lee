package org.sopt.dosopttemplate.data.datasourceimpl

import org.sopt.dosopttemplate.data.datasource.FollowerDataSource
import org.sopt.dosopttemplate.data.model.response.FollowerResponseDto
import org.sopt.dosopttemplate.data.service.FollowerService
import org.sopt.dosopttemplate.domain.entity.FollowerEntity
import org.sopt.dosopttemplate.domain.repository.FollowerRepository
import javax.inject.Inject

class FollowerDataSourceImpl @Inject constructor(
    private val followerService: FollowerService
) : FollowerDataSource {
    override suspend fun getFollower(page: Int): FollowerResponseDto =
        followerService.getFollowerListFromServer(page)
}
