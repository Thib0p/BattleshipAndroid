package com.excilys.formation.battleships.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import battleships.formation.excilys.com.battleships.R;

public class PlayerNameActivity extends AppCompatActivity {
    EditText mNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);  // bind view
        mNameEditText = (EditText) findViewById(R.id.example_text_name);  // bind widgets
    }

    public void onClickButton(View v) {
        String name = mNameEditText.getText().toString();
        if (!name.isEmpty()) {
           /* do stuff */
            //Toast.makeText(PlayerNameActivity.this, name, Toast.LENGTH_LONG).show();
            BattleShipsApplication.getGame().init(name);
        }
    }
}