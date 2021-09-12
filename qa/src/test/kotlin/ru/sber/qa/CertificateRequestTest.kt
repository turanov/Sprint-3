package test.kotlin.ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sber.qa.CertificateRequest
import ru.sber.qa.CertificateType
import ru.sber.qa.ScanTimeoutException
import ru.sber.qa.Scanner
import kotlin.random.Random

internal class CertificateRequestTest {
    private var employeeNumber = 1L
    private var certificateType = mockk<CertificateType>()
    private var certificateRequest = CertificateRequest(employeeNumber, certificateType)

    @Test
    fun process() {
        val hrEmployeeNumber = 123L
        val data = Random.nextBytes(100)
        mockkObject(Scanner)

        every { Scanner.getScanData() } returns data

        val certificate = certificateRequest.process(hrEmployeeNumber)

        every { Scanner.getScanData() } throws ScanTimeoutException()
        assertEquals(data, certificate.data)
        assertEquals(certificateRequest, certificate.certificateRequest)
        assertEquals(hrEmployeeNumber, certificate.processedBy)
        unmockkAll()
    }

    @Test
    fun getEmployeeNumber() {
        assertEquals(employeeNumber, certificateRequest.employeeNumber)
    }

    @Test
    fun getCertificateType() {
        assertEquals(certificateType, certificateRequest.certificateType)
    }
}