package com.team23.androidacademyapp.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team23.androidacademyapp.R

class NewsFragment : Fragment() {

    private var recycler: RecyclerView? = null
    private var adapter: NewsAdapter? = null

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        recycler = view.findViewById<RecyclerView>(R.id.rv_list)
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter()
        recycler?.adapter = adapter

        viewModel.newsList.observe(this.viewLifecycleOwner, {
            adapter?.bindNews(it)
            adapter?.notifyDataSetChanged()
        })
    }


}
