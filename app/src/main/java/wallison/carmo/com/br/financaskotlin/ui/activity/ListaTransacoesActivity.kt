package wallison.carmo.com.br.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import wallison.carmo.com.br.financaskotlin.R
import wallison.carmo.com.br.financaskotlin.delegate.TransacaoDelegate
import wallison.carmo.com.br.financaskotlin.model.Tipo
import wallison.carmo.com.br.financaskotlin.model.Transacao
import wallison.carmo.com.br.financaskotlin.ui.ResumeView
import wallison.carmo.com.br.financaskotlin.ui.adapter.ListaTransacoesAdapter
import wallison.carmo.com.br.financaskotlin.ui.dialog.AddTransacaoDialog
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configResume()
        configList()
        configFab()
    }

    private fun configFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            showAddDialog(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            showAddDialog(Tipo.DESPESA)
        }
    }

    private fun showAddDialog(tipo:Tipo) {
        AddTransacaoDialog(window.decorView as ViewGroup, this)
                .show(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        updateTransacao(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
    }

    private fun updateTransacao(transacao: Transacao) {
        transacoes.add(transacao)
        configList()
        configResume()
    }

    private fun configResume() {
        val view = window.decorView
        val resumeView = ResumeView(this, view, transacoes)
        resumeView.getResume()
    }

    private fun configList() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transactionList(): List<Transacao> {
        return listOf(
                Transacao(valor = BigDecimal(20.5), categoria = "Comida", tipo = Tipo.DESPESA),
                Transacao(valor = BigDecimal(100.0), categoria = "Economia", tipo = Tipo.RECEITA),
                Transacao(valor = BigDecimal(700.0), tipo = Tipo.DESPESA),
                Transacao(valor = BigDecimal(500.0), tipo = Tipo.RECEITA, categoria = "Premio")
        )
    }
}