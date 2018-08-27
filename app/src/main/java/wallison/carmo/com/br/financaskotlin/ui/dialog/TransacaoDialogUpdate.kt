package wallison.carmo.com.br.financaskotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import wallison.carmo.com.br.financaskotlin.R
import wallison.carmo.com.br.financaskotlin.extension.formatPT
import wallison.carmo.com.br.financaskotlin.model.Tipo
import wallison.carmo.com.br.financaskotlin.model.Transacao

class TransacaoDialogUpdate(
        viewGroup: ViewGroup,
        private val context: Context) : TransacaoDialogForm(context, viewGroup) {

    override val titleButtonOk: String
        get() = "Alterar"

    override fun getTitle(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }

    fun show(transacao: Transacao, delegate: (transacao: Transacao) -> Unit) {
        val tipo = transacao.tipo
        super.show(tipo, delegate)
        initFields(transacao)
    }

    private fun initFields(transacao: Transacao) {
        initValue(transacao)
        initDate(transacao)
        initCategoria(transacao)
    }

    private fun initDate(transacao: Transacao) {
        dateField.setText(transacao.data.formatPT())
    }

    private fun initValue(transacao: Transacao) {
        valorField.setText(transacao.valor.toString())
    }

    private fun initCategoria(transacao: Transacao) {
        val categoria = context.resources.getStringArray(super.getCategoria(transacao.tipo))
        val categoriaPosition = categoria.indexOf(transacao.categoria)
        categoriaField.setSelection(categoriaPosition, true)
    }

}