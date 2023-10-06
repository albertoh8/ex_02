package com.alberherjim.ex_02.domain

import com.alberherjim.ex_02.app.ErrorApp
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right

class SaveUserUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(user:User): Either<ErrorApp, Boolean> {
        return repository.save(user)
    }

}