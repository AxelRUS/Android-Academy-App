package com.team23.androidacademyapp.presentation.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.LectureAdapter
import com.team23.androidacademyapp.presentation.title.TitleViewModel

class NewsFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        val listNews: RecyclerView? = view?.findViewById(R.id.rv_news)
        val myAdapter = NewsAdapter()
        listNews?.adapter = myAdapter

        viewModel.modelNews.observe(viewLifecycleOwner, Observer {modelNews->
            modelNews?.let {
                myAdapter.submitList(modelNews)
                myAdapter.notifyDataSetChanged()
            }
        })
    }

}