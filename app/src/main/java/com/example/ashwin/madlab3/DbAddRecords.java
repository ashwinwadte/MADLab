package com.example.ashwin.madlab3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DbAddRecords extends ActionBarActivity implements View.OnClickListener {
    EditText grNo, name, rollNo, division, Class;
    Button add;
    SQLiteAdapter dbadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_add_records);
        dbadapter = new SQLiteAdapter(this);

        grNo = (EditText) findViewById(R.id.etGRNo);
        name = (EditText) findViewById(R.id.etName);
        rollNo = (EditText) findViewById(R.id.etRollNo);
        division = (EditText) findViewById(R.id.etDivision);
        Class = (EditText) findViewById(R.id.etClass);
        add = (Button) findViewById(R.id.bAdd);

        add.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_db_add_records, menu);
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
        dbadapter.openDB();
        dbadapter.insertInDB(Integer.parseInt(grNo.getText().toString()), name.getText().toString(), Integer.parseInt(rollNo.getText().toString()), division.getText().toString(), Class.getText().toString());
        dbadapter.closeDB();
        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
    }
}
