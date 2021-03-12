package com.team23.androidacademyapp.domain.interactor

import com.team23.androidacademyapp.domain.repo.LectionsRepo
import com.team23.androidacademyapp.domain.usecase.GetYoutubeLinkUseCase

class GetYoutubeLinkInteractor(
    private val lectionsRepo: LectionsRepo
): GetYoutubeLinkUseCase {
    override suspend fun execute() {
        lectionsRepo.getYoutubeLink()
    }
}