package org.example.utils;

import java.util.Map;
import lombok.experimental.UtilityClass;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricDetail;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.example.utils.common.BpmnTaskConstants;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.processEngine;

@UtilityClass
public class TestUtils {

  private final String PROCESS_DEFINITION_KEY = "example-bpmn";

  public static Object getLocalVariableFromHistory(String processInstanceId, String variableName) {
    // find all camunda activities to get needed delegateExecutionId
    var historyService = processEngine().getHistoryService();
    var historicDetails = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).list();

    // find all delegateExecution's variables to get localVariable and check it
    for(HistoricDetail historicDetail : historicDetails) {
      var historicVariables = historyService.createHistoricVariableInstanceQuery().executionIdIn(historicDetail.getExecutionId()).list();
      for(HistoricVariableInstance historicVariable : historicVariables)
        if(historicVariable.getName().equals(variableName))
          return historicVariable.getValue();
    }
    return null;
  }

  public String createProcessInstance(RuntimeService runtimeService, Map<String, Object> variables, BpmnTaskConstants taskId) {
    return runtimeService.createProcessInstanceByKey(PROCESS_DEFINITION_KEY)
        .startBeforeActivity(taskId.name())
        .setVariables(variables)
        .execute().getId();
  }

}
