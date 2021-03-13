package com.team23.androidacademyapp.presentation.title

import android.content.Intent
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
import com.team23.androidacademyapp.domain.models.Model

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

//        val button: Button? = view?.findViewById(R.id.iv_quiz)
//        button?.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_quizFragment, )
//        )

       return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)

        

        val listLecture: RecyclerView? = view?.findViewById(R.id.lecture_list)
        val myAdapter = LectureAdapter(clickListener)
        listLecture?.adapter = myAdapter



        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        viewModel.model.observe(viewLifecycleOwner, Observer {model->
            model?.let {
                myAdapter.submitList(model)
                myAdapter.notifyDataSetChanged()
            }
        })

    }

    private val clickListener = object : OnItemClick {
        override fun onClick(lecture: Model) {
            val args = Bundle()
            args.putParcelable("lecture", lecture)
//            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_lectureDetailFragment, args)
            val i = Intent(context, LectureDetailActivity::class.java)
            i.putExtra("lecture", lecture)
            //findNavController().navigate(R.id.action_titleFragment_to_lectureDetailFragment, args)
            startActivity(i)
        }
    }

}

interface OnItemClick{
    fun onClick(lecture : Model)
}