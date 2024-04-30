package com.example.uts_pemrograman_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Spinner jenisKelamin;
    private DatePickerDialog datePickerDialog;
    private TextView tv_umur;
    private EditText et_tanggalLahir, et_alamat, et_nik, et_nama, et_email, et_tempatLahir;
    private RadioGroup rg_kewarganegaraan;
    private CheckBox cb_dev, cb_design, cb_ai, cb_finance, cb_writing;
    private Button btn_reset, btn_submit;

    // Variabel Pembantu
    private int Umur = 0;
    private String TglLahir = "";
    private String Kewarganegaraan = "";
    private Set<String> Kompetensi = new HashSet<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inisialisasi
        tv_umur = (TextView) findViewById(R.id.txt_umur);
        et_tanggalLahir = (EditText) findViewById(R.id.et_tanggalLahir);
        et_nama = (EditText) findViewById(R.id.et_namaLengkap);
        et_alamat = (EditText) findViewById(R.id.et_alamat);
        et_nik = (EditText) findViewById(R.id.et_nik);
        et_email = (EditText) findViewById(R.id.et_email);
        jenisKelamin = (Spinner) findViewById(R.id.sp_jenisKelamin);
        et_tempatLahir = (EditText) findViewById(R.id.et_tempatLahir);
        rg_kewarganegaraan = (RadioGroup) findViewById(R.id.rg_kewarganegaraan);
        cb_dev = (CheckBox) findViewById(R.id.checkbox_devIt);
        cb_ai = (CheckBox) findViewById(R.id.checkbox_aiService);
        cb_design = (CheckBox) findViewById(R.id.checkbox_designCreative);
        cb_finance = (CheckBox) findViewById(R.id.checkbox_finance);
        cb_writing = (CheckBox) findViewById(R.id.checkbox_writing);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        // Spinner
        List<String> mList = Arrays.asList("Pilih", "Pria", "Wanita");
        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, R.layout.sp_item_jeniskelamin, mList) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        mArrayAdapter.setDropDownViewResource(R.layout.sp_item_jeniskelamin);
        jenisKelamin.setAdapter(mArrayAdapter);

        // DatePicker
        et_tanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        // RadioGroup
        rg_kewarganegaraan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.rb_wni) {
                    Kewarganegaraan = "WNI";
                } else if (id == R.id.rb_wna) {
                    Kewarganegaraan = "WNA";
                }
            }
        });

        // CheckBox
        cb_dev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onKompetensiChangeListener(isChecked, cb_dev.getText().toString());
            }
        });

        cb_design.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onKompetensiChangeListener(isChecked, cb_design.getText().toString());
            }
        });

        cb_ai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onKompetensiChangeListener(isChecked, cb_ai.getText().toString());
            }
        });

        cb_finance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onKompetensiChangeListener(isChecked, cb_finance.getText().toString());
            }
        });

        cb_writing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onKompetensiChangeListener(isChecked, cb_writing.getText().toString());
            }
        });

        // Button
            // Reset
            btn_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearAllform();
                }
            });

            // Submit
            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!formValidation()) {
                        return;
                    }
                    Intent i = new Intent(MainActivity.this, ViewActivity.class);
                    i.putExtra("Kewarganegaraan", Kewarganegaraan);
                    i.putExtra("TglLahir", TglLahir);
                    i.putExtra("Umur", tv_umur.getText().toString());
                    i.putExtra("TempatLahir", et_tempatLahir.getText().toString());
                    i.putExtra("Email", et_email.getText().toString());
                    i.putExtra("Alamat", et_alamat.getText().toString());
                    i.putExtra("Nik", et_nik.getText().toString());
                    i.putExtra("Nama", et_nama.getText().toString());
                    i.putExtra("JenisKelamin", jenisKelamin.getSelectedItem().toString());
                    i.putExtra("Kompetensi", new ArrayList<>(Kompetensi));
                    startActivity(i);
                }
            });
    }

    // Function
    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               et_tanggalLahir.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int currentAge = currentYear - year;
                tv_umur.setText(currentAge + " Tahun");
                Umur = currentAge;
                TglLahir = (dayOfMonth + "/" + monthOfYear + "/" + year).toString();
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void clearAllform(){
        tv_umur.setText("(xx Tahun)");
        et_tanggalLahir.setText("");
        et_nama.setText("");
        et_alamat.setText("");
        et_nik.setText("");
        et_email.setText("");
        jenisKelamin = (Spinner) findViewById(R.id.sp_jenisKelamin);
        et_tempatLahir.setText("");
        rg_kewarganegaraan.clearCheck();
        cb_dev.setChecked(false);
        cb_ai.setChecked(false);
        cb_design.setChecked(false);
        cb_finance.setChecked(false);
        cb_writing.setChecked(false);
        jenisKelamin.setSelection(0);
        Umur = 0;
        TglLahir = "";
        Kewarganegaraan = "";
        Kompetensi.clear();
        Toast.makeText(this, "Formulir telah dibersihkan", Toast.LENGTH_SHORT).show();
    }

    public void onKompetensiChangeListener(boolean isChecked, String competency) {
        if (isChecked) {
            Kompetensi.add(competency);
        } else {
            Kompetensi.remove(competency);
        }
    }

    private boolean formValidation() {
        if (TextUtils.isEmpty(et_nik.getText().toString())) {
            et_nik.setError("NIK tidak boleh kosong");
            return false;
        }
        if (et_nik.length() != 16) {
            et_nik.setError("NIK harus 16 digit");
            return false;
        }

        if (TextUtils.isEmpty(et_nama.getText().toString())) {
            et_nama.setError("Nama tidak boleh kosong");
            return false;
        }

        if (TglLahir.isEmpty()) {
            Toast.makeText(this, "Tanggal lahir tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(et_tempatLahir.getText().toString())) {
            et_tempatLahir.setError("Tempat lahir tidak boleh kosong");
            return false;
        }

        if (TextUtils.isEmpty(et_alamat.getText().toString())) {
            et_alamat.setError("Alamat tidak boleh kosong");
            return false;
        }

        if (jenisKelamin.getSelectedItem().toString() == "Pilih") {
            Toast.makeText(this, "Jenis Kelamin tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Kewarganegaraan.isEmpty()) {
            Toast.makeText(this, "Status kewarganegaraan tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Kompetensi.isEmpty()) {
            Toast.makeText(this, "Wajib memilih minimal 1 kompetensi", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(et_email.getText().toString())) {
            et_email.setError("Email tidak boleh kosong");
            return false;
        }



        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
            et_email.setError("Email tidak valid");
            return false;
        }

        return true;
    }


}