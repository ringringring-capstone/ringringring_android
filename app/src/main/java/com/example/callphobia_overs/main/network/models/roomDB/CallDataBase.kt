package com.example.callphobia_overs.main.network.models.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CallRecords::class], version = 1, exportSchema = false)
abstract class CallDataBase : RoomDatabase() {
    abstract fun CallRecords() : CallRecords

    companion object{
        @Volatile
        private var instance : CallDataBase? = null

        fun getDataBase(context: Context) : CallDataBase? {
            if(instance == null){
                synchronized(CallDataBase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CallDataBase::class.java,
                        "call_database"
                    ).build()
                }
            }
            return instance
        }
    }

}