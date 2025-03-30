package nz.adjmunro.kty.functions.operators.bool

import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.Merge
import nz.adjmunro.kty.functions.Mergeable

@KtyDsl
public interface ExclusiveOrable<ActualWrapper, BackingField> :
    Mergeable<ActualWrapper, BackingField> where
    ActualWrapper : ExclusiveOrable<ActualWrapper, BackingField>
{
    public val xor: Merge<BackingField>

    public infix fun xor(other: ActualWrapper): ActualWrapper {
        return merge(other = other, transform = xor)
    }
}
