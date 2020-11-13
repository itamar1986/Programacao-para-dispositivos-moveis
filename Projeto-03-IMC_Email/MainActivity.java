package com.example.imc_email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtNome, edtPeso, edtAltura, edtDest, edtMensagem;
    // Declaração como atributo do imc para que possa ser acessado
    // por todos os métodos
    double imc =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNome = (EditText) findViewById(R.id.editText4);
        edtPeso = (EditText) findViewById(R.id.editText5);
        edtAltura = (EditText) findViewById(R.id.editText6);
        edtDest = (EditText) findViewById(R.id.editText);
        edtMensagem = (EditText) findViewById(R.id.editText3);
    }

    public void calcularIMC(View v) {
        double peso, altura;
        // Faz a entrada de dados e o cálculo
        // Foi incluído o tratamento de exceções para proteger o código
        try {
            // Leitura dos dados nos EditTexts
            altura = Double.parseDouble(edtAltura.getText().toString());
            peso = Double.parseDouble(edtPeso.getText().toString());
            // Cálculo do IMC
            this.imc = peso / Math.pow(altura, 2);
            // Apresentação do resultado pelo Toast
                Toast toast = Toast.makeText(getApplicationContext(), 
                        "IMC = " + String.format("%.2f", this.imc), Toast.LENGTH_LONG);
                toast.show();
        } catch (Exception e) {
            // Tratamento da exceção com aviso através do Toast
            Toast toast = Toast.makeText(getApplicationContext(), 
                    "Ocorreu algum problema nos dados da Pessoa!", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void enviarEmail(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        // Defina aqui o endereço de e-mail do remetente:
        emailIntent.setData(Uri.parse("mailfrom:" + "itamar_minnow@hotmail.com"));
        // Busca o endereço do destinatário no editText:
        emailIntent.setData(Uri.parse("mailto:" + edtDest.getText()));
        // Define o assunto do e-mail:
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Avaliação do IMC.");
        // Confirma que o imc foi calculado:
        calcularIMC(v);
        // Prepara os dados do conteúdo do e-mail:
        String conteudo;
        conteudo = "Nome: " + edtNome.getText();
        conteudo += "\n Peso: " + edtPeso.getText();
        conteudo += "\nAltura: " + edtAltura.getText();
        conteudo += "\nImc = " + String.format("%.2f", this.imc);
        // Apresenta o conteúdo na tela do aplicativo:
        edtMensagem.setText(conteudo);
        // Define o conteúdo do e-mail:
        emailIntent.putExtra(Intent.EXTRA_TEXT, conteudo);
        // Chama para execução o aplicativo de e-mail:
        try {
            startActivity(Intent.createChooser(emailIntent, "Enviando E-mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "Não existem clientes configurados!", Toast.LENGTH_SHORT).show();
        }
    }
}