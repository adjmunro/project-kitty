package nz.adjmunro.kty.functions.operators.bool

import nz.adjmunro.kty.Boxed
import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.Transform

@KtyDsl
public interface Nottable<ActualWrapper, BackingField> :
    Boxed<ActualWrapper, BackingField> where
    ActualWrapper : Nottable<ActualWrapper, BackingField>
{
    public val not: Transform<BackingField>

    @KtyDsl
    public operator fun not(): ActualWrapper {
        return reconstruct(not(value))
    }
}
