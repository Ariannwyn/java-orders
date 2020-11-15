package com.modeling.orders.services;

import com.modeling.orders.models.Agent;
import com.modeling.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices{
    @Autowired
    AgentRepository agentrepos;

    @Override
    public Agent findAgentById(long id) {
        return agentrepos
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + id +" Not Found"));
    }

    @Transactional
    @Override
    public Agent save(Agent agent){
        return agentrepos.save(agent);
    }

    @Transactional
    @Override
    public void deleteAllAgents() {
        agentrepos.deleteAll();
    }

    @Transactional
    @Override
    public void delete(long agentid) {
        if (agentrepos.findById(agentid).isPresent()){
            agentrepos.deleteById(agentid);
        }else
        {
            throw new EntityNotFoundException("Agent "+agentid+" Not Found");
        }
    }
}
