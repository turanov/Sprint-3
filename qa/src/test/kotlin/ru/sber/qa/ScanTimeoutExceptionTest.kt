package test.kotlin.ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import ru.sber.qa.ScanTimeoutException

internal class ScanTimeoutExceptionTest {
    private val exceptionTest = mockk<ScanTimeoutException>()

    @Test
    fun test() {
        every { exceptionTest.message } returns "Таймаут сканирования документа"
    }
}