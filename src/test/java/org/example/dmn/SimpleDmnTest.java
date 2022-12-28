package org.example.dmn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.io.InputStream;
import java.util.Map;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.test.DmnEngineRule;
import org.junit.Rule;
import org.junit.Test;

/**camunda dmn test example*/
public class SimpleDmnTest {

  @Rule
  public DmnEngineRule rule = new DmnEngineRule();

  @Test
  public void evaluateDecision_testGetBooleanResult_CorrectResultReturned() {
    DmnEngine dmnEngine = rule.getDmnEngine();

    checkDecision(dmnEngine, true, createVariables("first_value", 1));
    checkDecision(dmnEngine, false, createVariables("second_value", 2));
    checkDecision(dmnEngine, false, createVariables("third_value", 3));
  }

  private void checkDecision(DmnEngine dmnEngine, boolean expectedResult, Map<String, Object> variables) {
    var results = dmnEngine.evaluateDecisionTable(getDmnDecision(dmnEngine), variables);
    assertThat(results).hasSize(1);
    assertThat(results.getSingleResult()).containsOnly(entry("booleanVariable", expectedResult));
  }

  private Map<String, Object> createVariables(String stringVariable, Integer intVariable) {
    return Map.of(
        "stringVariable", stringVariable,
        "intVariable", intVariable
    );
  }

  private DmnDecision getDmnDecision(DmnEngine dmnEngine) {
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("dmn/example.dmn");
    return dmnEngine.parseDecision("EXAMPLE_DECISION", inputStream);
  }

}