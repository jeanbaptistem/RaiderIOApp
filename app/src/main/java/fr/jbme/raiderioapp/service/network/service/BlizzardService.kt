package fr.jbme.raiderioapp.service.network.service

import fr.jbme.raiderioapp.service.model.blizzard.characterEquipment.CharacterEquipment
import fr.jbme.raiderioapp.service.model.blizzard.characterMedia.CharacterMedia
import fr.jbme.raiderioapp.service.model.blizzard.characterProfile.CharacterProfile
import fr.jbme.raiderioapp.service.model.blizzard.itemInfo.ItemInfo
import fr.jbme.raiderioapp.service.model.blizzard.itemMedia.Media
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.service.model.blizzard.raidInfo.RaidInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BlizzardService {

    //User Profile
    @Headers("Namespace: Profile")
    @GET("/profile/user/wow")
    fun getProfileInfo(): Call<ProfileInfo>


    //Character Profile
    @Headers("Namespace: Profile")
    @GET("/profile/wow/character/{realmSlug}/{characterName}")
    fun getCharacterProfile(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?
    ): Call<CharacterProfile>

    @Headers("Namespace: Profile")
    @GET("/profile/wow/character/{realmSlug}/{characterName}/equipment")
    fun getCharacterEquipment(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?
    ): Call<CharacterEquipment>

    @Headers("Namespace: Profile")
    @GET("/profile/wow/character/{realmSlug}/{characterName}/character-media")
    fun getCharacterMedia(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?
    ): Call<CharacterMedia>

    @Headers("Namespace: Profile")
    @GET("/profile/wow/character/{realmSlug}/{characterName}/encounters/raids")
    fun getCharacterRaidInfo(
        @Path("realmSlug") realmSlug: String?,
        @Path("characterName") characterName: String?
    ): Call<RaidInfo>


    //Item Data
    @Headers("Namespace: Static")
    @GET("data/wow/media/item/{itemId}")
    fun getItemMedia(
        @Path("itemId") itemId: Int?
    ): Call<Media>

    @Headers("Namespace: Static")
    @GET("data/wow/item/{itemId}")
    fun getItemInfo(
        @Path("itemId") itemId: Int?
    ): Call<ItemInfo>


    //Spell Data
    @Headers("Namespace: Static")
    @GET("/data/wow/media/spell/{spellId}")
    fun getSpellMedia(
        @Path("spellId") spellId: Int?
    ): Call<Media>


    //AzeriteEssences Date
    @Headers("Namespace: Static")
    @GET("/data/wow/media/azerite-essence/{azeriteEssenceId}")
    fun getAzeriteEssenceMedia(
        @Path("azeriteEssenceId") azeriteEssenceId: Int?
    ): Call<Media>
}