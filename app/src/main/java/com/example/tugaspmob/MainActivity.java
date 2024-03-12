package com.example.tugaspmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    EditText etName, etTanggalLahir;
    Button btnHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        btnHitung = findViewById(R.id.btnHitung);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String tanggalLahirStr = etTanggalLahir.getText().toString();

                if (name.isEmpty() || tanggalLahirStr.isEmpty()) {
                    return;
                }

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name", name);
                intent.putExtra("umur", calculateAge(tanggalLahirStr));
                startActivity(intent);
            }
        });
    }

    private String calculateAge(String tanggalLahirStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date tanggalLahir = sdf.parse(tanggalLahirStr);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(tanggalLahir);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            calendar.setTime(new Date());
            int currentYear = calendar.get(Calendar.YEAR);
            int currentMonth = calendar.get(Calendar.MONTH) + 1;
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

            int umurTahun = currentYear - year;
            int umurBulan = currentMonth - month;
            int umurHari = currentDay - day;

            if (umurHari < 0) {
                umurBulan--;
                umurHari += calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if (umurBulan < 0) {
                umurTahun--;
                umurBulan += 12;
            }

            return umurTahun + " tahun, " + umurBulan + " bulan, " + umurHari + " hari";

        } catch (Exception e) {
            e.printStackTrace();
            return "Gagal menghitung umur";
        }
    }
}
