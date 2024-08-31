package com.example.treeassignment.controller;

import com.example.treeassignment.handler.DateNotinvaledFormatException;
import com.example.treeassignment.handler.InvalidDateRangeException;
import com.example.treeassignment.model.ResponseModel;
import com.example.treeassignment.model.Statement;
import com.example.treeassignment.model.dto.StatementDTO;
import com.example.treeassignment.service.StatementService;
import com.example.treeassignment.service.mapper.StatementMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Statement controller.
 */
@RestController
@RequestMapping("/api/statements")
public class StatementController {
    private StatementService statementService;

    /**
     * Instantiates a new Statement controller.
     *
     * @param statementService the statement service
     */
    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    /**
     * Create statement statement dto.
     *
     * @param statementDTO the statement dto
     * @return the statement dto
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StatementDTO createStatement(@RequestBody StatementDTO statementDTO) {
        Statement statement = StatementMapper.convertToStatement(statementDTO);
        Statement savedStatement = statementService.saveStatement(statement);
        return StatementMapper.convertToStatementDTO(savedStatement);
    }

    /**
     * Gets statement by date.
     *
     * @param accountId the account id
     * @param fromDate  the start date
     * @param toDate    the end date
     * @param min       the min amount
     * @param max       the max amount
     * @return the statement by date
     * @throws DateNotinvaledFormatException the date notinvaled format exception
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Validated
    public ResponseModel<ResponseEntity<List<StatementDTO> >> getStatementByDate (
            @RequestParam Long accountId,
            @Valid @RequestParam(required = false) String fromDate,
            @Valid @RequestParam(required = false) String toDate,
            @Valid @RequestParam(required = false) String min,
            @Valid  @RequestParam(required = false) String max
    ) throws DateNotinvaledFormatException {


        try {
            List<Statement> statements = statementService.findAllByDateFieldBetween(accountId, fromDate, toDate, min, max);
            List<StatementDTO> statementDTOs = StatementMapper.convertToStatementDTOs(statements);
            return new ResponseModel("the Data Retirement", ResponseEntity.ok(statementDTOs).getBody());
        } catch (InvalidDateRangeException ex) {
            return new ResponseModel("400", ResponseEntity.badRequest().body(ex.getMessage()));
        } catch (RuntimeException ex) {
            return new ResponseModel("400", ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage()));
        }

    }

    /**
     * Gets all statements.
     *
     * @param accountId the account id
     * @return the all statements
     */
    @GetMapping("/yourStatements")
    @PreAuthorize("hasRole('USER')")
    public ResponseModel  getAllStatements( @RequestParam Long accountId) {
        List<Statement> statements = statementService.findAllStatements(accountId);
        List<StatementDTO> statementDTOs = StatementMapper.convertToStatementDTOs(statements);
        return new ResponseModel("the Data Retirement", ResponseEntity.ok(statementDTOs).getBody());
    }
}
