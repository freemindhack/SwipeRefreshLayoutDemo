package com.dnsoftindia.swiperefreshlayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dnsoftindia.swiperefreshlayoutdemo.models.Search;

import java.util.ArrayList;

/**
 * Created by Ganesha on 2/28/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private ArrayList<Search> movies = new ArrayList<>();
    private Context context;

    public MovieAdapter(ArrayList<Search> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_cardview_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MyViewHolder holder, int position) {
        Search s = movies.get(position);
        holder.tvTitle.setText(s.getTitle());
        holder.tvType.setText(s.getType());
        holder.tvYear.setText(s.getYear());

        Glide.with(context)
                .load(s.getPoster())
                .placeholder(R.drawable.ic_autorenew_black_24dp)
                .into(holder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivMovie;
        private TextView tvTitle;
        private TextView tvYear;
        private TextView tvType;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivMovie = (ImageView) itemView.findViewById( R.id.ivMovie );
            tvTitle = (TextView) itemView.findViewById( R.id.tvTitle );
            tvYear = (TextView) itemView.findViewById( R.id.tvYear );
            tvType = (TextView) itemView.findViewById( R.id.tvType );
        }
    }
}
