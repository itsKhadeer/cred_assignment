package com.example.cred_assignment.domain.repository

import com.example.cred_assignment.data.datasource.NetworkResult
import com.example.cred_assignment.domain.models.ContentModel

interface CredRepository {
    suspend fun getCredContent(): NetworkResult<ContentModel>
}