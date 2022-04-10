package com.one4ll.xplayer.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.ImageRecyclerViewAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

private const val TAG = "imageFragment"

@ExperimentalCoroutinesApi
class GalleryFragment : Fragment() {

    private val viewModel: ImageFragmentStateViewModel by viewModels()
    private var imageRecyclerViewAdapter: ImageRecyclerViewAdapter? = null
    private lateinit var imageListRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageListRecyclerView = view.findViewById(R.id.image_list_recycler_view)
        progressBar = view.findViewById(R.id.progressBar)
        errorTextView = view.findViewById(R.id.errorTextView)
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    ImageFragmentState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is ImageFragmentState.ImageLoadedSuccess -> {
                        progressBar.visibility = View.GONE
                        setAdapterToUi(state.imagesList)
                    }
                    is ImageFragmentState.Error -> {
                        errorTextView.visibility = View.VISIBLE
                        errorTextView.text = state.message
                    }
                }
            }

        }
    }


    private fun setAdapterToUi(imageList: List<Media>) {
        imageRecyclerViewAdapter = activity?.let {
            ImageRecyclerViewAdapter(
                imageList,
                activity = it,
                lifeCycleScope = lifecycleScope
            )
        }
        imageListRecyclerView.apply {
            this.adapter = imageRecyclerViewAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}
