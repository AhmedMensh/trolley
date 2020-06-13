package com.android.company.app.e_commerce.repositories

import android.content.Context
import com.android.company.app.e_commerce.data.remote.RemoteDataSource
import sa.amaz.drovex.captain.repositories.IRepo

class Repo(private val remoteDataSource: RemoteDataSource, private val context: Context) : IRepo {
}