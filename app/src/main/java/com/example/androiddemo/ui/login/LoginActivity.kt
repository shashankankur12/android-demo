package com.example.androiddemo.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.androiddemo.R
import com.example.androiddemo.databinding.ActivityLoginBinding
import com.example.androiddemo.listner.login.ViewModelListener
import com.example.androiddemo.ui.dashboard.DashBoardActivity
import com.example.androiddemo.utils.snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), ViewModelListener, KodeinAware {
    override val kodein by kodein()
    private val factory: LoginViewModelFactory by instance()

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.viewModelLogin = viewModel
        viewModel.loginListener = this
    }

    override fun onStarted() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(loginResponse: String?) {
        binding.progressBar.visibility = View.GONE
        loginResponse?.let {
            val intent = Intent(this, DashBoardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    override fun onFailure(message: String?) {
        binding.progressBar.visibility = View.GONE
        message?.let { binding.mainRootLayout.snackbar(it) }
    }
}