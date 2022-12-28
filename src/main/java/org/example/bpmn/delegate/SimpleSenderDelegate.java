package org.example.bpmn.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleSenderDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) {
    log.info("in SimpleReceiverDelegate");
    var globalVariable = execution.getVariable("global_variable");
    log.info("global_variable value: {}", globalVariable);
  }

}