package br.com.mobway.agendai.viewmodel

import br.com.mobway.agendai.connection.data.GenericResponsePOST
import br.com.mobway.agendai.data.User
import br.com.mobway.agendai.repository.UserRepository
import io.reactivex.Observable

class LoginViewModel(private val userRepository: UserRepository) {

    fun createUser(user: User): Observable<GenericResponsePOST> {
        return userRepository.addUser(user)
    }


}