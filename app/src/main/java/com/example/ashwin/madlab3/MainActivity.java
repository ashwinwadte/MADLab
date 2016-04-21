package com.example.ashwin.madlab3;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {
    ListView list;
    String[] classes = {"CallLog - Lab3", "FileHandling - Lab4", "DbMenu - Lab5","MapsActivity - Lab6", "HTTPDemo - Lab7", "TCPServer - Lab8", "CameraActivity - Lab9", "AndroidDatabaseManager"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list_activities);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String tempclass = classes[position];

        String[] classname = tempclass.split(" - ");
        Intent intent = null;
        try {
            intent = new Intent(this, Class.forName("com.example.ashwin.madlab3." + classname[0]));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}