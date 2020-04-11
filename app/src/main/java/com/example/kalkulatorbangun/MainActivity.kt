package com.example.kalkulatorbangun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity(), View.OnClickListener { //membuat class main untuk menginisiasi aplikasi yang akan di bangun
    // deklarasi semua variabel yang dibutuhkan
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    //inisiasi nilai konstan
    companion object {
        private const val STATE_RESULT = "state_result"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //fungsi untuk memanggil desain layout

        //mengambil semua input dari user atau memanggil respon layout
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)  //mengklarifikasi bahwa tombol di tekan

        //menyimpan result
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_calculate) {

            //meng-casting seluruh inputan dari layer layout
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()


            var isEmptyFields = false
            var isInvalidDouble = false


            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }

            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }

            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }
            val length = toDouble(inputLength)
            val width = toDouble(inputWidth)
            val height = toDouble(inputHeight)



            if (length == null) {
                isInvalidDouble = true
                edtLength.error = "Field ini harus berupa nomer yang valid"
            }
            if (width == null) {
                isInvalidDouble = true
                edtWidth.error = "Field ini harus berupa nomer yang valid"
            }
            if (height == null) {
                isInvalidDouble = true
                edtHeight.error = "Field ini harus berupa nomer yang valid"
            }
            if (!isEmptyFields && !isInvalidDouble) {
                val volume = length as Double * width as Double * height as Double
                tvResult.text = volume.toString()
            }
        }
    }

    private fun toDouble(str: String): Double? { //memberikan casting double untuk input
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }
}

