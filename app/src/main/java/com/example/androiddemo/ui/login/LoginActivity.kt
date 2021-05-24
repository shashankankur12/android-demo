package com.example.androiddemo.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.androiddemo.R
import com.example.androiddemo.databinding.ActivityLoginBinding
import com.example.androiddemo.listner.login.LoginViewModelListener
import com.example.androiddemo.utils.UiUtils
import com.example.androiddemo.viewmodel.login.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginViewModelListener {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var viewModel:LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        viewModel =ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.viewModelLogin=viewModel
        viewModel.loginListener=this
    }

    override fun onStarted() {
        binding.progressBar.visibility= View.
                VISIBLE
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        binding.progressBar.visibility= View.
        GONE
       loginResponse.observe(this, Observer {
           UiUtils.showToast(this,it)
       })
    }

    override fun onFailure(message: String) {
        binding.progressBar.visibility= View.
        GONE
        UiUtils.showToast(this, message)
    }
}