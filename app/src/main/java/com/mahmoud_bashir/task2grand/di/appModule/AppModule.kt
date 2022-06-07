package com.mahmoud_bashir.task2grand.di.appModule

import android.content.Context
import androidx.room.Room
import com.mahmoud_bashir.task2grand.data.local.PhotosDao
import com.mahmoud_bashir.task2grand.data.local.PhotosDatabase
import com.mahmoud_bashir.task2grand.data.server.ApiServer
import com.mahmoud_bashir.task2grand.utilities.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BaseUrl

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,BaseUrl:String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()



    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiServer::class.java)


    @Singleton
    @Provides
    fun providerUserDatabase(@ApplicationContext context:Context):PhotosDatabase{
        return Room.databaseBuilder(context,
            PhotosDatabase::class.java,
            PhotosDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase:PhotosDatabase):PhotosDao{
        return userDatabase.dao()
    }


//    @Provides
//    @Singleton
//    fun provideApiHelper(apiHelper: ApiHelperImpl) :ApiHelper = apiHelper

}