package org.example.controller;

import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.bpmn.listener.ProcessStartListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {

  private final ProcessStartListener processStartListener;

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(path = "/process/start", consumes = {MediaType.APPLICATION_JSON_VALUE})
  public void startProcess(@RequestParam UUID businessKey, @RequestBody Map<String, Object> variables) {
    processStartListener.consumeProcessRequestEvent(businessKey, variables);
  }

}