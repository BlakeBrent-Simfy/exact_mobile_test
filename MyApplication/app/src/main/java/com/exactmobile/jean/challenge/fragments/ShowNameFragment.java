package com.exactmobile.jean.challenge.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.exactmobile.jean.challenge.R;
import com.exactmobile.jean.challenge.enums.Action;
import com.exactmobile.jean.challenge.enums.BundleExtras;

/**
 * Created by jmvnkuru on 20/04/16.
 */
public class ShowNameFragment extends Fragment {
    private View fragView;
    private TextView tvName;
    private Button btnBack, btnClose;
    private OnActionRequired mCallback;

    public interface OnActionRequired {
        void processAction(Action action);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView = inflater.inflate(R.layout.show_name_fragment, container, false);
        initialize();
        addListeners();
        return fragView;
    }

    private void addListeners() {
        btnBack.setOnClickListener(onClickListener);
        btnClose.setOnClickListener(onClickListener);
    }

    private void initialize() {
        tvName = (TextView) fragView.findViewById(R.id.tv_name);
        btnBack = (AppCompatButton) fragView.findViewById(R.id.btn_back);
        btnClose = (AppCompatButton) fragView.findViewById(R.id.btn_close);

        boolean returningUser=false;
        if (getArguments() != null && getArguments().getString(BundleExtras.EXTRAS.name()) != null) {

            if (getArguments() != null && getArguments().getBoolean(BundleExtras.RETURNING.name(), false)) {
                returningUser=true;
            }
        }
        if(returningUser){
            tvName.setText(getString(R.string.welcome_name_returning, getArguments().getString(BundleExtras.EXTRAS.name())));
        }else{
            tvName.setText(getString(R.string.welcome_name, getArguments().getString(BundleExtras.EXTRAS.name())));
        }


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_back:
                    mCallback.processAction(Action.BACK);
                    break;
                case R.id.btn_close:
                    mCallback.processAction(Action.CLOSE);
                    break;
                default:
                    //Do nothing for now
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnActionRequired) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnActionRequired");
        }
    }
}
