package com.myprojects.pauldirac.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.myprojects.pauldirac.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PatchObjectUtil {

    private final ObjectMapper objectMapper;

    public PatchObjectUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Employee apply(Map<String, Object> patchPayload, Employee tempEmployee) {

        ObjectNode patchPayloadNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
        ObjectNode tempEmployeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        tempEmployeeNode.setAll(patchPayloadNode);
        return objectMapper.convertValue(tempEmployeeNode, Employee.class);
    }
}
