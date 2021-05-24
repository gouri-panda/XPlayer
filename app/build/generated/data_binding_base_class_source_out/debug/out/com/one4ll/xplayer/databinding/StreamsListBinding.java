// Generated by view binder compiler. Do not edit!
package com.one4ll.xplayer.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.one4ll.xplayer.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class StreamsListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView duration;

  @NonNull
  public final ImageView fileListInGridMore;

  @NonNull
  public final ProgressBar progressBar2;

  @NonNull
  public final TextView streamDate;

  @NonNull
  public final TextView streamName;

  @NonNull
  public final TextView textView3;

  private StreamsListBinding(@NonNull ConstraintLayout rootView, @NonNull TextView duration,
      @NonNull ImageView fileListInGridMore, @NonNull ProgressBar progressBar2,
      @NonNull TextView streamDate, @NonNull TextView streamName, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.duration = duration;
    this.fileListInGridMore = fileListInGridMore;
    this.progressBar2 = progressBar2;
    this.streamDate = streamDate;
    this.streamName = streamName;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static StreamsListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static StreamsListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.streams_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static StreamsListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.duration;
      TextView duration = rootView.findViewById(id);
      if (duration == null) {
        break missingId;
      }

      id = R.id.file_list_in_grid_more;
      ImageView fileListInGridMore = rootView.findViewById(id);
      if (fileListInGridMore == null) {
        break missingId;
      }

      id = R.id.progressBar2;
      ProgressBar progressBar2 = rootView.findViewById(id);
      if (progressBar2 == null) {
        break missingId;
      }

      id = R.id.stream_date;
      TextView streamDate = rootView.findViewById(id);
      if (streamDate == null) {
        break missingId;
      }

      id = R.id.stream_name;
      TextView streamName = rootView.findViewById(id);
      if (streamName == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = rootView.findViewById(id);
      if (textView3 == null) {
        break missingId;
      }

      return new StreamsListBinding((ConstraintLayout) rootView, duration, fileListInGridMore,
          progressBar2, streamDate, streamName, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
