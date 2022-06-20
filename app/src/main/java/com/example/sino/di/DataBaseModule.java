package com.example.sino.di;


import android.app.Application;

import androidx.room.Room;

import com.example.sino.db.SinoDao;
import com.example.sino.db.SinoDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static SinoDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, SinoDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static SinoDao provideSinoDao(SinoDatabase sinoDatabase) {
        return sinoDatabase.sinoDao();
    }

}
