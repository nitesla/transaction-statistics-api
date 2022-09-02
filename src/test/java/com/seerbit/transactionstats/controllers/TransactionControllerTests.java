package com.seerbit.transactionstats.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seerbit.transactionstats.commons.Mocker;
import com.seerbit.transactionstats.services.TransactionService;

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
        post("/transactions")
          .content(objectMapper.writeValueAsString(Mocker.getRequest("")))
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isCreated());
  }

  @Test
  public void shouldReturn400BadRequest() throws Exception {
    doNothing().when(transactionService).saveTransaction(Mocker.getRequest(""));

    this.mockMvc.perform(post("/transactions"))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnStatistics() throws Exception {
    when(transactionService.getStatistics())
      .thenReturn(Mocker.getStatistics());

    this.mockMvc.perform(get("/statistics"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.max", Matchers.is("12.33")));
  }

  @Test
  public void shouldDeleteTransactions() throws Exception {
    doNothing().when(transactionService).deleteAll();

    this.mockMvc.perform(delete("/transactions"))
      .andExpect(status().isNoContent());
  }
}
