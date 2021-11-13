package com.sumeyra.newlanguage

import android.content.Context
import android.content.SharedPreferences

import android.os.Bundle


import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sumeyra.newlanguage.databinding.ActivityMainBinding
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import java.util.*


//Menuyu görmezse -> import android.view.Menu

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var array: Array<String>
    lateinit var sharedPrefences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefences = this.getSharedPreferences("com.sumeyra.newlanguage", Context.MODE_PRIVATE)
        println("Created")
        loadLocale()




        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)

        //setTitle(R.string.app_name)
        array = arrayOf("English", "Turkish")



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //Inflater
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_language,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.language) {
            choselang()

        }
        return super.onOptionsItemSelected(item)
    }

    fun choselang() {
        AlertDialog.Builder(this@MainActivity)
            .setTitle("Chose Your Language")
            .setIcon(R.drawable.ic_baseline_language_24)
            .setSingleChoiceItems(array, -1) { dialog, which ->
                when (which) {
                    0 -> {
                        changeLocale("en")
                        recreate()
                        println("alert en")


                    }
                    1 -> {
                        changeLocale("tr")
                        recreate()
                        println("alert tr")
                    }


                }
                dialog.dismiss()
            }.create().show()


    }

    private fun changeLocale(lang: String) {
        val dm = resources.displayMetrics
        val conf = resources.configuration
        conf.setLocale(Locale(lang))
        resources.updateConfiguration(conf, dm)
        sharedPrefences.edit().putString("language", lang).apply()
        println("changeLocal: $lang")

    }

    private fun loadLocale() {

        val language = sharedPrefences.getString("language", "")
        //setLocale(language!!)
        if (language.equals("en")) {
            changeLocale("en")
            println("loadLocal  en:) ")

        }else if (language.equals("tr")) {
            changeLocale("tr")
            println("loadLocal  tr ")



        }


    }









}





