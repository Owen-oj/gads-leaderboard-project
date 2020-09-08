package com.example.leaderboard.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leaderboard.R;
import com.example.leaderboard.models.LearningLeader;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link LearningLeader}.
 * TODO: Replace the implementation with code for your data type.
 */
public class LearningRecyclerViewAdapter extends RecyclerView.Adapter<LearningRecyclerViewAdapter.ViewHolder> {

    private final List<LearningLeader> mValues;
    private Context context;


    public LearningRecyclerViewAdapter(Context context,List<LearningLeader> items) {
        this.context = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_leader_tab, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.leader = mValues.get(position);
        holder.scoreText.setText(mValues.get(position).getScoreText());
        holder.name.setText(mValues.get(position).getName());

        Picasso.Builder builder = new Picasso.Builder(this.context);
        builder.downloader(new OkHttp3Downloader(this.context));
        builder.build().load(mValues.get(position).getBadgeUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.badge);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView scoreText;
        public final TextView name;
        public ImageView badge;

        public LearningLeader leader;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.leader_name);
            scoreText = (TextView) view.findViewById(R.id.score_text);
            badge = (ImageView) view.findViewById(R.id.badge);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + scoreText.getText() + "'";
        }
    }

}