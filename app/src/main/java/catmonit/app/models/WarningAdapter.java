package catmonit.app.models;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import catmonit.app.R;


public class WarningAdapter extends RecyclerView.Adapter<WarningAdapter.WarningHolder> {
    private final Warning[] dataSet;
    private final @LayoutRes int layout;

    public WarningAdapter(Warning[] dataSet, @LayoutRes int layout) {
        this.dataSet = dataSet;
        this.layout = layout;
    }

    @NonNull
    @Override
    public WarningHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new WarningHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull WarningHolder holder, int position) {
        holder.getDescription().setText(dataSet[position].getText());
        SpannableStringBuilder sb = new SpannableStringBuilder()
                .append(dataSet[position].getDeviceName(), new StyleSpan(Typeface.BOLD), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                .append(" (")
                .append(dataSet[position].getIpAddress())
                .append(")");
        holder.getTitle().setText(sb);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public static class WarningHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;

        public WarningHolder(View view) {
            super(view);

            this.title = view.findViewById(R.id.tvTitle);
            this.description = view.findViewById(R.id.tvDescription);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }
    }
}
