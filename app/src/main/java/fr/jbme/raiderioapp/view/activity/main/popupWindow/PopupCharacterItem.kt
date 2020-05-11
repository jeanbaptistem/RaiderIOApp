package fr.jbme.raiderioapp.view.activity.main.popupWindow

import fr.jbme.raiderioapp.REGION
import fr.jbme.raiderioapp.service.model.blizzard.AccountProfile
import java.io.Serializable
import java.util.concurrent.atomic.AtomicInteger

class PopupCharacterItem(
    var id: Long,
    var name: String,
    var realm: String,
    var realmSlug: String,
    var region: String,
    var thumbnailUrl: String = "default"
) : Serializable {


    constructor(character: AccountProfile.WowAccount.Character) : this(
        idCounter.getAndIncrement().toLong(),
        character.name!!,
        character.realm?.name!!,
        character.realm.slug!!,
        REGION
    )

    override fun toString(): String {
        return "PopupCharacterItem: {" +
                "id:$id, " +
                "name:$name, " +
                "realm:$realm, " +
                "realmSlug:$realmSlug, " +
                "thumbnailUrl:$thumbnailUrl, " +
                "region:$region}"
    }

    companion object {
        private var idCounter: AtomicInteger = AtomicInteger(1)
    }
}