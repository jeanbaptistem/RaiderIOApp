package fr.jbme.raiderioapp.utils

import java.util.*

object Whatever {

    fun parseToSlug(value: String?): String? {
        return value?.replace(' ', '-')
            ?.split(',')
            ?.reduce { acc, s -> acc + s }
            ?.split('\'')
            ?.reduce { acc, s -> acc + s }
            ?.toLowerCase(Locale.ROOT)
    }

    fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }

}