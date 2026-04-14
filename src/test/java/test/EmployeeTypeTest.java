package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;

import users.EmployeeType;

public class EmployeeTypeTest {

	public EmployeeTypeTest() {}

    @ParameterizedTest
    @CsvSource({
        "FULLTIME, true,  false, false",
        "PARTTIME, false, true,  false",
        "CASUAL,   false, false, true"
    })
    void testTypeChecks(EmployeeType type,
                        boolean isFullTime,
                        boolean isPartTime,
                        boolean isCasual) {

        assertEquals(isFullTime, type.isFullTime());
        assertEquals(isPartTime, type.isPartTime());
        assertEquals(isCasual, type.isCasual());
    }
    
    @ParameterizedTest
    @CsvSource({
        "FULLTIME, FULLTIME, true",
        "FULLTIME, PARTTIME, false",
        "FULLTIME, CASUAL,   false",
        "PARTTIME, PARTTIME, true",
        "CASUAL,   CASUAL,   true"
    })
    void testIsType(EmployeeType base,
                    EmployeeType compare,
                    boolean expected) {

        assertEquals(expected, base.isType(compare));
    }
    
    @ParameterizedTest
    @CsvSource({
        "FULLTIME, 40, 40",
        "PARTTIME, 25, 40",
        "CASUAL,   0,  25"
    })
    void testHourBounds(EmployeeType type,
                        int min,
                        int max) {

        assertEquals(min, type.getMinHours());
        assertEquals(max, type.getMaxHours());
    }
    
    @ParameterizedTest
    @CsvSource({
        "FULLTIME, FULLTIME",
        "fulltime, FULLTIME",
        "PartTime, PARTTIME",
        "casual,   CASUAL"
    })
    void testFromStringValid(String input, EmployeeType expected) {
        assertEquals(expected, EmployeeType.fromString(input));
    }
    
    @ParameterizedTest
    @CsvSource({
        "invalid",
        "manager",
        "123"
    })
    @EmptySource
    void testFromStringInvalid(String input) {
        assertNull(EmployeeType.fromString(input));
    }
}
