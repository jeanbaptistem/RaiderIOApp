package fr.jbme.raiderioapp.view.activity.main.popupWindow

import fr.jbme.raiderioapp.service.model.blizzard.AccountProfile
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class PopupCharacterItem(
    var id: Long,
    var name: String,
    var realm: String,
    var realmSlug: String,
    var thumbnailUrl: String = "default"
) {


    constructor(character: AccountProfile.WowAccount.Character) : this(
        idCounter.getAndIncrement().toLong(),
        character.name!!,
        character.realm?.name!!,
        character.realm.slug!!
    )

    constructor(charString: String) : this(
        idCounter.getAndIncrement().toLong(),
        charString.split('-')[0],
        charString.split('-')[1],
        charString.split('-')[1]
    )

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