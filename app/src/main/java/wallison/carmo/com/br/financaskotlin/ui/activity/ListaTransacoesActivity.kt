package wallison.carmo.com.br.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import wallison.carmo.com.br.financaskotlin.R
import wallison.carmo.com.br.financaskotlin.model.Tipo
import wallison.carmo.com.br.financaskotlin.model.Transacao
import wallison.carmo.com.br.financaskotlin.ui.ResumeView
import wallison.carmo.com.br.financaskotlin.ui.adapter.ListaTransacoesAdapter
import wallison.carmo.com.br.financaskotlin.ui.dialog.TransacaoDialogAdd
import wallison.carmo.com.br.financaskotlin.ui.dialog.TransacaoDialogUpdate

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()
    private val viewActivity by lazy {
        window.decorView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configResume()
        configList()
        configFab()
    }

    private fun showDialogAdd(tipo: Tipo) {
        TransacaoDialogAdd(viewActivity as ViewGroup, this)
                .show(tipo, { newTransacao ->
                    add(newTransacao)
                    lista_transacoes_adiciona_menu.close(true)
                })
    }

    private fun showDialogUpdate(transacao: Transacao, position: Int) {
        TransacaoDialogUpdate(viewActivity as ViewGroup, this)
                .show(transacao, { newTransacao ->
                    update(newTransacao, position)
                })
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val idMenu = item?.itemId
        if(idMenu==1){
            val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val position = menuInfo.position
            remove(position)
        }
        return super.onContextItemSelected(item)
    }

    private fun add(transacao: Transacao) {
        transacoes.add(transacao)
        updateTransacao()
    }

    private fun updateTransacao() {
        configList()
        configResume()
    }
    private fun remove(position: Int) {
        transacoes.removeAt(position)
        updateTransacao()
    }

    private fun update(transacao: Transacao, position: Int) {
        transacoes[position] = transacao
        updateTransacao()
    }

    private fun configFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            showDialogAdd(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            showDialogAdd(Tipo.DESPESA)
        }
    }

    private fun configResume() {
        val resumeView = ResumeView(this, viewActivity, transacoes)
        resumeView.getResume()
    }

    private fun configList() {
        val listaTransacoesAdapter = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                showDialogUpdate(transacao, position)
            }
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }



}