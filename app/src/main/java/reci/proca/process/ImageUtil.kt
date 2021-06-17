package reci.proca.process

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils


// https://stackoverflow.com/questions/2577221/android-how-to-create-runtime-thumbnail

fun createThumbnail(bitmap: Bitmap): Bitmap {
    return ThumbnailUtils.extractThumbnail(bitmap, 0, 0)!!
}

fun createThumbnail(fileName: String) =
    createThumbnail(BitmapFactory.decodeFile(fileName))