package ayds.lisboa.songinfo.home.view

import ayds.lisboa.songinfo.home.model.entities.Song

interface SongToReleaseDateFactory{
    fun getSongReleaseDate(song: Song) : SongToReleaseDateByPrecision
}

internal class SongToReleaseDateFactoryImpl : SongToReleaseDateFactory{

    override fun getSongReleaseDate(song: Song): SongToReleaseDateByPrecision =
        when (song.releaseDatePrecision){
            "day" -> SongToReleaseDateByDay(song)
            "month" -> SongToReleaseDateByMonth(song)
            "year" -> SongToReleaseDateByYear(song)
            else -> SongReleaseDateNotFound(song)
        }
}