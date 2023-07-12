package com.example.helpers

import android.content.Context
import java.io.BufferedReader

internal object FileHelper {

    fun loadJson(context: Context, jsonFileName: String): String {

        val jsonFile = context.assets.open(jsonFileName)

        val reader = BufferedReader(jsonFile.reader())
        val jsonData = StringBuilder()
        reader.use { r ->
            var line = r.readLine()
            while (line != null) {
                jsonData.append(line)
                line = r.readLine()
            }
        }

        return jsonData.toString()
    }

}