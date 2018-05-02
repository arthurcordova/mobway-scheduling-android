package br.com.mobway.agendai.repository

import retrofit2.Retrofit

interface Repository<T> {

    fun createAPI(retrofit: Retrofit): T

}