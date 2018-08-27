package wallison.carmo.com.br.financaskotlin.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.limitString(max:Int):String{
    if(this.length> max){
        return "${this.substring(0, max)}..."
    }
    return this
}

fun String.convertToCalendar(): Calendar {
    val format = SimpleDateFormat("dd/MM/yyyy")
    val dateConvert: Date = format.parse(this)
    val date = Calendar.getInstance()
    date.time = dateConvert
    return date
}