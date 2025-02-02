package com.example.cred_assignment.data.repository

import com.example.cred_assignment.data.datasource.NetworkResult
import com.example.cred_assignment.data.datasource.api.CredApiDataSource
import com.example.cred_assignment.domain.models.ContentModel
import com.example.cred_assignment.domain.repository.CredRepository
import com.example.cred_assignment.domain.util.toContentModelDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CredRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val credApiDataSource: CredApiDataSource): CredRepository {
    override suspend fun getCredContent() : NetworkResult<ContentModel> {
        return withContext(dispatcher) {
            try {
                val response = credApiDataSource.getCredContent()
                NetworkResult.Success(response.toContentModelDomain())
            } catch (e: Exception) {
                NetworkResult.Error(e.message?: "Something went wrong, please try again later")
            }
        }
    }
}