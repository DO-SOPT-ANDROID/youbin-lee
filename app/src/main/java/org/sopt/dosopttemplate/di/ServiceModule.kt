package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.service.FollowerService
import org.sopt.dosopttemplate.di.qualifier.OPEN
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideFollowerService(@OPEN retrofit: Retrofit): FollowerService =
        retrofit.create(FollowerService::class.java)
}