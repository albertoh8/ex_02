package com.alberherjim.ex_02.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alberherjim.ex_02.R
import com.alberherjim.ex_02.data.UserDataRepository
import com.alberherjim.ex_02.data.local.xml.XmlLocalDataSource
import com.alberherjim.ex_02.domain.DeleteUsersUseCase
import com.alberherjim.ex_02.domain.GetUserUseCase
import com.alberherjim.ex_02.domain.SaveUserUseCase
import com.alberherjim.ex_02.domain.User

class MainActivity : AppCompatActivity() {
    //val viewModels : MainActivityViewModel by viewModels()
    val viewModel: MainActivityViewModel by lazy {
        MainActivityViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            DeleteUsersUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    var userName = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()
        setupView()
    }

    private fun setupObservers() {
        val observer = Observer<MainActivityViewModel.UiState> {
            it.user?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)

    }

    private fun setupView() {
        val actionButton = findViewById<Button>(R.id.button_save)
        val actionButtonClean = findViewById<Button>(R.id.button_limpiar)
        val actionButtonRecovery = findViewById<Button>(R.id.button_recuperar)
        val actionDelete = findViewById<Button>(R.id.button_delete)
        actionButton.setOnClickListener {
            saveUser()
            userName = findViewById<EditText>(R.id.name_text).text.toString()
        }
        actionButtonClean.setOnClickListener {
            cleanForm()
        }
        actionButtonRecovery.setOnClickListener {
            recoverData()
        }
        actionDelete.setOnClickListener {
            deleteUsers()
        }
    }

    private fun bindData(user: User) {
        setNameInput(user.name)
        setSurnameInput(user.surname)
        setAgeInput(user.email)
    }

    private fun setNameInput(name: String) = findViewById<EditText>(R.id.name_text).setText(name)

    private fun setSurnameInput(surname: String) = findViewById<EditText>(R.id.surname_text).setText(surname)

    private fun setAgeInput(email: String) = findViewById<EditText>(R.id.email_text).setText(email)

    val user1 = User(getName(), getSurname(), getEmail())
    fun saveUser() = viewModel.saveUser(user1)

    private fun getName(): String = findViewById<EditText>(R.id.name_text).text.toString()
    private fun getSurname(): String = findViewById<EditText>(R.id.surname_text).text.toString()
    private fun getEmail(): String = findViewById<EditText>(R.id.email_text).text.toString()

    private fun cleanForm() {
        cleanName()
        cleanSurname()
        cleanEmail()
        changeVisibility()
    }

    fun changeVisibility() {
        val NameView = findViewById<TextView>(R.id.user_name)
        val SurnameView = findViewById<TextView>(R.id.user_surname)
        val EmailView = findViewById<TextView>(R.id.user_email)
        val DeleteButton = findViewById<Button>(R.id.button_delete)

        NameView.setVisibility(View.VISIBLE)
        SurnameView.setVisibility(View.VISIBLE)
        EmailView.setVisibility(View.VISIBLE)
        DeleteButton.setVisibility(View.VISIBLE)

        NameView.setText(
            XmlLocalDataSource(this).getUser(userName).fold({ it.toString() }, { it.name })
        )
        SurnameView.setText(
            XmlLocalDataSource(this).getUser(userName).fold({ it.toString() }, { it.surname })
        )
        EmailView.setText(
            XmlLocalDataSource(this).getUser(userName).fold({ it.toString() }, { it.email })
        )

    }

    fun cleanName() = findViewById<EditText>(R.id.name_text).setText("")

    fun cleanSurname() = findViewById<EditText>(R.id.surname_text).setText("")

    fun cleanEmail() = findViewById<EditText>(R.id.email_text).setText("")

    fun recoverData() {
        recoverName()
        recoverSurname()
        recoverEmail()
        getUser(userName)
    }

    fun recoverName() = findViewById<EditText>(R.id.name_text).setText(
        XmlLocalDataSource(this).getUser(userName).fold({ it.toString() }, { it.name })
    )

    fun recoverSurname() = findViewById<EditText>(R.id.surname_text).setText(
        XmlLocalDataSource(this).getUser(userName).fold({ it.toString() }, { it.surname })
    )

    fun recoverEmail() = findViewById<EditText>(R.id.email_text).setText(
        XmlLocalDataSource(this).getUser(userName).fold({ it.toString() }, { it.email })
    )

    fun getUser(name: String) =  viewModel.getUser(name)

    fun deleteUsers() = viewModel.deleteUsers()
}
