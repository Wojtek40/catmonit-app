package catmonit.app.models;

import android.content.Context;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import catmonit.app.R;

public class DiscInfoAdapter extends RecyclerView.Adapter<DiscInfoAdapter.DiscInfoHolder> {
    private final DiscInfo[] dataSet;

    public DiscInfoAdapter(DiscInfo[] dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public DiscInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.disc_display, parent, false);
        return new DiscInfoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscInfoHolder holder, int position) {
        DiscInfo di = dataSet[position];
        Context context = holder.itemView.getContext();
        holder.discLabel.setText(String.format("%s - %s / %s used",
                di.label,
                Formatter.formatShortFileSize(context, di.usedBytes),
                Formatter.formatShortFileSize(context, di.totalBytes)));
        holder.getProgressUsed().setProgress((int) ((float) di.usedBytes / (float) di.totalBytes * 100));
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public static class DiscInfoHolder extends RecyclerView.ViewHolder {
        private final LinearProgressIndicator progressUsed;
        private final TextView discLabel;
        public DiscInfoHolder(@NonNull View itemView) {
            super(itemView);
            progressUsed = itemView.findViewById(R.id.used_progress);
            discLabel = itemView.findViewById(R.id.disc_label);
        }


        public LinearProgressIndicator getProgressUsed() {
            return progressUsed;
        }

        public TextView getDiscLabel() {
            return discLabel;
        }
    }
}
