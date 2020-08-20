package io.github.anandpc.memeful.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.anandpc.memeful.R
import io.github.anandpc.memeful.data.model.Data


class MemeAdapter :
    RecyclerView.Adapter<MemeAdapter.MemeViewHolder>() {

    var mMemeList: List<Data> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meme_view_single_item, parent, false)
        view.setOnClickListener {
            Toast.makeText(parent.context, "Clicked", Toast.LENGTH_SHORT).show()
        }
        return MemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val currentItem: Data = mMemeList[position]
        Picasso.get().load(currentItem.images?.get(0)?.link).into(holder.mMemeImage)
        holder.mMemeTitle.text = currentItem.title
        holder.mMemePoints.text = currentItem.points.toString()
    }

    override fun getItemCount(): Int {
        return mMemeList.size
    }

    inner class MemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mMemeImage: ImageView = itemView.findViewById(R.id.iv_meme)
        val mMemeTitle: TextView = itemView.findViewById(R.id.tv_meme_title)
        val mMemePoints: TextView = itemView.findViewById(R.id.tv_text_points)
    }

    fun setMemesList(memesList: List<Data>) {
        mMemeList = memesList
        notifyDataSetChanged()
    }
}