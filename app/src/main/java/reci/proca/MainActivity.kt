package reci.proca

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.preference.PreferenceManager
import reci.proca.pref.PrefUtil
import reci.proca.pref.PrefKeys
import reci.proca.pref.SettingsActivity

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

    private val takePictureLauncher =  registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->

        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val bitmap = data?.extras?.get("data") as? Bitmap
            findViewById<ImageView>(R.id.thumbnail).setImageBitmap(bitmap)
        }
    }

    private fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // https://stackoverflow.com/questions/62671106/onactivityresult-method-is-deprecated-what-is-the-alternative

       takePictureLauncher.launch(takePictureIntent)
    }
}