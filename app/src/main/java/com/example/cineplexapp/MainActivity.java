package com.example.cineplexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MoviesAdapter adapter;
    private int selectedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieList.add(new Movie("WarRoom", "November 20", R.drawable.war_room));
        movieList.add(new Movie("FireProof", "November 22", R.drawable.fireproof));
        movieList.add(new Movie("Facing Giants", "November 24", R.drawable.facing_giants));
        movieList.add(new Movie("Courageous", "November 26", R.drawable.courageous));
        movieList.add(new Movie("Soul Surfer", "November 28", R.drawable.soul_surfer));
        // Set up adapter
        adapter = new MoviesAdapter(movieList, this::handleMovieSelection);
        recyclerView.setAdapter(adapter);

        // Start background music
        Intent musicService = new Intent(this, com.example.cineplexapp.BackgroundMusicService.class);
        startService(musicService);
    }

    private void handleMovieSelection(Movie movie) {
        if (movie.isSelected()) {
            movie.setSelected(false);
            selectedCount--;
        } else if (selectedCount < 2) {
            movie.setSelected(true);
            selectedCount++;
        } else {
            Toast.makeText(this, "You cannot select more than two movies", Toast.LENGTH_SHORT).show();
            return;
        }
        adapter.notifyDataSetChanged();
        showToastWithSelection();
    }

    private void showToastWithSelection() {
        StringBuilder selectedMovies = new StringBuilder("Selected Movies: ");
        for (Movie movie : movieList) {
            if (movie.isSelected()) {
                selectedMovies.append(movie.getTitle()).append(", ");
            }
        }
        Toast.makeText(this, selectedMovies.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent musicService = new Intent(this, com.example.cineplexapp.BackgroundMusicService.class);
        stopService(musicService);
    }
}