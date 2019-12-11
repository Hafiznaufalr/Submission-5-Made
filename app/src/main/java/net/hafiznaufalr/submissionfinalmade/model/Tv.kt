package net.hafiznaufalr.submissionfinalmade.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tv(
    @SerializedName("id")
    val id: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("vote_average")
    val vote: String,
    @SerializedName("popularity")
    val popularity: String
): Parcelable