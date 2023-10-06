package com.alberherjim.ex_02.data

import com.alberherjim.ex_02.app.ErrorApp
import com.alberherjim.ex_02.data.local.xml.XmlLocalDataSource
import com.alberherjim.ex_02.domain.User
import com.alberherjim.ex_02.domain.UserRepository
import com.iesam.kotlintrainning.Either

class UserDataRepository(
    val localSource : XmlLocalDataSource
) : UserRepository {

    override fun save(user: User): Either<ErrorApp, Boolean> {
        return localSource.saveUser(user)
    }

    override fun get(name:String): Either<ErrorApp, User> {
        return localSource.getUser(name)
    }

    override fun getAll(): Either<ErrorApp, List<User>> {
        return localSource.getAllUsers()
    }

    override fun delete(): Either<ErrorApp, Boolean> {
        return localSource.delete()
    }


}