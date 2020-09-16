package br.com.lilla.projetolilla

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Executando o clique do botão Cadastrar
        btnRegisterCadastrar.setOnClickListener {

            //Capturar os dados digitados pelo usuário
            val nome = edtRegisterNome.text.toString().trim()
            val sobrenome = edtRegisterSobrenome.text.toString().trim()
            val email = edtRegisterEmail.text.toString().trim().toLowerCase()
            val senha = edtRegisterSenha.text.toString().trim()

            //Validação dos campos
            if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                //Apresentando uma mensagem de erro ao usuário
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }else{
                //Todos os campos forem preenchidos

                //Criando ou acessando o arquivo de prefêrencias compartilhadas
                val sharedPrefs = getSharedPreferences("Cadastro_$email", Context.MODE_PRIVATE)

                //Editando o arquivo de preferências compartilhadas
                val editPrefs = sharedPrefs.edit()

                //Preparando os dados a serem salvos no arquivo
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBREN0ME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)

                //Salvando os dados no arquivo Shared Preferences
                editPrefs.apply()

                //Abrindo a MainActivity
                val mIntent = Intent(this, MainActivity::class.java)

                //Passando informações através da Intent
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)

                //Tirando todas as telas do empilhamento
                finishAffinity()
            }
        }
    }
}