package com.example.pointofsale.di

import com.example.pointofsale.presentation.navigation.Navigation
import com.example.pointofsale.presentation.navigation.NavigationImplementation
import org.koin.dsl.module
import java.util.Collections.singleton

val navigationModule =module{
    single<Navigation> { NavigationImplementation() }
}

val presentationModule = listOf(navigationModule)