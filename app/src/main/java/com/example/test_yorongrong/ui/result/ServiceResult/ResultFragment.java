package com.example.test_yorongrong.ui.result.ServiceResult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.test_yorongrong.R;
import com.example.test_yorongrong.ui.result.ServiceResult.Card.ResultAdapter;
import com.example.test_yorongrong.ui.result.ServiceResult.Card.ResultModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */

    ArrayList<ResultModel> models = new ArrayList<>();

    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_result, container, false);

        createResultModel();
        DiscreteScrollView scrollView = root.findViewById(R.id.picker);

        ResultAdapter adapter = new ResultAdapter(getActivity(), models);
        scrollView.setAdapter(adapter);
        RoundedImageView imageView = root.findViewById(R.id.img_capture);

        if(getArguments() != null) {
            String path = getArguments().getString("path");

            if (path != null && !path.isEmpty()) {

                Bitmap img = BitmapFactory.decodeFile(path);
                imageView.setImageBitmap(img);
            }
        }

        return root;
    }

    public void createResultModel() {
        ResultModel m;

        m = new ResultModel();
        m.setTitle("ResultModel1");
        m.setPresent("---%");
        m.setShop_info("11번가");
        m.setImg(R.drawable.images);
        models.add(m);

        for (ResultModel model:
             models) {
            Log.i("model", "test : " + model.getTitle());
        }

        Log.i("model", "test : " + models.size());
    }
}