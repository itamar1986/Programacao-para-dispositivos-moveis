package com.example.trocandomensagens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalculaIMC extends AppCompatActivity {
    EditText ed1, ed2, ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_i_m_c);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        // recebe o nome da pessoa e coloca no Edittext do nome
        String nome = getIntent().getExtras().getString("nome");
        ed1.setText(nome);
    }
    public void calcularVoltar(View v){
        double peso, altura, imc;
        // Pega os valores do peso e altura nos EditTexts
        peso = Double.parseDouble(ed2.getText().toString());
        altura = Double.parseDouble(ed3.getText().toString());
        // Calcula o imc
        imc = peso / Math.pow(altura,2);
        // Apresenta o imc através de um Toast - pode ser retirado
        Toast.makeText(getApplicationContext(), "IMC=" + String.valueOf(imc), Toast.LENGTH_LONG).show();
        // Prepara a Intent de retorno para o valor do imc
        Intent returnIntent = new Intent();
        // Define o parâmetro e o valor
        returnIntent.putExtra("imc",imc);
        setResult(RESULT_OK,returnIntent);
        // Encerra a atividade
        finish();
    }
}