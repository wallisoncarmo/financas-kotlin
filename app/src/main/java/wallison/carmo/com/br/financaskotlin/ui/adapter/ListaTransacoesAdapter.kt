package wallison.carmo.com.br.financaskotlin.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.transacao_item.view.*
import wallison.carmo.com.br.financaskotlin.R
import wallison.carmo.com.br.financaskotlin.extension.formatPT
import wallison.carmo.com.br.financaskotlin.extension.limitString
import wallison.carmo.com.br.financaskotlin.model.Tipo
import wallison.carmo.com.br.financaskotlin.model.Transacao


class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val  context: Context) : BaseAdapter() {

    private val LIMIT_CARACTER = 14

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val viewCreate = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = transacoes[position]

        addValue(transacao, viewCreate)
        addIcon(transacao, viewCreate)
        addCategoria(transacao, viewCreate)
        addData(viewCreate, transacao)

        return viewCreate
    }

    private fun addData(viewCreate: View, transacao: Transacao) {
        viewCreate.transacao_data.text = transacao.data.formatPT()
    }

    private fun addCategoria(transacao: Transacao, viewCreate: View) {
        viewCreate.transacao_categoria.text = transacao.categoria.limitString(LIMIT_CARACTER)
    }

    private fun addValue(transacao: Transacao, viewCreate: View) {
        val cor :Int = getColorToTipo(transacao.tipo)
        viewCreate.transacao_valor.setTextColor(cor)
        viewCreate.transacao_valor.text = transacao.valor.formatPT()
    }

    private fun getColorToTipo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return  ContextCompat.getColor(context, R.color.receita)
        }
        return   ContextCompat.getColor(context, R.color.despesa)
    }

    private fun addIcon(transacao: Transacao, viewCreate: View) {
        val icon : Int = getIconToTipo(transacao.tipo)
        viewCreate.transacao_icone.setBackgroundResource(icon)
    }

    private fun getIconToTipo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return   R.drawable.icone_transacao_item_receita
        }
        return  R.drawable.icone_transacao_item_despesa
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

}