package nz.adjmunro.kty.functions.operators.comparable

import nz.adjmunro.kty.functions.KtyDsl

@KtyDsl
@Suppress("INAPPLICABLE_JVM_NAME")
public interface WrapperComparable<ActualWrapper, BackingField> :
    BoxedComparable<ActualWrapper, BackingField>,
    Comparable<ActualWrapper> where
    ActualWrapper : WrapperComparable<ActualWrapper, BackingField>
{
    @KtyDsl
    @JvmName("compareToActualWrapper")
    public override fun compareTo(other: ActualWrapper): Int = compare(value, other.value)
}
