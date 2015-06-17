package com.storm.calendartest;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;


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
    private static Cursor calCursor = null;

    public void onGetEventsButtonClick (View v){
        ContentResolver cr = getContentResolver();
        TextView tv = (TextView) findViewById(R.id.textview);

        if(calCursor == null)
            calCursor = cr.query(CalendarContract.Events.CONTENT_URI,null,null,null,null);


            if (calCursor.moveToNext() && isFirst) {

                tv.setText(String.valueOf(calCursor.getCount()) +" "+  calCursor.getPosition());

                Toast.makeText(this, calCursor.getString(calCursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE)), Toast.LENGTH_LONG).show();
                isFirst = false;
            }else if (!isFirst){
            //calCursor.moveToPosition(15);

                tv.setText(calCursor.getString(calCursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE))  + " позиция: " +  calCursor.getPosition());
                Toast.makeText(this, calCursor.getString(calCursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE)), Toast.LENGTH_LONG).show();
            }
       // calCursor.close();
    }

    public void onInsertEventButtonClick(View v){
        //Uri insertUri = Uri.withAppendedPath(CalendarContract.Events.CONTENT_URI);
        Intent intent = new Intent(Intent.ACTION_INSERT,CalendarContract.Events.CONTENT_URI);
        Calendar startTime = Calendar.getInstance();
        startTime.set(2015,Calendar.JUNE,18,15,0);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis());
        intent.putExtra(CalendarContract.Events.TITLE,"TEst event");
        startActivity(intent);


    }

}
