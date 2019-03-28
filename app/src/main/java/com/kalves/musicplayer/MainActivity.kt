package com.kalves.musicplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.kalves.musicplayer.fragments.ApplicationMenu
import com.kalves.musicplayer.fragments.NowPlaying
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ApplicationMenu.Listener, NowPlaying.OnFragmentInteractionListener {
    override fun interaction(uri: Uri) {

    }

    override fun onMenuClicked(option: CharSequence) {
        Toast.makeText(applicationContext, "$option clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpMenu()
    }

    private fun setUpMenu() {
        bottomAppBar.setNavigationOnClickListener {
            ApplicationMenu.newInstance(R.array.MenuOptions).show(supportFragmentManager, "appMenu")
        }
        bottomAppBarFab.setOnClickListener {
            when (bottomAppBar.fabAlignmentMode) {
                BottomAppBar.FAB_ALIGNMENT_MODE_END -> bottomAppBar.fabAlignmentMode =
                    BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                else -> bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            }
        }
    }
}
