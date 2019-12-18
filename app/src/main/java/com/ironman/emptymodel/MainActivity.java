package com.ironman.emptymodel;


import android.app.Activity;
import android.os.Bundle;

import com.ironman.pluginvideoplayer.VideoPlayerView;

public class MainActivity extends Activity {


    private VideoPlayerView videoPlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        videoPlayerView = (VideoPlayerView) findViewById(R.id.video_player);


        String url = "http://test.api.gateway.hejizx.com/file/file/upload/6d4eef2f57806817d7fd0ed319807021" + ".m3u8";
        videoPlayerView.playerVideo(url);
    }
}
