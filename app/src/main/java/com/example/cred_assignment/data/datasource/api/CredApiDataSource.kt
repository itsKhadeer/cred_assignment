package com.example.cred_assignment.data.datasource.api

import com.example.cred_assignment.data.datasource.api.entity.Content


interface CredApiDataSource {
    suspend fun getCredContent(): Content
}