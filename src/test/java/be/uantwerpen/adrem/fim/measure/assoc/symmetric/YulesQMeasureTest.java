package be.uantwerpen.adrem.fim.measure.assoc.symmetric;

import static be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureTests.delta;
import static java.lang.Double.NaN;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureTestBase;

/**
 * @author Sandy Moens
 * @author Emin Aksehirli
 */
public class YulesQMeasureTest extends RuleMeasureTestBase {

	private YulesQMeasure m;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		m = new YulesQMeasure(db);
	}

	@Override
	@Test
	public void test_empty_empty() {
		assertEquals(NaN, m.evaluate(sEmpty, sEmpty), delta);
	}

	@Override
	@Test
	public void test_empty_set() {
		assertEquals(NaN, m.evaluate(sEmpty, s0), delta);
		assertEquals(NaN, m.evaluate(sEmpty, s01), delta);
		assertEquals(NaN, m.evaluate(sEmpty, s012), delta);
		assertEquals(NaN, m.evaluate(sEmpty, s47), delta);
		assertEquals(NaN, m.evaluate(sEmpty, s56), delta);
	}

	@Override
	@Test
	public void test_set_empty() {
		assertEquals(NaN, m.evaluate(s0, sEmpty), delta);
		assertEquals(NaN, m.evaluate(s01, sEmpty), delta);
		assertEquals(NaN, m.evaluate(s012, sEmpty), delta);
		assertEquals(NaN, m.evaluate(s47, sEmpty), delta);
		assertEquals(NaN, m.evaluate(s56, sEmpty), delta);
	}

	@Override
	@Test
	public void test_set_set_equal() {
		assertEquals(1.0, m.evaluate(s0, s0), delta);
		assertEquals(1.0, m.evaluate(s01, s01), delta);
		assertEquals(1.0, m.evaluate(s012, s012), delta);
		assertEquals(1.0, m.evaluate(s47, s47), delta);
		assertEquals(1.0, m.evaluate(s56, s56), delta);
	}

	@Override
	@Test
	public void test_set_set_closed() {
		assertEquals(1.0, m.evaluate(s0, s01), delta);
		assertEquals(1.0, m.evaluate(s01, s0), delta);
	}

	@Override
	@Test
	public void test_set_set_include() {
		assertEquals(1.0, m.evaluate(s0, s012), delta);
		assertEquals(1.0, m.evaluate(s01, s012), delta);

		assertEquals(1.0, m.evaluate(s012, s0), delta);
		assertEquals(1.0, m.evaluate(s012, s01), delta);
	}

	@Override
	@Test
	public void test_set_set_overlap() {
		assertEquals(-1.0 / 3.0, m.evaluate(s0, s56), delta);

		assertEquals(-1.0 / 3.0, m.evaluate(s01, s56), delta);

		assertEquals(-1.0 / 3.0, m.evaluate(s56, s0), delta);
		assertEquals(-1.0 / 3.0, m.evaluate(s56, s01), delta);
	}

	@Override
	@Test
	public void test_set_set_no_overlap() {
		assertEquals(-1.0, m.evaluate(s0, s47), delta);

		assertEquals(-1.0, m.evaluate(s01, s47), delta);

		assertEquals(-1.0, m.evaluate(s012, s47), delta);
		assertEquals(-1.0, m.evaluate(s012, s56), delta);

		assertEquals(-1.0, m.evaluate(s47, s0), delta);
		assertEquals(-1.0, m.evaluate(s47, s01), delta);
		assertEquals(-1.0, m.evaluate(s47, s012), delta);
		assertEquals(-1.0, m.evaluate(s47, s56), delta);

		assertEquals(-1.0, m.evaluate(s56, s012), delta);
		assertEquals(-1.0, m.evaluate(s56, s47), delta);
	}
}
