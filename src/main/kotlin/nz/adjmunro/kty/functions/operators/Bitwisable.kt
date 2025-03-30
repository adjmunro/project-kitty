package nz.adjmunro.kty.functions.operators

import nz.adjmunro.kty.functions.KtyDsl
import nz.adjmunro.kty.functions.operators.bitwise.Invertable

@KtyDsl
public interface Bitwisable<ActualWrapper, BackingField> :
    Invertable<ActualWrapper, BackingField> where
    ActualWrapper : Bitwisable<ActualWrapper, BackingField>
