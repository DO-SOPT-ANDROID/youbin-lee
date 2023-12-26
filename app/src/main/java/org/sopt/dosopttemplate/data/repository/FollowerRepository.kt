package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.model.response.FollowerResponseDto
import org.sopt.dosopttemplate.data.service.FollowerService

class FollowerRepository(private val followerService: FollowerService) {
    suspend fun getFollowerList(page: Int) =
        runCatching {
            followerService.getFollowerListFromServer(page)
        }
}