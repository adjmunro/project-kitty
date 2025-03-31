package nz.adjmunro.kty

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.types.shouldBeInstanceOf
import nz.adjmunro.kty.BooleanyTest.EnumAnswer.YES
import nz.adjmunro.kty.BooleanyTest.EnumAnswer.NO
import nz.adjmunro.kty.BooleanyTest.InlineAnswer.No
import nz.adjmunro.kty.BooleanyTest.InlineAnswer.Yes
import nz.adjmunro.kty.functions.Booleany
import kotlin.test.Test

class BooleanyTest {

    private sealed interface InlineAnswer: Booleany<InlineAnswer> {
        override val reconstruct: (Boolean) -> InlineAnswer get() = { if (it) Yes() else No() }
        @JvmInline value class Yes(override val value: Boolean = true) : InlineAnswer
        @JvmInline value class No(override val value: Boolean = false): InlineAnswer
    }

    enum class EnumAnswer: Booleany<EnumAnswer> {
        YES, NO;
        override val reconstruct: (Boolean) -> EnumAnswer get() = { if (it) YES else NO }
        override val value: Boolean get() = this == YES
    }

    @Test
    fun `create booleany instance`() {
        // When
        val yes: InlineAnswer = Yes()
        val no: InlineAnswer = No()

        // Then
        yes.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        no.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
    }

    @Test
    fun `create enum booleany instance`() {
        // When
        val yes: EnumAnswer = YES
        val no: EnumAnswer = NO

        // Then
        yes.shouldBeEqual(YES).value.shouldBeTrue()
        no.shouldBeEqual(NO).value.shouldBeFalse()
    }

    @Test
    fun `booleany instance should be comparable with bool`() {
        // Given
        val yes: InlineAnswer = Yes()
        val no: EnumAnswer = NO

        // When
        val a = yes > false
        val b = yes < false
        val c = yes >= true
        val d = yes <= true
        val e = no > true
        val f = no < true
        val g = no >= false
        val h = no <= false
        val i = no >= YES

        // Then
        a.shouldBeTrue()
        b.shouldBeFalse()
        c.shouldBeTrue()
        d.shouldBeTrue()
        e.shouldBeFalse()
        f.shouldBeTrue()
        g.shouldBeTrue()
        h.shouldBeTrue()
        i.shouldBeTrue()
    }

    @Test
    fun `booleany instance should be negatable`() {
        // Given
        val yes: InlineAnswer = Yes()
        val no: InlineAnswer = No()
        val enum = NO

        // When
        val a = !!!yes
        val b = !no
        val c = !!yes
        val d = yes.not()
        val e = no.not()
        val f = !enum

        // Then
        a.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        b.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        c.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        d.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        e.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        f.shouldBeEqual(YES).value.shouldBeTrue()
    }

    @Test
    fun `booleany instance should be and-able`() {
        // Given
        val yes: InlineAnswer = Yes()
        val no: InlineAnswer = No()

        // When
        val a = yes and no
        val b = yes and yes
        val c = no and no
        val d = no and yes

        // Then
        a.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        b.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        c.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        d.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
    }

    @Test
    fun `booleany instance should be and-able with bool`() {
        // Given
        val yes: InlineAnswer = Yes()
        val no: InlineAnswer = No()

        // When
        val a = yes and false
        val b = yes and true
        val c = no and false
        val d = no and true

        // Then
        a.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        b.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        c.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        d.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
    }

    @Test
    fun `booleany instance should be or-able`() {
        // Given
        val yes: InlineAnswer = Yes()
        val no: InlineAnswer = No()

        // When
        val a = yes or no
        val b = yes or yes
        val c = no or no
        val d = no or yes

        // Then
        a.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        b.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        c.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        d.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
    }

    @Test
    fun `booleany instance should be or-able with bool`() {
        // Given
        val yes: InlineAnswer = Yes()
        val no: InlineAnswer = No()

        // When
        val a = yes or false
        val b = yes or true
        val c = no or false
        val d = no or true

        // Then
        a.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        b.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        c.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        d.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
    }

    @Test
    fun `booleany instance should be xor-able`() {
        // Given
        val yes: InlineAnswer = Yes()
        val no: InlineAnswer = No()

        // When
        val a = yes xor no
        val b = yes xor yes
        val c = no xor no
        val d = no xor yes

        // Then
        a.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        b.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        c.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        d.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
    }

    @Test
    fun `booleany instance should be xor-able with bool`() {
        // Given
        val yes: InlineAnswer = Yes()
        val no: InlineAnswer = No()

        // When
        val a = yes xor false
        val b = yes xor true
        val c = no xor false
        val d = no xor true

        // Then
        a.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
        b.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        c.shouldBeInstanceOf<No> { it.value.shouldBeFalse() }
        d.shouldBeInstanceOf<Yes> { it.value.shouldBeTrue() }
    }
}
