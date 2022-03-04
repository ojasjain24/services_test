package com.example.services_test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import io.flutter.Log;

public class service extends Service {

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(android.content.Intent.ACTION_VIEW);
        galleryIntent.setType("*/*");
        galleryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(galleryIntent);
//        startActivityForResult(galleryIntent, 1);
        openSomeActivityForResult();
        Log.e("TAG", "CALLED");
        return START_STICKY;
    }

    public void openSomeActivityForResult() {
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<Instrumentation.ActivityResult>() {
                    @Override
                    public void onActivityResult(Instrumentation.ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getResultData();
                            Uri uri = data.getData();
                        }
                    }
                });

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
