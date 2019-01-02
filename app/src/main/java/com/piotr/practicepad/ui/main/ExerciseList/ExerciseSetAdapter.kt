package com.piotr.practicepad.ui.main.ExerciseList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.piotr.practicepad.R


import kotlinx.android.synthetic.main.fragment_exerciseset.view.*
import java.util.*

class ExerciseSetAdapter : RecyclerView.Adapter<ExerciseSetAdapter.ViewHolder>() {

    private var onClickListener: View.OnClickListener
    private var exerciseSetList: List<ExerciseSetEntity> = Collections.emptyList()

    init {
        onClickListener = View.OnClickListener { v ->
            //            mListener?.onListFragmentInteraction(item)
        }
    }

    fun setListener(listener: View.OnClickListener) {
        onClickListener = View.OnClickListener { view -> }
    }

    fun setItems(items: List<ExerciseSetEntity>?) {
        this.exerciseSetList = items!!
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_exerciseset, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = exerciseSetList.get(position)
        holder.mIdView.text = item.id.toString()
        holder.mContentView.text = item.name

        with(holder.mView) {
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int {
        return exerciseSetList.size

    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
