package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.FollowerDataSource
import org.sopt.dosopttemplate.data.datasourceimpl.FollowerDataSourceImpl
import org.sopt.dosopttemplate.data.repository.FollowerRepositoryImpl
import org.sopt.dosopttemplate.domain.repository.FollowerRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    @Singleton
    abstract fun bindFollowerDataSource(followerDataSourceImpl: FollowerDataSourceImpl): FollowerDataSource

    @Binds
    @Singleton
    abstract fun bindFollowerRepository(followerRepositoryImpl: FollowerRepositoryImpl): FollowerRepository

}