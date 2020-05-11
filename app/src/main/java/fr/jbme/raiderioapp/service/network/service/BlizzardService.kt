package fr.jbme.raiderioapp.service.network.service

import fr.jbme.raiderioapp.LAST_SEASON_ID
import fr.jbme.raiderioapp.service.model.blizzard.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BlizzardService {

    //User Profile
    @Headers("Namespace: Profile")
    @GET("/profile/user/wow")
    fun getProfileInfoBliz(): Call<AccountProfile>


    //Character Profile
    @Headers("Namespace: Profile")
    @GET("/profile/wow/character/{realmSlug}/{characterName}")
    fun getCharacterProfileBliz(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?
    ): Call<CharacterProfile>

    @Headers("Namespace: Profile")
    @GET("/profile/wow/character/{realmSlug}/{characterName}/equipment")
    fun getCharacterEquipmentBliz(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?
    ): Call<CharacterEquipment>

    @Headers("Namespace: Profile")
    @GET("/profile/wow/character/{realmSlug}/{characterName}/character-media")
    fun getCharacterMediaBliz(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?
    ): Call<CharacterMedia>

    @Headers("Namespace: Profile")
    @GET("/profile/wow/character/{realmSlug}/{characterName}/encounters/raids")
    fun getCharacterRaidInfoBliz(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?
    ): Call<RaidInfo>


    //Item Data
    @Headers("Namespace: Static")
    @GET("data/wow/media/item/{itemId}")
    fun getItemMediaBliz(
        @Path("itemId") itemId: Int?
    ): Call<ItemMedia>

    @Headers("Namespace: Static")
    @GET("data/wow/item/{itemId}")
    fun getItemInfoBliz(
        @Path("itemId") itemId: Int?
    ): Call<ItemInfo>


    //Spell Data
    @Headers("Namespace: Static")
    @GET("/data/wow/media/spell/{spellId}")
    fun getSpellMediaBliz(
        @Path("spellId") spellId: Int?
    ): Call<ItemMedia>


    //AzeriteEssences Data
    @Headers("Namespace: Static")
    @GET("/data/wow/media/azerite-essence/{azeriteEssenceId}")
    fun getAzeriteEssenceMediaBliz(
        @Path("azeriteEssenceId") azeriteEssenceId: Int
    ): Call<ItemMedia>


    //Dungeons Data
    @Headers("Namespace: Profile")
    @GET("/profile/wow/character/{realmSlug}/{characterName}/mythic-keystone-profile/season/${LAST_SEASON_ID}")
    fun getCharacterDungeonBliz(
        @Path("realmSlug") realmSlug: String,
        @Path("characterName") characterName: String
    ): Call<DungeonInfo>
}