package fr.jbme.raiderioapp.view.activity.main.popupWindow

import fr.jbme.raiderioapp.service.model.blizzard.AccountProfile
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class PopupCharacterItem {
    var id: Long
    var name: String
    var realm: String
    var realmSlug: String
    var thumbnailUrl: String = "default"

    constructor(character: AccountProfile.WowAccount.Character) {
        id = idCounter.getAndIncrement().toLong()
        name = character.name!!
        realm = character.realm?.name!!
        realmSlug = character.realm.slug!!
    }

    constructor(charString: String) {
        name = charString.split('-')[0]
        realm = charString.split('-')[1]
        realmSlug = charString.split('-')[1]
        id = idCounter.getAndIncrement().toLong()
    }

    fun asCharString(): String {
        return "${name.toLowerCase(Locale.ROOT)}-$realmSlug"
    }

    override fun toString(): String {
        return "PopupCharacterItem: {" +
                "id:$id, " +
                "name:$name, " +
                "realm:$realm, " +
                "realmSlug:$realmSlug, " +
                "thumbnailUrl:$thumbnailUrl}"
    }


    companion object {
        private var idCounter: AtomicInteger = AtomicInteger(1)
    }
}