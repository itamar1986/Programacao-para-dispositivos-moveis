package com.example.persistencia_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // Componente lista da view
    ListView listaDisciplinas;
    // ArrayAdapter para realizar o preenchimento da lista com os itens
    // do vetor (array)
    ArrayAdapter adapter;
    // Determina o código da intent
    public static final int ACTIVITY_REQUEST_DISCIPLINA = 1;
    // Determina o número total de itens (disciplinas)
    public static final int TOTAL_DISCIPLINAS = 50;
    // Determina o vetor de textos para os itens da lista
    public String[] disciplinas = new String[TOTAL_DISCIPLINAS];
    // Determina o vetor de objetos Disciplina
    public Disciplina[] vetDisciplinas = new Disciplina[TOTAL_DISCIPLINAS];
    // Declaração da intent
    Intent intent;
    // Armazena o índice do item da lista
    public int indiceItem = 0;
    // Define o nome do arquivo de dados
    private final String NOMEARQUIVO = "dadosDisciplinas.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaDisciplinas = (ListView) findViewById(R.id.lista);

        // Instancia os objetos Disciplina para poderem
        // ser usados na carga dos dados
        iniciarVetor();
        // Carrega o arquivo e prepara os itens da lista
        try {
            if (carregarDisciplinasArquivo(NOMEARQUIVO)) {
                // Mensagem de informação de sucesso
                Toast.makeText(getApplicationContext(),
                        "Dados carregados na lista!", Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException fnfe) {
            // Poderá ser exibida até a primeira gravação
            Toast.makeText(getApplicationContext(),
                    "Arquivo INEXISTENTE!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Erro de IO!", Toast.LENGTH_SHORT).show();
        }
        // Atualiza a lista (view) com os itens do arquivo
        atualizarLista();
        // Altera o título da view
        setTitle("Disciplinas");
        // Determina que os itens da lista serão clicáveis e
        // prepara o método de controle: onItemClick(..)
        listaDisciplinas.setOnItemClickListener(this); // Clique no item
    }
    // Método para atualizar a lista após o carregamento ou
    // alguma alteração em um item
    private void atualizarLista() {
        for (int i = 0; i < TOTAL_DISCIPLINAS; i++) {
            // Para cada disciplina, é preparado o vetor de
            // itens com os dados formatados
            disciplinas[i] = vetDisciplinas[i].textoLista();
        }
        // Prepara o adapter com os itens do vetor de disciplinas
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, disciplinas);
        // Preenche a lista com os itens do vetor de disciplinas
        listaDisciplinas.setAdapter(adapter);
    }
    // Método para instanciação (criação) dos objetos Disciplina
    private void iniciarVetor() {
        for (int i = 0; i < TOTAL_DISCIPLINAS; i++) {
            // Instancia cada objeto
            vetDisciplinas[i] = new Disciplina();
        }
    }
    // Método para carregar os dados do arquivo
    // Recebe o nome do arquivo
    private boolean carregarDisciplinasArquivo(String arq) throws
            FileNotFoundException, IOException {
        // Define o objeto File para identificar e abrir o arquivo
        // getFilesDir() retorna o diretório corrente
        File file = new File(getFilesDir(), arq);
        // Cria o objeto de leitura dos dados
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        // Realiza a leitura e atribuição dos dados do arquivo
        for (int i = 0; i < TOTAL_DISCIPLINAS; i++) {
        // Para cada objeto do vetor de Disciplinas
            vetDisciplinas[i].setNome(dis.readUTF());
            vetDisciplinas[i].setA1(dis.readDouble());
            vetDisciplinas[i].setA2(dis.readDouble());
            vetDisciplinas[i].setA3(dis.readDouble());
        }
        // Encerra o recurso
        dis.close();
        return true;
    }
    // Trata a escolha de um item pelo usuário
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Position é a posição do item na lista
        // Armazena o índice do elemento escolhido na lista
        indiceItem = position;
        // Preparação da intent com previsão de retorno de dados
        intent = new Intent(getApplicationContext(), TratarDisciplina.class);
        // Passa os dados do item escolhido para a atividade de tratamento
        intent.putExtra("indice", indiceItem);
        intent.putExtra("nome" , vetDisciplinas[indiceItem].getNome());
        intent.putExtra("a1", vetDisciplinas[indiceItem].getA1());
        intent.putExtra("a2", vetDisciplinas[indiceItem].getA2());
        intent.putExtra("a3", vetDisciplinas[indiceItem].getA3());
        // Chama a atividade aguardando valores de retorno
        startActivityForResult(intent, ACTIVITY_REQUEST_DISCIPLINA);
    }
    // Método para o tratamento do retorno dos dados da atividade secundária
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Identifica a atividade de retorno
        if (requestCode == ACTIVITY_REQUEST_DISCIPLINA) {
            if (resultCode == RESULT_OK) {
                // Altera os dados do objeto por meio do índice
                int i = data.getIntExtra("indice", 0);
                vetDisciplinas[i].setNome(data.getStringExtra("nome"));
                vetDisciplinas[i].setA1(data.getDoubleExtra("a1" , 0.0));
                vetDisciplinas[i].setA2(data.getDoubleExtra("a2" , 0.0));
                vetDisciplinas[i].setA3(data.getDoubleExtra("a3" , 0.0));
                // Atualiza a lista na atividade principal com os novos dados
                atualizarLista();
            }
        }

    }
    // Método para a chamada a tratamento de exceções da gravação
    public void gravarArquivo(View v) {
        // Carrega o arquivo e prepara os itens da lista
        try {
            if (armazenarDisciplinasArquivo(NOMEARQUIVO)) {
                // Mensagem de sucesso na gravação
                Toast.makeText(getApplicationContext(),
                        "Arquivo gravado!", Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException fnfe) {
            Toast.makeText(getApplicationContext(),
                    "Arquivo INEXISTENTE!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Erro de IO!", Toast.LENGTH_SHORT).show();
        }
    }
    // Método para o armazenamento dos dados
    private boolean armazenarDisciplinasArquivo(String arq) throws FileNotFoundException, IOException {
        // Define o objeto File para identificar e abrir o arquivo
        // getFilesDir() retorna o diretório corrente
        File file = new File(getFilesDir(), arq);
        // Cria o objeto para a gravação dos dados
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        // Realiza a gravação dos dados de todas as disciplinas
            for (int i = 0; i < TOTAL_DISCIPLINAS; i++) {
                dos.writeUTF(vetDisciplinas[i].getNome());
                dos.writeDouble(vetDisciplinas[i].getA1());
                dos.writeDouble(vetDisciplinas[i].getA2());
                dos.writeDouble(vetDisciplinas[i].getA3());
            }
            // Libera o recurso
            dos.close();
            return true;
        }
        // Método de encerramento do aplicativo
        public void sair(View v) {
        // Encerra o aplicativo
        finish();
    }
}
