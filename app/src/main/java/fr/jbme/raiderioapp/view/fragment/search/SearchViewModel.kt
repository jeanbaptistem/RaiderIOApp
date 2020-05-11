package fr.jbme.raiderioapp.view.fragment.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.service.model.raiderio.CharacterScore
import fr.jbme.raiderioapp.service.model.wowprogress.WoWProgressResponse
import fr.jbme.raiderioapp.service.repository.DungeonRepository
import fr.jbme.raiderioapp.service.repository.SearchRepository
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.Whatever
import fr.jbme.raiderioapp.utils.network.Result

class SearchViewModel : ViewModel() {

    val searchQuery = MutableLiveData<String>()
    fun putSearchQuery(query: String?) {
        searchQuery.postValue(query?.trim())
    }

    private val characterSearchResults =
        Transformations.switchMap(searchQuery) {
            performSearch(it)
        }

    val searchResultData =
        Transformations.switchMap(characterSearchResults) {
            findSearchResultData(it)
        }

    val searchDataLoading = Transformations.switchMap(searchResultData) {
        return@switchMap MutableLiveData(it.size != characterSearchResults.value?.size)
    }

    private fun findSearchResultData(charactersList: List<WoWProgressResponse.Characters>): LiveData<List<CharacterScore>> {
        val resultList = MutableLiveData<List<CharacterScore>>()
        val tempList = mutableListOf<CharacterScore>()
        if (charactersList.isNotEmpty()) {
            charactersList.forEach { character ->
                DungeonRepository.fetchScore(
                    character.region,
                    Whatever.parseToSlug(character.realm),
                    Whatever.parseToSlug(character.name),
                    object : DataCallback {
                        override fun onDataLoaded(result: Result.Success<*>) {
                            tempList.add(result.data as CharacterScore)
                            resultList.value = tempList
                        }

                        override fun onDataNotAvailable(error: Result.Error) {
                            tempList.add(
                                CharacterScore(
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    character.name,
                                    null,
                                    null,
                                    null,
                                    character.realm,
                                    character.region,
                                    null
                                )
                            )
                            resultList.value = tempList
                            Log.i("searchResultDataError", error.exception.message.toString())
                        }

                    })
            }
        } else {
            tempList.clear()
            resultList.value = tempList
        }
        return resultList
    }

    private fun performSearch(query: String): LiveData<List<WoWProgressResponse.Characters>> {
        val searchResults = MutableLiveData<List<WoWProgressResponse.Characters>>()
        SearchRepository.searchCharacters(query, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                searchResults.value = result.data as List<WoWProgressResponse.Characters>
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("SearchError", error.exception.message.toString())
            }
        })
        return searchResults
    }


}