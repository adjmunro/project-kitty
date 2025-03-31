package nz.adjmunro.kty

import nz.adjmunro.kty.functions.Difference
import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.Merge
import nz.adjmunro.kty.functions.Transform
import kotlin.math.absoluteValue
import kotlin.math.sign

@KtyDsl
public interface Longy<W : Longy<W>> : Numbery<W, Long> {
    override val value: Long
    override val spec: (Number) -> Long get() = Number::toLong
    override val plus: Merge<Long> get() = Long::plus
    override val minus: Merge<Long> get() = Long::minus
    override val times: Merge<Long> get() = Long::times
    override val divide: Merge<Long> get() = Long::div
    override val remainder: Merge<Long> get() = Long::rem
    override val absolute: Transform<Long> get() = Long::absoluteValue
    override val negate: Transform<Long> get() = Long::unaryMinus
    override val increment: Transform<Long> get() = Long::inc
    override val decrement: Transform<Long> get() = Long::dec
    override val compare: Difference<Long> get() = Long::compareTo
    override val sign: W get() = reconstruct(spec(value.sign))
}
