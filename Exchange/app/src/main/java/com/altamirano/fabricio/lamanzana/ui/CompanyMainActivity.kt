package com.altamirano.fabricio.lamanzana.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.altamirano.fabricio.lamanzana.R
import com.altamirano.fabricio.lamanzana.services.ServiceNavigation
import com.altamirano.fabricio.lamanzana.services.ServiceUser
import com.altamirano.fabricio.lamanzana.ui.fragments.CompanyEditFragment
import com.altamirano.fabricio.lamanzana.ui.fragments.CountriesCompanyFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class CompanyMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        navView.getMenu().getItem(0).setChecked(true)
        replaceFragmentWithAnimation(CountriesCompanyFragment())
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_countries -> {
                // Handle the camera action
                replaceFragmentWithAnimation(CountriesCompanyFragment())
            }
            R.id.nav_company -> {
                replaceFragmentWithAnimation(CompanyEditFragment())
            }
            R.id.nav_logout -> {
                ServiceUser.logout()
                ServiceNavigation.logOut(this)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun replaceFragmentWithAnimation(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            android.R.anim.fade_in,
             android.R.anim.fade_out
        )
        transaction.replace(R.id.my_nav_host_fragment, fragment)
        transaction.commit()
    }

    fun lod(view: View){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}
