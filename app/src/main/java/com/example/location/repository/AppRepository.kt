package com.example.location.repository

import android.content.Context


class AppRepository(context: Context) {
    var db : AppDao = AppDatabase.getInstance(context)?.appDao()!!

    suspend fun insertApp(apps: Apps ){
        db.insertApp(apps)
    }

    suspend fun isAppIsExist(appName: String?): Boolean {
        return db.isAppIsExist(appName)
    }

    suspend fun updateApp(app : Apps){
        db.updateApp(app)
    }

    suspend fun loadAllApp() : List<Apps>{
        return db.loadAllApp()
    }

    suspend fun getCount() : Int{
        return db.getCount()
    }

    suspend fun getTimStamp(appName: String?) :Long{
        return db.getTimeStamp(appName)
    }

/*

    private class insertAsyncTask internal constructor(private val appDao: AppDao): AsyncTask<Apps,Void, Void>(){
        override suspend fun doInBackground(vararg p0: Apps): Void? {
            appDao.insertApp(p0[0])
            return null
            }

    }*/
}