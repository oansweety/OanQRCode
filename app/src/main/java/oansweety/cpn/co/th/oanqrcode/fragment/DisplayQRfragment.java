package oansweety.cpn.co.th.oanqrcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import oansweety.cpn.co.th.oanqrcode.R;
import oansweety.cpn.co.th.oanqrcode.utility.GetFoodWhereQRcode;
import oansweety.cpn.co.th.oanqrcode.utility.MyConstance;

/**
 * Created by kachutima on 13/3/2561.
 */

public class DisplayQRfragment extends Fragment{

    private String qrScanString;

    public static DisplayQRfragment displayQRInstance(String qrCodeString,
                                                      String[] loginStrings) {

        DisplayQRfragment displayQRfragment = new DisplayQRfragment();
        Bundle bundle = new Bundle();
        bundle.putString("QRcode", qrCodeString);
        bundle.putStringArray("Login", loginStrings);
        displayQRfragment.setArguments(bundle);
        return displayQRfragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Show QRscan
        showQRscan();

//        Search QRscan
        searchController();

//        QRscan Controlller
        QRscanControlller();

    }   // Main Method

    private void QRscanControlller() {
        Button button = getView().findViewById(R.id.btnQRscan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,
                                QRScanFragment.qRscanInstance(getArguments().getStringArray("Login")))
                        .commit();

            }
        });
    }

    private void searchController() {
        Button button = getView().findViewById(R.id.btnSearchQR);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tag = "13MarchV2";
                MyConstance myConstance = new MyConstance();
                String[] columnStrings = myConstance.getColumnFoodStrings();
                String[] valueStrings = new String[columnStrings.length];

                try {

                    GetFoodWhereQRcode getFoodWhereQRcode = new GetFoodWhereQRcode(getActivity());
                    getFoodWhereQRcode.execute(qrScanString, myConstance.getUrlGetFoodWhereQRcode());

                    String jsonString = getFoodWhereQRcode.get();
                    Log.d(tag, "JSON ==> " + jsonString);

                    JSONArray jsonArray = new JSONArray(jsonString);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    for (int i=0; i<columnStrings.length; i+=1) {

                        valueStrings[i] = jsonObject.getString(columnStrings[i]);

                    }   // for

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentServiceFragment, DetailFragment.detailInstance(
                                    valueStrings[2],
                                    valueStrings[5],
                                    valueStrings[1],
                                    valueStrings[3],
                                    valueStrings[4]))
                            .addToBackStack(null)
                            .commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }   // onClick
        });
    }

    private void showQRscan() {
        TextView textView = getView().findViewById(R.id.txtQRcode);
        qrScanString = getArguments().getString("QRcode");
        textView.setText(qrScanString);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_scan, container, false);
        return view;
    }
}
