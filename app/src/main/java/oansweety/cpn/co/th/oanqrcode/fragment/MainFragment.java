package oansweety.cpn.co.th.oanqrcode.fragment;

import android.content.Intent;
import android.icu.text.Replaceable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import oansweety.cpn.co.th.oanqrcode.R;
import oansweety.cpn.co.th.oanqrcode.ServiceActivity;
import oansweety.cpn.co.th.oanqrcode.utility.GetAllData;
import oansweety.cpn.co.th.oanqrcode.utility.MyAlert;
import oansweety.cpn.co.th.oanqrcode.utility.MyConstance;

/**
 * Created by kachutima on 7/3/2561.
 */

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

//        Login Controller
        loginController();

    }   // Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Get Value From Edit Text
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Have Space",
                            "Please Fill All User and Password");
                } else {

//                    No Space
                    try {

                        MyConstance myConstance = new MyConstance();
                        GetAllData getAllData = new GetAllData(getActivity());
                        getAllData.execute(myConstance.getUrlReadAllUser());

                        String jsonString = getAllData.get();
                        Log.d("8MarchV1", "JSON ==> " + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);
                        String[] columnUserStrings = myConstance.getColumnUserTableStrings();
                        String[] loginStrings = new String[columnUserStrings.length];
                        boolean userStatus = true;

                        for (int i = 0; i < jsonArray.length(); i+=1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (userString.equals(jsonObject.getString("User"))) {
//                                User True
                                userStatus = false;

                                for (int i1=0; i1<columnUserStrings.length; i1+=1) {

                                    loginStrings[i1] = jsonObject.getString(columnUserStrings[i1]);
                                    Log.d("8MarchV1", "loginStrings[" + i1 + "] ==> " + loginStrings[i1]);

                                }   // for

                            }   // if

                        }   // for

                        if (userStatus) {
//                            User False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("User False",
                                    "No this User in my Database");

                        } else if (passwordString.equals(loginStrings[3])) {
//                            Password True
                            Toast.makeText(getActivity(), "Welcome " + loginStrings[1],
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getActivity(), ServiceActivity.class);
                            intent.putExtra("Login", loginStrings);
                            startActivity(intent);
                            getActivity().finish(); // Close Main (Memory Performance)

                        } else {
//                            Password False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("Password False",
                                    "Please Try Again Password False");

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }   // if

            }
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);  // Initial View
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}   // Main Class
