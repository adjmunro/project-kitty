package nz.adjmunro.kty.functions.operators.bool

import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.Merge
import nz.adjmunro.kty.functions.Mergeable

@KtyDsl
public interface Andable<ActualWrapper, BackingField> :
    Mergeable<ActualWrapper, BackingField> where
    ActualWrapper : Andable<ActualWrapper, BackingField>
{
    public val and: Merge<BackingField>

    @KtyDsl
    public infix fun and(other: ActualWrapper): ActualWrapper {
        return merge(other = other, transform = and)
    }

    @KtyDsl
    public infix fun and(other: BackingField): ActualWrapper {
        return reconstruct(and(value, other))
    }
}
