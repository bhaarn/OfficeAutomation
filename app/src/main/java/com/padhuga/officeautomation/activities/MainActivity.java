package com.padhuga.officeautomation.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.padhuga.officeautomation.R;
import com.padhuga.officeautomation.adapter.UserListAdapter;
import com.padhuga.officeautomation.tables.UserTable;
import com.padhuga.officeautomation.viewmodel.UserViewModel;

import org.parceler.Parcels;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_USER_ACTIVITY_REQUEST_CODE = 1;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UserListAdapter userListAdapter = new UserListAdapter(this);
        recyclerView.setAdapter(userListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        userViewModel.getAllUsers().observe(this, new Observer<List<UserTable>>() {
            @Override
            public void onChanged(@Nullable final List<UserTable> words) {
                // Update the cached copy of the words in the adapter.
                userListAdapter.setUsers(words);
            }
        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
                startActivityForResult(intent, NEW_USER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            UserTable userTable = Parcels.unwrap(data.getParcelableExtra(NewUserActivity.EXTRA_REPLY));
            userViewModel.insert(userTable);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
