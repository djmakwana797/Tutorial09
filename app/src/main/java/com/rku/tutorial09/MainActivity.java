package com.rku.tutorial09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText edtTextData;
    TextView txtDisplayData;
    final String FILE_ASSETS ="data.json";
    final String FILE_INTERNAL ="help.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTextData = findViewById(R.id.edtTextData);
        txtDisplayData = findViewById(R.id.txtData);
    }

    public void readAssets(View view) {
        try{
            InputStream is = getAssets().open(FILE_ASSETS);
            InputStreamReader isReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isReader);

            String mLine;
            StringBuilder sb = new StringBuilder();
            while((mLine = reader.readLine())!=null){
                sb.append(mLine);
            }
            txtDisplayData.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFiles(View view) {
        try {
            FileInputStream fin = openFileInput(FILE_INTERNAL);
            int c;
            String temp = "";
            while((c = fin.read())!=-1){
                temp += String.valueOf(((char) c));
            }
            txtDisplayData.setText(temp);
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFiles(View view) {
        try{
            FileOutputStream fOut = openFileOutput(FILE_INTERNAL, MODE_PRIVATE);
            String data = edtTextData.getText().toString();
            fOut.write(data.getBytes());
            fOut.close();
            edtTextData.setText("");
            Toast.makeText(this, "Stored data Successfully.", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}