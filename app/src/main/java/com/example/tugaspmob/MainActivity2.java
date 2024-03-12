package com.example.tugaspmob;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView etResult = findViewById(R.id.etUmur);

        // Menerima data dari Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            String umur = extras.getString("umur");

            // Menampilkan data pada TextView
            String resultText = "Nama: " + name + "\nUmur: " + umur ;
            etResult.setText(resultText);
            Button backButton = findViewById(R.id.backButton);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Kembali ke MainActivity saat tombol diklik
                    finish();
                }
            });
        }
    }
}