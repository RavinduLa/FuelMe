package com.example.fuelme.ui.mainscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelme.R;
import com.example.fuelme.commonconstants.StationCommonConstants;
import com.example.fuelme.models.FuelStation;

public class StationSingleViewActivity extends AppCompatActivity {

    String TAG = "demo";
    TextView textViewStationName, textViewStationAddress, textViewOpenStatus,
            textViewPetrolAvailabilityStatus, textViewDieselAvailabilityStatus, textViewPetrolQueueLength, textViewDieselQueueLength;
    Button petrolQueueUpdateButton, dieselQueueUpdateButton, stationPhoneNumberButton, stationEmailButton, websiteButton,
            viewFeedbackButton, viewNoticesButton;
    SharedPreferences sharedPreferences;
    FuelStation fuelStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_single_view);

        //instantiate toolbar and set the back button
        Toolbar toolbar = (Toolbar) findViewById(R.id.station_single_view_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set the views by id
        textViewStationName = findViewById(R.id.txtView_stationName_station_single_view);
        textViewStationAddress = findViewById(R.id.txtView_stationAddress_station_single_view);
        textViewOpenStatus = findViewById(R.id.txtView_openStatus_station_single_view);
        textViewPetrolAvailabilityStatus = findViewById(R.id.txtView_petrol_availability_status_station_single_viw);
        textViewPetrolQueueLength = findViewById(R.id.txtView_petrol_queue_length_station_single_viw);
        textViewDieselAvailabilityStatus = findViewById(R.id.txtView_diesel_availability_status_station_single_viw);
        textViewDieselQueueLength = findViewById(R.id.txtView_diesel_queue_length_station_single_viw);
        petrolQueueUpdateButton = findViewById(R.id.btn_petrolQueueUpdate_station_single_view);
        dieselQueueUpdateButton = findViewById(R.id.btn_dieselQueueUpdate_station_single_view);
        stationPhoneNumberButton = findViewById(R.id.btn_phoneNumber_station_single_view);
        stationEmailButton = findViewById(R.id.btn_email_station_single_view);
        websiteButton = findViewById(R.id.btn_website_station_single_view);
        viewFeedbackButton = findViewById(R.id.btnViewFeedback_station_single_view);
        viewNoticesButton = findViewById(R.id.btnViewNotices_station_single_view);

        //instantiate shared preferences
        //sharedPreferences = getSharedPreferences(StationCommonConstants.STATION_SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //get the extras
        Bundle extras  = getIntent().getExtras();
        if (extras != null){
            FuelStation fuelStation = (FuelStation) extras.getSerializable("selected_fuel_station"); //get the serializable and cast into fuel station object
            this.fuelStation = fuelStation;

            //get string values of queue lengths
            String petrolQueueLengthString = String.valueOf(fuelStation.getPetrolQueueLength());
            String dieselQueueLengthString = String.valueOf(fuelStation.getDieselQueueLength());

            //open status
            String openStatus = "Not Assigned";

            if (fuelStation.getOpenStatus().equalsIgnoreCase("open")){
                openStatus = "Open";
            }
            else if (fuelStation.getOpenStatus().equalsIgnoreCase("closed")){
                openStatus = "Closed";
            }

            //availability strings
            String petrolAvailabilityStatus = "Not Assigned";
            String dieselAvailabilityStatus  = "Not Assigned";

            //set availability strings based on the availability to maintain capitalization
            if (fuelStation.getPetrolStatus().equalsIgnoreCase("available")){
                petrolAvailabilityStatus = "Available";
            }
            else if(fuelStation.getPetrolStatus().equalsIgnoreCase("unavailable")){
                petrolAvailabilityStatus = "Unavailable";
            }

            if (fuelStation.getDieselStatus().equalsIgnoreCase("available")){
                dieselAvailabilityStatus = "Available";
            }
            else if(fuelStation.getDieselStatus().equalsIgnoreCase("unavailable")){
                dieselAvailabilityStatus = "Unavailable";
            }

            //set the details of the station in the views
            textViewStationName.setText(fuelStation.getStationName());
            textViewStationAddress.setText(fuelStation.getStationAddress());
            textViewOpenStatus.setText(openStatus);
            textViewPetrolAvailabilityStatus.setText(petrolAvailabilityStatus);
            textViewPetrolQueueLength.setText(petrolQueueLengthString);
            textViewDieselAvailabilityStatus.setText(dieselAvailabilityStatus);
            textViewDieselQueueLength.setText(dieselQueueLengthString);
            stationPhoneNumberButton.setText(fuelStation.getStationPhoneNumber());
            stationEmailButton.setText(fuelStation.getStationEmail());
            websiteButton.setText(fuelStation.getStationWebsite());

            //change station open text view color based on open status
            if (fuelStation.getOpenStatus().equalsIgnoreCase("open")){
                //change color to green
                textViewOpenStatus.setTextColor(Color.parseColor("#0E8921"));
            }
            else if (fuelStation.getOpenStatus().equalsIgnoreCase("closed")){
                //change color to red
                textViewOpenStatus.setTextColor(Color.parseColor("#FF0000"));
            }

            //change petrol status text view color based on petrol availability
            if (fuelStation.getPetrolStatus().equalsIgnoreCase("available")){
                //change color to green
                textViewPetrolAvailabilityStatus.setTextColor(Color.parseColor("#0E8921"));
            }
            else if (fuelStation.getPetrolStatus().equalsIgnoreCase("unavailable")){
                //change color to red
                textViewPetrolAvailabilityStatus.setTextColor(Color.parseColor("#FF0000"));
            }

            //change diesel status text view color based on diesel availability
            if (fuelStation.getDieselStatus().equalsIgnoreCase("available")){
                //change color to green
                textViewDieselAvailabilityStatus.setTextColor(Color.parseColor("#0E8921"));
            }
            else if (fuelStation.getDieselStatus().equalsIgnoreCase("unavailable")){
                //change color to red
                textViewDieselAvailabilityStatus.setTextColor(Color.parseColor("#FF0000"));
            }

            sharedPreferences = getSharedPreferences(StationCommonConstants.STATION_SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String currentlyJoinedQueueStationId = sharedPreferences.getString(StationCommonConstants.IN_QUEUE_STATION_ID,"");
            String queueType = sharedPreferences.getString(StationCommonConstants.QUEUE, "");

            Log.d(TAG, "User is in queue in station with id : " + currentlyJoinedQueueStationId);
            Log.d(TAG, "User is in queue type : " + queueType);
            Log.d(TAG, "User queue station is empty : " + currentlyJoinedQueueStationId.isEmpty());

            updateQueueButtons(currentlyJoinedQueueStationId, queueType, fuelStation.getId());

        }


    }

    //update the queue buttons based on the shared preferences
    public void updateQueueButtons(String currentlyJoinedQueueStationId, String queueType, String viewFuelStationId){

        //check if the currently joined queue's station id is this view's station id
        if (viewFuelStationId.equalsIgnoreCase(currentlyJoinedQueueStationId)){
            //check which is the joined queue

            if (queueType.equalsIgnoreCase("petrol")){
                //user is in this station's petrol queue
                //change the petrol queue button attributes
                petrolQueueUpdateButton.setText("Leave the queue");
                petrolQueueUpdateButton.setBackgroundColor(Color.parseColor("#F3AD25"));

                //disable the diesel queue update button
                dieselQueueUpdateButton.setEnabled(false);
            }
            else if (queueType.equalsIgnoreCase("diesel")){
                //user is in this station's diesel queue
                //change the diesel queue button attributes
                dieselQueueUpdateButton.setText("Leave the queue");
                dieselQueueUpdateButton.setBackgroundColor(Color.parseColor("#F3AD25"));

                //disabled the petrol queue update button
                petrolQueueUpdateButton.setEnabled(false);
            }
        }
        else {
            // if the currently joined queue's station id is not this view's station id, user is in a different queue
            //then the user cannot join these queues
            //disable both queue update buttons

            //check if currentlyJoinedQueueStationId is empty
            if (currentlyJoinedQueueStationId.isEmpty()){
                //if the currentlyJoinedQueueStationId is empty user is not in a queue

                //reset button attributes
                //reset the queue button attributes
                petrolQueueUpdateButton.setText("Join the queue");
                petrolQueueUpdateButton.setBackgroundColor(Color.parseColor("#0E8921")); //color green
                dieselQueueUpdateButton.setText("Join the Queue");
                dieselQueueUpdateButton.setBackgroundColor(Color.parseColor("#0E8921")); //color green

                //enable both the buttons
                petrolQueueUpdateButton.setEnabled(true);
                dieselQueueUpdateButton.setEnabled(true);
            }
            else {
                //if the currentlyJoinedQueueStationId is not empty user is in a queue of different station
                //disable both the buttons
                petrolQueueUpdateButton.setEnabled(false);
                dieselQueueUpdateButton.setEnabled(false);
            }

        }



    }

    //button click to update the petrol queue
    public void  petrolQueueUpdateButtonClick(View view){
        if (isUserNotInAQueue()){
            //if user is not in a queue user is requesting to and can join this queue
            sharedPreferences = getSharedPreferences(StationCommonConstants.STATION_SHARED_PREF_NAME, Context.MODE_PRIVATE);

            //set the station id and queue type in ths shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit(); //get the editor
            editor.putString(StationCommonConstants.IN_QUEUE_STATION_ID, this.fuelStation.getId()); //get this view's fuel station id
            editor.putString(StationCommonConstants.QUEUE,"petrol"); //set the queue type
            editor.apply(); //apply the changes

            String currentlyJoinedQueueStationId = sharedPreferences.getString(StationCommonConstants.IN_QUEUE_STATION_ID,"");
            String queueType = sharedPreferences.getString(StationCommonConstants.QUEUE, "");

            updateQueueButtons(currentlyJoinedQueueStationId, queueType, fuelStation.getId());
        }
        else {
            //the user is already in a queue
            //check whether it is this station's this queue
            //if it is this station's this queue, user is leaving
            //handle the leaving logic
            sharedPreferences = getSharedPreferences(StationCommonConstants.STATION_SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String currentlyJoinedQueueStationId = sharedPreferences.getString(StationCommonConstants.IN_QUEUE_STATION_ID,"");
            String queueType = sharedPreferences.getString(StationCommonConstants.QUEUE, "");

            Log.d(TAG, "User is in queue in station with id : " + currentlyJoinedQueueStationId);
            Log.d(TAG, "User is in queue type : " + queueType);
            if (currentlyJoinedQueueStationId.equalsIgnoreCase(this.fuelStation.getId())){
                //user is in a queue of this station

                //check whether the user in this queue
                queueType = sharedPreferences.getString(StationCommonConstants.QUEUE, "");
                if (queueType.equalsIgnoreCase("petrol")){
                    //user is in this queue
                    //user is leaving the queue
                    SharedPreferences.Editor editor = sharedPreferences.edit(); //get the editor
                    editor.remove(StationCommonConstants.IN_QUEUE_STATION_ID); //remove station id
                    editor.remove(StationCommonConstants.QUEUE); //remove queue
                    editor.apply(); //apply the changes

                    currentlyJoinedQueueStationId = sharedPreferences.getString(StationCommonConstants.IN_QUEUE_STATION_ID,"");
                    queueType = sharedPreferences.getString(StationCommonConstants.QUEUE, "");

                    updateQueueButtons(currentlyJoinedQueueStationId, queueType, fuelStation.getId());
                }
            }
            else {
                //if the user is not in this station's this queue, user cannot join this queue too
                //display the related messages
                Toast.makeText(this, "You are already in a different queue", Toast.LENGTH_SHORT).show();
            }


        }

    }

    //button click to update the diesel queue
    public void dieselQueueUpdateButtonClick(View view){

        if (isUserNotInAQueue()){
            //if user is not in a queue user is requesting to and can join this queue
            sharedPreferences = getSharedPreferences(StationCommonConstants.STATION_SHARED_PREF_NAME, Context.MODE_PRIVATE);

            //set the station id and queue type in ths shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit(); //get the editor
            editor.putString(StationCommonConstants.IN_QUEUE_STATION_ID, this.fuelStation.getId()); //get this view's fuel station id
            editor.putString(StationCommonConstants.QUEUE,"diesel"); //set the queue type
            editor.apply(); //apply the changes

            String currentlyJoinedQueueStationId = sharedPreferences.getString(StationCommonConstants.IN_QUEUE_STATION_ID,"");
            String queueType = sharedPreferences.getString(StationCommonConstants.QUEUE, "");

            updateQueueButtons(currentlyJoinedQueueStationId, queueType, fuelStation.getId());
        }
        else {
            //the user is already in a queue
            //check whether it is this station's this queue
            //if it is this station's this queue, user is leaving
            //handle the leaving logic
            sharedPreferences = getSharedPreferences(StationCommonConstants.STATION_SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String currentlyJoinedQueueStationId = sharedPreferences.getString(StationCommonConstants.IN_QUEUE_STATION_ID,"");
            String queueType = sharedPreferences.getString(StationCommonConstants.QUEUE, "");

            Log.d(TAG, "User is in queue in station with id : " + currentlyJoinedQueueStationId);
            Log.d(TAG, "User is in queue type : " + queueType);
            if (currentlyJoinedQueueStationId.equalsIgnoreCase(this.fuelStation.getId())){
                //user is in a queue of this station

                //check whether the user in this queue
                queueType = sharedPreferences.getString(StationCommonConstants.QUEUE, "");
                if (queueType.equalsIgnoreCase("diesel")){
                    //user is in this queue
                    //user is leaving the queue
                    SharedPreferences.Editor editor = sharedPreferences.edit(); //get the editor
                    editor.remove(StationCommonConstants.IN_QUEUE_STATION_ID); //remove station id
                    editor.remove(StationCommonConstants.QUEUE); //remove queue
                    editor.apply(); //apply the changes

                    currentlyJoinedQueueStationId = sharedPreferences.getString(StationCommonConstants.IN_QUEUE_STATION_ID,"");
                    queueType = sharedPreferences.getString(StationCommonConstants.QUEUE, "");

                    updateQueueButtons(currentlyJoinedQueueStationId, queueType, fuelStation.getId());
                }
            }
            else {
                //if the user is not in this station's this queue, user cannot join this queue too
                //display the related messages
                Toast.makeText(this, "You are already in a different queue", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //method to check whether the user is not in a queue
    //returns true of user is not in a queue
    //returns false if user is in a queue
    public boolean isUserNotInAQueue(){
        sharedPreferences = getSharedPreferences(StationCommonConstants.STATION_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String currentlyJoinedQueueStationId = sharedPreferences.getString(StationCommonConstants.IN_QUEUE_STATION_ID,"");

        if (currentlyJoinedQueueStationId.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

    //method called when toolbar back button is clicked
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //handle back press
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}