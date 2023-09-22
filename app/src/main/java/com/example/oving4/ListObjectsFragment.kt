package com.example.oving4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ListObjectsFragment : Fragment() {

    private var objects: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        objects = resources.getStringArray(R.array.objects)
    }

    private fun initList(view: View) {
        val titleList = view.findViewById<ListView>(R.id.listObjectsView)
        titleList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, rowId ->
            onClickTitle(parent, view, position, rowId)
        }
        val adapter = ArrayAdapter(activity as Context, android.R.layout.simple_list_item_activated_1, objects)

        titleList.adapter = adapter
    }

    private fun onClickTitle(parent: AdapterView<*>, view: View, position: Int, rowId: Long) {
        listener?.onItemSelected(position)
        Log.d("ListObjectsFragment", "Clicked on " + objects[position])
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.list_objects_fragment, container, false);
        initList(view)
        return view
    }

    private var listener: OnItemSelectedListener? = null

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Setting the memberListener to the activity, aka the other fragment, that implements the defined interface
        listener = try {
            activity as OnItemSelectedListener
        } catch (e: ClassCastException) {
            throw e
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}