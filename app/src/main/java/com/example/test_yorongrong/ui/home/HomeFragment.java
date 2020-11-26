package com.example.test_yorongrong.ui.home;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.test_yorongrong.R;
import com.example.test_yorongrong.ShowCamera;

import java.io.File;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public Camera camera;
    FrameLayout frameLayout;
    public ShowCamera showCamera;
    ImageButton camera_btn;

    private MediaRecorder mediaRecorder;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //camera_btn = root.findViewById(R.id.);

        frameLayout = root.findViewById(R.id.frameLayout);
        camera = Camera.open();

        showCamera = new ShowCamera(this.getActivity(), camera);
        frameLayout.addView(showCamera);
        return root;
    }

    public void CaptureCamera() {
        if (showCamera.safeToTakePicture) {
            camera.takePicture(null, null, PictureCallback);
            showCamera.safeToTakePicture = false;
        }
    }

    Camera.PictureCallback PictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File picture_file = getOutputMediaFile(MEDIA_TYPE_IMAGE);

            camera.startPreview();

            try {
                Bitmap img = BitmapFactory.decodeByteArray(data, 0, data.length);
                showCamera.tmpImg = img;

                Intent intent = new Intent();
                ComponentName name = new ComponentName("com.example.test_yorongrong", "com.example.test_yorongrong.ui.result.ResultActivity");
                intent.setComponent(name);
                intent.putExtra("cameraImg", data);

                startActivityForResult(intent, 100);
            }catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "[ Image Error ]", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };

    private File getOutputMediaFile(int mediaTypeImage) {
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            return null;
        }
        else{
            File folder_gui=new File(Environment.getExternalStorageDirectory()+File.separator+"GUI");

            if(!folder_gui.exists()){
                folder_gui.mkdir();
            }
            File outputFile = new File(folder_gui,"temp.jpg");
            return outputFile;
        }
    }


}