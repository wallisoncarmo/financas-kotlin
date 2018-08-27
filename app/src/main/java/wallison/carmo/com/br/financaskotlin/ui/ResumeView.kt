package wallison.carmo.com.br.financaskotlin.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import kotlinx.android.synthetic.main.resumo_card.view.*
import wallison.carmo.com.br.financaskotlin.R
import wallison.carmo.com.br.financaskotlin.extension.formatPT
import wallison.carmo.com.br.financaskotlin.model.Resume
import wallison.carmo.com.br.financaskotlin.model.Transacao
import java.math.BigDecimal

class ResumeView(context: Context,
                 private val view: View,
                 transacoes: List<Transacao>) {

    private val resume: Resume = Resume(transacoes)
    private val colorDespesa = ContextCompat.getColor(context, R.color.despesa)
    private val colorReceita = ContextCompat.getColor(context, R.color.receita)

    private  fun addReceita() {
        with(view.resumo_card_receita) {
            text = resume.receita.formatPT()
            setTextColor(colorReceita)
        }
    }

    private fun addDespesa() {
        with(view.resumo_card_despesa) {
            text = resume.despesa.formatPT()
            setTextColor(colorDespesa)
        }
    }

    private  fun addTotal() {
        val total = resume.total
        val cor = getColor(total)
        with(view.resumo_card_total) {
            text = total.formatPT()
            setTextColor(cor)
        }
    }

    private fun getColor(valor: BigDecimal): Int {
        if (valor >= BigDecimal.ZERO) {
            return colorReceita
        }
        return colorDespesa
    }

    fun getResume() {
        addReceita()
        addDespesa()
        addTotal()
    }
}