package test.kotlin.ru.sber.qa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sber.qa.CertificateType

internal class CertificateTypeTest {
    @Test
    fun ndfl() {
        assertEquals("NDFL", CertificateType.NDFL.name)
    }

    @Test
    fun labourBook() {
        assertEquals("LABOUR_BOOK", CertificateType.LABOUR_BOOK.name)
    }

    @Test
    fun size() {
        assertEquals(2, CertificateType.values().size)
    }
}