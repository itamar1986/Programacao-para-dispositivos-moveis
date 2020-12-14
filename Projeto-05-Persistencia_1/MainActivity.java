package com.example.persistencia_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Objetos relacionados aos componentes da tela (view)
    EditText edNome, edPeso, edAltura, edIdade;
    RadioButton rdFeminino, rdMasculino;
    // Declaração do objeto para o armazenamento das preferências
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edNome = (EditText) findViewById(R.id.editText);
        edPeso = (EditText) findViewById(R.id.editText2);
        edAltura = (EditText) findViewById(R.id.editText3);
        edIdade = (EditText) findViewById(R.id.editText4);
        rdFeminino = (RadioButton) findViewById(R.id.radioButton);
        rdMasculino = (RadioButton) findViewById(R.id.radioButton2);
        // Definição do arquivo de preferências com o nome do arquivo
        // e o modo de compartilhamento de dados privado
        prefs = getSharedPreferences("dadosUsuario" , MODE_PRIVATE);
        // Método para preencher a tela com os dados do arquivo de
        // preferências ou com valores-padrão
        iniciarDadosUsuario();
    }
    private void iniciarDadosUsuario(){
        // Verifica se o dado com o nome está disponível no arquivo
        // de preferências e preenche o componente; caso contrário,
        // preenche o componente com um texto padrão
        if(prefs.contains("nome")) {
            edNome.setText(prefs.getString("nome", ""));
        }
        else{
            edNome.setText("Digite o seu nome!");
        }
        // Verifica se o dado com a idade está disponível no arquivo
        // de preferências e preenche o componente; caso contrário,
        // preenche o componente com valor 0 (zero)
        if(prefs.contains("idade")) {
            edIdade.setText(String.valueOf(prefs.getInt("idade", 0)));
        }
        else{
            edIdade.setText("0");
        }
        // Verifica se o dado com o peso está disponível no arquivo
        // de preferências e preenche o componente; caso contrário,
        // preenche o componente com valor 0.0 (zero.zero)
        if(prefs.contains("peso")) {
            edPeso.setText(String.valueOf(prefs.getFloat("peso" , 0.0f)));
        }
        else{
            edPeso.setText("0.0");
        }
        // Verifica se o dado com a altura está disponível no arquivo
        // de preferências e preenche o componente; caso contrário,
        // preenche o componente com valor 0.0 (zero.zero)
        if(prefs.contains("altura")){
            edAltura.setText(String.valueOf(prefs.getFloat("altura",0.0f)));
        }
        else{
            edAltura.setText("0.0");
        }
        // Verifica se o dado com o sexo está disponível no arquivo
        // de preferências e seleciona o componente radioButton adequado;
        // caso contrário, seleciona o componente padrão (feminino)
        if(prefs.contains("sexo")) {
            if (prefs.getBoolean("sexo", true)) {
                // Caso verdadeiro (true), seleciona o radioButton do sexo feminino
                rdFeminino.setChecked(true);
            } else {
                // Caso false (falso), seleciona o radioButton do sexo masculino
                rdMasculino.setChecked(true);
            }
        }
        else {
            rdFeminino.setChecked(true);
        }
    }
    public void salvar(View v){
        // Abre o arquivo de preferências para edição
        SharedPreferences.Editor prefUsuario = prefs.edit();
        // Determina os pares identificador/valor
        // de cada dado do arquivo de preferências
        // para texto (String)
        prefUsuario.putString("nome", edNome.getText().toString());
        // Para inteiro (int)
        prefUsuario.putInt("idade", Integer.parseInt(edIdade.getText().toString()));
        // Para real (float)
        prefUsuario.putFloat("peso", Float.parseFloat(edPeso.getText().toString()));
        prefUsuario.putFloat("altura", Float.parseFloat(edAltura.getText().toString()));
        // Para booleano (boolean)
        if(rdFeminino.isChecked()){
            prefUsuario.putBoolean("sexo", true);
        }
        else{
            prefUsuario.putBoolean("sexo", false);
        }
        // Confirma a alteração
        prefUsuario.commit();
        // Mensagem de aviso ao usuário
        Toast.makeText(getApplicationContext(),
                "Suas preferências foram salvas!\nAguarde alguns instantes para sair.",
        Toast.LENGTH_LONG).show();
    }
    public void limpar(View v){
        // Abre o arquivo de preferências para edição
        SharedPreferences.Editor prefUsuario = prefs.edit();
        // Limpa o arquivo de preferências
        prefUsuario.clear();
        // Confirma a alteração
        prefUsuario.apply();
        // Prepara a tela novamente, sem os dados de preferência
        iniciarDadosUsuario();
        // Mensagem de aviso ao usuário
        Toast.makeText(getApplicationContext(),
                "Suas preferências foram apagadas!\nAguarde alguns instantes para sair.",
        Toast.LENGTH_LONG).show();
    }
    private void sair(View v){
        // Encerra o aplicativo
        finish();
    }
}