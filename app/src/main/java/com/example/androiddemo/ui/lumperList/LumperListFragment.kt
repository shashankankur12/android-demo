package com.example.androiddemo.ui.lumperList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddemo.R
import com.example.androiddemo.utils.Coroutines
import com.example.androiddemo.utils.UiUtils
import kotlinx.android.synthetic.main.lumper_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class LumperListFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private lateinit var viewModel: LumperListViewModel
    private val factory:LumperListViewModelFactory by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.lumper_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(LumperListViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            val lumperList = viewModel.lumperList.await()
            lumperList.observe(viewLifecycleOwner, Observer { lumperList ->
                recycler_view_lumper.also {
                    it.layoutManager =LinearLayoutManager(context)
                    it.setHasFixedSize(true)
                    it.adapter= LumperListAdapter(lumperList)
                }
            })
        }

    }

}