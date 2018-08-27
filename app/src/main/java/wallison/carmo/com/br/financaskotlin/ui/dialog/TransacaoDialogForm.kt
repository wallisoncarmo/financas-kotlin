package wallison.carmo.com.br.financaskotlin.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.form_transacao.view.*
import wallison.carmo.com.br.financaskotlin.R
import wallison.carmo.com.br.financaskotlin.extension.convertToCalendar
import wallison.carmo.com.br.financaskotlin.extension.formatPT
import wallison.carmo.com.br.financaskotlin.model.Tipo
import wallison.carmo.com.br.financaskotlin.model.Transacao
import java.math.BigDecimal
import java.util.*

abstract class TransacaoDialogForm(
        private val context: Context,
        private val viewGroup: ViewGroup) {

    private val newView = createView()
    protected val dateField = newView.form_transacao_data
    protected val categoriaField = newView.form_transacao_categoria
    protected val valorField = newView.form_transacao_valor
    abstract protected val titleButtonOk:String

    fun show(tipo: Tipo, delegate: (transacao:Transacao) -> Unit) {
        configFieldDate()
        configFieldCategoria(tipo)
        configForm(tipo,delegate)
    }

    private fun configForm(tipo: Tipo, delegate: (transacao: Transacao) -> Unit) {
        val titulo = getTitle(tipo)

        AlertDialog.Builder(context)
                .setTitle(titulo)
                .setView(newView)
                .setPositiveButton(titleButtonOk,
                        { _, _ ->
                            val dateValue = dateField.text.toString()
                            val categoria = categoriaField.selectedItem.toString()
                            var value = convertFieldValue(valorField.text.toString())
                            var date = dateValue.convertToCalendar()

                            val transacao = Transacao(
                                    tipo = tipo,
                                    valor = value,
                                    data = date,
                                    categoria = categoria)

                            delegate(transacao)
                        }
                )
                .setNegativeButton("Cancelar", null)
                .show()
    }

   abstract protected fun getTitle(tipo: Tipo): Int

    private fun convertFieldValue(value: String): BigDecimal {
        return try {
            BigDecimal(value)
        } catch (error: NumberFormatException) {
            Toast.makeText(context, "Falha na conversão de valores", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun configFieldCategoria(tipo: Tipo) {
        val categorias = getCategoria(tipo)
        val adapter = ArrayAdapter.createFromResource(context,
                categorias,
                android.R.layout.simple_spinner_dropdown_item)
        categoriaField.adapter = adapter
    }

    protected fun getCategoria(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.array.categorias_de_receita
        }
        return R.array.categorias_de_despesa
    }

    private fun configFieldDate() {
        val current = Calendar.getInstance()
        dateField.setText(current.formatPT())

        val year = current.get(Calendar.YEAR)
        val month = current.get(Calendar.MONTH)
        val day = current.get(Calendar.DAY_OF_MONTH)

        dateField.setOnClickListener {
            DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { _, year, month, day ->
                        val currentDate = Calendar.getInstance()
                        currentDate.set(year, month, day)
                        dateField.setText(currentDate.formatPT())
                    }, year, month, day)
                    .show()
        }
    }

    private fun createView(): View {
        return LayoutInflater.from(context).inflate(R.layout.form_transacao, viewGroup, false)
    }
}