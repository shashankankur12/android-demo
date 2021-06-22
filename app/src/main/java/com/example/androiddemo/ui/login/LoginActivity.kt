package com.example.androiddemo.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androiddemo.R
import com.example.androiddemo.databinding.ActivityLoginBinding
import com.example.androiddemo.ui.dashboard.DashBoardActivity
import com.example.androiddemo.utils.Resource
import com.example.androiddemo.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: LoginViewModelFactory by instance()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        setDataObserver()

        buttonLogin.setOnClickListener {
            val userId = editTextEmployeeId.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            when {
                userId.isNullOrEmpty() -> {
                    mainRootLayout.snackbar("Please enter UserId")
                }
                password.isNullOrEmpty() -> {
                    mainRootLayout.snackbar("Please enter Password")
                }
                else -> {
                    viewModel.onSignInButtonClick(userId, password)

                }
            }
        }
    }

    private fun setDataObserver() {
        viewModel.getLoginData().observe(this, Observer { data ->
            when (data.status) {
                Resource.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    data?.let {
                        val intent = Intent(this, DashBoardActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    }
                }
                Resource.Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    data.message?.let { mainRootLayout.snackbar(it) }
                }
                Resource.Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }
}