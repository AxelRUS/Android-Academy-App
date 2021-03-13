package com.team23.androidacademyapp.data.di

import com.team23.androidacademyapp.data.LectionsRepository
import com.team23.androidacademyapp.domain.repo.LectionsRepo
import org.koin.dsl.module

val data = module {
    factory<LectionsRepo> { LectionsRepository(get()) }
}
