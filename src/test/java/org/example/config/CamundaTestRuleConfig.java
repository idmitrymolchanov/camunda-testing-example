package org.example.config;

import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.example.Application;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.camunda.spin.plugin.impl.SpinProcessEnginePlugin;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CamundaTestRuleConfig {

  @Rule
  public final ProcessEngineRule rule = new StandaloneInMemoryTestConfiguration()
  {
    {
      getProcessEnginePlugins().add(new SpinProcessEnginePlugin());
    }
  }.rule();

}