package fr.jbme.raiderioapp.network.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

object LiveDataUtils {
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
                    this.value = Quadruple(localLastA, localLastB, localLastC, localLastD)
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
}
