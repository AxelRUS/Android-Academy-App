package com.team23.androidacademyapp.domain.di

import com.team23.androidacademyapp.domain.interactor.GetYoutubeLinkInteractor
import com.team23.androidacademyapp.domain.usecase.GetYoutubeLinkUseCase
import org.koin.dsl.module

val domain = module {
    factory<GetYoutubeLinkUseCase> { GetYoutubeLinkInteractor(get()) }
}