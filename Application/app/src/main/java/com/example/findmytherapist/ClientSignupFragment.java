package com.example.findmytherapist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientSignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientSignupFragment extends Fragment {

//    public static final String SHARED_PREFS = "sharedPrefs";
//    public static final String EMAIL = "email";
    EditText firstname,lastname,age,email,password,repass,address;
    CheckBox female,male;
    Button clientSignup;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClientSignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientSignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientSignupFragment newInstance(String param1, String param2) {
        ClientSignupFragment fragment = new ClientSignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_signup, container, false);
        firstname = view.findViewById(R.id.fNameClientDisplayPT);
        lastname = view.findViewById(R.id.lNameClientPDisplayT);
        email = view.findViewById(R.id.emailClientPT);
        age = view.findViewById(R.id.ageClientDisplay);
        address = view.findViewById(R.id.clientAddress);
        password = view.findViewById(R.id.clientPassword);
        repass = view.findViewById(R.id.clientPassword2);
        female = (CheckBox) view.findViewById(R.id.femaleClientCB);
        male = (CheckBox) view.findViewById(R.id.maleClientCB);
        clientSignup = (Button)view.findViewById((R.id.signupClientButton));
        DBHelper db = new DBHelper(getActivity());

        clientSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstname.getText().toString();
                String lastName = lastname.getText().toString();
                String email2 = email.getText().toString();
                String age2 = age.getText().toString();
                String address2 = address.getText().toString();
                String password2 = password.getText().toString();
                String repass2 = repass.getText().toString();
                Boolean isFemale = female.isChecked();
                Boolean isMale = male.isChecked();
                String gender=" ";

                //making sure password and confirm password is the same
                if(!password2.equals(repass2)){
                    Toast.makeText(getActivity(),"Make sure your passwords match",Toast.LENGTH_SHORT).show();
                }
                //checking gender
                if(isMale==true && isFemale==true){
                    Toast.makeText(getActivity(),"Please choose one gender",Toast.LENGTH_SHORT).show();
                }else if (isMale==true){
                    gender = "male";
                }else{
                    gender = "female";
                }
                //checking if client already exists else adding to database
                if(db.checkClientEmailExists(email2)){
                    Toast.makeText(getActivity(),"User with this email already exists",Toast.LENGTH_SHORT).show();
                }else{
                    if(db.insertClient(email2,password2,firstName,lastName,gender,age2,address2)){
                        //saving the email of person who is logged in
//                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString(EMAIL, email2);
//                        editor.apply();
                        //go to client profile
                        Integer id = db.getIdByEmailClient(email2);
                        Intent intent = new Intent(getActivity(),ClientProfile.class);
                        intent.putExtra("USER_ID",id);
                        startActivity(intent);
                    };
                }

            }
        });
        return view;
    }
}