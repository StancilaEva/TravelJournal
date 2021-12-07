package com.example.traveljournalstancilaeva.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveljournalstancilaeva.R;


public class ContactUsFragment extends Fragment {




    public ContactUsFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        if(view!=null){
            TextView phone = view.findViewById(R.id.tv_phone_number);
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0799964940"));
                    if (ActivityCompat.checkSelfPermission(view.getContext(),
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(view.getContext(),"Denied",Toast.LENGTH_SHORT).show();
                    }


                    else

                    startActivity(intent);

                }
            });
        }
        return view;
    }
}