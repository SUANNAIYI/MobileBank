// Generated by view binder compiler. Do not edit!
package com.example.mobilebank.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mobilebank.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginForgetPswBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button ForgetFor;

  @NonNull
  public final EditText codeFor;

  @NonNull
  public final Button getCodeFor;

  @NonNull
  public final EditText newpsw;

  @NonNull
  public final EditText numberFor;

  @NonNull
  public final Spinner region;

  @NonNull
  public final TextView temp21;

  @NonNull
  public final TextView temp22;

  private ActivityLoginForgetPswBinding(@NonNull LinearLayout rootView, @NonNull Button ForgetFor,
      @NonNull EditText codeFor, @NonNull Button getCodeFor, @NonNull EditText newpsw,
      @NonNull EditText numberFor, @NonNull Spinner region, @NonNull TextView temp21,
      @NonNull TextView temp22) {
    this.rootView = rootView;
    this.ForgetFor = ForgetFor;
    this.codeFor = codeFor;
    this.getCodeFor = getCodeFor;
    this.newpsw = newpsw;
    this.numberFor = numberFor;
    this.region = region;
    this.temp21 = temp21;
    this.temp22 = temp22;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginForgetPswBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginForgetPswBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login_forget_psw, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginForgetPswBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ForgetFor;
      Button ForgetFor = ViewBindings.findChildViewById(rootView, id);
      if (ForgetFor == null) {
        break missingId;
      }

      id = R.id.codeFor;
      EditText codeFor = ViewBindings.findChildViewById(rootView, id);
      if (codeFor == null) {
        break missingId;
      }

      id = R.id.getCodeFor;
      Button getCodeFor = ViewBindings.findChildViewById(rootView, id);
      if (getCodeFor == null) {
        break missingId;
      }

      id = R.id.newpsw;
      EditText newpsw = ViewBindings.findChildViewById(rootView, id);
      if (newpsw == null) {
        break missingId;
      }

      id = R.id.numberFor;
      EditText numberFor = ViewBindings.findChildViewById(rootView, id);
      if (numberFor == null) {
        break missingId;
      }

      id = R.id.region;
      Spinner region = ViewBindings.findChildViewById(rootView, id);
      if (region == null) {
        break missingId;
      }

      id = R.id.temp21;
      TextView temp21 = ViewBindings.findChildViewById(rootView, id);
      if (temp21 == null) {
        break missingId;
      }

      id = R.id.temp22;
      TextView temp22 = ViewBindings.findChildViewById(rootView, id);
      if (temp22 == null) {
        break missingId;
      }

      return new ActivityLoginForgetPswBinding((LinearLayout) rootView, ForgetFor, codeFor,
          getCodeFor, newpsw, numberFor, region, temp21, temp22);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
