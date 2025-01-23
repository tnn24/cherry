package com.cherry.test;

import com.cherry.components.account.*;
import com.cherry.constants.RESTPaths;
import com.cherry.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        Account newAccount = new Account().setName(name).setType(type);
        Account createdAccount = new Account().setId(1L).setName(expectedName).setType(type);
        when(mockService.create(any(Account.class))).thenReturn(createdAccount);

        mockMvc.perform(post(RESTPaths.ACCOUNTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(newAccount)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$." + AccountConstant.JSON_KEY_ID).value(1L))
                .andExpect(jsonPath("$." + AccountConstant.JSON_KEY_NAME).value(expectedName))
                .andExpect(jsonPath("$." + AccountConstant.JSON_KEY_TYPE).value(type.name()));
    }

    public static Stream<Arguments> invalidAccountDP() {
        return Stream.of(
                Arguments.of(null, AccountType.CASH, AccountConstant.ERROR_NAME_REQUIRED),
                Arguments.of("", AccountType.CASH,
                        String.join(" ", AccountConstant.ERROR_NAME_PATTERN_VALID,
                        AccountConstant.ERROR_NAME_LENGTH_VALID, AccountConstant.ERROR_NAME_REQUIRED)),
                Arguments.of("a", null, AccountConstant.ERROR_TYPE_REQUIRED),
                Arguments.of("a".repeat(101), AccountType.CASH, AccountConstant.ERROR_NAME_LENGTH_VALID),
                Arguments.of("a-b", AccountType.CASH, AccountConstant.ERROR_NAME_PATTERN_VALID)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidAccountDP")
    public void createFailure(String name, AccountType type, String errorMessage) throws Exception {
        Account newAccount = new Account().setName(name).setType(type);

        mockMvc.perform(post(RESTPaths.ACCOUNTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(newAccount)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_STATUS).value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_ERROR_MESSAGE).value(errorMessage))
                .andExpect(jsonPath("$." + ErrorResponse.JSON_KEY_TIMESTAMP).value(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(ErrorResponse.JSON_TIMESTAMP_FORMAT))));
    }
}