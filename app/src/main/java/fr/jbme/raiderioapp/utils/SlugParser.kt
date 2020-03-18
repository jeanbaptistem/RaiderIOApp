package fr.jbme.raiderioapp.utils

import java.util.*

object SlugParser {

    fun parseToSlug(value: String?): String? {
        return value?.replace(' ', '-')
            ?.split(',')
            ?.reduce { acc, s -> acc + s }
            ?.split('\'')
            ?.reduce { acc, s -> acc + s }
            ?.toLowerCase(Locale.ROOT)
    }
}