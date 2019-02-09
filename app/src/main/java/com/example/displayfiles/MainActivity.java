package com.example.displayfiles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editName, editAge, editEmail;
    Button btnSend, btnDisplay;

    TextView textShow;


    String message;

    private List<String> log = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAge = findViewById(R.id.editAge);
        editEmail = findViewById(R.id.editEmail);
        editName = findViewById(R.id.editName);

        btnSend = findViewById(R.id.btnSend);
        btnDisplay = findViewById(R.id.btnDisplay);

        textShow = findViewById(R.id.textShow);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //String name = editName.getText().toString();
                String age = editAge.getText().toString();
                //String email = editEmail.getText().toString();

                Bundle bundle = new Bundle();
                //bundle.putString("name", name);
                bundle.putString("age", age);
                //bundle.putString("email", email);

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });





    }

    private void handleSetEmail(){
        SharedPreferences sharedPreferences = getSharedPreferences("MysharedPreferences",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name = editEmail.getText().toString();
        editor.putString("name", name);
        editor.apply();
        textShow.setText( name);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void writeLogsToFile() {
        File file = new File(getFilesDir(), "Logs.txt");
        try (FileOutputStream fileOutputStream = openFileOutput("Logs.txt", Context.MODE_PRIVATE)) {

            StringBuilder stringBuilder = new StringBuilder();
            for (String result : log) {
                stringBuilder.append(result);
                stringBuilder.append("\n");
            }

            fileOutputStream.write(stringBuilder.toString().getBytes());

        } catch (IOException ioException) {
            Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
            ioException.printStackTrace();
        }
    }
}