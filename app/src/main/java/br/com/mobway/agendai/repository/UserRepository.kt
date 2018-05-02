package br.com.mobway.agendai.repository

import br.com.mobway.agendai.connection.UserAPI
import br.com.mobway.agendai.connection.data.GenericResponsePOST
import br.com.mobway.agendai.data.User
import io.reactivex.Observable
import retrofit2.Retrofit

class UserRepository(retrofit: Retrofit) : Repository<UserAPI> {

    private val api: UserAPI

    override fun createAPI(retrofit: Retrofit): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }

    fun addUser(user: User): Observable<GenericResponsePOST> {
        return api.addUser(user)
    }

    init {
        api = createAPI(retrofit)
    }

}