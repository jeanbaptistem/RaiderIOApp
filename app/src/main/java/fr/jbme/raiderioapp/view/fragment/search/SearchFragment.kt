package fr.jbme.raiderioapp.view.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.activity.main.MainActivityViewModel
import fr.jbme.raiderioapp.view.utils.OnRecyclerViewItemClickListener
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
            addOnItemTouchListener(OnRecyclerViewItemClickListener(context,
                this,
                object :
                    OnRecyclerViewItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val selectedItem = searchAdapter.searchList[position]
                        val bundle = bundleOf(
                            "region" to selectedItem.realm,
                            "realm" to selectedItem.realm,
                            "name" to selectedItem.name,
                            "thumbnailUrl" to selectedItem.thumbnailUrl
                        )
                        findNavController().navigate(R.id.nav_character_page, bundle)
                    }

                    override fun onLongItemClick(view: View, position: Int) {}
                }
            ))
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
