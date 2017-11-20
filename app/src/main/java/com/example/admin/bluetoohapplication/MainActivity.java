package com.example.admin.bluetoohapplication;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
                Log.d(TAG, "onReceive: discovery started.");
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog
                Log.d(TAG, "onReceive: discovery finished.");
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                Toast.makeText(context, "Found device " + device.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);
        adapter.startDiscovery();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);

        super.onDestroy();
    }


//    private ProgressDialog mProgressDlg;
//
//    private ArrayList<BluetoothDevice> mDeviceList = new ArrayList<BluetoothDevice>();
//
//    private BluetoothAdapter mBluetoothAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_main);
//
//        mBluetoothAdapter	= BluetoothAdapter.getDefaultAdapter();
//
//        mProgressDlg 		= new ProgressDialog(this);
//
//        mProgressDlg.setMessage("Scanning...");
//        mProgressDlg.setCancelable(false);
//        mProgressDlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//
//                mBluetoothAdapter.cancelDiscovery();
//            }
//        });
//
//        if (mBluetoothAdapter == null) {
//            showUnsupported();
//        } else {
//
//            if (mBluetoothAdapter.isEnabled()) {
//                showEnabled();
//            } else {
//                showDisabled();
//            }
//        }
//
//        IntentFilter filter = new IntentFilter();
//
//        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
//        filter.addAction(BluetoothDevice.ACTION_FOUND);
//        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
//        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//
//        Log.d(TAG, "onCreate: register receiver");
//        registerReceiver(mReceiver, filter);
//    }
//
//    @Override
//    public void onPause() {
//        if (mBluetoothAdapter != null) {
//            if (mBluetoothAdapter.isDiscovering()) {
//                mBluetoothAdapter.cancelDiscovery();
//            }
//        }
//
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        unregisterReceiver(mReceiver);
//
//        super.onDestroy();
//    }
//
//    private void showEnabled() {
//    }
//
//    private void showDisabled() {
//    }
//
//    private void showUnsupported() {
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//    }
//
//    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent) {
//            Log.d(TAG, "onReceive: ");
//            String action = intent.getAction();
//
//            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
//                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
//
//                if (state == BluetoothAdapter.STATE_ON) {
//                    showToast("Enabled");
//
//                    showEnabled();
//                }
//            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
//                mDeviceList = new ArrayList<BluetoothDevice>();
//
//                mProgressDlg.show();
//            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
//                mProgressDlg.dismiss();
////
////                Intent newIntent = new Intent(MainActivity.this, DeviceListActivity.class);
////
////                newIntent.putParcelableArrayListExtra("device.list", mDeviceList);
////
////                startActivity(newIntent);
//            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//
//                mDeviceList.add(device);
//
//                showToast("Found device " + device.getName());
//            }
//        }
//    };
}

/*
Assignment 2
Create an application with that finds nearby Bluetooth devices and display in a recyclerView
-using BLE
-create a separate library for the view

-You can submit the link to your repos in your “Bench chat”
 */