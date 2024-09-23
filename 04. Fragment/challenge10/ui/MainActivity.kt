package com.sample.doitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(FirstFragment::class.java)
        setBottomNavigation()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setDrawer(toolbar)
        setNavigationView()
    }

    private fun setBottomNavigation() {
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab1 -> {
                    showFragment(FirstFragment::class.java)
                    true
                }
                R.id.tab2 -> {
                    showFragment(SecondFragment::class.java)
                    true
                }
                R.id.tab3 -> {
                    showFragment(ThirdFragment::class.java)
                    true
                }
                else -> false
            }
        }
    }

    private fun setDrawer(toolbar: Toolbar) {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.previous, R.string.next)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setNavigationView() {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu1 -> {
                    showFragment(FirstFragment::class.java)
                }
                R.id.menu2 -> {
                    showFragment(SecondFragment::class.java)
                }
                R.id.menu3 -> {
                    showFragment(ThirdFragment::class.java)
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun showFragment(fragmentClass: Class<out androidx.fragment.app.Fragment> ) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragmentClass, null).commit()
    }
}