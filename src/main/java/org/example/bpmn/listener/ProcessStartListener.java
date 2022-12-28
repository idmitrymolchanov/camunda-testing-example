package org.example.bpmn.listener;

import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ProcessStartListener {

  private final RuntimeService runtimeService;

  private static final String DESTINATION_PROCESS_KEY = "example-bpmn";

  public void consumeProcessRequestEvent(UUID businessKey, Map<String, Object> variables) {
    String id = runtimeService.startProcessInstanceByKey(DESTINATION_PROCESS_KEY, businessKey.toString(), variables).getId();
    log.info("start processId {}", id);
  }

}
