package com.example.ashwin.madlab3;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class SQLiteDbDemo extends ActionBarActivity {
    static final String[] from = {SQLiteAdapter.COLUMN_ROLLNO, SQLiteAdapter.COLUMN_NAME};
    static final int[] to = {R.id.tvRollNo, R.id.tvName};

    ListView list;
    Cursor cursor;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_db_demo);
        list = (ListView) findViewById(R.id.listView);

        SQLiteAdapter dbAdapter = new SQLiteAdapter(this);
        dbAdapter.openDB();
        cursor = dbAdapter.displayAll();
        adapter = new SimpleCursorAdapter(this, R.layout.layout_row_of_students, cursor, from, to);

        list.setAdapter(adapter);
        dbAdapter.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlite_db_demo, menu);
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
}
