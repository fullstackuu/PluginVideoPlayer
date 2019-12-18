package com.ironman.pluginvideoplayer;

import android.content.Context;


import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ironman.pluginvideoplayer.SuperPlayerModel;
import com.ironman.pluginvideoplayer.SuperPlayerView;
import com.ironman.pluginvideoplayer.controller.TCVodControllerLarge;
import com.ironman.pluginvideoplayer.controller.TCVodControllerSmall;
import com.ironman.pluginvideoplayer.view.TCDanmuView;



public class VideoPlayerView extends FrameLayout implements SuperPlayerView.OnSuperPlayerViewCallback {

    //超级播放器View
    private SuperPlayerView mSuperPlayerView;


    public VideoPlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.video_player_view,this);
        //设置界面
        mSuperPlayerView = (SuperPlayerView) findViewById(R.id.superVodPlayerView);
        mSuperPlayerView.setPlayerViewCallback(this);




        //隐藏 返回按钮 和 标题
        hiddenSmallVideoPlayerBackAndTitle();



    }
    private void hiddenSmallVideoPlayerBackAndTitle() {
        ViewGroup vg1 = (ViewGroup) mSuperPlayerView;
        int count1 = vg1.getChildCount();
        for (int i = 0; i < count1; i ++) {
            View v1 = vg1.getChildAt(i);
            Log.i("SmailVP1",v1.toString());
            if (v1 instanceof TCVodControllerSmall) {
                ViewGroup vg2 = (ViewGroup)v1;
                int count2 = vg2.getChildCount();
                for (int j = 0; j < count2; j ++) {
                    View v2 = vg2.getChildAt(j);
                    Log.i("SmailVP2",v2.toString());
                    if (v2 instanceof RelativeLayout) {
                        v2.findViewById(R.id.iv_back).setVisibility(GONE);
                        v2.findViewById(R.id.tv_title).setVisibility(GONE);
                    }
                    Log.i("SmailVP",v2.toString());

                }
            }
            if (v1 instanceof TCDanmuView) {
                v1.setVisibility(GONE);
            }
        }
    }


    public void playerVideo(String url) {
        SuperPlayerModel mode = new SuperPlayerModel();
        mode.title = "";
        mode.url = url;
        mSuperPlayerView.playWithModel(mode);
    }

    //设置 全屏 和 非全屏 状态下内容
    private void hiddenLargeVideoPlayerStatus() {
        ViewGroup vg1 = (ViewGroup) mSuperPlayerView;
        int count1 = vg1.getChildCount();
        for (int i = 0; i < count1; i ++) {
            View v1 = vg1.getChildAt(i);
            if (v1 instanceof TCVodControllerLarge) {
                ViewGroup vg2 = (ViewGroup) v1;
                int count2 = vg2.getChildCount();
                for (int j = 0; j < count2; j ++) {
                    View v2 = vg2.getChildAt(j);
                    Log.i("SPV2", v2.toString());
                    v2.findViewById(R.id.tv_title).setVisibility(GONE);//标题
                    v2.findViewById(R.id.iv_danmuku).setVisibility(GONE);//弹幕控制开关
//                    v2.findViewById(R.id.iv_snapshot).setVisibility(GONE);//截图控制开关
                }
            }
            if (v1 instanceof TCDanmuView) {
                v1.setVisibility(GONE);
            }
        }
    }



    @Override
    public void onStartFullScreenPlay() {
        Log.i("VideoPlayer","开始全屏播放");
        hiddenLargeVideoPlayerStatus();
    }

    @Override
    public void onStopFullScreenPlay() {
        Log.i("VideoPlayer","结束全屏播放");
    }

    @Override
    public void onClickFloatCloseBtn() {
        Log.i("VideoPlayer","点击关闭按钮");
    }

    @Override
    public void onClickSmallReturnBtn() {
        // 点击小窗模式下返回按钮，开始悬浮播放
        Log.i("VideoPlayer","开始悬浮播放");
    }

    @Override
    public void onStartFloatWindowPlay() {
        // 开始悬浮播放后，直接返回到桌面，进行悬浮播放
        Log.i("VideoPlayer","返回桌面进行悬浮播放");
    }




}
