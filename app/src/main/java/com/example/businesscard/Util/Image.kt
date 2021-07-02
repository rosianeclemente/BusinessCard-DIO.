package com.example.businesscard.Util

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.businesscard.R
import java.io.File
import java.io.OutputStream

class Image{
    companion object{
        fun share(context: Context, view: View){
            val bitmap = getScreenShotFromView(view)

            bitmap?.let {
                saveMediaToStorage(context, bitmap)
            }
        }
        private fun getScreenShotFromView(card: View): Bitmap? {
            var screenshot: Bitmap? = null
            try{
                screenshot= Bitmap.createBitmap(
                    card.measuredWidth,
                    card.measuredHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(screenshot)
                card.draw(canvas)
            }catch (e: Exception){
                Log.e("Error ->", "Falha ao capturar imagem" + e.message)
            }
            return screenshot
        }
        private fun saveMediaToStorage(context: Context, bitmap: Bitmap){
            val filename = "${System.currentTimeMillis()}.jpg"
            var fos: OutputStream? = null

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                context.contentResolver?.also { resolver ->
                    val contextValuess = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES)
                    }
                    var imageUri: Uri?=
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contextValuess)

                    fos = imageUri?.let {
                        shareIntent(context, imageUri)
                        resolver.openOutputStream(it)
                    }
                }
            }else {
                //Devices rodando < Q
                val imagesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File(imagesDir, filename)
                shareIntent(context, Uri.fromFile(image))
            }
            fos?.use{
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                Toast.makeText(context, "Imagem capturada com sucesso", Toast.LENGTH_LONG).show()
            }
        }

        private fun shareIntent(context: Context, imageUri: Uri) {
            var shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, imageUri)
                type = "image/jpeg"
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    context.resources.getText(R.string.compartilhar)
                )
            )

        }

    }
}