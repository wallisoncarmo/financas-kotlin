package wallison.carmo.com.br.financaskotlin.dao

import wallison.carmo.com.br.financaskotlin.model.Transacao

class TransacaoDao {

    val transacoes: List<Transacao> = Companion.transacoes

    companion object {
        private val transacoes: MutableList<Transacao> = mutableListOf()
    }

    fun insert(transacao: Transacao) {
        Companion.transacoes.add(transacao)
    }

    fun update(transacao: Transacao, position: Int) {
        Companion.transacoes[position] = transacao
    }

    fun delete(position: Int) {
        Companion.transacoes.removeAt(position)
    }

}