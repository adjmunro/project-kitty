package nz.adjmunro.kty

import nz.adjmunro.kty.functions.Difference
import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.Merge
import nz.adjmunro.kty.functions.Transform
import kotlin.math.absoluteValue
import kotlin.math.sign

@KtyDsl
public interface Inty<W : Inty<W>> : Numbery<W, Int> {
    override val value: Int
    override val spec: (Number) -> Int get() = Number::toInt
    override val plus: Merge<Int> get() = Int::plus
    override val minus: Merge<Int> get() = Int::minus
    override val times: Merge<Int> get() = Int::times
    override val divide: Merge<Int> get() = Int::div
    override val remainder: Merge<Int> get() = Int::rem
    override val absolute: Transform<Int> get() = Int::absoluteValue
    override val negate: Transform<Int> get() = Int::unaryMinus
    override val increment: Transform<Int> get() = Int::inc
    override val decrement: Transform<Int> get() = Int::dec
    override val compare: Difference<Int> get() = Int::compareTo
    override val sign: W get() = reconstruct(value.sign)
}
