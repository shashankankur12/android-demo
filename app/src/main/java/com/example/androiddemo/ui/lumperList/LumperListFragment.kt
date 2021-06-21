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
import com.example.androiddemo.utils.Resource
import com.example.androiddemo.utils.snackbar
import kotlinx.android.synthetic.main.lumper_list_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class LumperListFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private lateinit var viewModel: LumperListViewModel
    private val factory: LumperListViewModelFactory by instance()
    lateinit var binding: LumperListFragmentBinding
    lateinit var lumperListAdapter: LumperListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.lumper_list_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(LumperListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setDataObserver()
    }

    private fun setDataObserver() {
        viewModel.getLumperList().observe(viewLifecycleOwner)  { lumperList ->
            when (lumperList.status) {
                Resource.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    lumperList.data?.let { lumperListAdapter.updateArrayList(it) }
                }
                Resource.Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    lumperList.message?.let { rootFrameLayout.snackbar(it) }
                }
                Resource.Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerViewLumper.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            lumperListAdapter = LumperListAdapter()
            adapter = lumperListAdapter
        }
    }

}