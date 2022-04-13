package ayds.lisboa.songinfo.home.view

import ayds.lisboa.songinfo.home.model.entities.EmptySong
import ayds.lisboa.songinfo.home.model.entities.Song
import ayds.lisboa.songinfo.home.model.entities.SpotifySong

interface SongDescriptionHelper {
    fun getSongDescriptionText(song: Song = EmptySong): String
}

internal class SongDescriptionHelperImpl : SongDescriptionHelper {
    override fun getSongDescriptionText(song: Song): String {
        return when (song) {
            is SpotifySong ->
                "${
                    "Song: ${song.songName} " +
                            if (song.isLocallyStored) "[*]" else ""
                }\n" +
                        "Artist: ${song.artistName}\n" +
                        "Album: ${song.albumName}\n" +
                        "Release date: ${getSongReleaseDateDescription(
                            song
                        )}"
            else -> "Song not found"
        }
    }

    private fun getSongReleaseDateDescription(song: Song): String {
        return when (song.releaseDatePrecision) {
            "day" -> getSongReleaseDateByDay(song.releaseDate)
            "month" -> getSongReleaseDateByMonth(song.releaseDate)
            "year" -> getSongReleaseDateByYear(song.releaseDate)
            else -> {"Invalid song release date precision"}
        }
    }

    private fun getSongReleaseDateByDay(releaseDate: String): String {
        val releaseDateAsList : List<String> = releaseDate.split("-")
        val year = releaseDateAsList[0]
        val month = releaseDateAsList[1]
        val day = releaseDateAsList[2]
        return "$day/$month/$year"
    }
    
    private fun getSongReleaseDateByMonth(releaseDate: String): String {
        val releaseDateAsList : List<String> = releaseDate.split("-")
        val year = releaseDateAsList[0]
        val month = releaseDateAsList[1]
        val monthDescription = when (month){
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
            else -> {"Invalid month"}
        }
        return "$monthDescription, $year"
    }

    private fun getSongReleaseDateByYear(releaseDate: String): String {
        val releaseDateAsList : List<String> = releaseDate.split("-")
        val year = releaseDateAsList[0]
        val yearAsInt = year.toInt()
        val leapYear = when {
            (yearAsInt % 4 == 0 && yearAsInt % 100 == 0 && yearAsInt % 400 == 0) -> "(a leap year)"
            else -> "(not a leap year)"
        }
        return "$year $leapYear"
    }
}