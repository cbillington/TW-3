package com.travelexperts.travelpackages.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.travelexperts.travelpackages.PackageSearchActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 468364 on 4/8/2017.
 */

public class DatePickerDialogFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    public static final int FLAG_START_DATE = 0;
    public static final int FLAG_END_DATE = 1;
    private OnDateSelectedListener mCallback;

    private int flag = 0;

    /*@NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = DatePickerDialog.newInstance((DatePickerDialog
                        .OnDateSetListener)getActivity(), year,
                month, day);


        return dpd;
    }*/

    public void setFlag(int i) {
        flag = i;
    }



    public void setmCallback(OnDateSelectedListener mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mCallback.onDateSelected(view, year, monthOfYear, dayOfMonth);
    }

    public interface OnDateSelectedListener{
        void onDateSelected(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth);
    }
}
