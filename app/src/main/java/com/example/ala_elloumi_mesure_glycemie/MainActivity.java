package com.example.ala_elloumi_mesure_glycemie;

// Les importation necessaires
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Declaration des variable
    private TextView tvAge,tvresult;
    private SeekBar sbAge;
    private RadioButton rbtOui;
    private EditText etValeur;
    private Button btnConsulter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Appel de la methode init()
        init();

        // Action sur le SeekBar
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Afficher les informations utiles dans la console de débogage
                Log.i("information","onProgressChange"+progress);
                // Mettre à jour le TextView (tvAge) avec la nouvelle valeur de la SeekBar
                tvAge.setText("Votre Age : "+progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        /*consulterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                calculer(v);
            }
            }*/

        // Action sur le bouton  consulter
        btnConsulter.setOnClickListener(this::calculer);
    }
    private void init()
    {
        // Initialisation des attributs
        tvAge=findViewById(R.id.tvAge);
        sbAge=findViewById(R.id.sbAge);
        rbtOui=findViewById(R.id.rbtOui);
        etValeur=findViewById(R.id.etValeur);
        btnConsulter=findViewById(R.id.btnConsulter);
        tvresult=findViewById(R.id.tvresult);
    }
    @SuppressLint("SetTextI18n")
    public void calculer(View v)
    {
        int age=sbAge.getProgress();
        float valeurMesure;
        String Val= etValeur.getText().toString();
        boolean verifAge=false,verifValeur=false;
        if(age!=0)
            verifAge=true;
        else
            Toast.makeText(MainActivity.this, "Veuillez verifier votre Age !", Toast.LENGTH_SHORT).show();
        if(!Val.isEmpty())
            verifValeur=true;
        else
            Toast.makeText(MainActivity.this,"Veuillez verifier votre Valeur !",Toast.LENGTH_LONG).show();
        if (verifAge && verifValeur)
        {
            valeurMesure = Float.parseFloat(Val);
            if (rbtOui.isChecked())
                if(age>=13)
                    if(valeurMesure<5.0)
                        tvresult.setText("Le niveau de glycémie est bas");
                    else if(valeurMesure>=5.0 && valeurMesure<=7.2)
                        tvresult.setText("Le niveau de glycémie est normal");
                    else
                        tvresult.setText("Le niveau de glycémie est élevé");

                else if(age>=6)
                    if(valeurMesure<5.0)
                        tvresult.setText("Le niveau de glycémie est bas");
                    else if(valeurMesure>=5.0 && valeurMesure<=10.0)
                        tvresult.setText("Le niveau de glycémie est normal");
                    else
                        tvresult.setText("Le niveau de glycémie est élevé");
                else
                    if(valeurMesure<5.5)
                        tvresult.setText("Le niveau de glycémie est bas");
                    else if(valeurMesure>=5.5 && valeurMesure<=10.0)
                        tvresult.setText("Le niveau de glycémie est normal");
                    else
                        tvresult.setText("Le niveau de glycémie est élevé");
            else
                if(valeurMesure<=10.5)
                    tvresult.setText("Le niveau de glycémie est normal");
                else
                    tvresult.setText("Le niveau de glycémie est élevé");
        }
    }
}



