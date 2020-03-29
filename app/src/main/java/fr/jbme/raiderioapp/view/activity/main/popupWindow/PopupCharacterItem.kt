package fr.jbme.raiderioapp.view.activity.main.popupWindow

import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.Characters

class PopupCharacterItem {
    var id: Long
    var name: String
    var realm: String
    var realmSlug: String
    var thumbnailUrl: String? = null

    constructor(character: Characters) {
        id =
            ID_COUNTER
        name = character.name
        realm = character.realm.name
        realmSlug = character.realm.slug
        thumbnailUrl = "d"
        ID_COUNTER++
    }

    constructor(charString: String) {
        name = charString.split('-')[0]
        realm = charString.split('-')[1]
        realmSlug = charString.split('-')[1]
        id =
            ID_COUNTER
        thumbnailUrl = "d"
        ID_COUNTER++
    }

    fun asCharString(): String {
        return "$name-$realmSlug"
    }


    companion object {
        var ID_COUNTER: Long = 0
    }
}