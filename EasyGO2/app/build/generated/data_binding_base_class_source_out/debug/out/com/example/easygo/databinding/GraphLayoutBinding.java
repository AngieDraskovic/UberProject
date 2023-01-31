// Generated by view binder compiler. Do not edit!
package com.example.easygo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.easygo.R;
import com.jjoe64.graphview.GraphView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class GraphLayoutBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView graph1Name;

  @NonNull
  public final GraphView numOfRidesGraph;

  private GraphLayoutBinding(@NonNull RelativeLayout rootView, @NonNull TextView graph1Name,
      @NonNull GraphView numOfRidesGraph) {
    this.rootView = rootView;
    this.graph1Name = graph1Name;
    this.numOfRidesGraph = numOfRidesGraph;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static GraphLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static GraphLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.graph_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static GraphLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.graph1Name;
      TextView graph1Name = ViewBindings.findChildViewById(rootView, id);
      if (graph1Name == null) {
        break missingId;
      }

      id = R.id.numOfRidesGraph;
      GraphView numOfRidesGraph = ViewBindings.findChildViewById(rootView, id);
      if (numOfRidesGraph == null) {
        break missingId;
      }

      return new GraphLayoutBinding((RelativeLayout) rootView, graph1Name, numOfRidesGraph);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
