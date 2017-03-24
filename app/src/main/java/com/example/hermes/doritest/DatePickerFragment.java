package com.example.hermes.doritest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

/**
 * Created by Hermes on 21/03/2017.
 * This class is used to instantiate a date picker dialog and interact with it.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    onDateSelected mCallback;

    /**
     * This interface is used to communicate the year, month and day parameters to the main activity.
     */

    public interface onDateSelected{
        public void onDateSelect(int year, int month, int day);
    }

    /**
     * This method is used to instantiate and return a date picker dialog.
     * @param savedInstanceState this parameter stores the initial state of the application before
     *                           it instantiated the dialog.
     * @return an instance of the date picker dialog.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), this,year,month,day);

    }

    /**
     * This method is used to use the date that the user selected and use it in a specific
     * functionality.
     *
     * @param view the date picker instance.
     * @param year the date that the user has picked.
     * @param month the month that the user has picked.
     * @param dayOfMonth the day that the user has picked.
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mCallback.onDateSelect(year, month, dayOfMonth);
    }

    /**
     * This method is used to connect the fragment to the activity and we make sure that the
     * activity implemented the onDateSelected interface.
     * @param context is the activity that will implement the onDateSelected interface.
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (onDateSelected)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement onDateSelected method.");
        }
    }
}
