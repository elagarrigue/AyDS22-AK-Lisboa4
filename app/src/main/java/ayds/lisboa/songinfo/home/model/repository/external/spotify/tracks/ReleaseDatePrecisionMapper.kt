package ayds.lisboa.songinfo.home.model.repository.external.spotify.tracks

import ayds.lisboa.songinfo.home.model.entities.ReleaseDatePrecision

interface ReleaseDatePrecisionMapper {
    fun getReleaseDatePrecisionByString(datePrecision: String): ReleaseDatePrecision
}

internal class ReleaseDatePrecisionMapperImpl : ReleaseDatePrecisionMapper {
    override fun getReleaseDatePrecisionByString(datePrecision: String): ReleaseDatePrecision {
        return when (datePrecision) {
            DAY_PRECISION -> ReleaseDatePrecision.DAY
            MONTH_PRECISION -> ReleaseDatePrecision.MONTH
            YEAR_PRECISION -> ReleaseDatePrecision.YEAR
            else -> ReleaseDatePrecision.NONE
        }
    }
}

