package com.modeling.orders.controllers;

import com.modeling.orders.models.Agent;
import com.modeling.orders.models.Customer;
import com.modeling.orders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/agents")
@RestController
public class AgentController {
    @Autowired
    AgentServices agentServices;

    //http://localhost:2019/agents/agent/{id}
    @GetMapping(value = "/agent/{agentid}", produces = {"application/json"})
    public ResponseEntity<?> findAgentById(@PathVariable long agentid){
        Agent rtnAgent = agentServices.findAgentById(agentid);
        return new ResponseEntity<>(rtnAgent, HttpStatus.OK);
    }

    //DELETE
    //http://localhost:2019/agents/agent/9
    @DeleteMapping(value = "/agent/{agentid}")
    public ResponseEntity<?> deleteAgentById(@PathVariable long agentid){
        agentServices.delete(agentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
