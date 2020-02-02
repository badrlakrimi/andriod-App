package com.example.myapplication.fragment_user;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.recyclerview.ExampleAdapter;
import com.example.myapplication.recyclerview.ExampleItem;
import com.example.myapplication.timePicker.AlertReceiver;
import com.example.myapplication.timePicker.TimePickerFragment;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.myapplication.timePicker.App.CHANNEL_1_ID;

public class reminderFragment extends Fragment implements TimePickerFragment.OnInputSelected {

    private static final String TAG = "reminderFragment";

    private  ArrayList<ExampleItem> exampleItems;
    private FloatingActionButton actionButton;


    public String hour;

    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View v  = inflater.inflate(R.layout.fragment_reminder,container,false);

        //notification use
        notificationManager = NotificationManagerCompat.from(getContext());

        createExampleList();
        buildRecycleView(v);

        addButton(v);

        return v;

    }



    public void addButton(View v){

        actionButton = v.findViewById(R.id.btn_add_alarme);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //add the reminder

                DialogFragment newFragment  = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "time Picker");
                //so important to use for passing data btw dialogFragment and fragment
                newFragment.setTargetFragment(reminderFragment.this, 1);
                newFragment.getArguments();

            }
        });

    }

    public void createExampleList(){
        exampleItems = new ArrayList<>();
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"08:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"09:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"10:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"11:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"12:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"13:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"14:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"15:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"16:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"17:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"18:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"19:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"20:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"21:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"22:00",1));
        exampleItems.add(new ExampleItem(R.drawable.ic_delete,"23:00",1));

    }

    public void buildRecycleView(View v){
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(v.getContext());
        adapter = new ExampleAdapter(exampleItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                  int AlaramId = exampleItems.get(position).getPos();
//                Toast.makeText(getContext(), var , Toast.LENGTH_SHORT).show();

                AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getContext(), AlertReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), AlaramId, intent, 0);
                alarmManager.cancel(pendingIntent);

                exampleItems.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
    }
    @Override
    public void sendInput(int houreOfday,int minute) {
        hour = ""+houreOfday+":"+minute;
        Log.d(TAG, "sendInput: found incoming input: " + hour);
        exampleItems.add(0,new ExampleItem(R.drawable.ic_delete,hour,houreOfday+minute));
        adapter.notifyItemInserted(0);


        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, houreOfday);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);


        startAlarm(c,houreOfday+minute);
    }



    private void startAlarm(Calendar c,int i) {
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), i, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }


        if(Build.VERSION.SDK_INT < 23){
            if(Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            }
        } else {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
        recyclerView.smoothScrollToPosition(0);
    }

    // Notification functions
    private NotificationManagerCompat notificationManager;

}


