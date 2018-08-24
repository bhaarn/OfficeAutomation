package com.padhuga.officeautomation.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.padhuga.officeautomation.tables.UserTable;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface UserDaoAccess {

    @Query("SELECT * FROM User")
    LiveData<List<UserTable>> fetchAllUsers();

    @Query("SELECT * FROM User ORDER BY user_id ASC")
    LiveData<List<UserTable>> fetchAllUsersInAscending();

    @Query("SELECT * FROM User WHERE user_id = :userId")
    Maybe<UserTable> fetchOneUserbyUserId(int userId);

    @Query("SELECT * FROM User WHERE user_id IN (:userIds)")
    List<UserTable> fetchAllUsersByUserIds(int[] userIds);

    @Query("SELECT * FROM User WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    UserTable fetchByName(String first, String last);

    @Query("SELECT * from User ORDER BY login_name ASC")
    LiveData<List<UserTable>> getAlphabetizedUserNames();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOnlySingleUser(UserTable userTable);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMultipleUser(List<UserTable> userTableList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserTable... UserTables);

    @Update
    void updateUserTable(UserTable userTable);

    @Delete
    void deleteUserTable(UserTable userTable);

    @Query("DELETE FROM user")
    void deleteAll();
}
