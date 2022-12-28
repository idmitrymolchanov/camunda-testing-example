package org.example.bpmn.delegate;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) {
    log.info("in SimpleDelegate");
    UUID applicationId = (UUID) execution.getVariable("applicationId");
    assert applicationId != null;
  }

}