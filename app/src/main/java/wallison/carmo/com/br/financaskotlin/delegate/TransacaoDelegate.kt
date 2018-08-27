package wallison.carmo.com.br.financaskotlin.delegate

import wallison.carmo.com.br.financaskotlin.model.Transacao

interface TransacaoDelegate {
    fun delegate(transacao:Transacao)
}