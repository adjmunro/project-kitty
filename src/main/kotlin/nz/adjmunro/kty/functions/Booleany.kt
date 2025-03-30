package nz.adjmunro.kty.functions

import nz.adjmunro.kty.functions.operators.Boolable
import nz.adjmunro.kty.functions.operators.comparable.BoxedComparable

public interface Booleany<W: Booleany<W>> :
    BoxedComparable<W, Boolean>,
    Boolable<W, Boolean>
{
    override val value: Boolean
    override val not: Transform<Boolean> get() = Boolean::not
    override val and: Merge<Boolean> get() = Boolean::and
    override val or: Merge<Boolean> get() = Boolean::or
    override val xor: Merge<Boolean> get() = Boolean::xor
    override val compareToSelf: Difference<Boolean> get() = Boolean::compareTo
}

// todo think more about how you would use this?
//public sealed interface XO: Booleany<XO> {
//    public data object X: XO {
//        override val value: Boolean
//            get() = false
//
//        override val reconstruct: (Boolean) -> XO
//            get() = X
//    }
//    public data object O: XO
//}
//
//public enum class Xo(override val value: Boolean) : Booleany<Xo> {
//    X(true),
//    O(false);
//}
