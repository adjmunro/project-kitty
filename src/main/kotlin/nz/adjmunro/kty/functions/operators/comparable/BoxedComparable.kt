package nz.adjmunro.kty.functions.operators.comparable

import nz.adjmunro.kty.Boxed
import nz.adjmunro.kty.functions.Difference
import nz.adjmunro.kty.functions.KtyDsl

/**
 * A [Boxed] type that can compare to [itself][ActualWrapper], or its [field type][BackingField].
 *
 * *Only comparing against the [ActualWrapper] inherits from [Comparable],
 * as comparing to oneself is seen as default behaviour.*
 */
@KtyDsl
@Suppress("INAPPLICABLE_JVM_NAME")
public interface BoxedComparable<ActualWrapper, BackingField> :
    Boxed<ActualWrapper, BackingField> where
    ActualWrapper : BoxedComparable<ActualWrapper, BackingField>
{
    @KtyDsl
    public val compare: Difference<BackingField>

    @KtyDsl
    @JvmName("compareToBackingField")
    public operator fun compareTo(other: BackingField): Int = compare(value, other)
}
