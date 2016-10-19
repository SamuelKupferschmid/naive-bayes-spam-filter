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
        trainer.calculatePerformance(0.4);
        verify(filterMock).setSpamThreshhold(0.4);
        verify(filterMock).setSpamThreshhold(0.5);
    }
}