// Generated by view binder compiler. Do not edit!
package com.example.easygo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.easygo.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPassengerMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView passengerMessageIcon;

  @NonNull
  public final Button rideOrderBtn;

  @NonNull
  public final ToolbarLayoutBinding toolbar;

  @NonNull
  public final WebView webView;

  private ActivityPassengerMainBinding(@NonNull RelativeLayout rootView,
      @NonNull ImageView passengerMessageIcon, @NonNull Button rideOrderBtn,
      @NonNull ToolbarLayoutBinding toolbar, @NonNull WebView webView) {
    this.rootView = rootView;
    this.passengerMessageIcon = passengerMessageIcon;
    this.rideOrderBtn = rideOrderBtn;
    this.toolbar = toolbar;
    this.webView = webView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPassengerMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPassengerMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_passenger_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPassengerMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.passenger_message_icon;
      ImageView passengerMessageIcon = ViewBindings.findChildViewById(rootView, id);
      if (passengerMessageIcon == null) {
        break missingId;
      }

      id = R.id.rideOrderBtn;
      Button rideOrderBtn = ViewBindings.findChildViewById(rootView, id);
      if (rideOrderBtn == null) {
        break missingId;
      }

      id = R.id.toolbar;
      View toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }
      ToolbarLayoutBinding binding_toolbar = ToolbarLayoutBinding.bind(toolbar);

      id = R.id.web_view;
      WebView webView = ViewBindings.findChildViewById(rootView, id);
      if (webView == null) {
        break missingId;
      }

      return new ActivityPassengerMainBinding((RelativeLayout) rootView, passengerMessageIcon,
          rideOrderBtn, binding_toolbar, webView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
