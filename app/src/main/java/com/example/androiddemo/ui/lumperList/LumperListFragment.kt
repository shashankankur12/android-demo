package com.example.androiddemo.ui.lumperList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddemo.R
import com.example.androiddemo.databinding.LumperListFragmentBinding
import com.example.androiddemo.listner.login.ViewModelListener
import com.example.androiddemo.utils.snackbar
import kotlinx.android.synthetic.main.lumper_list_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class LumperListFragment : Fragment(), KodeinAware, ViewModelListener {
    override val kodein by kodein()
    private lateinit var viewModel: LumperListViewModel
    private val factory: LumperListViewModelFactory by instance()
    lateinit var binding: LumperListFragmentBinding
    lateinit var lumperListAdapter: LumperListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.lumper_list_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(LumperListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lumperFragment = viewModel

//        viewModel = ViewModelProvider(this, factory).get(LumperListViewModel::class.java)
        viewModel.viewModelListener = this

        binding.recyclerViewLumper.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            lumperListAdapter = LumperListAdapter()
            adapter = lumperListAdapter
        }

//        viewLifecycleOwner.lifecycleScope.launch {
//            val lumperList = viewModel.lumpersList
        viewModel.getLumperList().observe(viewLifecycleOwner, Observer { lumperList ->
            lumperListAdapter.updateArrayList(lumperList)
        })
//        }

//        viewModel.getLumperList()
    }

    override fun onStarted() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(loginResponse: String?) {
        progressBar.visibility = View.GONE
        noRecordText.visibility = View.GONE
    }

    override fun onFailure(message: String?) {
        progressBar.visibility = View.GONE
        noRecordText.visibility = View.VISIBLE
        message?.let { rootFrameLayout.snackbar(it) }
    }

}