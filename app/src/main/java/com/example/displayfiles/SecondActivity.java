package com.example.displayfiles;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    TextView textOutput;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        textOutput = findViewById(R.id.textOutput);

        Bundle bundle = getIntent().getExtras();

        //String name =  bundle.getString("name");
        String age  = (String) bundle.getString("age");
        //String email = bundle.getString("email");


        textOutput.setText(age);
        textOutput.setText(readDataFromFile());




    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readDataFromFile() {
        File file = new File(getFilesDir(), "Logs.txt");
        int size = (int) file.length();
        byte[] contents = new byte[size];
        try(FileInputStream fileInputStream = new FileInputStream(file)){
            fileInputStream.read(contents);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new String(contents);
    }
}