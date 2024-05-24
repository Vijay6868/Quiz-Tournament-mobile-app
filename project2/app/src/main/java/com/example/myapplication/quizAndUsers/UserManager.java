package com.example.myapplication.quizAndUsers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserManager {
    private static UserManager instance;
    private FirebaseUser firebaseUser;
    private String userId;
    private String userName;
    private String email;
    private String userType;
    private DatabaseReference userRef;
    boolean isliked;

    // Private constructor to prevent instantiation
    private UserManager() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
            userName = firebaseUser.getDisplayName();
            email = firebaseUser.getEmail();
            isliked = false;

            userRef = FirebaseDatabase.getInstance().getReference().child("User_Liked_Quizzes")
                    .child(userId);
        }
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    public void updateQuizCompleted(String quizID) {
        DatabaseReference quizzesCompletedRef = FirebaseDatabase.getInstance().getReference()
                .child("Players")
                .child(userId)
                .child("quizzes_completed");
        quizzesCompletedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> quizzesCompleted;
                if (snapshot.exists()) {
                    quizzesCompleted = (ArrayList<String>) snapshot.getValue();
                } else {
                    quizzesCompleted = new ArrayList<>();
                }

                if (!quizzesCompleted.contains(quizID)) {
                    quizzesCompleted.add(quizID);
                    quizzesCompletedRef.setValue(quizzesCompleted);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
                Log.e("Firebase", "Error updating quizzes completed", error.toException());
            }
        });
    }


    public void getUserType(UserTypeCallback callback) {
        DatabaseReference userTypeRef = FirebaseDatabase.getInstance().getReference()
                .child("Admins")
                .child(userId);

        userTypeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    userType = "Admins";
                }
                else {
                    userType = "Players";
                }
                callback.onCallback(userType);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase","Error reading userType", error.toException());
            }
        });


    }
    public void updatelikedQuizees(String quizID,boolean isLikes){

        if(isLikes){
            userRef.child(quizID).setValue(true);
        }
        else{
            userRef.child(quizID).setValue(false);
        }
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Getters for user information
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    // Method to update user info if necessary
    public void updateUser(FirebaseUser user) {
        this.firebaseUser = user;
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
            userName = firebaseUser.getDisplayName();
            email = firebaseUser.getEmail();
        }
    }
    public void isLiked(LikeCallback callback,String quizID){

        userRef.child(quizID).addListenerForSingleValueEvent(new ValueEventListener() {
            boolean isliked = false;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.getValue(Boolean.class) != null) {
                    isliked = snapshot.getValue(Boolean.class);
                }
                callback.onCallback(isliked);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void fetchCompletedQuizzes(QCompletedcallback qCompletedcallback){
        ArrayList<String> compQuizzes = new ArrayList<>();
        DatabaseReference quizzesCompletedRef = FirebaseDatabase.getInstance().getReference()
                .child("Players")
                .child(userId)
                .child("quizzes_completed");
        quizzesCompletedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot data : snapshot.getChildren()){
                        compQuizzes.add(data.getValue(String.class));
                    }
                }
                qCompletedcallback.quizCompleted(compQuizzes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

