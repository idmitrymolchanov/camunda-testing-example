package org.example.bpmn.listener;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.example.utils.common.BpmnTaskConstants.SIMPLE_SENDER_TASK;
import static org.example.utils.common.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.UUID;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.example.bpmn.delegate.SimpleSenderDelegate;
import org.example.config.CamundaTestRuleConfig;
import org.example.utils.TestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**camunda integration test example*/
public class SimpleListenerTest extends CamundaTestRuleConfig {

  @Autowired
  private SimpleListener listener;
  @Autowired
  private SimpleSenderDelegate senderDelegate;

  @Test
  @Deployment(resources = {"bpm/example.bpmn"})
  public void test() {
    init(rule.getProcessEngine());
    Mocks.register("simpleListener", listener);
    Mocks.register("simpleSenderDelegate", senderDelegate);

    var runtimeService = runtimeService();
    var processInstanceId = TestUtils.createProcessInstance(runtimeService, createVariables(), SIMPLE_SENDER_TASK);
    var exampleLocalVariable = TestUtils.getLocalVariableFromHistory(processInstanceId, EXAMPLE_LOCAL_VARIABLE);

    assert exampleLocalVariable != null;
    assertEquals(EXAMPLE_LOCAL_VARIABLE_VALUE, exampleLocalVariable.toString());
    assertEquals(EXAMPLE_GLOBAL_VARIABLE_VALUE, runtimeService.getVariable(processInstanceId, EXAMPLE_GLOBAL_VARIABLE));
  }

  private Map<String, Object> createVariables() {
    return Map.of(
        APPLICATION_ID, UUID.randomUUID()
    );
  }

}