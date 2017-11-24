package com.firstapp.joel.walgreens.util.Login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firstapp.joel.walgreens.R;

public class ForgotUsernameFragment extends Fragment {

    EditText emailEditText;
    Button btnContinue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_username, container, false);
    }

}
