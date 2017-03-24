package com.example.hermes.doritest;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Hermes on 21/03/2017.
 * This class is a used to extend a fragment that implements a time picker.
 * Implements the TimePickerDialog interface that returns an instance of a time picker dialog and
 * receives a callback when the user sets the time.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    onTimeSelected mCallback;

    /**
     * This interface is used to open a communication between an activity and this fragmeny by
     * sending the hour and minute parameters to the activity that will
     * implement this interface.
     */

    public interface onTimeSelected{
        public void onTimeSelect(int hour, int minute);
    }


    /**
     * This method returns an instance of a time picker dialog.
     * @param savedInstanceState the application can return to its previous state using this parameter
     * @return an instance of a time picker dialog.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values of the picker
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        View listView = getActivity().findViewById(R.id.timeTextView);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(),this,hour,minute, DateFormat.is24HourFormat(getActivity()));
    }

    /**
     * This method is used to take actions with the time chosen by the user.
     * @param view the time picker.
     * @param hourOfDay the hour chosen by the user.
     * @param minute the minute chosen by the user.
     */

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        mCallback.onTimeSelect(hourOfDay,minute);
    }

    /**
     * This method is used to connect the fragment to the activity by making sure that the
     * activity that will connect to this interface will implement the onTimeSelected interface.
     *
     * @param context the activity that will implement the onTimeSelected interface.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (onTimeSelected) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" must implement " +
                    "OnHeadlineSelectedListener method.");
        }
    }
}
