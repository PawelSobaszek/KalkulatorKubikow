package com.pawelsobaszek.kalkulatorkubikow

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    private var srednica : Double? = null
    private var dlugosc : Double? = null
    private var treeIndex : Int = 0
    private var sumaTree : Double = 0.0
    var treeArray : ArrayList<TreeItem> = ArrayList()

    private var player: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_srednica.transformationMethod = null
        et_dlugosc.transformationMethod = null

        btn_oblicz.setOnClickListener {
            calculate()
        }

        btn_skasuj.setOnClickListener {

            AlertDialog.Builder(this)
                .setMessage("Na pewno chcesz skasować?")
                .setCancelable(false)
                .setPositiveButton("Tak", { dialog, id -> delete() })
                .setNegativeButton("Nie", null)
                .show()
        }
    }

    private fun calculate() {
        var suma : Double? = null
        var sre : String? = null
        var dlu : String? = null
        sre = et_srednica.text.toString()
        dlu = et_dlugosc.text.toString()


        try {
            srednica = sre.toDouble()
            try {
                dlugosc = dlu.toDouble()
                suma = (srednica!! / 200) * (srednica!! / 200) * (dlugosc!! / 100) * Math.PI
                suma = BigDecimal(suma).setScale(5, RoundingMode.HALF_EVEN).toDouble()
                sumaTree = sumaTree + suma
                wynik.text = sumaTree.toString() + "m\u00B3"
                val listView = findViewById(R.id.tree_list_view) as ListView
                treeIndex = treeIndex + 1
                treeArray.add(TreeItem(treeIndex, sre, dlu, suma.toString()))
                listView.adapter = TreeAdapter(applicationContext, treeArray)
                player = MediaPlayer.create(applicationContext, R.raw.kaching)
                player!!.isLooping = false
                player!!.start()
            } catch (e: Exception) {
                Toast.makeText(this, "Podaj długość", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Podaj średnice", Toast.LENGTH_SHORT).show()
        }

        et_srednica.text?.clear()

        if (checkbox_zapamietaj.isChecked == true) {
        } else {
            et_dlugosc.text?.clear()
        }

        et_srednica.requestFocus()

    }

    private fun delete() {
        val listView = findViewById(R.id.tree_list_view) as ListView
        treeArray.clear()
        listView.adapter = TreeAdapter(applicationContext, treeArray)
        sumaTree = 0.0
        wynik.text = "0"
        treeIndex = 0

    }
}
