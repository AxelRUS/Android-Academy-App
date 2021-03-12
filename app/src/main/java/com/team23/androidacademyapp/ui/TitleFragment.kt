package com.team23.androidacademyapp.ui

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

class TitleFragment : Fragment() {

    companion object {
        fun newInstance() = TitleFragment()
    }

    private lateinit var viewModel: TitleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.title_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)

        val listLecture: RecyclerView? = view?.findViewById(R.id.lecture_list)
        val myAdapter = LectureAdapter()
        listLecture?.adapter = myAdapter



        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        viewModel.model.observe(viewLifecycleOwner, Observer {model->
            model?.let {
                myAdapter.submitList(model)
                myAdapter.notifyDataSetChanged()
            }
        })

    }

}