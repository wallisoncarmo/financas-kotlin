package wallison.carmo.com.br.financaskotlin.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatPT(): String? {
    val pattern = "dd/MM/yyyy"
    val format = SimpleDateFormat(pattern)
    return format.format(this.time)
}