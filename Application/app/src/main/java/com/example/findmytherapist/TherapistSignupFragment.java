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

    EditText firstname,lastname,therapistLicense,password,repass;
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
        View view = inflater.inflate(R.layout.fragment_client_signup, container, false);
        firstname = view.findViewById(R.id.fNameCTherapistPT);
        lastname = view.findViewById(R.id.lNameTherapistPT);
        therapistLicense = view.findViewById(R.id.licenseTherapistPT);
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
                //email needs to be added
                String password2 = password.getText().toString();
                String repass2 = repass.getText().toString();
                Boolean isFemale = female.isChecked();
                Boolean isMale = male.isChecked();
                Boolean doesPhone = phone.isChecked();
                Boolean doesText = text.isChecked();
                Boolean doesZoom = zoom.isChecked();
                Boolean doesInPerson= person.isChecked();
                String gender=" ";
                int doesPhone2;
                int doesText2;
                int doesZoom2;
                int doesInPerson2;

                //if it's platforms is checked were giving value of 1 else giving value of 0
                if(doesPhone){
                    doesPhone2 = 1;
                }else{
                    doesPhone2 = 0;
                }
                if(doesText){
                    doesText2 = 1;
                }else{
                    doesText2 = 0;
                }
                if(doesZoom){
                    doesZoom2 = 1;
                }else{
                    doesZoom2 = 0;
                }
                if(doesInPerson){
                    doesInPerson2 = 1;
                }else{
                    doesInPerson2 = 0;
                }
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
               /* if(db.checkClientEmailExists(email2)){
                    Toast.makeText(getActivity(),"User with this email already exists",Toast.LENGTH_SHORT).show();
                }else{
                    if(db.insertTherapist(license,email2,password2,firstName,lastName,gender,address,doesPhone2,doesText2,doesZoom2,doesInPerson2)){
                        Intent intent = new Intent(getActivity(),ClientProfile.class);
                        startActivity(intent);
                    };
                }*/

            }
        });
        return view;
    }
}