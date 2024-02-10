package mbk.io.hm5_m6.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mbk.io.hm5_m6.model.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}