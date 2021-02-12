package com.example.architectureapp1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DeleteAllDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.all_delete_dialog_message);
        builder.setPositiveButton(R.string.all_delete_dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogButtonClick(getString(R.string.all_delete_dialog_positive));
            }
        }).setNegativeButton(R.string.all_delete_dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogButtonClick(getString(R.string.all_delete_dialog_negative));
            }
        });
        return builder.create();
    }

    private OnClickDialogButtonListener listener;

    public void setListener(OnClickDialogButtonListener listener) {
        this.listener = listener;
    }

    public interface OnClickDialogButtonListener {
        void onDialogButtonClick(String string);
    }
}
