package com.ksu.exercise3.di

import com.ksu.exercise3.domain.GetNewsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetNewsUseCase(get()) }
}