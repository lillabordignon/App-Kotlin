package br.com.lilla.projetolilla


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Obter o dado passado pela Intent
        val email :String? = intent.getStringExtra("INTENT_EMAIL")

        //Abrindo o arquivo de Shared Preferences
        val sharedPrefs: SharedPreferences = getSharedPreferences("Cadastro_$email",
            Context.MODE_PRIVATE)
        val sharedPrefsImc: SharedPreferences = getSharedPreferences("imc_data", Context.MODE_PRIVATE)

        //Recuperar os dados do Shared Preferences
        val nome :String? = sharedPrefs.getString("NOME", "")
        val sobrenome :String? = sharedPrefs.getString("SOBRENOME", "")
        val calcImc :String? = sharedPrefsImc.getString("CALCULOIMC", "")
        val resultImc :String? = sharedPrefsImc.getString("RESULTADOIMC", "")

        //Exibindo as informações obtidas para o usuário
        txvResultNome.text = "$nome $sobrenome"
        txvResultImc.text = "$calcImc"
        txvResultClasse.text = "$resultImc"

        //Executando o botão recalcular
        btnResultRecalcular.setOnClickListener {
            //Executando o clique do botão sim
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
            finish()
        }

        //Executando o botão sair
        btnResultSair.setOnClickListener{
            //Criando um alerta
            AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Você realmente deseja sair?")
                .setPositiveButton("Sim"){_,_ ->
                    //Executando o clique do botão sim
                    val mIntent = Intent(this, LoginActivity::class.java)
                    startActivity(mIntent)
                    finishAffinity()
                }
                .setNeutralButton("Cancelar"){_,_ ->}
                .setCancelable(false)
                .create()
                .show()
        }
    }
}