package mbk.io.hm5_m6

import android.app.Application
import androidx.room.Room
import mbk.io.hm5_m6.data.local.db.AppDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }

    companion object{
        lateinit var db:AppDatabase
    }
}