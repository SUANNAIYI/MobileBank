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

public final class ActivityPayhistoryBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout PayhistoryLLLastFive1;

  @NonNull
  public final LinearLayout PayhistoryLLLastFive2;

  @NonNull
  public final LinearLayout PayhistoryLLLastFive3;

  @NonNull
  public final LinearLayout PayhistoryLLLastFive4;

  @NonNull
  public final LinearLayout PayhistoryLLLastFive5;

  @NonNull
  public final TextView PayhistoryTVBankcardID;

  @NonNull
  public final TextView PayhistoryTVLastFive1;

  @NonNull
  public final TextView PayhistoryTVLastFive2;

  @NonNull
  public final TextView PayhistoryTVLastFive3;

  @NonNull
  public final TextView PayhistoryTVLastFive4;

  @NonNull
  public final TextView PayhistoryTVLastFive5;

  @NonNull
  public final TextView PayhistoryTVSchoolcardID;

  private ActivityPayhistoryBinding(@NonNull LinearLayout rootView,
      @NonNull LinearLayout PayhistoryLLLastFive1, @NonNull LinearLayout PayhistoryLLLastFive2,
      @NonNull LinearLayout PayhistoryLLLastFive3, @NonNull LinearLayout PayhistoryLLLastFive4,
      @NonNull LinearLayout PayhistoryLLLastFive5, @NonNull TextView PayhistoryTVBankcardID,
      @NonNull TextView PayhistoryTVLastFive1, @NonNull TextView PayhistoryTVLastFive2,
      @NonNull TextView PayhistoryTVLastFive3, @NonNull TextView PayhistoryTVLastFive4,
      @NonNull TextView PayhistoryTVLastFive5, @NonNull TextView PayhistoryTVSchoolcardID) {
    this.rootView = rootView;
    this.PayhistoryLLLastFive1 = PayhistoryLLLastFive1;
    this.PayhistoryLLLastFive2 = PayhistoryLLLastFive2;
    this.PayhistoryLLLastFive3 = PayhistoryLLLastFive3;
    this.PayhistoryLLLastFive4 = PayhistoryLLLastFive4;
    this.PayhistoryLLLastFive5 = PayhistoryLLLastFive5;
    this.PayhistoryTVBankcardID = PayhistoryTVBankcardID;
    this.PayhistoryTVLastFive1 = PayhistoryTVLastFive1;
    this.PayhistoryTVLastFive2 = PayhistoryTVLastFive2;
    this.PayhistoryTVLastFive3 = PayhistoryTVLastFive3;
    this.PayhistoryTVLastFive4 = PayhistoryTVLastFive4;
    this.PayhistoryTVLastFive5 = PayhistoryTVLastFive5;
    this.PayhistoryTVSchoolcardID = PayhistoryTVSchoolcardID;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPayhistoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPayhistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_payhistory, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPayhistoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Payhistory_LL_lastFive_1;
      LinearLayout PayhistoryLLLastFive1 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryLLLastFive1 == null) {
        break missingId;
      }

      id = R.id.Payhistory_LL_lastFive_2;
      LinearLayout PayhistoryLLLastFive2 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryLLLastFive2 == null) {
        break missingId;
      }

      id = R.id.Payhistory_LL_lastFive_3;
      LinearLayout PayhistoryLLLastFive3 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryLLLastFive3 == null) {
        break missingId;
      }

      id = R.id.Payhistory_LL_lastFive_4;
      LinearLayout PayhistoryLLLastFive4 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryLLLastFive4 == null) {
        break missingId;
      }

      id = R.id.Payhistory_LL_lastFive_5;
      LinearLayout PayhistoryLLLastFive5 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryLLLastFive5 == null) {
        break missingId;
      }

      id = R.id.Payhistory_TV_bankcardID;
      TextView PayhistoryTVBankcardID = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryTVBankcardID == null) {
        break missingId;
      }

      id = R.id.Payhistory_TV_lastFive_1;
      TextView PayhistoryTVLastFive1 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryTVLastFive1 == null) {
        break missingId;
      }

      id = R.id.Payhistory_TV_lastFive_2;
      TextView PayhistoryTVLastFive2 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryTVLastFive2 == null) {
        break missingId;
      }

      id = R.id.Payhistory_TV_lastFive_3;
      TextView PayhistoryTVLastFive3 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryTVLastFive3 == null) {
        break missingId;
      }

      id = R.id.Payhistory_TV_lastFive_4;
      TextView PayhistoryTVLastFive4 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryTVLastFive4 == null) {
        break missingId;
      }

      id = R.id.Payhistory_TV_lastFive_5;
      TextView PayhistoryTVLastFive5 = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryTVLastFive5 == null) {
        break missingId;
      }

      id = R.id.Payhistory_TV_schoolcardID;
      TextView PayhistoryTVSchoolcardID = ViewBindings.findChildViewById(rootView, id);
      if (PayhistoryTVSchoolcardID == null) {
        break missingId;
      }

      return new ActivityPayhistoryBinding((LinearLayout) rootView, PayhistoryLLLastFive1,
          PayhistoryLLLastFive2, PayhistoryLLLastFive3, PayhistoryLLLastFive4,
          PayhistoryLLLastFive5, PayhistoryTVBankcardID, PayhistoryTVLastFive1,
          PayhistoryTVLastFive2, PayhistoryTVLastFive3, PayhistoryTVLastFive4,
          PayhistoryTVLastFive5, PayhistoryTVSchoolcardID);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
