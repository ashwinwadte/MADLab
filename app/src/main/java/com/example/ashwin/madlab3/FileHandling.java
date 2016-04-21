package com.example.ashwin.madlab3;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileHandling extends ActionBarActivity implements View.OnClickListener {
    EditText editText;
    TextView textView;
    Button bRead, bWrite;
    BufferedWriter writer;
    BufferedReader reader;
    String string_read = "";

    File sdcard, myDir, file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_handling);
        editText = (EditText) findViewById(R.id.et_WriteData);
        textView = (TextView) findViewById(R.id.tv_Contents);
        bRead = (Button) findViewById(R.id.b_Read);
        bWrite = (Button) findViewById(R.id.b_Write);

        bRead.setOnClickListener(this);
        bWrite.setOnClickListener(this);

        sdcard = Environment.getExternalStorageDirectory();
        myDir = new File(sdcard.getAbsolutePath() + "/MyDir");
        myDir.mkdirs();

        file = new File(myDir, "f1.txt");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_handling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.b_Write:
                String textToSave = editText.getText().toString();

                try {
                    writer = new BufferedWriter(new FileWriter(file, true));
                    writer.write(textToSave + " ");
                    Toast.makeText(this, "Written successfully!", Toast.LENGTH_SHORT).show();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.b_Read:

                try {
                    reader = new BufferedReader(new FileReader(file));
                    do{
                        string_read += reader.readLine();
                    }while(reader.readLine() != null);

                    textView.setText(string_read);
                    reader.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
