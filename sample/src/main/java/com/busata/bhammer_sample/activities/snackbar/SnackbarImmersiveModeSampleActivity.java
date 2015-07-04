package com.busata.bhammer_sample.activities.snackbar;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.views.snackbar.Snackbar;
import com.busata.bhammer.views.snackbar.SnackbarManager;
import com.busata.bhammer.views.snackbar.enums.SnackbarType;
import com.busata.bhammer_sample.R;


/**
 * Reference https://github.com/nispok/snackbar
 */
public class SnackbarImmersiveModeSampleActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive_mode_sample);

        setToolBar(getString(R.string.immersive_mode));

        hideSystemUI();

        Button singleLineButton = (Button) findViewById(R.id.single_line);
        singleLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarImmersiveModeSampleActivity.this)
                                .text("Single-line snackbar"));
            }
        });

        Button multiLineButton = (Button) findViewById(R.id.multi_line);
        multiLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarImmersiveModeSampleActivity.this)
                                .type(SnackbarType.MULTI_LINE)
                                .text("This is a multi-line snackbar. Keep in mind that snackbars" +
                                        " are meant for VERY short messages"));
            }
        });

        ViewGroup container = (ViewGroup) findViewById(android.R.id.content);
        container.setClickable(true);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickContainerView();
            }
        });
    }

    public static boolean isImmersiveModeCapable() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean isSystemUiHidden() {
        return ((getWindow().getDecorView().getSystemUiVisibility() & View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) != 0);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void onClickContainerView() {
        if (isSystemUiHidden()) {
            showSystemUI();
        } else {
            hideSystemUI();
        }
    }
}
