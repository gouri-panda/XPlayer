package com.one4ll.xplayer.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.ImageRecyclerViewAdapter
import com.one4ll.xplayer.databinding.FragmentGalleryBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

private const val TAG = "imageFragment"

@ExperimentalCoroutinesApi
class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel: ImageFragmentStateViewModel by viewModels()
    private var imageRecyclerViewAdapter: ImageRecyclerViewAdapter? = null
    private lateinit var imageListRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageListRecyclerView = view.findViewById(R.id.image_list_recycler_view)
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
                LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}
