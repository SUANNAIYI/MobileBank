// Generated by view binder compiler. Do not edit!
package com.example.mobilebank.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mobilebank.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDashboardBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageButton jilu;

  @NonNull
  public final ImageButton shenghuo;

  @NonNull
  public final ImageButton zhangdan;

  @NonNull
  public final ImageButton zhuanzhang;

  private FragmentDashboardBinding(@NonNull LinearLayout rootView, @NonNull ImageButton jilu,
      @NonNull ImageButton shenghuo, @NonNull ImageButton zhangdan,
      @NonNull ImageButton zhuanzhang) {
    this.rootView = rootView;
    this.jilu = jilu;
    this.shenghuo = shenghuo;
    this.zhangdan = zhangdan;
    this.zhuanzhang = zhuanzhang;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_dashboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDashboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.jilu;
      ImageButton jilu = ViewBindings.findChildViewById(rootView, id);
      if (jilu == null) {
        break missingId;
      }

      id = R.id.shenghuo;
      ImageButton shenghuo = ViewBindings.findChildViewById(rootView, id);
      if (shenghuo == null) {
        break missingId;
      }

      id = R.id.zhangdan;
      ImageButton zhangdan = ViewBindings.findChildViewById(rootView, id);
      if (zhangdan == null) {
        break missingId;
      }

      id = R.id.zhuanzhang;
      ImageButton zhuanzhang = ViewBindings.findChildViewById(rootView, id);
      if (zhuanzhang == null) {
        break missingId;
      }

      return new FragmentDashboardBinding((LinearLayout) rootView, jilu, shenghuo, zhangdan,
          zhuanzhang);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
