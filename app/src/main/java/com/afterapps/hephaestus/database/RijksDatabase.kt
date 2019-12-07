package com.afterapps.hephaestus.database

import androidx.paging.DataSource
import androidx.room.*
import com.afterapps.hephaestus.model.database.DatabaseArtEntry
import com.afterapps.hephaestus.model.domain.ArtEntry

@Dao
interface RijksDao {

    //Get art entries sorted in an ascending order
    @Query("select * from databaseartentry ORDER BY indexInResponse ASC")
    fun getArtEntries(): DataSource.Factory<Int, ArtEntry>

    //Insert art entry/entries int database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtEntries(vararg databaseArtEntry: DatabaseArtEntry)

    //Get the next art index in the database
    @Query("SELECT MAX(indexInResponse) + 1 FROM databaseartentry")
    fun getNextIndexInResponse(): Int
}

@Database(entities = [DatabaseArtEntry::class], version = 1)
abstract class RijksDatabase : RoomDatabase() {
    abstract val rijksDao: RijksDao
}