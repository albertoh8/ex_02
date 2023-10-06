package com.alberherjim.ex_02.domain

import com.alberherjim.ex_02.app.ErrorApp
import com.iesam.kotlintrainning.Either

class GetUserUseCase (
    private val repository: UserRepository
){
    operator fun invoke(name:String): Either<ErrorApp, User> {
        return repository.get(name)
    }
}