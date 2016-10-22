package com.samuelkupferschmid.fhnw.dist;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class TrainerTest {
    @Mock
    Spamfilter filterMock;

    private Trainer trainer;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void Setup(){
        trainer = new Trainer(filterMock,new String[]{"spam"}, new String[]{"ham"});
    }

    @Test
    public void TestCalcPerfTemporarilyThreshold()  {
        when(filterMock.getSpamThreshhold()).thenReturn(0.5);
        trainer.calculatePerformance(new String[0],new String[0],0.4);
        verify(filterMock).setSpamThreshhold(0.4);
        verify(filterMock).setSpamThreshhold(0.5);
    }

    @Test
    public void TrainIteratesNtimes(){
        trainer.train(new String[0],new String[0],3);

        //after validation the performance for test data will be calculated -> 3*validation + 1*test
        verify(filterMock,times(4)).getSpamThreshhold();
    }

    @Test
    public void TestCalcPositivesNegatives() {
        when(filterMock.isSpam("tp")).thenReturn(true);
        when(filterMock.isSpam("fp")).thenReturn(true);

        Performance p = trainer.calculatePerformance(new String[]{"tp"}, new String[]{"fp"},0.5);

        assertEquals(0.5, p.getPrecision(),0);

        when(filterMock.isSpam("tp")).thenReturn(true);
        when(filterMock.isSpam("fn")).thenReturn(false);
        when(filterMock.isSpam("fn")).thenReturn(false);
        when(filterMock.isSpam("fn")).thenReturn(false);

        p = trainer.calculatePerformance(new String[]{"tp","fn","fn","fn"}, new String[]{},0.5);

        assertEquals(0.25, p.getRecall(),0);
    }
}