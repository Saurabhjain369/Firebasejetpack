package com.example.firebasejetpack;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class registerFragment extends Fragment {



    EditText edt_name,edt_email,edt_pass,edt_cpass;
    Button btn_register;
    TextView txt_reg;


    private FirebaseAuth auth;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        edt_name= view.findViewById(R.id.edt_rname);

        edt_email= view.findViewById(R.id.edt_remail);

        edt_pass= view.findViewById(R.id.edt_rpass);

        edt_cpass= view.findViewById(R.id.edt_rcpass);

        btn_register= view.findViewById(R.id.btn_reg);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isCheckEptyField())
                {

                    if(edt_pass.getText().toString().length()<6)
                    {
                        edt_pass.setError("Invalid password shoud be at least 6 charcters");

                        edt_pass.requestFocus();

                    }else{

                        if(!edt_pass.getText().toString().equals(edt_cpass.getText().toString()))
                        {
                            edt_cpass.setError("Password not match!");

                            edt_cpass.requestFocus();


                        }else {


                            String name = edt_name.getText().toString();
                            String email = edt_email.getText().toString();
                            String pass = edt_pass.getText().toString();


                            createUser(name,email,pass);



                        }


                    }



                }


                //if(!isCheckEptyField()())


            }
        });


    }

    public registerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth= FirebaseAuth.getInstance();

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


    // Validation -  not efficient - with if else
    public boolean isCheckEptyField()

    {

        if(TextUtils.isEmpty((edt_name.getText().toString())))
        {

            edt_name.setError("Name cannot be blank");

            edt_name.requestFocus();

            return true;

        }else if (TextUtils.isEmpty((edt_email.getText().toString())))
    {

        edt_name.setError("Email cannot be blank");

        edt_name.requestFocus();

        return true;

    }else if(TextUtils.isEmpty((edt_pass.getText().toString())))
        {

            edt_name.setError("Pass cannot be blank");

            edt_name.requestFocus();

            return true;

        }else if(TextUtils.isEmpty((edt_cpass.getText().toString())))
        {

            edt_name.setError("Cpass cannot be blank");

            edt_name.requestFocus();

            return true;

        }


        return false;
    }



    // optimize code - polymophisim - check any edit text by passing in parameters of the function


    public boolean IsEmptyField(EditText edt_txt)
    {

        if(TextUtils.isEmpty((edt_txt.getText().toString())))
        {

            edt_txt.setError("This Field is cannot be blank");

            edt_txt.requestFocus();

            return true;
        }




        return false;

    }


    // Register the user in database
    public void createUser(final String name, final String email, String pass)
    {
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    // add user in db

                    FirebaseUser user = auth.getCurrentUser();

                    FirebaseFirestore db =  FirebaseFirestore.getInstance();

                    Map<String,Object> usermap= new HashMap<>();

                    usermap.put("Name",name);

                    usermap.put("email",email);

                    db.collection("users").document(user.getUid()).set(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {


                            Toast.makeText(getActivity().getApplicationContext(), "Registration is completed", Toast.LENGTH_SHORT).show();



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Log.d("From Firestore ",e.getMessage());

                        }
                    });

                }else
                {

                    Log.d("from Register",task.getException().toString());

                    Toast.makeText(getActivity().getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();

                }

            }
        });



        FirebaseAuth.getInstance().signOut();

        NavController navController = Navigation.findNavController(getActivity(),R.id.host_frag);

        navController.navigate(R.id.loginFragment);


    }

}
