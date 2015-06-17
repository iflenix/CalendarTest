package com.storm.calendartest;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private boolean isFirst = true;

    public void onGetEventsButtonClick (View v){
        ContentResolver cr = getContentResolver();

        Cursor calCursor = cr.query(CalendarContract.Events.CONTENT_URI,null,null,null,null);
        if(isFirst) {
            if (calCursor.moveToFirst()) {

                Toast.makeText(this, calCursor.getString(calCursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE)), Toast.LENGTH_LONG).show();
                isFirst = false;
            }

        }else if (calCursor.moveToNext()){

                Toast.makeText(this, calCursor.getString(calCursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE)), Toast.LENGTH_LONG).show();
            }

        calCursor.close();



    }

}
