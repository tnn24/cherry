package com.cherry.test;

import com.cherry.components.account.Account;
import com.cherry.components.account.AccountController;
import com.cherry.components.account.AccountService;
import com.cherry.components.account.AccountType;
import com.cherry.constants.RESTPaths;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    private static final Gson GSON = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService mockService;

    @Test
    public void createNewAccount() throws Exception {
        String name = "New Account";
        AccountType type = AccountType.VENDOR;
        Account newAccount = new Account().setName(name).setType(type);
        Account createdAccount = new Account().setId(1L).setName(name).setType(type);
        when(mockService.create(any(Account.class))).thenReturn(createdAccount);

        mockMvc.perform(post(RESTPaths.ACCOUNTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(newAccount)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.type").value(type.name()));
    }

}