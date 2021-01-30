package com.nexters.data.repository

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.text.TextUtils
import android.util.Log
import com.nexters.data.mapper.MapperImpl
import com.nexters.data.source.LocalLabelData
import com.nexters.fullstack.repository.AlbumRepository
import com.nexters.fullstack.source.data.LocalLabelDomain
import kotlin.collections.ArrayList


internal class AlbumRepositoryImpl(private val context: Context) : AlbumRepository {

    override fun getUnLabeling(pathFilter: String): List<LocalLabelDomain> {
        return getPathOfAllImages().map { filePath ->
            MapperImpl.fromDomain(
                LocalLabelData(
                    id = filePath.length.toLong(),
                    originUrl = filePath
                )
            )
        }
    }

    private fun getPathOfAllImages(): ArrayList<String> {
        val result: ArrayList<String> = ArrayList()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaColumns.DATA, MediaColumns.DISPLAY_NAME)
        val cursor: Cursor? = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            MediaColumns.DATE_ADDED + " desc"
        )

        val columnIndex: Int = cursor?.getColumnIndexOrThrow("_data") ?: 0
        val columnDisplayname: Int = cursor?.getColumnIndexOrThrow(MediaColumns.DISPLAY_NAME) ?: 0
//        var lastIndex: Int
        while (cursor?.moveToNext() == true) {
            val absolutePathOfImage: String = cursor.getString(columnIndex)

            val nameOfFile: String = cursor.getString(columnDisplayname)
//            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile)
//            lastIndex = if (lastIndex >= 0) lastIndex else nameOfFile.length - 1
            if (!TextUtils.isEmpty(absolutePathOfImage) && absolutePathOfImage.contains(
                    SCREENSHOT_POSTFIX
                )
            ) {
                result.add(absolutePathOfImage)
            }
        }
        for (string in result) {
            Log.i("getPathOfAllImages", "|$string|")
        }
        return result
    }

    companion object {
        private const val SCREENSHOT_POSTFIX = "Screenshots"
    }
}