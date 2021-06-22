package com.example.androiddemo

import android.app.Application
import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.utils.NetworkConnectionInterceptor
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.reopsitories.LumperListRepository
import com.example.androiddemo.data.reopsitories.ProfileRepository
import com.example.androiddemo.data.reopsitories.UserRepository
import com.example.androiddemo.ui.lumperList.LumperListViewModelFactory
import com.example.androiddemo.ui.profile.ProfileViewModelFactory
import com.example.androiddemo.ui.login.LoginViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class MyApplication :Application(), KodeinAware {
    override val kodein: Kodein= Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiInterFace(instance()) }
        bind() from singleton { SharedPref(instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { LoginViewModelFactory(instance(), instance()) }
        bind() from singleton { ProfileRepository(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from singleton { LumperListRepository(instance()) }
        bind() from provider { LumperListViewModelFactory(instance()) }
    }


}