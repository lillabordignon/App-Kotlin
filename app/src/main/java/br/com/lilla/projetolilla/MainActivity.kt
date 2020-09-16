package br.com.lilla.projetolilla

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Executando o clique do botão calcular
        btnMainCalcular.setOnClickListener {

            //Capturar dados digitados pelo usuario
            val peso = edtMainPeso.text.toString().toFloat()
            val altura = edtMainAltura.text.toString().toFloat()
            val calcImc = peso / (altura * altura)
            val resultImc: String

            if (calcImc > 0 && calcImc < 17) {
                resultImc = "Abaixo do peso"
            } else if (calcImc >= 17 && calcImc <= 24.9) {
                resultImc = "Peso normal"
            } else if (calcImc >= 25 && calcImc <= 29.9) {
                resultImc = "Sobrepeso"
            } else if (calcImc >= 30 && calcImc <= 39.9) {
                resultImc = "Obesidade"
            } else {
                resultImc = "Obesidade grave"
            }

            //Criando ou acessando o arquivo de prefêrencias compartilhadas
            val sharedPrefsImc = getSharedPreferences("imc_data", Context.MODE_PRIVATE)

            //Editando o arquivo de preferências compartilhadas
            val editPrefs = sharedPrefsImc.edit()

            //Preparando os dados a serem salvos no arquivo
            editPrefs.putString("CALCULOIMC", calcImc.toString())
            editPrefs.putString("RESULTADOIMC", resultImc)

            editPrefs.apply()

            //Abrindo a MainActivity
            val mIntent = Intent(this, ResultActivity::class.java)

            //Passando informações através da Intent
            startActivity(mIntent)

            //Tirando todas as telas do empilhamento
            finishAffinity()
        }
    }
}