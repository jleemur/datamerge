package datamerge;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/** Unit test for simple App. */
public class UtilsTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public UtilsTest(String testName) {
    super(testName);
  }

  /** @return the suite of tests being tested */
  public static Test suite() {
    return new TestSuite(UtilsTest.class);
  }

  /** Rigourous Test :-) */
  public void testDateEpoch() {
    assert Utils.epochToDate(1467175919322L).equals("2016-06-29 04:51:59 GMT");
  }

  public void testDateADTtoGMT() {
    assert Utils.timezoneToGMT("2016-06-29 07:22:30 ADT").equals("2016-06-29 10:22:30 GMT");
  }

  public void testDateUTCtoGMT() {
    assert Utils.timezoneToGMT("2016-06-29 07:22:30 UTC").equals("2016-06-29 07:22:30 GMT");
  }
}
