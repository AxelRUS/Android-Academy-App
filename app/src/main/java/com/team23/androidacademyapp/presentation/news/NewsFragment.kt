package com.team23.androidacademyapp.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
        recycler = view.findViewById<RecyclerView>(R.id.rv_list)
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(clickListener)
        recycler?.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        viewModel.modelNews.observe(this.viewLifecycleOwner, {
            adapter?.bindNews(it)
            adapter?.notifyDataSetChanged()
        })
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(news: ModelNews) {
//            val i = Intent(Intent.ACTION_VIEW)
//            i.data = Uri.parse(news.contact)
//            ContextCompat.startActivity(this@NewsFragment.requireContext(), i, null)
        }
    }

}


class NewsAdapter(
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<NewsViewHolder>() {

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .circleCrop()

    private var news : List<ModelNews> = listOf<ModelNews>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_mentor,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(imageOption, news.get(position))
        holder.itemView.setOnClickListener {
            clickListener.onClick(news.get(position))
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun bindNews(newNews: List<ModelNews>){
        news = newNews
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val content : ImageView = itemView.findViewById(R.id.iv_content)
    private val title : TextView = itemView.findViewById(R.id.tv_title)

    fun onBind(options: RequestOptions, news: ModelNews){
        title.text = news.title

        Glide.with(context)
            .load(news.content)
            .apply(options)
            .into(content)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnRecyclerItemClicked {
    fun onClick(news: ModelNews)
}

data class ModelNews(val title: String, val content : String, val text : String)