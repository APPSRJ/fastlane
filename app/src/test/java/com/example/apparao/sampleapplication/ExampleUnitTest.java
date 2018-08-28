package com.example.apparao.sampleapplication;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private static final String FAKE_STRING = "hello world";

    @Mock
    Context mMockContext;
    ClassUnderTest myObjectUnderTest;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        myObjectUnderTest = new ClassUnderTest(mMockContext);
    }



    @Test
    public void readStringFromContext_LocalizedString() {

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getHelloWorldString();

        // ...then the result should be the expected one.
        assertThat(result,is(FAKE_STRING));
    }

    class ClassUnderTest{
        private final Context context;

        public ClassUnderTest(Context context){
            this.context=context;
        }

        public String getHelloWorldString() {
            return context.getString(R.string.hello_world);
        }
    }
}