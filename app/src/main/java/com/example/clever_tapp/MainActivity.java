package com.example.clever_tapp;

import android.app.NotificationManager;
import android.os.Bundle;

import com.clevertap.android.sdk.CleverTapAPI;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.clever_tapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;

public class MainActivity extends AppCompatActivity {
    CleverTapAPI clevertapDefaultInstance;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    Button pushEventBtn;
    Button sendEventBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        ActivityLifecycleCallback.register(this.getApplication());
        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendEventBtn = (Button)findViewById(R.id.buttonsend);
        pushEventBtn = (Button)findViewById(R.id.buttonpush);
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);


        sendEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        pushEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        pushToClevertap();
        pushNotification();

    }
    private void pushToClevertap(){
        // each of the below mentioned fields are optional
        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
        profileUpdate.put("Name", "Pham Gia Hien");    // String
        profileUpdate.put("Identity", 3102000);      // String or number
        profileUpdate.put("Email", "hienpg0310@gmail.com"); // Email address of the user
        profileUpdate.put("Phone", "+84392435608");   // Phone (with the country code, starting with +)
        profileUpdate.put("Gender", "M");             // Can be either M or F
        profileUpdate.put("DOB", new Date());         // Date of Birth. Set the Date object to the appropriate value first
// optional fields. controls whether the user will be sent email, push etc.
        profileUpdate.put("MSG-email", true);        // Disable email notifications
        profileUpdate.put("MSG-push", true);          // Enable push notifications
        profileUpdate.put("MSG-sms", false);          // Disable SMS notifications
        profileUpdate.put("MSG-whatsapp", true);      // Enable WhatsApp notifications
        ArrayList<String> stuff = new ArrayList<String>();
        stuff.add("bag");
        stuff.add("shoes");
        profileUpdate.put("MyStuff", stuff);                        //ArrayList of Strings
        String[] otherStuff = {"Jeans","Perfume"};
        profileUpdate.put("MyStuff", otherStuff);                   //String Array

        clevertapDefaultInstance.onUserLogin(profileUpdate);
        clevertapDefaultInstance.pushEvent("Product viewed");
        System.out.println("Send done");
    }

    private void onUserLogin(){}

    private void pushNotification(){
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"hienpgchanel","HienPG Chanel","Hhhhhhhhh",NotificationManager.IMPORTANCE_MAX,true);
// How to add a sound file to your app : https://developer.clevertap.com/docs/add-a-sound-file-to-your-android-app

//        //Delete Chanel
//        CleverTapAPI.deleteNotificationChannel(getApplicationContext(),"hienpgchanel");

    }

//    private void setUpService() {
////        String fcmRegId = FirebaseInstanceId.getInstance().getToken();
////        clevertapDefaultInstance.pushFcmRegistrationId(fcmRegId,true);
//        clevertapDefaultInstance.pushFcmRegistrationId("fcm:dAug0gQ3QNmhGNYhSJ4moo:APA91bGxEVp7XFfnEeVd5WC1DHamXSVqoHeiWsBO-mHqrW4m3YlOhahG57tfhkxqcw_Snjz4uYsu5qjmNllUqsHmsRdDZsUi0RecwzzaOHbQ-RwuKexJSMN1l53PCE4BfHFuVrZoOrSc",true);
//        //st up Notification
//        CleverTapAPI.createNotificationChannel(getApplicationContext(),"test_channel_id","Test Channel","Test Channel Created", NotificationManager.IMPORTANCE_MAX,true);
//        System.out.println("Is push registered: "+clevertapDefaultInstance.isPushPermissionGranted());
//        //set up api
//        clevertapDefaultInstance.enableDeviceNetworkInfoReporting(true);
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//
//    }
}