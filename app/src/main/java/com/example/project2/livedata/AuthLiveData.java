package com.example.project2.livedata;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.lifecycle.LiveData;

public class AuthLiveData extends LiveData<FirebaseUser> {

    private static final String TAG = "AuthLiveData";

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public AuthLiveData() {
        super();
        initFirebase();
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            setValue(user);
        };
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.e(TAG, "onActive: ");
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.e(TAG, "onInactive: ");
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}

