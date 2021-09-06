package com.example.notetaking.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "notes_table"
)
@Parcelize
data class Note(
    var title: String,
    var description: String,
    var created: Long = System.currentTimeMillis(),

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
): Parcelable{
    val createdDateFormatted: String get() = DateFormat.getDateTimeInstance().format(created)
}
