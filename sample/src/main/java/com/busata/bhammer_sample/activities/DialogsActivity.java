package com.busata.bhammer_sample.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.Toast;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.dialogs.BDatePicker;
import com.busata.bhammer.dialogs.BDialog;
import com.busata.bhammer.dialogs.BTimePicker;
import com.busata.bhammer.utils.BViewUtil;
import com.busata.bhammer.views.materialedittext.MaterialEditText;
import com.busata.bhammer_sample.R;


public class DialogsActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        setToolBar(getString(R.string.dialogs));
    }

    public void openDialog(View view) {
        BViewUtil.createDialogInfo(this, "Tittle", "A dialog is a small window that prompts the user " +
                "to make a decision or enter additional information.");
    }

    public void openAnswerDialog(View view) {
        final BDialog dialog = new BDialog(this, "Tittle", "A dialog is a small " +
                "window that prompts the user to make a decision or enter additional information.");
        dialog.addButton(getString(R.string.cancel), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                BViewUtil.makeToast(DialogsActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        }).addButton(getString(R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                BViewUtil.makeToast(DialogsActivity.this, "Ok", Toast.LENGTH_SHORT).show();
            }
        }, true).show();
    }

    public void openComponentsDialog(View view) {
        final LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);

        MaterialEditText floatLabel = new MaterialEditText(this);
        floatLabel.setHint("Hint Text");
        floatLabel.setFloatingLabel(MaterialEditText.FLOATING_LABEL_NORMAL);
        floatLabel.setLayoutParams(params);

        final BDialog dialog = new BDialog(this, "Form");
        dialog.addView(floatLabel)
                .addButton(getString(R.string.ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                }, true).show();
    }

    public void showNumberPicker(View view) {
        BViewUtil.showNumberPicker(this, (EditText) view, 0, 100);
    }

    public void showDatePicker(View view) {
        BViewUtil.showDatePicker(this, (EditText) view, BDatePicker.DD_MM_YYYY);
    }

    public void showTimePicker(View view) {
        BViewUtil.showTimePicker(this, (EditText) view, BTimePicker.HH_MM, true);
    }
}