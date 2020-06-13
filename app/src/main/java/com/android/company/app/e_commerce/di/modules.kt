package com.android.company.app.e_commerce.di

import com.android.company.app.e_commerce.data.local.AppDatabase
import com.android.company.app.e_commerce.data.local.DatabaseHelper
import com.android.company.app.e_commerce.data.local.DatabaseHelperImpl
import com.android.company.app.e_commerce.data.remote.Network
import com.android.company.app.e_commerce.data.remote.RemoteDataSource
import com.android.company.app.e_commerce.repositories.Repo
import org.koin.core.module.Module
import org.koin.dsl.module
import sa.amaz.drovex.captain.repositories.IRepo


private val network =  module {
    factory { Network.apiService }
}

private val remoteModule = module { factory { RemoteDataSource(get()) } }
//private val localModule = module { factory { AppDatabase() } }

private val remoteRepositoryModule = module { single<IRepo> { Repo(get() , get()) } }
private val localRepositoryModule = module { single<DatabaseHelper> { DatabaseHelperImpl(get()) } }


private val viewModelModule = module {

//    viewModel { HomeViewModel() }

}

fun getModules() : Array<Module>{

    return  arrayOf(
        network,
        remoteModule,
        remoteRepositoryModule,
        viewModelModule
    )
}