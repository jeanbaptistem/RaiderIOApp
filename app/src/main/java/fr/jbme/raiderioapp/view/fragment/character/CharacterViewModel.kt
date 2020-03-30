package fr.jbme.raiderioapp.view.fragment.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.model.raiderio.characterScore.CharacterScore
import fr.jbme.raiderioapp.service.model.raiderio.dungeonRanks.Rank
import fr.jbme.raiderioapp.service.model.raiderio.dungeonsBestRuns.MythicPlusBestRuns
import fr.jbme.raiderioapp.service.repository.DungeonRepository
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupCharacterItem
import java.util.*

@Suppress("UNCHECKED_CAST")
class CharacterViewModel : ViewModel() {

    private val _viewSelectedCharacter = MutableLiveData<PopupCharacterItem>()
    val selectedCharacter =
        Transformations.distinctUntilChanged(_viewSelectedCharacter)

    fun setSelectedCharacter(selectedCharacter: PopupCharacterItem) {
        _viewSelectedCharacter.postValue(selectedCharacter)
    }

    private val _characterScoreLoading = MutableLiveData<Boolean>()
    val characterScore =
        Transformations.switchMap(selectedCharacter) { character ->
            loadCharacterScore(character.realmSlug, character.name.toLowerCase(Locale.ROOT))
        }

    private fun loadCharacterScore(realm: String, name: String): LiveData<CharacterScore> {
        val characterDataResult = MutableLiveData<CharacterScore>()
        _characterScoreLoading.value = true
        DungeonRepository.fetchScore(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterDataResult.value = result.data as CharacterScore
                _characterScoreLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                _characterScoreLoading.value = false
            }
        })
        return characterDataResult
    }

    private val _characterBestRunsLoading = MutableLiveData<Boolean>()
    val characterBestRuns: LiveData<List<MythicPlusBestRuns>> =
        Transformations.switchMap(selectedCharacter) { character ->
            loadCharacterBestRuns(character.realmSlug, character.name.toLowerCase(Locale.ROOT))
        }

    private fun loadCharacterBestRuns(
        realm: String,
        name: String
    ): LiveData<List<MythicPlusBestRuns>> {
        _characterBestRunsLoading.value = true
        val characterBestRunsResult = MutableLiveData<List<MythicPlusBestRuns>>()
        DungeonRepository.fetchRioBestRuns(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterBestRunsResult.value = result.data as List<MythicPlusBestRuns>
                _characterBestRunsLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                _characterBestRunsLoading.value = false
            }
        })
        return characterBestRunsResult
    }

    private val _characterRanksLoading = MutableLiveData<Boolean>()
    val characterRanks: LiveData<List<Rank>> =
        Transformations.switchMap(selectedCharacter) { character ->
            loadCharacterRanks(character.realmSlug, character.name.toLowerCase(Locale.ROOT))
        }

    private fun loadCharacterRanks(realm: String, name: String): LiveData<List<Rank>> {
        val characterRanksResult = MutableLiveData<List<Rank>>()
        _characterRanksLoading.value = true
        DungeonRepository.fetchRanks(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterRanksResult.value = result.data as List<Rank>
                _characterRanksLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                _characterRanksLoading.value = false
            }

        })
        return characterRanksResult
    }
}