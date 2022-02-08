package com.codes.quizme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codes.quizme.databinding.RowLeaderboardBinding;

import java.util.ArrayList;

public class LeaderBoardsAdapter extends RecyclerView.Adapter<LeaderBoardsAdapter.LeaderBoardViewHolder> {

    Context context;
    ArrayList<User> users;

    public LeaderBoardsAdapter(Context context, ArrayList<User> users){
        this.context = context;
        this.users = users;

    }

    @NonNull
    @Override
    public LeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_leaderboard, parent, false);
        return new LeaderBoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardViewHolder holder, int position) {
        User user = users.get(position);

        holder.binding.name.setText(user.getName());
        holder.binding.coin.setText(String.valueOf(user.getCoins()));
        holder.binding.index.setText(String.format("%d",position+1));

        Glide.with(context)
                .load(user.getProfile())
                .into(holder.binding.imageView9);

    }

    @Override
    public int getItemCount() {
        return  users.size();
    }

    public class LeaderBoardViewHolder extends RecyclerView.ViewHolder{

        RowLeaderboardBinding binding;
        public LeaderBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowLeaderboardBinding.bind(itemView);
        }
    }
}
