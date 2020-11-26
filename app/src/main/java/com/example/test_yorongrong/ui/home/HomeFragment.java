package com.example.test_yorongrong.ui.home;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.test_yorongrong.MainActivity;
import com.example.test_yorongrong.R;
import com.example.test_yorongrong.ShowCamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public Camera camera;
    FrameLayout frameLayout;
    public ShowCamera showCamera;
    ImageButton camera_btn;

    private File tempFile;
    private String imageFilePath;
    private String photoUri;

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

        root.findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).goToAlbum();
            }
        });
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
            File picture_file = null;
            try {
                picture_file = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //camera.startPreview();

//            try {
//                MainActivity mainActivity = (MainActivity) getActivity();
//                Bitmap img = BitmapFactory.decodeByteArray(data, 0, data.length);
//                showCamera.tmpImg = img;
//
//                Intent intent = new Intent();
//                ComponentName name = new ComponentName("com.example.test_yorongrong", "com.example.test_yorongrong.ui.result.ResultActivity");
//                intent.setComponent(name);
//                intent.putExtra("cameraImg", data);
//
//                startActivityForResult(intent, 100);
//            }catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(getActivity().getApplicationContext(), "[ Image Error ]", Toast.LENGTH_SHORT).show();
//                return;
//            }

            if(picture_file == null) {
                return;
            }
            else {
                try {
                    FileOutputStream fos  = new FileOutputStream(picture_file);
                    fos.write(data);
                    fos.close();

                    camera.startPreview();

                    ((MainActivity)getActivity()).SendCapture(imageFilePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private File createImageFile() throws IOException {
        // 이미지 파일 이름 ( blackJin_{시간}_ )
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "temp_" + timeStamp + "_";

        // 이미지가 저장될 폴더 이름 ( blackJin )
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/YoRongRong/");
        if (!storageDir.exists()) storageDir.mkdirs();

        // 빈 파일 생성
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imageFilePath = image.getPath();
        return image;
    }


}