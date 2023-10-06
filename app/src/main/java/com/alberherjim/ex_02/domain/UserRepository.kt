package com.alberherjim.ex_02.domain

import com.alberherjim.ex_02.app.ErrorApp
import com.iesam.kotlintrainning.Either

interface UserRepository {

    fun save(user:User) : Either<ErrorApp, Boolean>

    fun get(name:String): Either<ErrorApp,User>

    fun getAll(): Either<ErrorApp,List<User>>

    fun delete(): Either<ErrorApp,Boolean>

}