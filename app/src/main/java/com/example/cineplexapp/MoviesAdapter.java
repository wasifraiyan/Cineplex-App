package com.example.cineplexapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private MovieSelectionListener listener;

    public MoviesAdapter(List<Movie> movieList, MovieSelectionListener listener) {
        this.movieList = movieList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.date.setText(movie.getShowDate());
        holder.image.setImageResource(movie.getImageResource());

        // Change background color based on selection
        holder.itemView.setBackgroundColor(movie.isSelected() ? Color.RED : Color.BLUE);

        holder.itemView.setOnLongClickListener(v -> {
            listener.onMovieSelected(movie);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, date;
        ImageView image;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movieTitle);
            date = itemView.findViewById(R.id.movieDate);
            image = itemView.findViewById(R.id.movieImage);
        }
    }

    public interface MovieSelectionListener {
        void onMovieSelected(Movie movie);
    }
}
