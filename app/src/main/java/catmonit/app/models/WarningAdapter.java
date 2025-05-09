package catmonit.app.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import catmonit.app.R;


public class WarningAdapter extends RecyclerView.Adapter<WarningAdapter.WarningHolder> {
    private Warning[] dataSet;

    public WarningAdapter(Warning[] dataSet){
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public WarningHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.warning_display, parent, false);
        return new WarningHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WarningHolder holder, int position) {
        holder.getDescription().setText(dataSet[position].getText());
        holder.getTitle().setText(String.valueOf(dataSet[position].getIpAddress()));
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public static class WarningHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView description;

        public WarningHolder(View view)  {
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
