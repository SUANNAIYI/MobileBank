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

public final class Item5Binding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView tvAttachment;

  @NonNull
  public final TextView tvGetaccount;

  @NonNull
  public final TextView tvGetdate;

  @NonNull
  public final TextView tvGetsender;

  @NonNull
  public final TextView tvPayaccount;

  @NonNull
  public final TextView tvPaymoney;

  @NonNull
  public final TextView tvReceiver;

  private Item5Binding(@NonNull LinearLayout rootView, @NonNull TextView tvAttachment,
      @NonNull TextView tvGetaccount, @NonNull TextView tvGetdate, @NonNull TextView tvGetsender,
      @NonNull TextView tvPayaccount, @NonNull TextView tvPaymoney, @NonNull TextView tvReceiver) {
    this.rootView = rootView;
    this.tvAttachment = tvAttachment;
    this.tvGetaccount = tvGetaccount;
    this.tvGetdate = tvGetdate;
    this.tvGetsender = tvGetsender;
    this.tvPayaccount = tvPayaccount;
    this.tvPaymoney = tvPaymoney;
    this.tvReceiver = tvReceiver;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static Item5Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static Item5Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.item5, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static Item5Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.tv_attachment;
      TextView tvAttachment = ViewBindings.findChildViewById(rootView, id);
      if (tvAttachment == null) {
        break missingId;
      }

      id = R.id.tv_getaccount;
      TextView tvGetaccount = ViewBindings.findChildViewById(rootView, id);
      if (tvGetaccount == null) {
        break missingId;
      }

      id = R.id.tv_getdate;
      TextView tvGetdate = ViewBindings.findChildViewById(rootView, id);
      if (tvGetdate == null) {
        break missingId;
      }

      id = R.id.tv_getsender;
      TextView tvGetsender = ViewBindings.findChildViewById(rootView, id);
      if (tvGetsender == null) {
        break missingId;
      }

      id = R.id.tv_payaccount;
      TextView tvPayaccount = ViewBindings.findChildViewById(rootView, id);
      if (tvPayaccount == null) {
        break missingId;
      }

      id = R.id.tv_paymoney;
      TextView tvPaymoney = ViewBindings.findChildViewById(rootView, id);
      if (tvPaymoney == null) {
        break missingId;
      }

      id = R.id.tv_receiver;
      TextView tvReceiver = ViewBindings.findChildViewById(rootView, id);
      if (tvReceiver == null) {
        break missingId;
      }

      return new Item5Binding((LinearLayout) rootView, tvAttachment, tvGetaccount, tvGetdate,
          tvGetsender, tvPayaccount, tvPaymoney, tvReceiver);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
