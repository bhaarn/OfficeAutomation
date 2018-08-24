package com.padhuga.officeautomation.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.padhuga.officeautomation.dao.UserDaoAccess;
import com.padhuga.officeautomation.tables.UserTable;

@Database(entities = {UserTable.class}, version = 1, exportSchema = false)
public abstract class MasterDatabase extends RoomDatabase {

    public abstract UserDaoAccess userDaoAccess();
    private static final String DATABASE_NAME = "master_db";
    private static MasterDatabase INSTANCE;

    public static MasterDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MasterDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MasterDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDaoAccess userDaoAccess;

        PopulateDbAsync(MasterDatabase masterDatabase) {
            userDaoAccess = masterDatabase.userDaoAccess();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            userDaoAccess.deleteAll();

            UserTable userTable = new UserTable("Bharani Dharan", "Krishnaswamy", "bhaarn", "123");
            userDaoAccess.insertOnlySingleUser(userTable);
            return null;
        }
    }
}