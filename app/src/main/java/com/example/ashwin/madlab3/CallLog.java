package com.example.ashwin.madlab3;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class CallLog extends ActionBarActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        tv = (TextView) findViewById(R.id.tvCallLogs);
        callLog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_call_log, menu);
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

    private void callLog() {

        StringBuffer str = new StringBuffer();

        String number, name, type = null;
        int tempType;
        @Deprecated
        Cursor cursor = managedQuery(Uri.parse("content://call_log/calls"), null, null, null, android.provider.CallLog.Calls.DATE + " DESC");

        while (cursor.moveToNext()) {
            number = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER));

            name = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME));
            if (name == null)
                name = "Unknown";

            tempType = Integer.parseInt(cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.TYPE)));

            switch (tempType) {
                case 1:
                    type = "Incoming Call";
                    break;
                case 2:
                    type = "Outgoing Call";
                    break;
                case 3:
                    type = "Missed Call";
                    break;
            }

            str.append("Number: " + number + "\nName: " + name + "\nCall Type: " + type + "\n----------------------------------------------\n");
            tv.setText(str.toString());
        }
    }
}
