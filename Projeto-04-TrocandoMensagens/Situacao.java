package com.example.trocandomensagens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class Situacao extends AppCompatActivity {
    EditText ed1, ed2, ed3;
    RadioButton rbFeminino, rbMasculino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        rbFeminino = (RadioButton) findViewById(R.id.radioButton);
        rbMasculino = (RadioButton) findViewById(R.id.radioButton2);
        // Recebe os parâmetros do nome e do imc
        // Coloca nos EditTexts da View
        ed1.setText(getIntent().getExtras().getString("nome"));
        ed2.setText(String.format("%.2f", getIntent().getExtras().getDouble("imc")));
    }
    public void verificarVoltar(View v) {
        String situacao = "";
        // Pega os valores e dados do imc, idade e sexo dos componentes da View
        double imc = Double.parseDouble(ed2.getText().toString());
        int idade = Integer.parseInt(ed3.getText().toString());
        // Determina o sexo
        int sexo = 0;
        if (rbFeminino.isChecked()) {
            sexo = 1;
        }
        if (rbMasculino.isChecked()) {
            sexo = 2;
        }
        // Verifica a situação
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
            }
        }
        // Prepara a Intent de retorno para a situação
        Intent returnIntent = new Intent();
        // Define o parâmetro e o valor
        returnIntent.putExtra("situacao",situacao);
        setResult(RESULT_OK,returnIntent);
        // Encerra a atividade
        finish();
    }
}