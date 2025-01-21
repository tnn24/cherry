package com.cherry.test;

import com.cherry.components.transaction.TransactionService;
import com.google.gson.Gson;
import com.cherry.components.transaction.TransactionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    private static final Gson GSON = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService mockService;

    @Test
    public void createNewTransaction() throws Exception {

    }
}