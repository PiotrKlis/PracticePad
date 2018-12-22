package com.piotr.practicepad.ui.main.ExerciseList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_exerciseset_list.*

class ExerciseListFragment : Fragment(), OnExerciseListInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        Toast.makeText(context, "LOL $item", Toast.LENGTH_SHORT).show()
    }

    private var listener: OnExerciseListInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_exerciseset_list, container, false)
        listener = this
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_list.adapter = MyExerciseSetRecyclerViewAdapter(DummyContent.ITEMS, listener)

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        fun newInstance() = ExerciseListFragment()
    }
}
