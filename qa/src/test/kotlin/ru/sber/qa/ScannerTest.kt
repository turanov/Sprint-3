package test.kotlin.ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.sber.qa.ScanTimeoutException
import ru.sber.qa.Scanner
import kotlin.random.Random

internal class ScannerTest {


    @BeforeEach
    fun initMockk() {
        mockkObject(Random)
    }

    @Test
    fun getScanDataException() {
        every { Random.nextLong(5000L, 15000L) } returns 10001L
        assertThrows<ScanTimeoutException> {
            Scanner.getScanData()
        }
    }

    @Test
    fun getScanData() {
        val data = Random.nextBytes(100)

        every { Random.nextLong(5000L, 15000L) } returns 10000L
        every { Random.nextBytes(100) } returns data

        assertArrayEquals(data, Scanner.getScanData())
    }

    @AfterEach
    fun unmockk() {
        unmockkAll()
    }
}