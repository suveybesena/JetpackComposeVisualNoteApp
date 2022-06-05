package com.example.jetpackcomposevisualnoteapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpackcomposevisualnoteapp.common.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constants.TABLE_NAME)
data class NoteModel(
    @ColumnInfo(name = Constants.LOCAL_DB_NOTE_URL)
    val imageUrl: String?,
    @ColumnInfo(name = Constants.LOCAL_DB_NOTE_DATE)
    val date: Long,
    @ColumnInfo(name = Constants.LOCAL_DB_NOTE_TITLE)
    val noteTitle: String?,
    @ColumnInfo(name = Constants.LOCAL_DB_NOTE_DESC)
    val noteDesc: String?,
    @ColumnInfo(name = Constants.LOCAL_DB_NOTE_HOUR)
    val noteHour: Int?,
    @ColumnInfo(name = Constants.LOCAL_DB_NOTE_MIN)
    val noteMin: Int?,
    @ColumnInfo(name = Constants.EDITED_TAG)
    val editedTag: String? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.LOCAL_DB_NOTE_ID)
    val id: Int? = null
) : Parcelable
