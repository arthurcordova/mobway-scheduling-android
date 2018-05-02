package br.com.mobway.agendai

import android.app.Application
import br.com.mobway.agendai.repository.UserRepository
import br.com.mobway.agendai.viewmodel.LoginViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        const val URL_API = "https://us-central1-mobway-schedule-tracker.cloudfunctions.net"

        private lateinit var refrofit: Retrofit

        private lateinit var userRepository: UserRepository

        private lateinit var loginViewModel: LoginViewModel

        fun injectLoginViewModel() = loginViewModel
    }

    override fun onCreate() {
        super.onCreate()

        refrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(App.URL_API)
                .build()

        userRepository = UserRepository(refrofit)
        loginViewModel = LoginViewModel(userRepository)

    }

    fun getConnection(): Retrofit {
        return refrofit
    }


}