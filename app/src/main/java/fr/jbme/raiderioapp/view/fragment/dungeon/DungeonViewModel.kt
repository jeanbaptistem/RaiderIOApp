package fr.jbme.raiderioapp.view.fragment.dungeon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo.BestRuns
import fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo.DungeonInfo
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.model.raiderio.dungeonRanks.Rank
import fr.jbme.raiderioapp.service.repository.DungeonRepository
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.Whatever

class DungeonViewModel : ViewModel() {
    private val _viewSelectedCharacter = MutableLiveData<String>()
    private val _trueSelectedCharacter =
        Transformations.distinctUntilChanged(_viewSelectedCharacter)

    fun selectedCharacter(selectedCharName: String?) {
        _viewSelectedCharacter.postValue(selectedCharName)
    }

    private val _characterRanksLoading = MutableLiveData<Boolean>()
    private val characterRanks: LiveData<List<Rank>> =
        Transformations.switchMap(_trueSelectedCharacter) { character ->
            val name = Whatever.parseToSlug(character.split('-')[0])
            val realm = Whatever.parseToSlug(character.split('-')[1])
            loadCharacterRanks(realm, name)
        }

    private val _characterDungeonsLoading = MutableLiveData<Boolean>()
    private val characterDungeons: LiveData<List<BestRuns>> =
        Transformations.switchMap(_trueSelectedCharacter) { character ->
            val name = Whatever.parseToSlug(character.split('-')[0])
            val realm = Whatever.parseToSlug(character.split('-')[1])
            loadCharacterDungeons(realm, name)
        }

    val zippedDungeonData: LiveData<Pair<List<Rank>, List<BestRuns>>> =
        Whatever.zipPair(characterRanks, characterDungeons)
    val zippedDungeonDataLoading: LiveData<Pair<Boolean, Boolean>> =
        Whatever.zipPair(_characterRanksLoading, _characterDungeonsLoading)


    private fun loadCharacterRanks(realm: String, name: String): LiveData<List<Rank>> {
        val characterRanksResult = MutableLiveData<List<Rank>>()
        _characterRanksLoading.value = true
        DungeonRepository.fetchRanks(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterRanksResult.value = (result.data as List<Rank>)
                _characterRanksLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                _characterRanksLoading.value = true
            }

        })
        return characterRanksResult
    }

    private fun loadCharacterDungeons(realm: String, name: String): LiveData<List<BestRuns>> {
        val characterDungeonsResult = MutableLiveData<List<BestRuns>>()
        _characterDungeonsLoading.value = true
        DungeonRepository.fetchBlizzardDungeons(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterDungeonsResult.value = (result.data as DungeonInfo).best_runs
                _characterDungeonsLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                _characterDungeonsLoading.value = true
            }

        })
        return characterDungeonsResult
    }
}