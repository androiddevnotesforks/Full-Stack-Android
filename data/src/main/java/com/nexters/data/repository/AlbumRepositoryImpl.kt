package com.nexters.data.repository

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import com.nexters.data.mapper.MapperImpl
import com.nexters.data.source.LocalLabelData
import com.nexters.fullstack.repository.AlbumRepository
import com.nexters.fullstack.source.data.LocalLabelDomain

internal class AlbumRepositoryImpl(private val context: Context) : AlbumRepository {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun getUnLabeling(pathFilter: String): List<LocalLabelDomain>? {
        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.listFiles()?.map {
            MapperImpl.fromDomain(LocalLabelData(id = it.freeSpace, originUrl = it.path))
        }
    }
}