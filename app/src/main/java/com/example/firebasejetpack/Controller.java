package com.example.firebasejetpack;

import android.app.Activity;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Controller {


    public void navigetTofragment(int fragid, Activity onact, Bundle b)
    {
        NavController navController = Navigation.findNavController(onact,R.id.host_frag);

        navController.navigate(fragid,b);



    }

}
