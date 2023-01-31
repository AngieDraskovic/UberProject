// Generated by view binder compiler. Do not edit!
package com.example.easygo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.easygo.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPassengerRegisterBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText adress;

  @NonNull
  public final EditText email;

  @NonNull
  public final EditText name;

  @NonNull
  public final EditText number;

  @NonNull
  public final EditText password;

  @NonNull
  public final EditText passwordRepeat;

  @NonNull
  public final MaterialButton signbtn;

  @NonNull
  public final TextView signup;

  @NonNull
  public final EditText surname;

  private ActivityPassengerRegisterBinding(@NonNull LinearLayout rootView, @NonNull EditText adress,
      @NonNull EditText email, @NonNull EditText name, @NonNull EditText number,
      @NonNull EditText password, @NonNull EditText passwordRepeat, @NonNull MaterialButton signbtn,
      @NonNull TextView signup, @NonNull EditText surname) {
    this.rootView = rootView;
    this.adress = adress;
    this.email = email;
    this.name = name;
    this.number = number;
    this.password = password;
    this.passwordRepeat = passwordRepeat;
    this.signbtn = signbtn;
    this.signup = signup;
    this.surname = surname;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPassengerRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPassengerRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_passenger_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPassengerRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.adress;
      EditText adress = ViewBindings.findChildViewById(rootView, id);
      if (adress == null) {
        break missingId;
      }

      id = R.id.email;
      EditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.name;
      EditText name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
        break missingId;
      }

      id = R.id.number;
      EditText number = ViewBindings.findChildViewById(rootView, id);
      if (number == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.passwordRepeat;
      EditText passwordRepeat = ViewBindings.findChildViewById(rootView, id);
      if (passwordRepeat == null) {
        break missingId;
      }

      id = R.id.signbtn;
      MaterialButton signbtn = ViewBindings.findChildViewById(rootView, id);
      if (signbtn == null) {
        break missingId;
      }

      id = R.id.signup;
      TextView signup = ViewBindings.findChildViewById(rootView, id);
      if (signup == null) {
        break missingId;
      }

      id = R.id.surname;
      EditText surname = ViewBindings.findChildViewById(rootView, id);
      if (surname == null) {
        break missingId;
      }

      return new ActivityPassengerRegisterBinding((LinearLayout) rootView, adress, email, name,
          number, password, passwordRepeat, signbtn, signup, surname);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
