package com.team23.androidacademyapp.presentation.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        val view = inflater.inflate(R.layout.title_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)


        val listLecture: RecyclerView? = view?.findViewById(R.id.lecture_list)
        val myAdapter = LectureAdapter()
        listLecture?.adapter = myAdapter


        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        viewModel.model.observe(viewLifecycleOwner, Observer { model ->
            model?.let {
                myAdapter.submitList(model)
                myAdapter.notifyDataSetChanged()
            }
        })

    }
}
