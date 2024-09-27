package com.sample.doitandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context,
    name: String = DATABASE_NAME,
    version: Int = 1
) : SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ${MainActivity.TABLE_NAME} (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        }
    }

    companion object {
        const val DATABASE_NAME = "database"
        const val TABLE_NAME = "sample"
        const val PERSON_ID = "_id"
        const val PERSON_NAME = "name"
        const val PERSON_AGE = "age"
        val ALL_COLUMNS = arrayOf(PERSON_ID, PERSON_NAME, PERSON_AGE)
    }
}