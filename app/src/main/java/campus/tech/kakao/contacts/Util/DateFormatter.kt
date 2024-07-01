package campus.tech.kakao.contacts.Util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {
    private val dateFormatter = SimpleDateFormat("yyyy.MM.dd", Locale.KOREAN)

    fun stringToDate(string: String) : Date?{
        try{
            return dateFormatter.parse(string)
        } catch (e:Exception){
            return null
        }
    }

    fun dateToString(date : Date?) : String?{
        if(date == null) return null
        return dateFormatter.format(date)
    }
}