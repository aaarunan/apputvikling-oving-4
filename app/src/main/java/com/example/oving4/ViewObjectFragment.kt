package com.example.oving4;

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ViewObjectFragment : Fragment() {
    private lateinit var descriptions: Array<String>
    private lateinit var imagePaths: Array<String>;
    private lateinit var titles: Array<String>

    private var currPos: Int = 0

    private lateinit var titleView : TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var thumbnailImageView: ImageView

    private lateinit var assetManager: AssetManager

    override fun onCreate(savedInstanceState: Bundle?) {
        descriptions = resources.getStringArray(R.array.descriptions)
        titles = resources.getStringArray(R.array.objects)
        imagePaths = resources.getStringArray(R.array.images)
        assetManager = resources.assets
        super.onCreate(savedInstanceState)
    }

    fun displayObject(i: Int) {
        currPos = i
        descriptionTextView.text = descriptions[currPos]
        titleView.text = titles[currPos]

        val filename = assetManager.open("images/" + imagePaths[currPos])
        val bitmap = BitmapFactory.decodeStream(filename)
        thumbnailImageView.setImageBitmap(bitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_object_fragment, container, false)
        descriptionTextView = view.findViewById(R.id.description)
        thumbnailImageView = view.findViewById(R.id.thumbnail)
        titleView = view.findViewById(R.id.textView)
        displayObject(0)
        return view
    }

    fun next() {
        displayObject(currPos + 1)
    }

    fun prev() {
        displayObject(currPos - 1)
    }
}