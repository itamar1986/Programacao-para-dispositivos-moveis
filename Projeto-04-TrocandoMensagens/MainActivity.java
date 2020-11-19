package com.example.trocandomensagens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Intent intent1, intent2;
    EditText ed1, ed2, ed3;
    // define os valores para o request de cada atividade
    static final int ACTIVITY_REQUEST_IMC = 1;
    static final int ACTIVITY_REQUEST_SITUACAO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_IMC) {
            if(resultCode == RESULT_OK){
                double imc = data.getDoubleExtra("imc",0);
                //Coloca o valor do imc no EditText
                ed2.setText(String.format("%.2f", imc));
            }
        }
        if (requestCode == ACTIVITY_REQUEST_SITUACAO) {
            if(resultCode == RESULT_OK){
                String situacao = data.getStringExtra("situacao");
                //Coloca a situação da pessoa no EditText
                ed3.setText(situacao);
            }
        }
    }
    public void chamarCalcularIMC(View v){
        intent1 = new Intent(getApplicationContext(), CalculaIMC.class);
        // Envia o nome para a segunda atividade
        intent1.putExtra("nome", ed1.getText().toString());
        // chama a segunda atividade aguardando valores de retorno
        // no request = 1
        startActivityForResult(intent1, ACTIVITY_REQUEST_IMC);
    }

    public void chamarVerificarSituacao(View v){
        intent2 = new Intent(getApplicationContext(), Situacao.class);
        // Envia o nome e o imc para a segunda atividade
        intent2.putExtra("nome", ed1.getText().toString());
        intent2.putExtra("imc", Double.parseDouble(ed2.getText().toString()));
        // chama a segunda atividade aguardando valores de retorno
        // no request = 2
        startActivityForResult(intent2, ACTIVITY_REQUEST_SITUACAO);
    }
}