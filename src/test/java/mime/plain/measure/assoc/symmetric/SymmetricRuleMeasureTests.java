package mime.plain.measure.assoc.symmetric;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CosineMeasureTest.class, JaccardMeasureTest.class,
		KulcinskyMeasureTest.class, LiftMeasureTest.class,
		OddsRatioMeasureTest.class, RelativeOverlapMeasureTest.class,
		YulesQMeasureTest.class, YulesYMeasureTest.class })
public class SymmetricRuleMeasureTests {

}