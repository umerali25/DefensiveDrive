package com.example.defencedrive.tabfrags.ui.lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.defencedrive.R;
import com.example.defencedrive.databinding.ActivityHazardvideoBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class HazardvideoActivity extends AppCompatActivity {
    ActivityHazardvideoBinding binding;
    VideoAdapter adapter;
    ArrayList<String> videos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHazardvideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        binding.videoRecycler.setLayoutManager(new LinearLayoutManager(this));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Videos");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                videos.clear(); // Clear the list before adding new data
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.exists()) {
                        String video = dataSnapshot.getValue(String.class);
                        videos.add(video);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HazardvideoActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new VideoAdapter(this, videos);
        binding.videoRecycler.setAdapter(adapter);
    }

    public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
        Context context;
        ArrayList<String> videos;

        public VideoAdapter(Context context, ArrayList<String> videos) {
            this.context = context;
            this.videos = videos;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.video_row, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String videoUrl = videos.get(position);
            new VideoLoaderTask(holder).execute(videoUrl);
        }

        @Override
        public int getItemCount() {
            return videos.size();
        }

        private class VideoLoaderTask extends AsyncTask<String, Void, String> {
            private WeakReference<ViewHolder> viewHolderRef;

            VideoLoaderTask(ViewHolder viewHolder) {
                viewHolderRef = new WeakReference<>(viewHolder);
            }

            @Override
            protected String doInBackground(String... params) {
                // Simulate video loading delay (Remove this in your actual implementation)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return params[0];
            }

            @Override
            protected void onPostExecute(String videoUrl) {
                ViewHolder viewHolder = viewHolderRef.get();
                if (viewHolder != null) {
                    viewHolder.bindVideo(videoUrl);
                }
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            VideoView videoView;
            MediaController controller;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                videoView = itemView.findViewById(R.id.video_View);
                controller = new MediaController(context);
            }

            public void bindVideo(String videoUrl) {
                videoView.setVideoPath(videoUrl);
                controller.setAnchorView(videoView);
                videoView.setMediaController(controller);
                videoView.start();
            }
        }
    }
}


