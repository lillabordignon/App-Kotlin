package br.com.lilla.projetolilla

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Executando o clique do botão entrar
        btnLoginEntrar.setOnClickListener {

            //Capturar dados digitados pelo usuário
            val email = edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = edtLoginSenha.text.toString().trim()

            //Validação dos campos
            if(email.isEmpty()){
                edtLoginEmail.error = "Campo obrigatório"
                edtLoginEmail.requestFocus()
            }else if (senha.isEmpty()){
                edtLoginSenha.error = "Campo obrigatório"
                edtLoginSenha.requestFocus()

            }else{
                //Acessando o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences("Cadastro_$email", Context.MODE_PRIVATE)

                //Recuperando dados no arquivo Shared Preferences
                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")

                //Verificando o email e senha que o usuário colocou
                if(email == emailPrefs && senha == senhaPrefs){
                    Toast.makeText(this, "Usuário logado com sucesso!", Toast.LENGTH_LONG).show()

                    //Abrindo a MainActivity
                    val mIntent = Intent(this, MainActivity::class.java)

                    //Passando informações através da Intent
                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)
                    finish()
                }else{
                    //Apresentar uma mensagem de erro ao usuário
                    Toast.makeText(this, "E-mail ou senha inválidos", Toast.LENGTH_LONG).show()
                }
            }
        }

        //Executando o clique do botão cadastrar
        btnLoginCadastrar.setOnClickListener {

            //Abrindo a tela de cadastro
            val mIntent = Intent(this, RegisterActivity::class.java)
            startActivity(mIntent)

        }
    }
}