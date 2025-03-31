package nz.adjmunro.kty

import nz.adjmunro.kty.functions.Difference
import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.Merge
import nz.adjmunro.kty.functions.MergeABtoA
import nz.adjmunro.kty.functions.Transformable
import nz.adjmunro.kty.functions.operators.comparable.BoxedComparable
import nz.adjmunro.kty.functions.operators.comparable.WrapperComparable
import nz.adjmunro.kty.functions.operators.plus.Addable
import nz.adjmunro.kty.functions.operators.plus.AnyAddable

@KtyDsl
public interface Stringy<W : Stringy<W>> :
    WrapperComparable<W, String>,
    CharSequence
{
    /** @see String.compareTo */
    override val compare: Difference<String>
        get() = String::compareTo

    /** @see CharSequence.length */
    override val length: Int
        get() = value.length

    /** @see CharSequence.get */
    override operator fun get(index: Int): Char = value[index]

    /** @see CharSequence.subSequence */
    override fun subSequence(startIndex: Int, endIndex: Int): CharSequence =
        value.subSequence(startIndex, endIndex)

}
