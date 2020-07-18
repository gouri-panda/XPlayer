package com.one4ll.xplayer

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.one4ll.xplayer.helpers.IS_GRID_LAYOUT
import com.one4ll.xplayer.helpers.SHARED_PREF_SETTINGS
import com.squareup.leakcanary.LeakCanary
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.math.log
import kotlin.properties.Delegates

private val TAG = "galleryview"
class GalleryView : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var navController : NavController
    private var isGrid by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gallery_view)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


         sharedPreferences = getSharedPreferences(SHARED_PREF_SETTINGS,Context.MODE_PRIVATE)
        isGrid = sharedPreferences.getBoolean(IS_GRID_LAYOUT,false)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val navView: NavigationView = findViewById(R.id.nav_view)
         navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_slideshow, R.id.nav_gallery), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.gallery_view, menu)
        if (isGrid){
            menu.getItem(1).title = "Display in list"
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings ->{}
            R.id.display_in_grid ->{
                if (isGrid){
                    sharedPreferences.edit().putBoolean(IS_GRID_LAYOUT,false).apply()
                    onStart()
                }else{
                    sharedPreferences.edit().putBoolean(IS_GRID_LAYOUT,true).apply()
                    onStart()
                }
                return true
            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}