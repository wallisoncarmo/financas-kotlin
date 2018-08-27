package wallison.carmo.com.br.financaskotlin.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale


fun BigDecimal.formatPT(): String {
    val currencyPT = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return currencyPT.format(this).replace("R$", "R$ ").replace("-R$","R$ -")
}