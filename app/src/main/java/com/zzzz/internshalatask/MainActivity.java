package com.zzzz.internshalatask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LoginFragment()).commit();

        if(isuserNotLogin()){
            LoginFragment loginFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, loginFragment)
                    .commit();
        }
        else {
            ShowListFragment showListFragment = new ShowListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, showListFragment).addToBackStack(null)
                    .commit();
        }
    }
    public boolean isuserNotLogin(){
        //##############################  Get User ID from shared Preference ##################
        // if user ID exist then
        return true;
    }

    public void navigateToAnotherFragment(){
        ShowListFragment fragment = new ShowListFragment();
    }

}