package br.com.conseng.viewcustomizada

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pedir_nome.setTipoDado(FieldView.TipoDado.NOME, true)
        pedir_idade.setTipoDado(FieldView.TipoDado.IDADE, true)
        pedir_email.setTipoDado(FieldView.TipoDado.EMAIL, true)
    }

    fun validarDados(view : View) {
        var nomeOk = pedir_nome.validarValor()
        var idadeOk = pedir_idade.validarValor()
        var emailOk = pedir_email.validarValor()

        mensagem_erro.text = nomeOk + "\n" + idadeOk + "\n" + emailOk
    }
}
