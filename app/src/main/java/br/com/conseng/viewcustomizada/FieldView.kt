package br.com.conseng.viewcustomizada

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.view_field.view.*


class FieldView : LinearLayout {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(getContext(), R.layout.view_field, this)
    }

    private var titulo: TextView? = null
    private var valor: EditText? = null

    fun setText(nome: String) {
        titulo?.text = nome
        titulo = txt_titulo
        valor = txt_valor
    }

    fun setHint(aviso: String) {
        valor?.hint = aviso
    }

    enum class TipoDado { NOME, IDADE, EMAIL, TEXTO, NUMERO }

    private var meuTipo = TipoDado.TEXTO

    fun setTipoDado(tipo: TipoDado, tituloPadrao: Boolean = false) {
        when (tipo) {
            TipoDado.EMAIL -> {
                valor?.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                if (tituloPadrao) {
                    titulo?.text = context.getString(R.string.titulo_email)
                    valor?.hint = context.getString(R.string.hint_titulo_email)
                }
            }
            TipoDado.IDADE -> {
                valor?.inputType = InputType.TYPE_CLASS_NUMBER
                if (tituloPadrao) {
                    titulo?.text = context.getString(R.string.titulo_idade)
                    valor?.hint = context.getString(R.string.hint_titulo_idade)
                }
            }
            TipoDado.NOME -> {
                valor?.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                if (tituloPadrao) {
                    titulo?.text = context.getString(R.string.titulo_nome)
                    valor?.hint = context.getString(R.string.hint_titulo_nome)
                }
            }
            TipoDado.NUMERO -> {
                valor?.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_NUMBER_FLAG_SIGNED
            }
            else -> {
                valor?.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
            }
        }
        meuTipo = tipo
    }

    fun validarValor(): String {
        var resultado = ""

        val valorDigitado = valor?.text.toString()
        when (meuTipo) {
            TipoDado.NOME -> {
                if (valorDigitado.length < 6) {
                    resultado = context.getString(R.string.erro_nome_curto)
                } else {
                    var espacos = 0
                    var caracteres = 0
                    for (c in valorDigitado.indices) {
                        if (c.equals(" ")) {
                            espacos++
                        } else {
                            caracteres++
                        }
                    }
                    if (espacos < 1) {
                        resultado = context.getString(R.string.erro_nome_minimo_1_espaco)
                    } else if (caracteres < 5) {
                        resultado = context.getString(R.string.erro_nome_minimo_5_letras)
                    }
                }
            }
            TipoDado.IDADE -> {
                val idade = valorDigitado.toInt()
                if (idade < 10) {
                    resultado = context.getString(R.string.erro_idade_minima_10_anos)
                } else if (idade > 120) {
                    resultado = context.getString(R.string.erro_idade_maxima_120_anos)
                }
            }
            TipoDado.EMAIL -> {
                if (valorDigitado.length < 6) {
                    resultado = context.getString(R.string.erro_email_curto)
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(valorDigitado).matches()) {
                    resultado = context.getString(R.string.erro_email_invalido)
                }
            }
            else -> {
                if (valor?.text.isNullOrBlank()) {
                    resultado = context.getString(R.string.erro_faltou_dado)
                }
            }
        }
        return resultado
    }
}