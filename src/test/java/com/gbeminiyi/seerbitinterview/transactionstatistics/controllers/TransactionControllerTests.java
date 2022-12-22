package com.gbeminiyi.seerbitinterview.transactionstatistics.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbeminiyi.seerbitinterview.transactionstatistics.commons.Mocker;
import com.gbeminiyi.seerbitinterview.transactionstatistics.services.TransactionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  private TransactionService transactionService;

  @Test
  public void shouldReturn201Created() throws Exception {
    doNothing().when(transactionService).saveTransaction(Mocker.getRequest(""));

    this.mockMvc.perform(
        post("/transaction")
          .content(objectMapper.writeValueAsString(Mocker.getRequest("")))
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isCreated());
  }

  @Test
  public void shouldReturn400BadRequest() throws Exception {
    doNothing().when(transactionService).saveTransaction(Mocker.getRequest(""));

    this.mockMvc.perform(post("/transaction"))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnStatistics() throws Exception {
    when(transactionService.getStatistics())
      .thenReturn(Mocker.getStatistics());

    this.mockMvc.perform(get("/transaction"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.max", Matchers.is("12.33")));
  }

  @Test
  public void shouldDeleteTransactions() throws Exception {
    doNothing().when(transactionService).deleteAll();

    this.mockMvc.perform(delete("/transaction"))
      .andExpect(status().isNoContent());
  }
}
