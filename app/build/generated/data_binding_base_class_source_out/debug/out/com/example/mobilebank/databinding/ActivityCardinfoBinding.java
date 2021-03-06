// Generated by view binder compiler. Do not edit!
package com.example.mobilebank.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mobilebank.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCardinfoBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView bankcardview;

  @NonNull
  public final TextView lastfive;

  @NonNull
  public final TextView leftcurrency;

  @NonNull
  public final TextView schoolcardview;

  @NonNull
  public final TextView topText;

  private ActivityCardinfoBinding(@NonNull LinearLayout rootView, @NonNull TextView bankcardview,
      @NonNull TextView lastfive, @NonNull TextView leftcurrency, @NonNull TextView schoolcardview,
      @NonNull TextView topText) {
    this.rootView = rootView;
    this.bankcardview = bankcardview;
    this.lastfive = lastfive;
    this.leftcurrency = leftcurrency;
    this.schoolcardview = schoolcardview;
    this.topText = topText;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCardinfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCardinfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_cardinfo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCardinfoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bankcardview;
      TextView bankcardview = ViewBindings.findChildViewById(rootView, id);
      if (bankcardview == null) {
        break missingId;
      }

      id = R.id.lastfive;
      TextView lastfive = ViewBindings.findChildViewById(rootView, id);
      if (lastfive == null) {
        break missingId;
      }

      id = R.id.leftcurrency;
      TextView leftcurrency = ViewBindings.findChildViewById(rootView, id);
      if (leftcurrency == null) {
        break missingId;
      }

      id = R.id.schoolcardview;
      TextView schoolcardview = ViewBindings.findChildViewById(rootView, id);
      if (schoolcardview == null) {
        break missingId;
      }

      id = R.id.topText;
      TextView topText = ViewBindings.findChildViewById(rootView, id);
      if (topText == null) {
        break missingId;
      }

      return new ActivityCardinfoBinding((LinearLayout) rootView, bankcardview, lastfive,
          leftcurrency, schoolcardview, topText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
