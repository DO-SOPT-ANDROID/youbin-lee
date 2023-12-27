package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.model.response.FollowerResponseDto

interface FollowerDataSource {
    suspend fun getFollower(page : Int) : FollowerResponseDto
}