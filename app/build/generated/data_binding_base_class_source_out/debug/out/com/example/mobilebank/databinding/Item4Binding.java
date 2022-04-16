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

public final class Item4Binding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView itemTextView1;

  @NonNull
  public final TextView itemTextView2;

  @NonNull
  public final TextView itemTextView3;

  @NonNull
  public final TextView itemTextView4;

  private Item4Binding(@NonNull LinearLayout rootView, @NonNull TextView itemTextView1,
      @NonNull TextView itemTextView2, @NonNull TextView itemTextView3,
      @NonNull TextView itemTextView4) {
    this.rootView = rootView;
    this.itemTextView1 = itemTextView1;
    this.itemTextView2 = itemTextView2;
    this.itemTextView3 = itemTextView3;
    this.itemTextView4 = itemTextView4;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static Item4Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static Item4Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.item4, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static Item4Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.itemTextView1;
      TextView itemTextView1 = ViewBindings.findChildViewById(rootView, id);
      if (itemTextView1 == null) {
        break missingId;
      }

      id = R.id.itemTextView2;
      TextView itemTextView2 = ViewBindings.findChildViewById(rootView, id);
      if (itemTextView2 == null) {
        break missingId;
      }

      id = R.id.itemTextView3;
      TextView itemTextView3 = ViewBindings.findChildViewById(rootView, id);
      if (itemTextView3 == null) {
        break missingId;
      }

      id = R.id.itemTextView4;
      TextView itemTextView4 = ViewBindings.findChildViewById(rootView, id);
      if (itemTextView4 == null) {
        break missingId;
      }

      return new Item4Binding((LinearLayout) rootView, itemTextView1, itemTextView2, itemTextView3,
          itemTextView4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
