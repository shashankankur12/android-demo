package com.example.androiddemo.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androiddemo.R
import com.example.androiddemo.databinding.ProfileFragmentBinding
import com.example.androiddemo.listner.login.ViewModelListener
import com.example.androiddemo.utils.snackbar
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware, ViewModelListener {
    override val kodein by kodein()
    private val factory: ProfileViewModelFactory by instance()
    private lateinit var viewModel: ProfileViewModel
    lateinit var binding: ProfileFragmentBinding














         override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewModelListener = this
        viewLifecycleOwner.lifecycleScope.launch {
            val lumperList = viewModel.profileData
            lumperList.observe(viewLifecycleOwner, Observer {
                binding.profileViewModel = it
            })
        }

//        viewModel.getProfileData()
    }

    override fun onStarted() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(loginResponse: String?) {
        progressBar.visibility = View.GONE
    }

    override fun onFailure(message: String?) {
        progressBar.visibility = View.GONE
        message?.let { rootFrameLayout.snackbar(it) }
    }

}