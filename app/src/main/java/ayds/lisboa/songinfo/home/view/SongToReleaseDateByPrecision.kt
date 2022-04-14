package ayds.lisboa.songinfo.home.view

import ayds.lisboa.songinfo.home.model.entities.Song

interface SongToReleaseDateByPrecision {

    fun getDescriptionByPrecision() : String
}

internal class SongToReleaseDateByDay (private val song: Song) : SongToReleaseDateByPrecision{

    private fun getSongReleaseDateByDay(releaseDate: String): String {
        val releaseDateAsList : List<String> = releaseDate.split("-")
        val year = releaseDateAsList[0]
        val month = releaseDateAsList[1]
        val day = releaseDateAsList[2]
        return "$day/$month/$year"
    }

    override fun getDescriptionByPrecision(): String {
        return getSongReleaseDateByDay(song.releaseDate)
    }
}

internal class SongToReleaseDateByMonth (private val song: Song) : SongToReleaseDateByPrecision{

    private fun getMonthNameByMonthNumber(monthNumber: String) =
        when (monthNumber) {
            "01" -> "January"
            "02" -> "February"
            "03" -> "March"
            "04" -> "April"
            "05" -> "May"
            "06" -> "June"
            "07" -> "July"
            "08" -> "August"
            "09" -> "September"
            "10" -> "October"
            "11" -> "November"
            "12" -> "December"
            else -> throw IllegalArgumentException(monthNumber)
        }

    private fun getSongReleaseDateByMonth(releaseDate : String): String {
        val releaseDateAsList : List<String> = releaseDate.split("-")
        val year = releaseDateAsList[0]
        val monthNumber = releaseDateAsList[1]
        val monthName = getMonthNameByMonthNumber(monthNumber)
        return "$monthName,$year"
    }

    override fun getDescriptionByPrecision(): String {
        return getSongReleaseDateByMonth(song.releaseDate)
    }
}

internal class SongToReleaseDateByYear (private val song: Song) : SongToReleaseDateByPrecision{

    private fun isLeapYear(year: Int) = (year % 4 == 0 && year % 100 == 0 && year % 400 == 0)

    private fun getSongReleaseDateByYear(year : String): String {
        return when(isLeapYear(year.toInt())) {
            false -> "$year (not a leap year)"
            else -> "$year (a leap year)"
        }
    }

    override fun getDescriptionByPrecision(): String {
        return getSongReleaseDateByYear(song.releaseDate)
    }
}

internal class SongReleaseDateNotFound (private val song: Song): SongToReleaseDateByPrecision{

    override fun getDescriptionByPrecision(): String {
        return song.releaseDate
    }
}