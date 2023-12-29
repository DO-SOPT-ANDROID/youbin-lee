package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.FollowerEntity

interface FollowerRepository {
    suspend fun getFollowerList(page: Int): Result<List<FollowerEntity>>
}