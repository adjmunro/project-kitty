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
public interface Doubly<W : Doubly<W>> : Numbery<W, Double>, RoundCastable {
    override val value: Double
    override val spec: (Number) -> Double get() = Number::toDouble
    override val plus: Merge<Double> get() = Double::plus
    override val minus: Merge<Double> get() = Double::minus
    override val times: Merge<Double> get() = Double::times
    override val divide: Merge<Double> get() = Double::div
    override val remainder: Merge<Double> get() = Double::rem
    override val absolute: Transform<Double> get() = Double::absoluteValue
    override val negate: Transform<Double> get() = Double::unaryMinus
    override val increment: Transform<Double> get() = Double::inc
    override val decrement: Transform<Double> get() = Double::dec
    override val compare: Difference<Double> get() = Double::compareTo
    override val roundToInt: () -> Int get() = value::roundToInt
    override val roundToLong: () -> Long get() = value::roundToLong
    override val sign: W get() = reconstruct(value.sign)
}
