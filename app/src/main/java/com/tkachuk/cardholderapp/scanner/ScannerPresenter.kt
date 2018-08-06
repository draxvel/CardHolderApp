package com.tkachuk.cardholderapp.scanner

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.android.gms.vision.text.TextRecognizer
import android.content.Intent
import android.content.IntentFilter
import com.tkachuk.cardholderapp.R
import com.google.android.gms.vision.Frame
import com.tkachuk.cardholderapp.util.BusinessCardParser

class ScannerPresenter(private val context: Context) {

    private val tag: String = "scanner"

    fun scan(bitmap: Bitmap) {
        val textRecognizer = TextRecognizer.Builder(context).build()
        if (!textRecognizer.isOperational) {
            Log.d(tag, "Detector dependencies are not yet available.")

            // Check for low storage.  If there is low storage, the native library will not be
            // downloaded, so detection will not become operational.
            val lowStorageFilter = IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW)
            val hasLowStorage = context.registerReceiver(null, lowStorageFilter) != null

            if (hasLowStorage) {
                Log.d(tag, context.getString(R.string.low_storage_error))
            }

        } else Log.d(tag, "Detector dependencies are available.")


        val imageFrame = Frame.Builder()
                    .setBitmap(bitmap)
                    .build()

        val textBlocks = textRecognizer.detect(imageFrame)

        val myList: MutableList<String> = mutableListOf()
        for (i in 0 until textBlocks.size()) {
            val textBlock = textBlocks.get(textBlocks.keyAt(i))
            Log.d(tag, textBlock.value)
            myList.add(textBlock.value)
        }

        val map = BusinessCardParser.parse(myList)
        for(item in map){
            Log.d(tag, "/nkey: "+item.key +", value:"+ item.value)
        }
    }
}