package net.hafiznaufalr.submissionfinalmade.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvResponse(
    @SerializedName("results")
    val results: List<Tv>
): Parcelable