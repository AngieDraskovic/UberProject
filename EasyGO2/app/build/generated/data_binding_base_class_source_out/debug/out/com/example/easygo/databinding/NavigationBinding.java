// Generated by view binder compiler. Do not edit!
package com.example.easygo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.easygo.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NavigationBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnAccount;

  @NonNull
  public final Button btnHistory;

  @NonNull
  public final Button btnHome;

  @NonNull
  public final Button btnInbox;

  private NavigationBinding(@NonNull LinearLayout rootView, @NonNull Button btnAccount,
      @NonNull Button btnHistory, @NonNull Button btnHome, @NonNull Button btnInbox) {
    this.rootView = rootView;
    this.btnAccount = btnAccount;
    this.btnHistory = btnHistory;
    this.btnHome = btnHome;
    this.btnInbox = btnInbox;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NavigationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NavigationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.navigation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NavigationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAccount;
      Button btnAccount = ViewBindings.findChildViewById(rootView, id);
      if (btnAccount == null) {
        break missingId;
      }

      id = R.id.btnHistory;
      Button btnHistory = ViewBindings.findChildViewById(rootView, id);
      if (btnHistory == null) {
        break missingId;
      }

      id = R.id.btnHome;
      Button btnHome = ViewBindings.findChildViewById(rootView, id);
      if (btnHome == null) {
        break missingId;
      }

      id = R.id.btnInbox;
      Button btnInbox = ViewBindings.findChildViewById(rootView, id);
      if (btnInbox == null) {
        break missingId;
      }

      return new NavigationBinding((LinearLayout) rootView, btnAccount, btnHistory, btnHome,
          btnInbox);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
