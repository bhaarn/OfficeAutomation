package com.padhuga.officeautomation.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.padhuga.officeautomation.repository.UserRepository;
import com.padhuga.officeautomation.tables.UserTable;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    private LiveData<List<UserTable>> allUsers;

    public UserViewModel (Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<UserTable>> getAllUsers() { return allUsers; }

    public void insert(UserTable userTable) { userRepository.insert(userTable); }
}
