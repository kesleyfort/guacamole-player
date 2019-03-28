package com.kalves.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ApplicationMenu.Listener {
    override fun onMenuClicked(position: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpMenu()
    }
    fun setUpMenu(){
        bottomAppBar.setNavigationOnClickListener {
            ApplicationMenu.newInstance(R.array.MenuOptions).show(supportFragmentManager, "tag")
        }
    }
}
