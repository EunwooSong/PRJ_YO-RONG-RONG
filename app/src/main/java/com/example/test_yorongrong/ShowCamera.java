package com.example.test_yorongrong;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Base64;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

//https://www.youtube.com/watch?v=_wZvds9CfuE
public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {
    Camera camera;
    SurfaceHolder holder;
    public boolean safeToTakePicture=false;
    public Bitmap tmpImg;

    public ShowCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Camera.Parameters params = camera.getParameters();

        List<Camera.Size> sizes = params.getSupportedPictureSizes();
        Camera.Size size = sizes.get(sizes.size() - 1);

        if(this.getResources().getConfiguration().orientation!= Configuration.ORIENTATION_LANDSCAPE) {
            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        }
        else {
            params.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        params.setPictureSize(size.width, size.height);
        camera.setParameters(params);

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
//            this.ServerRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
        safeToTakePicture=true;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        //camera.stopPreview();
        //camera.release();
    }

    private void ServerRequest(){
        final String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String APIData = "{phone_id:" + android_id + ", image:";
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    tmpImg.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    APIData += encodedImage + "}";

                    URL APIEndpoint = new URL("https://scon.postect.tech/");
                    HttpsURLConnection APIConnection =
                            (HttpsURLConnection) APIEndpoint.openConnection();
                    APIConnection.setRequestProperty("User-Agent", "yrr");
                    APIConnection.setRequestMethod("POST");
                    APIConnection.setDoOutput(true);
                    APIConnection.getOutputStream().write(APIData.getBytes());

                    if (APIConnection.getResponseCode() == 200) {
                        // Success
                        // Further processing here
                    } else {
                        // Error handling code goes here
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void onOffFlash(boolean flash) {
        Camera.Parameters param = camera.getParameters();
        param.setFlashMode((flash) ? Camera.Parameters.FLASH_MODE_TORCH :Camera.Parameters.FLASH_MODE_OFF );
        camera.setParameters(param);
        camera.startPreview();
    }
}
