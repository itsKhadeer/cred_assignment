package com.example.cred_assignment.core.di

import com.example.cred_assignment.data.datasource.api.CredApiDataSource
import com.example.cred_assignment.data.repository.CredApiDataSourceImpl
import com.example.cred_assignment.data.repository.CredRepositoryImpl
import com.example.cred_assignment.domain.repository.CredRepository
import com.example.cred_assignment.presentation.CredViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val credModule =
    module {

        single<CredApiDataSource> { CredApiDataSourceImpl() }

        single {Dispatchers.IO}

        single<CredRepository> { CredRepositoryImpl(credApiDataSource = get(), dispatcher = get()) }

        viewModel { CredViewModel(repository = get()) }
    }
