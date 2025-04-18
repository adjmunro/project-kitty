package nz.adjmunro.kty.functions

/** Represents a type that knows how to [reconstruct] itself. */
@KtyDsl
public interface Reconstructable<ActualWrapper, BackingField> where
    ActualWrapper : Reconstructable<ActualWrapper, BackingField> {

    /**
     * Function reference to the constructor of the value class.
     *
     * ***You need to override this** in the value class itself, so that its
     * parent interfaces know how to create an instance of the child type.*
     *
     * ```kotlin
     * @JvmInline
     * value class Bananas(override val value: Double) : Boxed<Bananas, Double> {
     *     // Value classes can't have other backing fields, so accessor syntax is used.
     *     override val reconstruct: (Double) -> Bananas get() = ::ValueClass
     * }
     * ```
     */
    @KtyDsl
    public val reconstruct: (BackingField) -> ActualWrapper
}
