package com.example.myapplication.timePicker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;
/************************************************/
/******************************/

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "TimePickerFragment";

    public interface OnInputSelected{
        void sendInput(int input1,int input2);
    }
    public OnInputSelected mOnInputSelected;


    public TimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker

        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

    }

    int input1 = -1;
    int input2 = -1;

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        input1 = hourOfDay;
        input2 = minute;
        if(input1 != -1){
            mOnInputSelected.sendInput(input1,input2);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            //so important to use for passing data btw dialogFragment and fragment
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }
}