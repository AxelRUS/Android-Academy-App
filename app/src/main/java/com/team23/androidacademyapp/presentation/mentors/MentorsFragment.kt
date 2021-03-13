package com.team23.androidacademyapp.presentation.mentors

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.ModelMentor
import com.team23.androidacademyapp.presentation.MainActivity

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
        recycler?.layoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.mentor_list_column_count))
        adapter = MentorAdapter(clickListener)
        recycler?.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MentorsViewModel::class.java)

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

class MentorAdapter(
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<MentorViewHolder>() {

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .circleCrop()

    private var mentors : List<ModelMentor> = listOf<ModelMentor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        return MentorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_mentor,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        holder.onBind(imageOption, mentors.get(position))
        holder.itemView.setOnClickListener {
            clickListener.onClick(mentors.get(position))
        }
    }

    override fun getItemCount(): Int {
        return mentors.size
    }

    fun bindMentors(newMentors: List<ModelMentor>){
        mentors = newMentors
    }

}

class MentorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val avatar : ImageView = itemView.findViewById(R.id.iv_avatar)
    private val name : TextView = itemView.findViewById(R.id.tv_name)

    fun onBind(options: RequestOptions, mentor: ModelMentor){
        name.text = mentor.surname + " " + mentor.name

        Glide.with(context)
            .load(mentor.foto)
            .apply(options)
            .into(avatar)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnRecyclerItemClicked {
    fun onClick(mentor: ModelMentor)
}