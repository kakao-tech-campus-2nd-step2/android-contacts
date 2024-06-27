package campus.tech.kakao.contacts

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AddContactActivityInstrumentedTest {

    @get:Rule
    var rule: ActivityScenarioRule<AddContactActivity> = ActivityScenarioRule(
        AddContactActivity::class.java
    )
    @Test
    fun contactActivityValidateBirthdayTest() {

        rule.scenario.onActivity {
            assertEquals(true, it.getBirthdayFromText("2010-01-01") != null)
            assertEquals(false, it.getBirthdayFromText("2010-01-1") != null)
            assertEquals(false, it.getBirthdayFromText("2010-1-100") != null)
            assertEquals(false, it.getBirthdayFromText("2010-13-10") != null)
            assertEquals(true, it.getBirthdayFromText("4012-11-15") != null)
        }
    }

    @Test
    fun contactActivityValidateEmailTest(){
        rule.scenario.onActivity {
            assertEquals(it.verifyEmail("ksc1008@naver.com"),true)
            assertEquals(it.verifyEmail("test@tt.net"),true)
            assertEquals(it.verifyEmail("wrong.domain"),false)
            assertEquals(it.verifyEmail("wrong@domain"),false)
        }
    }

    @Test
    fun contactActivityValidateInputTest(){
        Espresso.onView(ViewMatchers.withId(R.id.input_name))
            .perform(ViewActions.typeText("test"))

        rule.scenario.onActivity {
            assertEquals(it.validateInputs(),false)
        }

        Espresso.onView(ViewMatchers.withId(R.id.input_tel))
            .perform(ViewActions.typeText("555555"))

        rule.scenario.onActivity {
            assertEquals(it.validateInputs(),true)
        }
    }
}