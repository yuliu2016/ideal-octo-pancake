package reci.proca

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.preference.PreferenceManager
import reci.proca.pref.PrefUtil
import reci.proca.pref.PrefKeys
import reci.proca.pref.SettingsActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

        val darkTheme = preferences.getString(PrefKeys.darkTheme, null)
        PrefUtil.setDarkTheme(darkTheme)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    /**
     * Handle the top-right menu clicks
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings ->
                startActivity(Intent(this, SettingsActivity::class.java))

            R.id.menu_take_picture -> takePicture()
        }
        return true
    }

    // https://stackoverflow.com/questions/62671106/onactivityresult-method-is-deprecated-what-is-the-alternative
    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        println("Result maybe? $result")

        if (result.resultCode == RESULT_OK) {
            println("Result ok!!, path=$currentPhotoPath")

            val ppath = currentPhotoPath!!

            thread {
                val bitmap = BitmapFactory.decodeFile(ppath)!!

                println("Decode ok!!")

                // https://stackoverflow.com/questions/2577221/android-how-to-create-runtime-thumbnail
                val thumbnail = ThumbnailUtils.extractThumbnail(bitmap, 480, 480)

                runOnUiThread {
                    findViewById<ImageView>(R.id.thumbnail).setImageBitmap(thumbnail)
                    println("Thumb ok!!")
                }
            }
        }
    }

    private var currentPhotoPath: String? = null

    private fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val f = createImageFile()


        // https://stackoverflow.com/questions/56598480/couldnt-find-meta-data-for-provider-with-authority
        // https://stackoverflow.com/questions/62902968/couldnt-find-meta-data-for-provider-with-authority-com-example-android-provider?noredirect=1&lq=1
        // https://stackoverflow.com/questions/38200282/android-os-fileuriexposedexception-file-storage-emulated-0-test-txt-exposed

        val photoUri: Uri = FileProvider.getUriForFile(
            this, "$packageName.provider", f
        )

        currentPhotoPath = f.absolutePath
        Log.e("blah", "path=$currentPhotoPath")

        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            takePictureIntent.putExtra(MediaStore.EXTRA_BRIGHTNESS, 1.0)
        }

        takePictureIntent.putExtra(MediaStore.EXTRA_FULL_SCREEN, true)
        takePictureIntent.putExtra(
            MediaStore.EXTRA_SCREEN_ORIENTATION,
            ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
        )

        takePictureLauncher.launch(takePictureIntent)
    }


    private fun createImageFile(): File {
        val date = Date()
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.ROOT).format(date)
        // https://stackoverflow.com/questions/3425906/creating-temporary-files-in-android
        val storageDir = filesDir
        return File(storageDir, "$timeStamp.jpg")
    }
}