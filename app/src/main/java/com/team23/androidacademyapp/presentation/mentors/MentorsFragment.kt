package com.team23.androidacademyapp.presentation.mentors

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.ModelMentor

class MentorsFragment : Fragment() {

    private var recycler: RecyclerView? = null
    private var adapter: MentorAdapter? = null

    companion object {
        fun newInstance() = MentorsFragment()
    }

    private lateinit var viewModel: MentorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mentors_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MentorsViewModel::class.java)

        recycler = view.findViewById<RecyclerView>(R.id.rv_list)
        /*recycler?.layoutManager = GridLayoutManager(
            requireContext(),
            resources.getInteger(R.integer.mentor_list_column_count)
        )*/
        adapter = MentorAdapter(clickListener)
        recycler?.adapter = adapter

        viewModel.modelMentor.observe(this.viewLifecycleOwner, {
            adapter?.bindMentors(it)
            adapter?.notifyDataSetChanged()
        })
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(mentor: ModelMentor) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(mentor.contact)
            startActivity(this@MentorsFragment.requireContext(), i, null)
        }
    }
}

