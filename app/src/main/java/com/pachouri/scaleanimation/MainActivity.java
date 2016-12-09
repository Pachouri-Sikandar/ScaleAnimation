package com.pachouri.scaleanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewBottom;
    private ImageView imageViewTop;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    private void initView() {
        imageViewBottom = (ImageView) findViewById(R.id.imageViewBottom);
        imageViewTop = (ImageView) findViewById(R.id.imageViewTop);
        buttonReset = (Button) findViewById(R.id.buttonReset);

        imageViewBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call method for scale animation
                scaleDownAndTransitionAnimation(imageViewBottom, imageViewTop);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBottom.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Method to scale down animation
     *
     * @param startView  - first view from which animation starts
     * @param finishView - second view to which animation ends
     */

    private void scaleDownAndTransitionAnimation(final View startView, View finishView) {
        int startViewLocation[] = new int[2];
        startView.getLocationInWindow(startViewLocation);
        int finishViewLocation[] = new int[2];
        finishView.getLocationInWindow(finishViewLocation);
        int startX = startViewLocation[0] + startView.getWidth() / 2;
        int startY = startViewLocation[1] + startView.getHeight() / 2;
        int endX = finishViewLocation[0] + finishView.getWidth() / 2;
        int endY = finishViewLocation[1] + finishView.getHeight() / 2;
        ScaleAnimation animation = new ScaleAnimation(1f, 0f, 1, 0f,
                Animation.ABSOLUTE, endX - startX + startView.getWidth() / 2,
                Animation.ABSOLUTE, endY - startY + startView.getHeight() / 2);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                startView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startView.setVisibility(View.GONE);
            }
        });
        startView.startAnimation(animation);
    }
}
