package com.example.treeassignment.controller;

import com.example.treeassignment.model.Statement;
import com.example.treeassignment.model.dto.StatementDTO;
import com.example.treeassignment.service.StatementService;
import com.example.treeassignment.service.mapper.StatementMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import java.math.BigDecimal;
import java.time.LocalDate;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The type Statement controller test.
 */
public class StatementControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StatementService statementService;

    @Mock
    private View error;

    @InjectMocks
    private StatementController statementController;

    /**
     * Sets .
     */
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(statementController).build();
    }

    /**
     * Create statement.
     *
     * @throws Exception the exception
     */
    @Test
    public void createStatement() throws Exception {
        // Given
        StatementDTO statementDTO = new StatementDTO();
        statementDTO.setAccountId(1L);
        statementDTO.setDateField(LocalDate.now().toString());
        statementDTO.setAmount(BigDecimal.valueOf(100).toString());

        Statement statement = StatementMapper.convertToStatement(statementDTO);
        Statement savedStatement = new Statement();
        savedStatement.setId(1L);
        savedStatement.setAccount(statement.getAccount());
        savedStatement.setDatefield(statement.getDatefield());
        savedStatement.setAmount(statement.getAmount());

        when(statementService.saveStatement(any(Statement.class))).thenReturn(savedStatement);

        // When & Then
        mockMvc.perform(post("/api/statements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountId\":1,\"dateField\":\"2024-08-31\",\"amount\":\"100\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountId").value(1))
                .andExpect(jsonPath("$.amount").value("100"))
                .andExpect(jsonPath("$.dateField").value(LocalDate.now().toString()));
    }


}