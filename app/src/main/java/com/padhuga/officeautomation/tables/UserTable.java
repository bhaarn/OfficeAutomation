package com.padhuga.officeautomation.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.parceler.Parcel;

@Parcel
@Entity(tableName = "User")
public class UserTable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int userId;

    @NonNull
    @ColumnInfo(name = "first_name")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "last_name")
    private String lastName;

    @NonNull
    @ColumnInfo(name = "login_name")
    private String loginName;

    @NonNull
    private String password;

    public UserTable() {

    }

    public UserTable(@NonNull String firstName, @NonNull String lastName, @NonNull String loginName, @NonNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int loginId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
