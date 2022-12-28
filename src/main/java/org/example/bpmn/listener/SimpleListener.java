package org.example.bpmn.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleListener implements ExecutionListener {

  private static final String EXAMPLE_LOCAL_VARIABLE = "exampleLocalVariable";
  private static final String EXAMPLE_GLOBAL_VARIABLE = "exampleGlobalVariable";

  @Override
  public void notify(DelegateExecution execution) {

    execution.getVariable("applicationId");
    execution.setVariable(EXAMPLE_GLOBAL_VARIABLE, "exampleGlobalVariableValue");
    execution.setVariableLocal(EXAMPLE_LOCAL_VARIABLE, "exampleLocalVariableValue");
    log.info("set {}", EXAMPLE_LOCAL_VARIABLE);

  }

}