package test.kotlin.ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import ru.sber.qa.NotAllowReceiveRequestException

internal class NotAllowReceiveRequestExceptionTest {
    private val exceptionTest = mockk<NotAllowReceiveRequestException>()

    @Test
    fun test() {
        every { exceptionTest.message } returns "Не разрешено принять запрос на справку"
    }
}