package com.example.oving4

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.example.oving4.ui.theme.Oving4Theme

class MainActivity : AppCompatActivity(), ListObjectsFragment.OnItemSelectedListener {
    private lateinit var viewObjectFragment: ViewObjectFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewObjectFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as ViewObjectFragment
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.next -> {
                viewObjectFragment.next()
                true
            }
            R.id.back -> {
                viewObjectFragment.prev()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setOrientation(newConfig: Configuration) {
        val layout = findViewById<LinearLayout>(R.id.root_layout)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layout.orientation = LinearLayout.HORIZONTAL
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layout.orientation = LinearLayout.VERTICAL
        }
    }

    override fun onItemSelected(position: Int) {
        viewObjectFragment.displayObject(position)
    }
}