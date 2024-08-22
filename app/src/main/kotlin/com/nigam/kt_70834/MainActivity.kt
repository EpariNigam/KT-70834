package com.nigam.kt_70834

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var tvOutput: TextView
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvOutput = findViewById(R.id.tv_output)
        gson = Gson()
        findViewById<Button>(R.id.btn_null_data).setOnClickListener { nullData() }
        findViewById<Button>(R.id.btn_non_null_data).setOnClickListener { nonNullData() }
        findViewById<Button>(R.id.btn_null_data_with_variable).setOnClickListener { nullDataWithVariable() }
    }

    @SuppressLint("SetTextI18n")
    private fun nullData() {
        val data: String? = null
        val output = getAppsflyerConversionData(data)
        Log.d(TAG, "nullData: $output")
        tvOutput.text = HtmlCompat.fromHtml(
            "<b>Output:</b><br> ${output.keys}",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }

    @SuppressLint("SetTextI18n")
    private fun nonNullData() {
        val data = "{\"key\":\"value\"}"
        val output = getAppsflyerConversionData(data)
        Log.d(TAG, "nonNullData: $output")
        tvOutput.text = HtmlCompat.fromHtml(
            "<b>Output:</b><br> ${output.keys}",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }

    private fun getAppsflyerConversionData(data: String?): Map<String, Any> {
        return try {
            gson.fromJson(
                data,
                TypeToken.getParameterized(
                    MutableMap::class.java,
                    String::class.java,
                    Any::class.java
                ).type
            )
        } catch (ex: Exception) {
            Log.d(TAG, "getAppsflyerConversionData: $ex")
            emptyMap()
        }
    }

    private fun nullDataWithVariable() {
        val data: String? = null
        val output = getAppsflyerConversionDataVar(data)
        Log.d(TAG, "nullData: $output")
        tvOutput.text = HtmlCompat.fromHtml(
            "<b>Output:</b><br> ${output.keys}",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }

    private fun getAppsflyerConversionDataVar(data: String?): Map<String, Any> {
        return try {
            val response: MutableMap<String, Any> = gson.fromJson(
                data,
                TypeToken.getParameterized(
                    MutableMap::class.java,
                    String::class.java,
                    Any::class.java
                ).type
            )
            response
        } catch (ex: Exception) {
            Log.d(TAG, "getAppsflyerConversionData: $ex")
            emptyMap()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
