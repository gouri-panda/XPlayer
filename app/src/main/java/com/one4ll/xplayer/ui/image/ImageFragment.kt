package com.one4ll.xplayer.ui.image

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.adapter.ImageRecyclerViewAdapter
import com.one4ll.xplayer.databinding.FragmentGalleryBinding
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "imageFragment"

@ExperimentalCoroutinesApi
class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel: ImageFragmentViewHolder by viewModels()
    private var imageRecyclerViewAdapter: ImageRecyclerViewAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreateView: imageFragment view created")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    ImageFragmentState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ImageFragmentState.ImageLoadedSuccess -> {
                        binding.progressBar.visibility = View.GONE
                        setAdapterToUi(state.imagesList)
                    }
                    is ImageFragmentState.Error -> {
                        binding.errorTextView.visibility = View.VISIBLE
                        binding.errorTextView.text = state.message
                    }
                }
            }

        }
    }


    private fun setAdapterToUi(imageList: List<Media>) {
        imageRecyclerViewAdapter = activity?.let { ImageRecyclerViewAdapter(imageList, activity = it, lifeCycleScope = lifecycleScope) }
        binding.root.image_list_recycler_view.apply {
            this.adapter = imageRecyclerViewAdapter
            layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}
