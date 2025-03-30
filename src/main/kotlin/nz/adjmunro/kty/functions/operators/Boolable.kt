package nz.adjmunro.kty.functions.operators

import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.operators.bool.Andable
import nz.adjmunro.kty.functions.operators.bool.ExclusiveOrable
import nz.adjmunro.kty.functions.operators.bool.InclusiveOrable
import nz.adjmunro.kty.functions.operators.bool.Nottable

@KtyDsl
public interface Boolable<ActualWrapper, BackingField> :
    Andable<ActualWrapper, BackingField>,
    InclusiveOrable<ActualWrapper, BackingField>,
    ExclusiveOrable<ActualWrapper, BackingField>,
    Nottable<ActualWrapper, BackingField> where
    ActualWrapper : Boolable<ActualWrapper, BackingField>
