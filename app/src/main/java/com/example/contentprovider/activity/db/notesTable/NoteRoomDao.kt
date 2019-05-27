import androidx.room.*

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM note_room_table")
    suspend fun getAllNotes(): List<NoteRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(vararg noteModels: NoteRoomModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: NoteRoomModel)

    @Update
    suspend fun updateNote(noteModel: NoteRoomModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteRoomModel)

    @Query("SELECT * FROM note_room_table WHERE noteName LIKE :noteName")
    suspend fun getNoteByName(noteName: String): List<NoteRoomModel>

    @Query("SELECT * FROM note_room_table WHERE noteContent LIKE :noteContent")
    suspend fun getNoteByContent(noteContent: String): List<NoteRoomModel>

}