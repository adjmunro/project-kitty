package nz.adjmunro.kty.functions.operators.bool

import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.Merge
import nz.adjmunro.kty.functions.Mergeable

@KtyDsl
public interface InclusiveOrable<ActualWrapper, BackingField> :
    Mergeable<ActualWrapper, BackingField> where
    ActualWrapper : InclusiveOrable<ActualWrapper, BackingField>
{
    public val or: Merge<BackingField>

    @KtyDsl
    public infix fun or(other: ActualWrapper): ActualWrapper {
        return merge(other = other, transform = or)
    }

    @KtyDsl
    public infix fun or(other: BackingField): ActualWrapper {
        return reconstruct(or(value, other))
    }
}
