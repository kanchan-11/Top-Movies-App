package com.example.topmovies;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topmovies.databinding.ItemMoviesBinding;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private LayoutInflater layoutInflater;
    private final List<Movie> movies;

    public MovieAdapter(@NonNull List<Movie> movies) { this.movies = movies; }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemMoviesBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.item_movies, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.binding.setVariable(123,movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMoviesBinding binding;

        MovieViewHolder(@NonNull ItemMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
