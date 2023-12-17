package com.example.foodiepal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// BlogFragment.kt
class BlogFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var blogList: ArrayList<Blog>
    private lateinit var adapter: BlogAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blog, container, false)

        recyclerView = view.findViewById(R.id.blogRecyclerView)
        blogList = ArrayList()
        adapter = BlogAdapter(blogList)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // Add a new blog post
        val fab = view.findViewById<FloatingActionButton>(R.id.addBlogFab)
        fab.setOnClickListener {
            // Show dialog to add new blog post
            showAddBlogDialog()
        }

        return view
    }

    private fun showAddBlogDialog() {
        // Implement dialog to add new blog post
    }

    private fun addBlog(blog: Blog) {
        blogList.add(blog)
        adapter.notifyDataSetChanged()
    }
}

class BlogAdapter(private val blogList: ArrayList<Blog>) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blog = blogList[position]
        holder.titleTextView.text = blog.title
        holder.authorTextView.text = blog.author
        holder.dateTextView.text = blog.date
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tvTitle)
        val authorTextView: TextView = itemView.findViewById(R.id.tvAuthor)
        val dateTextView: TextView = itemView.findViewById(R.id.tvContent)
    }
}

data class Blog(val title: String, val author: String, val date: String)
