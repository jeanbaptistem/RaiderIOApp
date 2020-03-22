package fr.jbme.raiderioapp.network.main

import fr.jbme.raiderioapp.model.blizzard.characterEquipment.CharacterEquipment
import fr.jbme.raiderioapp.model.blizzard.characterMedia.CharacterMedia
import fr.jbme.raiderioapp.model.blizzard.characterProfile.CharacterProfile
import fr.jbme.raiderioapp.model.blizzard.itemInfo.ItemInfo
import fr.jbme.raiderioapp.model.blizzard.itemMedia.ItemMedia
import fr.jbme.raiderioapp.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.model.blizzard.raidInfo.CharacterRaidInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface BlizzardService {

    //User Profile
    @GET("/profile/user/wow")
    fun getProfileInfo(@QueryMap globalParam: Map<String, String>): Call<ProfileInfo>


    //Character Profile
    @GET("/profile/wow/character/{realmSlug}/{characterName}")
    fun getCharacterProfile(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?,
        @QueryMap globalParam: Map<String, String>
    ): Call<CharacterProfile>

    @GET("/profile/wow/character/{realmSlug}/{characterName}/equipment")
    fun getCharacterEquipment(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?,
        @QueryMap globalParam: Map<String, String>
    ): Call<CharacterEquipment>

    @GET("/profile/wow/character/{realmSlug}/{characterName}/character-media")
    fun getCharacterMedia(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?,
        @QueryMap globalParam: Map<String, String>
    ): Call<CharacterMedia>

    @GET("/profile/wow/character/{realmSlug}/{characterName}/encounters/raids")
    fun getCharacterRaidInfo(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?,
        @QueryMap globalParam: Map<String, String>
    ): Call<CharacterRaidInfo>


    //Item Data
    @GET("data/wow/media/item/{itemId}")
    fun getItemMedia(
        @Path("itemId") itemId: Int?,
        @QueryMap globalParam: Map<String, String>
    ): Call<ItemMedia>

    @GET("data/wow/item/{itemId}")
    fun getItemInfo(
        @Path("itemId") itemId: Int?,
        @QueryMap globalParam: Map<String, String>
    ): Call<ItemInfo>


    //Spell Data
    @GET("/data/wow/media/spell/{spellId}")
    fun getSpellMedia(
        @Path("spellId") spellId: Int?,
        @QueryMap globalParam: Map<String, String>
    ): Call<ItemMedia>


    //AzeriteEssences Date
    @GET("/data/wow/media/azerite-essence/{azeriteEssenceId}")
    fun getAzeriteEssenceMedia(
        @Path("azeriteEssenceId") azeriteEssenceId: Int?,
        @QueryMap globalParam: Map<String, String>
    ): Call<ItemMedia>
}