package com.sample.doitandroid

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.provider.ContactsContract.Data

class PersonProvider : ContentProvider() {
    private lateinit var database: SQLiteDatabase

    override fun onCreate(): Boolean {
        val helper = DatabaseHelper(requireContext())
        database = helper.writableDatabase

        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor: Cursor

        when (uriMatcher.match(uri)) {
            PERSONS -> cursor = database.query(
                DatabaseHelper.TABLE_NAME,
                DatabaseHelper.ALL_COLUMNS,
                selection,
                null,
                null,
                null,
                DatabaseHelper.PERSON_NAME + " ASC"
            )
            else -> throw java.lang.IllegalArgumentException("알 수 없는 URI $uri")
        }

        cursor.setNotificationUri(requireContext().contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String {
        when (uriMatcher.match(uri)) {
            PERSONS -> return "vnd.android.cursor.dir/persons"
            else -> throw java.lang.IllegalArgumentException("알 수 없는 URI $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = database.insert(DatabaseHelper.TABLE_NAME, null, values)

        if (id > 0) {
            val contentUri = ContentUris.withAppendedId(CONTENT_URI, id)
            notifyChange(contentUri)
            return uri
        }

        throw SQLException("추가 실패")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val count: Int

        when (uriMatcher.match(uri)) {
            PERSONS -> count = database.delete(DatabaseHelper.TABLE_NAME, selection, selectionArgs)
            else -> throw java.lang.IllegalArgumentException("알 수 없는 URI $uri")
        }

        notifyChange(uri)
        return count
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val count: Int

        when (uriMatcher.match(uri)) {
            PERSONS -> count = database.update(DatabaseHelper.TABLE_NAME, values, selection, selectionArgs)
            else -> throw java.lang.IllegalArgumentException("알 수 없는 URI $uri")
        }

        notifyChange(uri)
        return count
    }

    private fun notifyChange(uri: Uri) {
        requireContext().contentResolver.notifyChange(uri, null)
    }

    companion object {
        private const val AUTHORITY = "com.sample.doitandroid"
        private const val BASE_PATH = "person"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$BASE_PATH")

        private const val PERSONS = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, BASE_PATH, PERSONS)
        }
    }
}