package fr.jbme.raiderioapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import java.util.*
import java.util.concurrent.TimeUnit

object Whatever {

    fun parseToSlug(value: String?): String {
        return value
            ?.split(',')
            ?.reduce { acc, s -> acc + s }
            ?.split('\'')
            ?.reduce { acc, s -> acc + s }
            ?.split("!!")
            ?.reduce { acc, s -> acc + s }
            ?.split(':')
            ?.reduce { acc, s -> acc + s }
            ?.split(" -")
            ?.reduce { acc, s -> acc + s }
            ?.replace(' ', '-')
            ?.toLowerCase(Locale.ROOT) ?: ""
    }

    fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }

    fun <A, B, C, D> zipQuadruple(
        a: LiveData<A>,
        b: LiveData<B>,
        c: LiveData<C>,
        d: LiveData<D>
    ): LiveData<Quadruple<A, B, C, D>> {
        return MediatorLiveData<Quadruple<A, B, C, D>>().apply {
            var lastA: A? = null
            var lastB: B? = null
            var lastC: C? = null
            var lastD: D? = null

            fun update() {
                val localLastA = lastA
                val localLastB = lastB
                val localLastC = lastC
                val localLastD = lastD
                if (localLastA != null && localLastB != null && localLastC != null && localLastD != null)
                    this.value = Quadruple(
                        localLastA,
                        localLastB,
                        localLastC,
                        localLastD
                    )
            }

            addSource(a) {
                lastA = it
                update()
            }
            addSource(b) {
                lastB = it
                update()
            }
            addSource(c) {
                lastC = it
                update()
            }
            addSource(d) {
                lastD = it
                update()
            }
        }
    }

    fun <A, B, C> zipTriple(
        a: LiveData<A>,
        b: LiveData<B>,
        c: LiveData<C>
    ): LiveData<Triple<A, B, C>> {
        return MediatorLiveData<Triple<A, B, C>>().apply {
            var lastA: A? = null
            var lastB: B? = null
            var lastC: C? = null

            fun update() {
                val localLastA = lastA
                val localLastB = lastB
                val localLastC = lastC

                if (localLastA != null && localLastB != null && localLastC != null)
                    this.value = Triple(localLastA, localLastB, localLastC)
            }

            addSource(a) {
                lastA = it
                update()
            }
            addSource(b) {
                lastB = it
                update()
            }
            addSource(c) {
                lastC = it
                update()
            }
        }
    }

    fun <A, B> zipPair(
        a: LiveData<A>,
        b: LiveData<B>
    ): LiveData<Pair<A, B>> {
        return MediatorLiveData<Pair<A, B>>().apply {
            var lastA: A? = null
            var lastB: B? = null

            fun update() {
                val localLastA = lastA
                val localLastB = lastB

                if (localLastA != null && localLastB != null)
                    this.value = Pair(localLastA, localLastB)
            }

            addSource(a) {
                lastA = it
                update()
            }
            addSource(b) {
                lastB = it
                update()
            }
        }
    }

    fun formatTime(clearTimeMs: Long): String? {
        require(clearTimeMs >= 0) { "Duration must be greater than zero!" }
        var millis = clearTimeMs

        val hours = TimeUnit.MILLISECONDS.toHours(millis)
        millis -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
        millis -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis)

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}