package nz.adjmunro.kty.functions.operators.bitwise

import nz.adjmunro.kty.Boxed
import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.Transform

// TODO: shl, shr, ushr,
@KtyDsl
public interface Invertable<ActualWrapper, BackingField> :
    Boxed<ActualWrapper, BackingField> where
    ActualWrapper : Invertable<ActualWrapper, BackingField>
{
    public val invert: Transform<BackingField>

    public operator fun inv(): ActualWrapper {
        return reconstruct(invert(value))
    }
}
