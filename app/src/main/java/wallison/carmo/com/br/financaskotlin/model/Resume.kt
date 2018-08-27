package wallison.carmo.com.br.financaskotlin.model

import java.math.BigDecimal

class Resume(private val transacoes: List<Transacao>) {

    val receita: BigDecimal get() = filterValue(Tipo.RECEITA)

    val despesa: BigDecimal get() = filterValue(Tipo.DESPESA)

    val total: BigDecimal get() = receita.subtract(despesa)

    private fun filterValue(tipo: Tipo): BigDecimal {
        val filter = transacoes
                .filter { it.tipo == tipo }
                .sumByDouble { it.valor.toDouble() }
        return BigDecimal(filter)
    }

}