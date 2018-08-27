package wallison.carmo.com.br.financaskotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import wallison.carmo.com.br.financaskotlin.R
import wallison.carmo.com.br.financaskotlin.model.Tipo

class TransacaoDialogAdd(viewGroup: ViewGroup,
                         context: Context) : TransacaoDialogForm(context, viewGroup) {

    override val titleButtonOk: String
        get() = "Adicionar"

    override fun getTitle(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }

}