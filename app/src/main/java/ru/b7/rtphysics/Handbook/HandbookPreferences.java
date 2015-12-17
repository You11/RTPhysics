package ru.b7.rtphysics.Handbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import ru.b7.rtphysics.BaseActivity;

/**
 * settings activity for article
 */
public class HandbookPreferences extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            HandbookPreferencesFragment fragment = new HandbookPreferencesFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this).
                setTitle("Мяу").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).
                setMessage("Пожалуйста, перезапустите статью для применения изменений.");
        adb.show();
    }
}
