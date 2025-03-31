package nz.adjmunro.kty

import nz.adjmunro.kty.functions.DifferenceAB
import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.MergeABtoA
import nz.adjmunro.kty.functions.operators.Mathable
import nz.adjmunro.kty.functions.operators.NumberMathable
import nz.adjmunro.kty.functions.operators.UnaryMathable
import nz.adjmunro.kty.functions.operators.cast.NumberCastable
import nz.adjmunro.kty.functions.operators.comparable.BoxedComparable
import nz.adjmunro.kty.functions.operators.comparable.NumberComparable
import nz.adjmunro.kty.functions.operators.cast.Specifiable
import nz.adjmunro.kty.functions.operators.comparable.WrapperComparable
import java.math.BigDecimal
import java.math.BigInteger

@KtyDsl
public interface Numbery<ActualWrapper, BackingField> :
    Mathable<ActualWrapper, BackingField>,
    UnaryMathable<ActualWrapper, BackingField>,
    WrapperComparable<ActualWrapper, BackingField>,
    NumberMathable<ActualWrapper, BackingField>,
    NumberComparable<ActualWrapper, BackingField>,
    Specifiable<Number, BackingField>,
    NumberCastable where
    ActualWrapper : Numbery<ActualWrapper, BackingField>,
    BackingField : Number
{
    public val sign: ActualWrapper

    override val asFloat: Float get() = value.toFloat()
    override val asDouble: Double get() = value.toDouble()
    override val asBigDecimal: BigDecimal get() = BigDecimal.valueOf(asDouble)
    override val asInt: Int get() = value.toInt()
    override val asLong: Long get() = value.toLong()
    override val asBigInteger: BigInteger get() = BigInteger.valueOf(asLong)

    override val addByNumber: MergeABtoA<BackingField, Number>
        get() = MergeABtoA { a: BackingField, b: Number -> plus(a, spec(b)) }

    override val subtractByNumber: MergeABtoA<BackingField, Number>
        get() = MergeABtoA { a: BackingField, b: Number -> minus(a, spec(b)) }

    override val multiplyByNumber: MergeABtoA<BackingField, Number>
        get() = MergeABtoA { a: BackingField, b: Number -> times(a, spec(b)) }

    override val divideByNumber: MergeABtoA<BackingField, Number>
        get() = MergeABtoA { a: BackingField, b: Number -> divide(a, spec(b)) }

    override val remainderByNumber: MergeABtoA<BackingField, Number>
        get() = MergeABtoA { a: BackingField, b: Number -> remainder(a, spec(b)) }

    override val compareByNumber: DifferenceAB<BackingField, Number>
        get() = DifferenceAB { a: BackingField, b: Number -> compare(a, spec(b)) }
}
