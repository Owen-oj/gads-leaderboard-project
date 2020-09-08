package com.example.leaderboard.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leaderboard.R;
import com.example.leaderboard.models.SkillLeader;
import com.example.leaderboard.ui.main.dummy.DummyContent.DummyItem;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SkillRecyclerViewAdapter extends RecyclerView.Adapter<SkillRecyclerViewAdapter.ViewHolder> {

    private final List<SkillLeader> leaders;
    private Context context;

    public SkillRecyclerViewAdapter(Context context,List<SkillLeader> items) {
        this.context = context;
        leaders = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_skill_tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.leader = leaders.get(position);
        holder.name.setText(leaders.get(position).getName());
        holder.scoreText.setText(leaders.get(position).getScoreText());

        Picasso.Builder builder = new Picasso.Builder(this.context);
        builder.downloader(new OkHttp3Downloader(this.context));
        builder.build().load(leaders.get(position).getBadgeUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.badge);
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView scoreText;
        public final ImageView badge;
        public SkillLeader leader;

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