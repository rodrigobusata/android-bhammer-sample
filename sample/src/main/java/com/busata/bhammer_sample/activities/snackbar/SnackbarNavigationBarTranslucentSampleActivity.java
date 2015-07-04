package com.busata.bhammer_sample.activities.snackbar;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.views.snackbar.Snackbar;
import com.busata.bhammer.views.snackbar.SnackbarManager;
import com.busata.bhammer.views.snackbar.enums.SnackbarType;
import com.busata.bhammer_sample.R;

/**
 * Reference https://github.com/nispok/snackbar
 */
public class SnackbarNavigationBarTranslucentSampleActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar_translucent_sample);

        setToolBar(getString(R.string.navidationbar_translucent));

        Button singleLineButton = (Button) findViewById(R.id.single_line);
        singleLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarNavigationBarTranslucentSampleActivity.this)
                                .text("Single-line snackbar"));
            }
        });

        Button multiLineButton = (Button) findViewById(R.id.multi_line);
        multiLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarNavigationBarTranslucentSampleActivity.this)
                                .type(SnackbarType.MULTI_LINE)
                                .text("This is a multi-line snackbar. Keep in mind that snackbars" +
                                        " are meant for VERY short messages"));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disableTransparentSystemBars(getWindow());
    }

    public static boolean isTranslucentSystemBarsCapable() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void enableTransparentSystemBars(Window window) {
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void disableTransparentSystemBars(Window window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
}
