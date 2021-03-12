package com.team23.androidacademyapp.data

import com.team23.androidacademyapp.domain.repo.LectionsRepo

class LectionsRepository(private val remoteDataSource: LectionsRemoteDataSource): LectionsRepo {
    override suspend fun getYoutubeLink() {
        remoteDataSource.getYoutubeLink()
    }
}