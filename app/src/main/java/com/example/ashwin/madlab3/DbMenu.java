package com.example.ashwin.madlab3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DbMenu extends ActionBarActivity implements View.OnClickListener {
    Button add, show;
    TextView dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_menu);
        add = (Button) findViewById(R.id.bAddStudent);
        show = (Button) findViewById(R.id.bShowStudentList);
        dateView = (TextView) findViewById(R.id.tvTime);

        add.setOnClickListener(this);
        show.setOnClickListener(this);

        //Time
        DateFormat[] df = new DateFormat[] {DateFormat.getDateTimeInstance()};
        String date = df[0].format(new Date());
        dateView.setText(dateView.getText().toString() + date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_db_menu, menu);
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
        Intent intent;
        switch (v.getId()) {
            case R.id.bAddStudent:
                try {
                    intent = new Intent(this, Class.forName("com.example.ashwin.madlab3.DbAddRecords"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bShowStudentList:
                try {
                    intent = new Intent(this, Class.forName("com.example.ashwin.madlab3.SQLiteDbDemo"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
