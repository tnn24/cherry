package com.tnn.test;

import com.tnn.components.account.*;
import com.tnn.components.account.AccountConstant;
import com.tnn.components.RESTPath;
import com.tnn.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService mockService;

    public static Stream<Arguments> validAccountDP() {
        return Stream.of(
                Arguments.of("a", "a", AccountType.TAX),
                Arguments.of("a".repeat(100), "a".repeat(100), AccountType.CASH),
                Arguments.of("a b", "a b", AccountType.INCOME),
                Arguments.of("   a b", "a b", AccountType.BROKERAGE),
                Arguments.of("a b   ", "a b", AccountType.VENDOR)
        );
    }

    @ParameterizedTest
    @MethodSource("validAccountDP")
    public void createSuccess(String name, String expectedName, AccountType type) throws Exception {
        long accountId = 2L;
        Account newAccount = new Account().setName(name).setType(type);
        Account createdAccount = new Account().setId(accountId).setName(expectedName).setType(type);
        when(mockService.create(any(Account.class))).thenReturn(createdAccount);

        mockMvc.perform(post(RESTPath.ACCOUNTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(newAccount)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$." + AccountConstant.JSON_KEY_ID).value(accountId))
                .andExpect(jsonPath("$." + AccountConstant.JSON_KEY_NAME).value(expectedName))
                .andExpect(jsonPath("$." + AccountConstant.JSON_KEY_TYPE).value(type.name()));
    }

    @ParameterizedTest
    @MethodSource("validAccountDP")
    public void updateSuccess(String name, String expectedName, AccountType type) throws Exception {
        long accountId = 10L;
        Account updatedAccount = new Account().setId(accountId).setName(name).setType(type);
        Account expectedAccount = new Account().setId(accountId).setName(expectedName).setType(type);
        when(mockService.replace(updatedAccount, accountId)).thenReturn(expectedAccount);

        mockMvc.perform(put(RESTPath.ACCOUNTS + RESTPath.ID, accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(updatedAccount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$." + AccountConstant.JSON_KEY_ID).value(accountId))
                .andExpect(jsonPath("$." + AccountConstant.JSON_KEY_NAME).value(expectedName))
                .andExpect(jsonPath("$." + AccountConstant.JSON_KEY_TYPE).value(type.name()));
    }

    public static Stream<Arguments> invalidAccountDP() {
        return Stream.of(
                Arguments.of(null, AccountType.CASH, AccountConstant.ERROR_NAME_REQUIRED),
                Arguments.of("", AccountType.CASH, AccountConstant.ERROR_NAME_REQUIRED),
                Arguments.of("a", null, AccountConstant.ERROR_TYPE_REQUIRED),
                Arguments.of("a".repeat(101), AccountType.CASH, AccountConstant.ERROR_NAME_LENGTH_VALID),
                Arguments.of("a-b", AccountType.CASH, AccountConstant.ERROR_NAME_PATTERN_VALID)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidAccountDP")
    public void createFailure(String name, AccountType type, String errorMessage) throws Exception {
        Account newAccount = new Account().setName(name).setType(type);

        mockMvc.perform(post(RESTPath.ACCOUNTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(newAccount)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_STATUS).value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_ERROR_MESSAGE).value(Matchers.containsString(errorMessage)))
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_TIMESTAMP).value(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(ErrorResponse.JSON_TIMESTAMP_FORMAT))));
    }

    @ParameterizedTest
    @MethodSource("invalidAccountDP")
    public void updateFailure(String name, AccountType type, String errorMessage) throws Exception {
        long accountId = 15L;
        Account newAccount = new Account().setId(accountId).setName(name).setType(type);

        mockMvc.perform(put(RESTPath.ACCOUNTS + RESTPath.ID, accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(newAccount)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_STATUS).value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_ERROR_MESSAGE).value(Matchers.containsString(errorMessage)))
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_TIMESTAMP).value(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(ErrorResponse.JSON_TIMESTAMP_FORMAT))));
    }

    @Test
    public void updateFailureWrongId() throws Exception {
        long accountId = 15L;
        long pathId = accountId + 1;
        Account updatedAccount = new Account().setId(accountId).setName("name").setType(AccountType.VENDOR);

        mockMvc.perform(put(RESTPath.ACCOUNTS + RESTPath.ID, pathId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(updatedAccount)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_STATUS).value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_ERROR_MESSAGE)
                        .value(String.format(AccountConstant.ERROR_REPLACE_ID, pathId, accountId)))
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_TIMESTAMP).value(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(ErrorResponse.JSON_TIMESTAMP_FORMAT))));
    }
}