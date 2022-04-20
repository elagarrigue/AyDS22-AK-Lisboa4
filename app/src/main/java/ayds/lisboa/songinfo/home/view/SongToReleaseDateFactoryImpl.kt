package ayds.lisboa.songinfo.home.view

import ayds.lisboa.songinfo.home.model.entities.ReleaseDatePrecision
import ayds.lisboa.songinfo.home.model.entities.Song

interface SongToReleaseDateFactory{
    fun getSongReleaseDate(song: Song) : SongToReleaseDateByPrecision
}

internal class SongToReleaseDateFactoryImpl : SongToReleaseDateFactory{

    override fun getSongReleaseDate(song: Song): SongToReleaseDateByPrecision =
        when (song.releaseDatePrecision){
            ReleaseDatePrecision.DAY -> SongToReleaseDateByDay(song)
            ReleaseDatePrecision.MONTH -> SongToReleaseDateByMonth(song)
            ReleaseDatePrecision.YEAR-> SongToReleaseDateByYear(song)
            else -> SongReleaseDateNotFound(song)
        }
}