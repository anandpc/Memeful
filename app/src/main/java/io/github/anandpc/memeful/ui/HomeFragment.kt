package io.github.anandpc.memeful.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
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
    private lateinit var mProgressBarMain: ProgressBar
    private lateinit var mProgressBarEnd: ProgressBar
    private lateinit var mStaggeredGridLayoutManager: StaggeredGridLayoutManager

    private var isScrolling: Boolean = false

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
        mProgressBarMain = root.findViewById(R.id.progress_circular_main)
        mProgressBarEnd = root.findViewById(R.id.progress_circular_end)
        mMemeAdapter = MemeAdapter()
        mStaggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView.adapter = mMemeAdapter
        mRecyclerView.layoutManager = mStaggeredGridLayoutManager

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mHomeViewModel.memes.observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                mProgressBarMain.visibility = View.GONE
                mProgressBarEnd.visibility = View.GONE
            }
            mMemeAdapter.setMemesList(data)
        }

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItems = mStaggeredGridLayoutManager.childCount
                val totalItems = mStaggeredGridLayoutManager.itemCount
                val scrolledOutItems: IntArray? =
                    mStaggeredGridLayoutManager.findFirstVisibleItemPositions(null)

                if (isScrolling && (visibleItems + scrolledOutItems!![0] == totalItems)) {
                    isScrolling = false
                    mProgressBarEnd.visibility = View.VISIBLE
                    mHomeViewModel.getMemes()
                }

            }
        })
    }

}