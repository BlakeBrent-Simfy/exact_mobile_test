package com.exactmobile.jean.challenge.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.exactmobile.jean.challenge.R;


public class EnterNameFragment extends Fragment {
    private View fragView;
    private EditText etName;
    private AppCompatButton btnSubmit;
    private OnSubmitRequired mCallback;

    public interface OnSubmitRequired {
        void submitAction(final String enteredName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView = inflater.inflate(R.layout.enter_name_fragment, container, false);
        initialize();
        addListeners();
        return fragView;
    }

    private void addListeners() {
        btnSubmit.setOnClickListener(onClickListener);
    }

    private void initialize() {
        etName = (EditText) fragView.findViewById(R.id.et_enter_name);
        btnSubmit = (AppCompatButton) fragView.findViewById(R.id.btn_submit);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_submit:
                    if (validate()) {
                        mCallback.submitAction(etName.getText().toString().trim());
                    }
                    break;
                default:
                    //Do nothing for now
                    break;
            }
        }
    };

    private boolean validate() {
        if (etName.getText() != null && !TextUtils.isEmpty(etName.getText())) {
            return true;
        } else {
            etName.setError(getString(R.string.error_empty_field));
        }
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSubmitRequired) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnSubmitRequired");
        }
    }

}
