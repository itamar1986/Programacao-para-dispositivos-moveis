package com.example.exemplointent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Uri uri = null; // Objeto Uri para passagem dos parâmetros da chamada
    Intent intent = null; // Intent para chamada externa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void chamaWeb(View v){
        // Preparação dos parãmetros
        uri = Uri.parse("https://www.unijorge.edu.br/");
        // Definição da Intent
        intent = new Intent(Intent.ACTION_VIEW, uri);
        // Chamada para execução pelo Intent
        startActivity(intent);
    }
    public void chamaTelefone(View v){
        // Preparação dos parãmetros
        uri = Uri.parse("tel:+5521900000001");
        // Definição da Intent
        intent = new Intent(Intent.ACTION_DIAL, uri);
        // Chamada para execução pelo Intent
        startActivity(intent);
    }
    public void chamaMapa(View v){
        //  Preparação dos parâmetros
        uri = Uri.parse("geo:0,0?q=rua+ibituruna,rio de janeiro");
        // Definição da Intent
        intent = new Intent(Intent.ACTION_VIEW, uri);
        // Chamada para execução pelo Intent
        startActivity(intent);
    }
    public void chamaSMS(View v){
        // Preparação dos parâmetros
        uri = Uri.parse("sms: ++5521900000001");
        // Definição da Intent
        intent = new Intent(Intent.ACTION_VIEW, uri);
        // Passagem de mensagem (dados) pelo Intent
        intent.putExtra("sms _ body", "Corpo de conteúdo do SMS");
        // Chamada para execução pelo Intent
        startActivity(intent);
    }
    public void chamaToast(View v){
        // Chamada a Toast do Android
        Toast.makeText(getApplicationContext(), "Chamada Toast!", Toast.LENGTH_LONG).show();
    }
}