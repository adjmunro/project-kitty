package nz.adjmunro.kty.functions

import nz.adjmunro.kty.Boxed
import nz.adjmunro.kty.functions.operators.Boolable
import nz.adjmunro.kty.functions.operators.comparable.BoxedComparable

/**
 * A [Booleany] is a [Boxed] type that represents a boolean value.
 *
 * *I honestly don't know why you would want to use this, but it's here and it works.*
 *
 * ```kotlin
 * // Sealed Inline Example  (I assume this is the thinnest implementation?)
 * sealed interface InlineAnswer: Booleany<InlineAnswer> {
 *     override val reconstruct: (Boolean) -> InlineAnswer get() = { if (it) Yes() else No() }
 *     @JvmInline value class Yes(override val value: Boolean = true) : InlineAnswer
 *     @JvmInline value class No(override val value: Boolean = false): InlineAnswer
 * }
 * // You could also try data objects, or even data classes if you want additional instance data.
 *
 * // Enum Example - Ought to have a similar overhead to sealed data objects?
 * enum class EnumAnswer: Booleany<EnumAnswer> {
 *     YES, NO;
 *     // Of course, you can just assign value directly, but i find this interesting.
 *     override val value: Boolean get() = this == YES
 *     override val reconstruct: (Boolean) -> EnumAnswer get() = { if (it) YES else NO }
 * }
 * ```
 *
 * > **WARNING:** While compareTo [ActualWrapper][W] is not available due to a multi-inheritance
 * > conflict with enums, caution is advised when extending *with* enums, as [compareTo]
 * > depends on [Enum.ordinal] number, not [Boxed.value]!
 */
public interface Booleany<W: Booleany<W>> :
    BoxedComparable<W, Boolean>,
    Boolable<W, Boolean>
{
    override val value: Boolean
    override val not: Transform<Boolean> get() = Boolean::not
    override val and: Merge<Boolean> get() = Boolean::and
    override val or: Merge<Boolean> get() = Boolean::or
    override val xor: Merge<Boolean> get() = Boolean::xor
    override val compare: Difference<Boolean> get() = Boolean::compareTo
}
