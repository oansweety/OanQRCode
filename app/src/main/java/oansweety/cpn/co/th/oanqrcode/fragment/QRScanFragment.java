package oansweety.cpn.co.th.oanqrcode.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import oansweety.cpn.co.th.oanqrcode.R;

/**
 * Created by kachutima on 13/3/2561.
 */

public class QRScanFragment extends Fragment{

    //    Explicit
    private ZXingScannerView zXingScannerView;
    private String tag = "13MarchV1";
    private String[] loginStrings;

    public static QRScanFragment qRscanInstance(String[] loginStrings) {

        QRScanFragment qrScanFragment = new QRScanFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        qrScanFragment.setArguments(bundle);
        return qrScanFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value
        loginStrings = getArguments().getStringArray("Login");

//        QRscan Controller
        QRscanController();

    }   // Main Method

    private void QRscanController() {
        Button button = getView().findViewById(R.id.btnQRscan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                zXingScannerView = new ZXingScannerView(getActivity());
                getActivity().setContentView(zXingScannerView);
                zXingScannerView.startCamera();     // Open Camera

                zXingScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
                    @Override
                    public void handleResult(Result result) {

                        String resultString = result.getText().toString();
                        Log.d(tag, "QR code ==> " + resultString);

                        zXingScannerView.stopCamera();      // Close Camera
                        getActivity().setContentView(R.layout.activity_service);

                        Intent intent = getActivity().getIntent();
                        intent.putExtra("Login", loginStrings);
                        startActivity(intent);

//                        getActivity().getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.contentServiceFragment, new DisplayQRfragment())
//                                .commit();

                    }
                });

            }   // onClick
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr_scan, container, false);
        return view;
    }
}
