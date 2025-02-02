package com.example.cred_assignment

import android.app.Application
import com.example.cred_assignment.core.di.credModule
import org.koin.core.context.startKoin

class CredApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(credModule)
        }
    }
}