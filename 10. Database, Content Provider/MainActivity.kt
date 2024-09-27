package com.sample.doitandroid

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*--- SQLite Database 예시 ---*/
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null)
        database.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)")

        //SqliteOpenHelper 를 사용하는 경우
        val dbHelper = DatabaseHelper(this)
        database = dbHelper.writableDatabase

        /*val insertButton: Button = findViewById(R.id.button_insert)
        insertButton.setOnClickListener {
            insertData()
        }

        val selectButton: Button = findViewById(R.id.button_select)
        selectButton.setOnClickListener {
            getData()
        }*/

        /*--- Content Provider 예시 ---*/
        val uriString = "content://com.sample.doitandroid/person"
        val uri = Uri.parse(uriString)

        //조회
        val cursor = contentResolver.query(uri, null, null, null, null)
        while (cursor?.moveToNext() == true) {
            val id = cursor.getInt(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val age = cursor.getInt(cursor.getColumnIndex("age"))

            println("id: $id, name: $name, age: $age")
        }

        //삽입
        val values = ContentValues()
        values.put("name", "ios")
        values.put("age", 30)
        contentResolver.insert(uri, values)
    }

    private fun insertData() {
        database.execSQL("INSERT INTO $TABLE_NAME (name, age) VALUES ('android', 20)")
    }

    private fun getData() {
        val cursor = database.rawQuery("SELECT _id, name, age FROM $TABLE_NAME", null)
        val count = cursor.count

        for (i in 0 until count) {
            cursor.moveToNext()

            val id = cursor.getInt(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val age = cursor.getInt(cursor.getColumnIndex("age"))

            println("id: $id, name: $name, age: $age")
        }
    }

    companion object {
        const val DATABASE_NAME = "database"
        const val TABLE_NAME = "sample"
    }
}