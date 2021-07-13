package com.ksu.exercise3.di

import com.ksu.exercise3.domain.LoadNewsRepositoryImp
import org.koin.dsl.module

val repoModule = module {
    single { LoadNewsRepositoryImp(get()) }
}