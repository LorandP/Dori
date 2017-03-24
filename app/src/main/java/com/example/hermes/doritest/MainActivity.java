package com.example.hermes.doritest;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TimePickerFragment.onTimeSelected,
        DatePickerFragment.onDateSelected
{
    private int hour;
    private int minute;
    private int year;
    private int month;
    private int day;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
       // calendar.add(Calendar.HOUR, getHour());
        calendar.add(Calendar.SECOND, 7);
        //calendar.add(Calendar.PM, getMinute());
       // calendar.add(Calendar.YEAR, getYear());
        //calendar.add(Calendar.MONTH, getMonth());
       // calendar.add(Calendar.DAY_OF_MONTH, getDay());
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);

    }

    /**
     * This method returns the value of the private hour field.
     * @return the value of the hour field.
     */
    public int getHour() {
        return hour;
    }

    /**
     * This method assigns a value to the hour private field.
     * @param hour the private hour field.
     */

    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * This method returns the value of the private minute field.
     * @return the value of the minute field.
     */

    public int getMinute() {
        return minute;
    }

    /**
     * This method set's the value for the private minute field.
     * @param minute the private minute field
     */

    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * This method returns the value of the private year field.
     * @return the value of the private year field
     */

    public int getYear() {
        return year;
    }

    /**
     * This method set's the value for the private year field.
     * @param year the private year field.
     */

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * This method returns the value of the private month field.
     * @return the value of the private month field.
     */

    public int getMonth() {
        return month;
    }

    /**
     * This method set's the private month filed.
     * @param month the private month field.
     */

    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * This method returns the value of the private day field.
     * @return the value of the private day field.
     */

    public int getDay() {
        return day;
    }

    /**
     * This method sets the private day field.
     * @param day private day field.
     */

    public void setDay(int day) {
        this.day = day;
    }

    /**
     * This method is used to instantiate a time picker fragment when the button
     * "Set time" is pressed.
     *
     * @param view the current view this method is called from.
     */

    public void showTimePickerDialog(View view){
        DialogFragment dialogFragment = new TimePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     * This method is used to isntantiate a date picker fragment when the button
     * "Set date" is pressed.
     *
     * @param view the current view this method is called from.
     */

    public void showDatePickerDialog(View view){
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * This method is used to create a simple notification.
     * @param view the current view this method is called from.
     */
    public void startNotification(View view){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("My notification")
                .setContentText("Hello world from notification!");

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, ResultActivity.class);

        /* The stack builder object will contain an artificial back stack for the
        started Activity.
        This ensures that navigation backward from the Activity leads out of your application
        to your Home screen.
        */
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        //stackBuilder.addParentStack(ResultActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(123, mBuilder.build());
    }


    @Override
    public void onTimeSelect(int hour, int minute) {
        TextView textView = (TextView)findViewById(R.id.timeTextView);
        textView.setText(hour +":"+minute);
        setHour(hour);
        setMinute(minute);
    }

    @Override
    public void onDateSelect(int year, int month, int day) {
        TextView textView = (TextView)findViewById(R.id.dateTextView);
        textView.setText(day + " - "+ month + " - " + year);
        setYear(year);
        setMonth(month);
        setDay(day);
    }
}
