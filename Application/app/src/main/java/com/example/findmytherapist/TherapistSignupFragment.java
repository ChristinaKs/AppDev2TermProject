package com.example.findmytherapist;

import android.content.Intent;
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
 * Use the {@link TherapistSignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TherapistSignupFragment extends Fragment {

//    public static final String SHARED_PREFS = "sharedPrefs";
//    public static final String EMAIL = "email";
    EditText firstname,lastname,therapistLicense,therapistemail,therapistAddress,password,repass;
    CheckBox female,male,phone,text,zoom,person;
    Button therapistSignUp;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TherapistSignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TherapistSignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TherapistSignupFragment newInstance(String param1, String param2) {
        TherapistSignupFragment fragment = new TherapistSignupFragment();
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
        View view = inflater.inflate(R.layout.fragment_therapist_signup, container, false);
        firstname = view.findViewById(R.id.fNameTherapistPT);
        lastname = view.findViewById(R.id.lNameTherapistPT);
        therapistLicense = view.findViewById(R.id.licenseTherapistPT);
        therapistemail = view.findViewById(R.id.TherapistEmail);
        therapistAddress = view.findViewById(R.id.therapistAddress);
        password = view.findViewById(R.id.passwordTherapist);
        repass = view.findViewById(R.id.passwordTherapist2);
        female = (CheckBox) view.findViewById(R.id.femaleTherapistCB);
        male = (CheckBox) view.findViewById(R.id.maleTherapistCB);
        phone = (CheckBox) view.findViewById(R.id.phoneCB);
        text = (CheckBox) view.findViewById(R.id.textCB);
        zoom = (CheckBox) view.findViewById(R.id.zoomCB);
        person = (CheckBox) view.findViewById(R.id.inPersonCB);
        therapistSignUp = (Button)view.findViewById((R.id.signupTherapistButton));
        DBHelper db = new DBHelper(getActivity());

        therapistSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstname.getText().toString();
                String lastName = lastname.getText().toString();
                String license = therapistLicense.getText().toString();
                Integer licenseInt = Integer.valueOf(license);
                String address = therapistAddress.getText().toString();
                String email2 = therapistemail.getText().toString();
                String password2 = password.getText().toString();
                String repass2 = repass.getText().toString();
                Boolean isFemale = female.isChecked();
                Boolean isMale = male.isChecked();
                Boolean doesPhone = phone.isChecked();
                Boolean doesText = text.isChecked();
                Boolean doesZoom = zoom.isChecked();
                Boolean doesInPerson= person.isChecked();
                String gender=" ";
                Integer phone2;
                Integer text2;
                Integer zoom2;
                Integer person2;

                //making sure password and confirm password is the same
                if(!password2.equals(repass2)){
                    Toast.makeText(getActivity(),"Make sure your passwords match",Toast.LENGTH_SHORT).show();
                }else
                //checking gender
                if(isMale==true && isFemale==true){
                    Toast.makeText(getActivity(),"Please choose one gender",Toast.LENGTH_SHORT).show();
                }else if (isMale==true){
                    gender = "male";
                }else{
                    gender = "female";
                }

                //checking which platforms are checked and assigning 1 if true 0 if false
                if(doesPhone){
                    phone2 = 1;
                }else{
                    phone2 = 0;
                }
                if(doesText){
                    text2 = 1;
                }else{
                    text2 = 0;
                }
                if(doesZoom){
                    zoom2 = 1;
                }else{
                    zoom2 = 0;
                }
                if(doesInPerson){
                    person2 = 1;
                }else{
                    person2 = 0;
                }
                //checking if client already exists else adding to database and going immediately to profile
                if(db.checkTherapistEmailExists(email2)){
                    Toast.makeText(getActivity(),"User with this email already exists",Toast.LENGTH_SHORT).show();
                }else{
                    if(db.insertTherapist(licenseInt,email2,password2,firstName,lastName,gender,phone2,text2,zoom2,person2,address)){
                        //saving the email of person who is logged in
//                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString(EMAIL, email2);
//                        editor.apply();
                        //go to therapists profile

                        Intent intent = new Intent(getActivity(),TherapistProfile.class);
                        intent.putExtra("USER_ID",licenseInt);
                        startActivity(intent);
                    };
                }

            }
        });
        return view;
    }
}