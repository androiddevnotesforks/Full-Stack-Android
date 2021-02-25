package com.nexters.fullstack.local.image

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils

internal class LocalImagesImpl(private val context: Context) : LocalImages {
    override fun fetch(filterValue: String): ArrayList<String> {
        val result = arrayListOf<String>()

        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME)

        val cursor: Cursor? = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            MediaStore.MediaColumns.DATE_ADDED + " desc"
        )

        val columnIndex: Int = cursor?.getColumnIndexOrThrow("_data") ?: 0

        while (cursor?.moveToNext() == true) {
            val absolutePathOfImage: String = cursor.getString(columnIndex)


            val isLocalScreenShot = absolutePathOfImage.contains(filterValue)

            if (!TextUtils.isEmpty(absolutePathOfImage) && isLocalScreenShot) {
                result.add(absolutePathOfImage)
            }
        }
        return result
    }
}