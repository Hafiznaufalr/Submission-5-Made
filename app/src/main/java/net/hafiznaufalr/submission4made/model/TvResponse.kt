package net.hafiznaufalr.submission4made.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvResponse(
    @SerializedName("results")
    val results: List<Tv>
): Parcelable