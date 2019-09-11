package com.example.firebasejetpack;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link registerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link registerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class registerFragment extends Fragment {

    public registerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


}
