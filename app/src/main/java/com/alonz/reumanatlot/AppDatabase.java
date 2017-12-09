package com.alonz.reumanatlot;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.alonz.reumanatlot.Natlot.Natla;

/**
 * Created by alonz on 08/12/2017.
 */

@Database(entities = {Natla.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase singleton;
    private static final String DATABASE_NAME = "NatlaDB.db";

    public abstract NatlaDao natlaDao();

    public static AppDatabase getAppDatabase(Context context){
        if (singleton == null){
            synchronized (AppDatabase.class){
                if(singleton == null){
                    singleton = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return singleton;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
