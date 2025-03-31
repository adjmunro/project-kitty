package nz.adjmunro.kty

import nz.adjmunro.kty.functions.Difference
import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.Merge
import nz.adjmunro.kty.functions.Transform
import nz.adjmunro.kty.functions.operators.cast.RoundCastable
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.math.sign

@KtyDsl
public interface Floaty<W : Floaty<W>> : Numbery<W, Float>, RoundCastable {
    override val value: Float
    override val spec: (Number) -> Float get() = Number::toFloat
    override val plus: Merge<Float> get() = Float::plus
    override val minus: Merge<Float> get() = Float::minus
    override val times: Merge<Float> get() = Float::times
    override val divide: Merge<Float> get() = Float::div
    override val remainder: Merge<Float> get() = Float::rem
    override val absolute: Transform<Float> get() = Float::absoluteValue
    override val negate: Transform<Float> get() = Float::unaryMinus
    override val increment: Transform<Float> get() = Float::inc
    override val decrement: Transform<Float> get() = Float::dec
    override val compare: Difference<Float> get() = Float::compareTo
    override val roundToInt: () -> Int get() = value::roundToInt
    override val roundToLong: () -> Long get() = value::roundToLong
    override val sign: W get() = reconstruct(value.sign)
}
