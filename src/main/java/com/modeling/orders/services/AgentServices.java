package com.modeling.orders.services;

import com.modeling.orders.models.Agent;

public interface AgentServices {

    Agent findAgentById(long id);

    Agent save(Agent agent);

    void deleteAllAgents();

    void delete(long agentid);
}
