package com.team23.androidacademyapp.presentation.mentors

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team23.androidacademyapp.R

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
        recycler = view.findViewById<RecyclerView>(R.id.rv_list)
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        adapter = MentorAdapter()
        recycler?.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MentorsViewModel::class.java)

        viewModel.modelMentor.observe(this.viewLifecycleOwner, { adapter?.bindMentors(it) } )
    }
}

class MentorAdapter : RecyclerView.Adapter<MentorViewHolder>() {

    private var mentors : List<ModelMentor> = listOf<ModelMentor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        return MentorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_mentor, parent, false))
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        holder.onBind(mentors.get(position))
    }

    override fun getItemCount(): Int {
        return mentors.size
    }

    fun bindMentors(newMentors : List<ModelMentor>){
        mentors = newMentors
    }

}

class MentorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val avatar : ImageView = itemView.findViewById(R.id.iv_avatar)
    private val name : TextView = itemView.findViewById(R.id.tv_name)

    fun onBind(mentor : ModelMentor){
        name.text = mentor.name
    }
}
