package com.padhuga.officeautomation.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.padhuga.officeautomation.dao.UserDaoAccess;
import com.padhuga.officeautomation.database.MasterDatabase;
import com.padhuga.officeautomation.tables.UserTable;

import java.util.List;

public class UserRepository {

    private UserDaoAccess userDaoAccess;
    private LiveData<List<UserTable>> userTable;

    public UserRepository(Application application) {
        MasterDatabase masterDatabase = MasterDatabase.getDatabase(application);
        userDaoAccess = masterDatabase.userDaoAccess();
        userTable = userDaoAccess.getAlphabetizedUserNames();
    }

    public LiveData<List<UserTable>> getAllUsers() {
        return userTable;
    }


    public void insert (UserTable userTable) {
        new insertAsyncTask(userDaoAccess).execute(userTable);
    }

    private static class insertAsyncTask extends AsyncTask<UserTable, Void, Void> {

        private UserDaoAccess userDaoAccess;

        insertAsyncTask(UserDaoAccess dao) {
            userDaoAccess = dao;
        }

        @Override
        protected Void doInBackground(final UserTable... params) {
            userDaoAccess.insertOnlySingleUser(params[0]);
            return null;
        }
    }
}
