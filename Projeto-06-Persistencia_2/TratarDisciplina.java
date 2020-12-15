package com.example.persistencia_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TratarDisciplina extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    private int indice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratar_disciplina);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        // Recebe os dados da atividade principal
        // e preenche a tela (view)
        indice = getIntent().getExtras().getInt("indice");
        ed1.setText(getIntent().getExtras().getString("nome"));
        ed2.setText(String.format("%.1f", getIntent().getExtras().getDouble("a1")));
        ed3.setText(String.format("%.1f", getIntent().getExtras().getDouble("a2")));
        ed4.setText(String.format("%.1f", getIntent().getExtras().getDouble("a3")));
        // Altera o título da tela
        setTitle("Alterar Disciplina");
    }
    // Método para preparar os dados para retornar à atividade principal
    public void tratarVoltar(View v) {
        // Prepara a intent de retorno da disciplina nova
        Intent returnIntent = new Intent();
        // Define os parâmetros de retorno
            returnIntent.putExtra("indice", indice);
            returnIntent.putExtra("nome", ed1.getText().toString());
            returnIntent.putExtra("a1", Double.parseDouble(ed2.getText().toString()));
            returnIntent.putExtra("a2", Double.parseDouble(ed3.getText().toString()));
            returnIntent.putExtra("a3", Double.parseDouble(ed4.getText().toString()));
            // Retorna os dados à atividade principal
            setResult(RESULT_OK, returnIntent);
        // Encerra a atividade
        finish();
    }
    public void voltar(View v) {
        // Encerra a atividade, retornando à atividade principal
        finish();
    }
}