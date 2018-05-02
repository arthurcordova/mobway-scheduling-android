package br.com.mobway.agendai.connection

import br.com.mobway.agendai.connection.data.GenericResponsePOST
import br.com.mobway.agendai.data.User
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable

interface UserAPI {

    @POST("/addUser")
    fun addUser(@Body body: User): Observable<GenericResponsePOST>

}

interface Specialty {

}