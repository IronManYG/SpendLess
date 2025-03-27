package dev.gaddal.spendless

import android.app.Application
import dev.gaddal.auth.data.di.authDataModule
import dev.gaddal.core.data.di.coreDataModule
import dev.gaddal.core.database.di.databaseModule
import dev.gaddal.spendless.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class SpendlessApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@SpendlessApp)
            modules(
                authDataModule,
                appModule,
                coreDataModule,
                databaseModule,
            )
        }
    }
}