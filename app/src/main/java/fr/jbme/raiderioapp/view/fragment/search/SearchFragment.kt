package fr.jbme.raiderioapp.view.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.activity.main.MainActivityViewModel
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchProgressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.search_fragment, container, false)
        searchProgressBar = root.findViewById(R.id.searchProgressBar)

        val mainActivityViewModel =
            activity?.let { ViewModelProvider(it).get(MainActivityViewModel::class.java) }
        mainActivityViewModel?.searchQuery?.observe(viewLifecycleOwner, Observer {
            searchViewModel.putSearchQuery(it)
        })

        searchAdapter = SearchAdapter(context, listOf())
        searchRecyclerView = root.findViewById(R.id.searchRecyclerView)
        searchRecyclerView.run {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
        }

        observeViewModel(searchViewModel)

        return root
    }

    private fun observeViewModel(viewModel: SearchViewModel) {
        viewModel.searchResultData.observe(viewLifecycleOwner, Observer {
            searchAdapter.run {
                searchList = it
                notifyDataSetChanged()
            }
        })
        viewModel.searchQuery.observe(viewLifecycleOwner, Observer {
            queryTextView.text = it
        })
        viewModel.searchDataLoading.observe(viewLifecycleOwner, Observer {
            if (it) searchProgressBar.visibility = View.VISIBLE
            else searchProgressBar.visibility = View.INVISIBLE
        })
    }
}
