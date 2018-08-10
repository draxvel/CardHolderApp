package com.tkachuk.cardholderapp.ui.scanner

import android.content.Context
import com.google.android.gms.vision.text.TextRecognizer
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.provider.MediaStore
import com.tkachuk.cardholderapp.R
import com.google.android.gms.vision.Frame
import com.tkachuk.cardholderapp.ui.addCard.EditCardActivity
import com.tkachuk.cardholderapp.util.BusinessCardParser

class ScannerPresenter(private val context: Context, private val iScannerContract: IScannerContract) {

    fun scan(path: String) {
        val imageUri = Uri.parse("file://$path")
        val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)

        val textRecognizer = TextRecognizer.Builder(context).build()
        if (!textRecognizer.isOperational) {

            // Check for low storage.  If there is low storage, the native library will not be
            // downloaded, so detection will not become operational.
            val lowStorageFilter = IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW)
            val hasLowStorage = context.registerReceiver(null, lowStorageFilter) != null

            if (hasLowStorage) {
                iScannerContract.showMsg(context.getString(R.string.low_storage_error))
            }
        }

        val imageFrame = Frame.Builder()
                .setBitmap(bitmap)
                .build()

        val textBlocks = textRecognizer.detect(imageFrame)

        val myList: MutableList<String> = mutableListOf()
        for (i in 0 until textBlocks.size()) {
            val textBlock = textBlocks.get(textBlocks.keyAt(i))
            myList.add(textBlock.value)
        }

        if (myList.isEmpty()) {
            myList.add("")
            myList.add("")
        }
        val map: HashMap<String, String> = BusinessCardParser.parse(myList) as HashMap<String, String>

        val intent = Intent(context, EditCardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        map["photo"] = imageUri.toString()
        intent.putExtra("map", map)
        context.startActivity(intent)
    }
}