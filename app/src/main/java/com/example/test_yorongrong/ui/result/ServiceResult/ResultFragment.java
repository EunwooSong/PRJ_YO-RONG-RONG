package com.example.test_yorongrong.ui.result.ServiceResult;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_yorongrong.Data.AllData;
import com.example.test_yorongrong.Data.model;
import com.example.test_yorongrong.R;
import com.example.test_yorongrong.api.NetworkHelper;
import com.example.test_yorongrong.ui.result.ServiceResult.Card.ResultAdapter;
import com.example.test_yorongrong.ui.result.ServiceResult.Card.ResultModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private static final String BASE_URL = "https://scon.postect.tech/api";
    private model[] data;


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

    public model[] APICommunication(String uuid) {
        final model[][] result = {new model[1]};
        NetworkHelper.getInstance(BASE_URL).GetAll(uuid).enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {
                AllData data = response.body();
                result[0] = data.getData();
            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {

            }
        });

        return result[0];
    }

    public void createResultModel() {
        ResultModel m;

        TelephonyManager tm = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = tm.getDeviceId();

        model[] data = APICommunication(uuid);

        for(model i: data) {
            m = new ResultModel();
            m.setTitle(i.name);
            m.setPresent(i.per.toString()+"%");
            m.setShop_info(i.market);
            m.setImg(i.img);
            models.add(m);
        }

        for (ResultModel model:
             models) {
            Log.i("model", "test : " + model.getTitle());
        }

        Log.i("model", "test : " + models.size());
    }
}