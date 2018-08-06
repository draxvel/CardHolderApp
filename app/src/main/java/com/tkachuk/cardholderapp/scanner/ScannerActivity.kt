package com.tkachuk.cardholderapp.scanner

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.provider.MediaStore
import android.content.Intent
import android.support.v4.content.FileProvider
import android.net.Uri
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class ScannerActivity: AppCompatActivity() {

    private val requestImageCode = 1
    private lateinit var scannerPresenter: ScannerPresenter
    private var currentPhotoPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scannerPresenter = ScannerPresenter(applicationContext)
        dispatchTakePictureIntent()
    }

    // startActivityForResult() method is protected by a condition that calls resolveActivity(),
    // which returns the first activity component that can handle the intent.
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            val photoUri = FileProvider.getUriForFile(this, "com.tkachuk.cardholderapp", createImageFile())
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(takePictureIntent, requestImageCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == requestImageCode && resultCode == Activity.RESULT_OK){

            val imageUri = Uri.parse("file://$currentPhotoPath")
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
            scannerPresenter.scan(bitmap)

        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        )
        currentPhotoPath = image.absolutePath
        return image
    }
}