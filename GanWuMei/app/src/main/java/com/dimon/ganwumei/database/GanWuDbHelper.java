/*
 * Copyright (C) 2015 Federico Paolinelli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/**********************************************************************************************************************************************************************
 * ***** AUTO GENERATED FILE BY ANDROID SQLITE HELPER SCRIPT BY FEDERICO PAOLINELLI. ANY CHANGE WILL BE WIPED OUT IF THE SCRIPT IS PROCESSED AGAIN. *******
 **********************************************************************************************************************************************************************/
package com.dimon.ganwumei.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GanWuDbHelper {
    private static final String TAG = "DbHelper";

    private static final String DATABASE_NAME = "DbHelper.db";
    private static final int DATABASE_VERSION = 1;


    // Variable to hold the database instance
    protected SQLiteDatabase mDb;
    // Context of the application using the database.
    private final Context mContext;
    // Database open/upgrade helper
    private DbHelper mDbHelper;

    public GanWuDbHelper(Context context) {
        mContext = context;
        mDbHelper = new DbHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public GanWuDbHelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public GanWuDbHelper openForRead() throws SQLException {
        mDb = mDbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        mDb.close();
    }

    public static final String ROW_ID = "_id";


    // -------------- GAN_WU DEFINITIONS ------------
    public static final String GAN_WU_TABLE = "GanWu";

    public static final String GAN_WU_ID_COLUMN = "Id";
    public static final int GAN_WU_ID_COLUMN_POSITION = 1;

    public static final String GAN_WU_URL_COLUMN = "Name";
    public static final int GAN_WU_URL_COLUMN_POSITION = 2;

    public static final String GAN_WU_DESCRIPTION_COLUMN = "Description";
    public static final int GAN_WU_DESCRIPTION_COLUMN_POSITION = 3;

    public static final String GAN_WU_OWNER_COLUMN = "Owner";
    public static final int GAN_WU_OWNER_COLUMN_POSITION = 4;


    // -------- TABLES CREATION ----------


    // GAN_WU CREATION
    private static final String DATABASE_REPO_CREATE = "create table " + GAN_WU_TABLE + " (" +
            "_id integer primary key autoincrement, " +
            GAN_WU_ID_COLUMN + " text, " +
            GAN_WU_URL_COLUMN + " text, " +
            GAN_WU_DESCRIPTION_COLUMN + " text, " +
            GAN_WU_OWNER_COLUMN + " text" +
            ")";


    // ----------------GanWu HELPERS --------------------
    public long addGanWu(String Id, String Url, String Description, String Owner) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAN_WU_ID_COLUMN, Id);
        contentValues.put(GAN_WU_URL_COLUMN, Url);
        contentValues.put(GAN_WU_DESCRIPTION_COLUMN, Description);
        contentValues.put(GAN_WU_OWNER_COLUMN, Owner);
        return mDb.insert(GAN_WU_TABLE, null, contentValues);
    }

    public long updateGanWu(long rowIndex, String Id, String Url, String Description, String Owner) {
        String where = ROW_ID + " = " + rowIndex;
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAN_WU_ID_COLUMN, Id);
        contentValues.put(GAN_WU_URL_COLUMN, Url);
        contentValues.put(GAN_WU_DESCRIPTION_COLUMN, Description);
        contentValues.put(GAN_WU_OWNER_COLUMN, Owner);
        return mDb.update(GAN_WU_TABLE, contentValues, where, null);
    }

    public boolean removeGanWu(long rowIndex) {
        return mDb.delete(GAN_WU_TABLE, ROW_ID + " = " + rowIndex, null) > 0;
    }

    public boolean removeAllGanWu() {
        return mDb.delete(GAN_WU_TABLE, null, null) > 0;
    }

    public Cursor getAllGanWu() {
        return mDb.query(GAN_WU_TABLE, new String[]{
                ROW_ID,
                GAN_WU_ID_COLUMN,
                GAN_WU_URL_COLUMN,
                GAN_WU_DESCRIPTION_COLUMN,
                GAN_WU_OWNER_COLUMN
        }, null, null, null, null, null);
    }

    public Cursor getGanWu(long rowIndex) {
        Cursor res = mDb.query(GAN_WU_TABLE, new String[]{
                ROW_ID,
                GAN_WU_ID_COLUMN,
                GAN_WU_URL_COLUMN,
                GAN_WU_DESCRIPTION_COLUMN,
                GAN_WU_OWNER_COLUMN
        }, ROW_ID + " = " + rowIndex, null, null, null, null);

        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }


    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_REPO_CREATE);

        }

        // Called when there is a database version mismatch meaning that the version
        // of the database on disk needs to be upgraded to the current version.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Log the version upgrade.
            Log.w(TAG, "Upgrading from version " +
                    oldVersion + " to " +
                    newVersion + ", which will destroy all old data");

            // Upgrade the existing database to conform to the new version. Multiple
            // previous versions can be handled by comparing _oldVersion and _newVersion
            // values.

            // The simplest case is to drop the old table and create a new one.
            db.execSQL("DROP TABLE IF EXISTS " + GAN_WU_TABLE + ";");

            // Create a new one.
            onCreate(db);
        }
    }
}
