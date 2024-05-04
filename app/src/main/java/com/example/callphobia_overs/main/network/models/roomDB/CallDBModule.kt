package com.example.callphobia_overs.main.network.models.roomDB

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**참고 블로그 : https://readystory.tistory.com/210**/
@InstallIn(SingletonComponent::class)
@Module
object CallDBModule{
    @Singleton
    @Provides
    fun provideAppDataBase(
        @ApplicationContext context: Context
    ): CallDataBase = Room
        .databaseBuilder(context, CallDataBase::class.java, "call_records.db")
        .build()

    @Singleton
    @Provides
    fun provideCallRecordDao(callDataBase: CallDataBase) : CallRecordsDao = callDataBase.callRecordsDao()

}