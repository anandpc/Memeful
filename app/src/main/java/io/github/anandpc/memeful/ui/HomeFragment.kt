package io.github.anandpc.memeful.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.anandpc.memeful.R

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val mHomeViewModel: HomeViewModel by viewModels()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mMemeAdapter: MemeAdapter
    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeViewModel.getMemes()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        mRecyclerView = root.findViewById(R.id.recycler_view)
        mProgressBar = root.findViewById(R.id.progress_circular)
        mMemeAdapter = MemeAdapter()
        mRecyclerView.apply {
            adapter = mMemeAdapter
            mRecyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mHomeViewModel.memes.observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                mProgressBar.visibility = View.GONE
            }
            mMemeAdapter.setMemesList(data)
        }
    }

}