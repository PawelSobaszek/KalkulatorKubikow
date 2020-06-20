package com.pawelsobaszek.kalkulatorkubikow

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var srednica : Double? = null
    private var dlugosc : Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        et_srednica.transformationMethod = null
        et_dlugosc.transformationMethod = null

        btn_oblicz.setOnClickListener {
            calculate()
        }
    }

    private fun calculate(){
        var suma : Double? = null
        var sre : String? = null
        var dlu : String? = null
        sre = et_srednica.text.toString()
        dlu = et_dlugosc.text.toString()

        try {
            srednica = sre.toDouble()
            try {
                dlugosc = dlu.toDouble()
            } catch (e: Exception) {
                Toast.makeText(this, "Podaj długość", Toast.LENGTH_SHORT).show()
            }
            suma = (srednica!! / 200) * (srednica!! / 200) * (dlugosc!! / 100)  * Math.PI
            wynik.text = suma.toString() + "m\u00B3"
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
}
