package com.codes.quizme;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.codes.quizme.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    FragmentProfileBinding binding;
    FirebaseFirestore database;
    ProgressDialog dialog;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentProfileBinding.inflate(inflater,container,false);
        database = FirebaseFirestore.getInstance();
        dialog =  new ProgressDialog(getContext());
        dialog.setMessage("Updating..");



        binding.updateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailPro = binding.emailProfile.getText().toString();
                String namePro = binding.nameProfile.getText().toString();
                String passPro = binding.passwordProfile.getText().toString();
                dialog.show();
                database.collection("users")
                        .document(FirebaseAuth.getInstance().getUid())
                        .update("email",emailPro
                        ,"name",namePro,"pass",passPro).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), emailPro, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        return binding.getRoot();
    }
}