package com.myphka.phka.di

import com.myphka.phka.repositories.ICartRepository
import com.myphka.phka.repositories.IOrderRepository
import com.myphka.phka.repositories.ISearchRepository
import com.myphka.phka.repositories.IUserRepository
import com.myphka.phka.repositories.impl.MockCartRepository
import com.myphka.phka.repositories.impl.MockOrderRepository
import com.myphka.phka.repositories.impl.MockSearchRepository
import com.myphka.phka.repositories.impl.MockUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepository: MockCartRepository
    ): ICartRepository

    @Binds
    @Singleton
    abstract fun bindOrderRepository(
        orderRepository: MockOrderRepository
    ): IOrderRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        searchRepository: MockSearchRepository
    ): ISearchRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepository: MockUserRepository
    ): IUserRepository
}
