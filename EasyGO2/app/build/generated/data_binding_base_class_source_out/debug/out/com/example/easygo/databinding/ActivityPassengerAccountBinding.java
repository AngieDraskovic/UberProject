// Generated by view binder compiler. Do not edit!
package com.example.easygo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.easygo.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPassengerAccountBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RelativeLayout account;

  @NonNull
  public final LinearLayout financialCard;

  @NonNull
  public final ImageView iconProfile;

  @NonNull
  public final ImageView iconReports;

  @NonNull
  public final ImageView iconRoutes;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageView profileImg;

  @NonNull
  public final LinearLayout reports;

  @NonNull
  public final ToolbarLayoutBinding toolbar;

  @NonNull
  public final TextView txtAddress;

  @NonNull
  public final TextView txtEmail;

  @NonNull
  public final TextView txtPhone;

  @NonNull
  public final TextView txtUser;

  @NonNull
  public final LinearLayout userProfile;

  @NonNull
  public final LinearLayout userProfile2;

  @NonNull
  public final ImageView walletIcon;

  private ActivityPassengerAccountBinding(@NonNull RelativeLayout rootView,
      @NonNull RelativeLayout account, @NonNull LinearLayout financialCard,
      @NonNull ImageView iconProfile, @NonNull ImageView iconReports, @NonNull ImageView iconRoutes,
      @NonNull ImageView imageView, @NonNull ImageView profileImg, @NonNull LinearLayout reports,
      @NonNull ToolbarLayoutBinding toolbar, @NonNull TextView txtAddress,
      @NonNull TextView txtEmail, @NonNull TextView txtPhone, @NonNull TextView txtUser,
      @NonNull LinearLayout userProfile, @NonNull LinearLayout userProfile2,
      @NonNull ImageView walletIcon) {
    this.rootView = rootView;
    this.account = account;
    this.financialCard = financialCard;
    this.iconProfile = iconProfile;
    this.iconReports = iconReports;
    this.iconRoutes = iconRoutes;
    this.imageView = imageView;
    this.profileImg = profileImg;
    this.reports = reports;
    this.toolbar = toolbar;
    this.txtAddress = txtAddress;
    this.txtEmail = txtEmail;
    this.txtPhone = txtPhone;
    this.txtUser = txtUser;
    this.userProfile = userProfile;
    this.userProfile2 = userProfile2;
    this.walletIcon = walletIcon;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPassengerAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPassengerAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_passenger_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPassengerAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      RelativeLayout account = (RelativeLayout) rootView;

      id = R.id.financialCard;
      LinearLayout financialCard = ViewBindings.findChildViewById(rootView, id);
      if (financialCard == null) {
        break missingId;
      }

      id = R.id.iconProfile;
      ImageView iconProfile = ViewBindings.findChildViewById(rootView, id);
      if (iconProfile == null) {
        break missingId;
      }

      id = R.id.iconReports;
      ImageView iconReports = ViewBindings.findChildViewById(rootView, id);
      if (iconReports == null) {
        break missingId;
      }

      id = R.id.iconRoutes;
      ImageView iconRoutes = ViewBindings.findChildViewById(rootView, id);
      if (iconRoutes == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.profileImg;
      ImageView profileImg = ViewBindings.findChildViewById(rootView, id);
      if (profileImg == null) {
        break missingId;
      }

      id = R.id.reports;
      LinearLayout reports = ViewBindings.findChildViewById(rootView, id);
      if (reports == null) {
        break missingId;
      }

      id = R.id.toolbar;
      View toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }
      ToolbarLayoutBinding binding_toolbar = ToolbarLayoutBinding.bind(toolbar);

      id = R.id.txtAddress;
      TextView txtAddress = ViewBindings.findChildViewById(rootView, id);
      if (txtAddress == null) {
        break missingId;
      }

      id = R.id.txtEmail;
      TextView txtEmail = ViewBindings.findChildViewById(rootView, id);
      if (txtEmail == null) {
        break missingId;
      }

      id = R.id.txtPhone;
      TextView txtPhone = ViewBindings.findChildViewById(rootView, id);
      if (txtPhone == null) {
        break missingId;
      }

      id = R.id.txtUser;
      TextView txtUser = ViewBindings.findChildViewById(rootView, id);
      if (txtUser == null) {
        break missingId;
      }

      id = R.id.userProfile;
      LinearLayout userProfile = ViewBindings.findChildViewById(rootView, id);
      if (userProfile == null) {
        break missingId;
      }

      id = R.id.userProfile2;
      LinearLayout userProfile2 = ViewBindings.findChildViewById(rootView, id);
      if (userProfile2 == null) {
        break missingId;
      }

      id = R.id.walletIcon;
      ImageView walletIcon = ViewBindings.findChildViewById(rootView, id);
      if (walletIcon == null) {
        break missingId;
      }

      return new ActivityPassengerAccountBinding((RelativeLayout) rootView, account, financialCard,
          iconProfile, iconReports, iconRoutes, imageView, profileImg, reports, binding_toolbar,
          txtAddress, txtEmail, txtPhone, txtUser, userProfile, userProfile2, walletIcon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}