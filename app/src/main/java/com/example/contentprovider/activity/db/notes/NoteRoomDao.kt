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

    // get all cars with specific condition
    @Query("SELECT * FROM note_room_table WHERE note_id LIKE :noteId")
    suspend fun getNoteById(noteId: String): List<NoteRoomModel>

}