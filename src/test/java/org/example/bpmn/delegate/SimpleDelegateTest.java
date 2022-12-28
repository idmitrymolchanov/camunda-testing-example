package org.example.bpmn.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**camunda mock test example*/
@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SimpleDelegateTest {

  @Autowired
  private SimpleDelegate delegate;

  @Test
  public void test() {
    UUID applicationId = UUID.fromString("5f63c102-a617-401f-a115-402f94667b3c");

    DelegateExecution execution = mock(DelegateExecution.class);
    when(execution.getCurrentActivityId()).thenReturn(UUID.randomUUID().toString());
    when(execution.getBusinessKey()).thenReturn(UUID.randomUUID().toString());
    when(execution.getVariable("applicationId")).thenReturn(applicationId);

    delegate.execute(execution);
    assertEquals(execution.getVariable("applicationId"), applicationId);
  }

}