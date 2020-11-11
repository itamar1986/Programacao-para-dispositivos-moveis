package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    EditText edt1;
    EditText edt2;
    EditText edt3;
    EditText edt4;
    CheckBox cb1;
    RadioButton rad1;
    RadioButton rad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = (EditText) findViewById(R.id.editText);
        edt2 = (EditText) findViewById(R.id.editText2);
        edt3 = (EditText) findViewById(R.id.editText3);
        edt4 = (EditText) findViewById(R.id.editText4);
        cb1 = (CheckBox) findViewById(R.id.checkBox);
        rad1 = (RadioButton) findViewById(R.id.radioButton);
        rad2 = (RadioButton) findViewById(R.id.radioButton2);
    }

    public void calcular(View v) {
        // declaração de variáveis:
        double peso = 0, altura = 0, imc = 0;
        int idade = 0;
        int sexo = 0;
        String situacao;

        // Entradas de dados.
        peso = Double.parseDouble(edt1.getText().toString());
        altura = Double.parseDouble(edt2.getText().toString());
        if (cb1.isChecked()) { // se estiver marcado idade valerá 1, caso contrário valerá 2
            idade = 1;
        } else {
            idade = 2;
        }
        if (rad1.isChecked()) { // se o rad1 estiver marcado sexo valerá 1.
            sexo = 1;
        } else { // senão valerá 2.
            sexo = 2;
        }
        // Processamento
        imc = peso / Math.pow(altura, 2); // Math.pow realiza o cálculo de potência
        if (idade == 1) { // idade 1, Sim.
            situacao = "Idade fora da faixa. Situação não determinada";
        } else { // idade 2, Não.
            if (sexo == 1) { // sexo = 1 - Feminino
                if (imc < 19.1) {
                    situacao = "Abaixo do peso.";
                } else {
                    if (imc < 25.8) {
                        situacao = "No peso normal.";
                    } else {
                        if (imc < 27.3) {
                            situacao = "Marginalmente acima do peso.";
                        } else {
                            if (imc < 32.3) {
                                situacao = "Acima do peso.";
                            } else {
                                situacao = "Obesa.";
                            }
                        }
                    }
                }
            } else { // sexo = 2 - Masculino
                if (imc < 20.7) {
                    situacao = "Abaixo do peso.";
                } else {
                    if (imc < 26.4) {
                        situacao = "No peso normal.";
                    } else {
                    if (imc < 27.8) {
                        situacao = "Marginalmente acima do peso.";
                    } else {
                        if (imc < 31.1) {
                            situacao = "Acima do peso.";
                        } else {
                            situacao = "Obeso.";
                        }
                    }
                }
            }
        } // fim das tabelas
    }
    // Saída de dados
    edt3.setText(String.format("%.2f",imc));
    edt4.setText(situacao);
    }
}