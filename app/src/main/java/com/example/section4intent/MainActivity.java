package com.example.section4intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.section4intent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public final int Contact_REQ_CODE = 1;
    String newUserName = "", newUserPhone = "",
            newUserWebLink = "", newUserPostalAddress ="", newUserMood = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //
        binding.mainBtnCreateNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        createContactActivity.class);
                startActivityForResult(intent, Contact_REQ_CODE);
            }
        });
        //
        binding.mainIvPhone.setVisibility(View.GONE);
        binding.mainIvWebPage.setVisibility(View.GONE);
        binding.mainIvLoction.setVisibility(View.GONE);
        binding.mainIvUserMood.setVisibility(View.GONE);
        binding.mainTvNewContactuserName.setVisibility(View.GONE);
        //
        binding.mainIvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + newUserPhone));
                startActivity(intent);
            }
        });

        binding.mainIvWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://" + newUserWebLink));
                startActivity(intent);
            }
        });

        binding.mainIvLoction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + newUserPostalAddress));
                startActivity(intent);
            }
        });



    }
    //

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Contact_REQ_CODE){
            if (resultCode == createContactActivity.Contact_Done_RES_CODE){
                binding.mainIvPhone.setVisibility(View.VISIBLE);
                binding.mainIvWebPage.setVisibility(View.VISIBLE);
                binding.mainIvLoction.setVisibility(View.VISIBLE);
                binding.mainIvUserMood.setVisibility(View.VISIBLE);
                //
                newUserName = data.getStringExtra(createContactActivity.Name_Key);
                binding.mainTvNewContactuserName.setText(getString(R.string.new_user_name) + " " + newUserName);
                binding.mainTvNewContactuserName.setVisibility(View.VISIBLE);
                newUserPhone = data.getStringExtra(createContactActivity.Phone_Key);
                newUserPostalAddress = data.getStringExtra(createContactActivity.PostalAddress_Key);
                newUserWebLink = data.getStringExtra(createContactActivity.Web_Key);
                newUserMood = data.getStringExtra(createContactActivity.MOOD_Key);
                //
                if (newUserMood.equals(createContactActivity.happy)){
                    binding.mainIvUserMood.setImageResource(R.drawable.satisfied);
                } else if (newUserMood.equals(createContactActivity.okay)) {
                    binding.mainIvUserMood.setImageResource(R.drawable.neutral);
                }else { // newUserMood.equals(createContactActivity.sad
                    binding.mainIvUserMood.setImageResource(R.drawable.very_dissatisfied);
                }

            }
            else {
                Toast.makeText(MainActivity.this, getString(R.string.no_data_passed_through),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}