package test.kotlin.ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.sber.qa.*
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

internal class HrDepartmentTest {

    private val certificate = mockk<Certificate>()
    private val certificateRequest = mockk<CertificateRequest>()
    private val hrDepartment = mockk<HrDepartment>()
    private val hrEmployeeNumber = 123L

    @Test
    fun receiveRequestLabourBookWeekendDayException() {
        every { hrDepartment.clock } returns Clock.fixed(Instant.parse("2021-09-11T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK
        assertThrows(WeekendDayException::class.java) { HrDepartment.receiveRequest(certificateRequest) }
    }

    @Test
    fun receiveRequestNdflWeekendDayException() {
        every { hrDepartment.clock } returns Clock.fixed(Instant.parse("2021-09-11T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        assertThrows(WeekendDayException::class.java) { HrDepartment.receiveRequest(certificateRequest) }
    }

    @Test
    fun receiveRequestLabourBookNotAllowReceiveRequestException() {
        // request on monday
        every { hrDepartment.clock } returns Clock.fixed(Instant.parse("2021-09-13T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK
        assertThrows<NotAllowReceiveRequestException> {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun receiveRequestNdflNotAllowReceiveRequestException() {
        // request on tuesday
        every { hrDepartment.clock } returns Clock.fixed(Instant.parse("2021-09-14T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        assertThrows<NotAllowReceiveRequestException> {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun processNextRequestShouldNotGiveThrow() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-13T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate

        HrDepartment.receiveRequest(certificateRequest)

        assertDoesNotThrow {
            HrDepartment.processNextRequest(hrEmployeeNumber)
        }
    }

}