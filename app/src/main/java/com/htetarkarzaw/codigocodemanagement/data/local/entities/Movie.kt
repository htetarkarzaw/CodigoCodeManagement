package com.htetarkarzaw.codigocodemanagement.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.htetarkarzaw.codigocodemanagement.domain.vo.MovieVo
import com.htetarkarzaw.codigocodemanagement.utils.Constant

@Entity(tableName = Constant.MOVIE_TABLE_NAME)
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "image")
    val imageUrl: String,

    @ColumnInfo(name = "poster")
    val posterUrl: String,
    val originalTitle: String,
    val title: String,
    val overview: String,
    val releasedDate: String,
    val popularity: Double,
    val averageVote: Double,
    val voteCount: Int,
    val originalLanguage: String,
    var isFav: Boolean,
    val type: String
) {
    fun toVo(): MovieVo {
        return MovieVo(
            id = id,
            imageUrl = imageUrl,
            posterUrl = posterUrl,
            originalTitle = originalTitle,
            title = title,
            overview = overview,
            releasedDate = releasedDate,
            popularity = popularity,
            averageVote = averageVote,
            voteCount = voteCount,
            originalLanguage = originalLanguage,
            isFav = isFav
        )
    }
}