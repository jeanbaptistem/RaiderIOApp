package fr.jbme.raiderioapp.service.model.wowprogress

data class WoWProgressResponse(
    val type: String,
    val characters: List<Characters>
) {
    data class Characters(
        val name: String,
        val realm: String,
        val region: String,
        val _class: String
    )
}
