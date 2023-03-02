package com.example.section4intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.section4intent.databinding.ActivityCreateContactBinding;
import com.example.section4intent.databinding.ActivityMainBinding;

public class createContactActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityCreateContactBinding binding;
    public static final String Name_Key = "userName";
    public static final String Phone_Key = "userPhone";
    public static final String Web_Key = "userWebAddress";
    public static final String PostalAddress_Key = "userPostalAddress";
    public static final String MOOD_Key = "userMOOD";
    public static final String happy = "happy";
    public static final String okay = "okay";
    public static final String sad = "sad";
    public static final int Contact_Done_RES_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //
        binding.contactIvHappy.setOnClickListener(this);
        //
        binding.contactIvOkay.setOnClickListener(this);
        //
        binding.contactIvSad.setOnClickListener(this);
        //

    }

    @Override
    public void onClick(View view) {
        //
        String userName = binding.contactEdUserName.getText().toString();
        String userPhone = binding.contactEdUserPhone.getText().toString();
        String userWebAddress = binding.contactEdUserWebPage.getText().toString();
        String userPostalAddress = binding.contactEdUserPostalAddress.getText().toString();
        //
        if (userName.isEmpty() ||
            userPhone.isEmpty() ||
            userWebAddress.isEmpty() ||
            userPostalAddress.isEmpty()){
            Toast.makeText(createContactActivity.this, R.string.fill_all_fields_valid_data,
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent();

            intent.putExtra( Name_Key, userName.trim());
            intent.putExtra( Phone_Key, userPhone.trim());
            intent.putExtra( Web_Key, userWebAddress.trim());
            intent.putExtra( PostalAddress_Key, userPostalAddress.trim());

            if (view.getId() == R.id.contact_iv_happy){
                intent.putExtra( MOOD_Key, happy);
            } else if (view.getId() == R.id.contact_iv_okay) {
                intent.putExtra( MOOD_Key, okay);
            } else {// (view.getId() == R.id.contact_iv_sad)
                intent.putExtra( MOOD_Key, sad);
            }
            //
            setResult(Contact_Done_RES_CODE, intent);
            createContactActivity.this.finish();


        }

    }
}