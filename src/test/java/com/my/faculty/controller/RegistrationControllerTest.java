package com.my.faculty.controller;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.common.builders.AbstractCreator;
import com.my.faculty.common.builders.AuthBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.controller.parsers.BirthDateParser;
import com.my.faculty.controller.parsers.EmailParser;
import com.my.faculty.controller.parsers.NameParser;
import com.my.faculty.controller.parsers.PasswordParser;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.service.UserService;
import com.my.faculty.service.exception.UserExistException;
import com.my.faculty.service.impl.UserServiceImpl;
import com.my.faculty.web.Model;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.time.LocalDate;
import java.util.Map;

import static com.my.faculty.common.Key.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class RegistrationControllerTest extends AbstractCreator {
    private static final LocalDate BIRTH_DATE = LocalDate.parse("1933-11-13");
    private static final String USERNAME = "testUsername";
    private static final String EMAIL = "test@mail.ru";
    private static final String PASSWORD = "testPassword";
    private RegistrationController registrationController;
    private UserService userService;
    private Model model;

    @Before
    public void setUpRegistrationController() {
        model = mock(Model.class);
        userService = mock(UserServiceImpl.class);
        registrationController = new RegistrationController(userService);
    }

    @Test
    public void testSuccessRegistration() throws Exception {
        when(model.findParameter(eq(Key.USERNAME), any(NameParser.class))).thenReturn(USERNAME);
        when(model.findParameter(eq(Key.EMAIL), any(EmailParser.class))).thenReturn(EMAIL);
        when(model.findParameter(eq(Key.PASSWORD), any(PasswordParser.class))).thenReturn(PASSWORD);
        when(model.findParameter(eq(BIRTHDAY), any(BirthDateParser.class))).thenReturn(BIRTH_DATE);
        User createdUser = new UserBuilder()
                .withId(1L)
                .withUsername(USERNAME)
                .withBirthDate(BIRTH_DATE)
                .withAuth(new AuthBuilder()
                        .withEmail(EMAIL)
                        .withPassword(PASSWORD)
                        .withRole(UserRole.STUDENT)
                        .build())
                .build();

        when(userService.createUser(any(User.class))).thenReturn(createdUser);
        String actual = registrationController.execute(model);
        assertEquals(Page.LOGIN, actual);
        verify(model).findParameter(eq(Key.USERNAME), any(NameParser.class));
        verify(model).findParameter(eq(Key.EMAIL), any(EmailParser.class));
        verify(model).findParameter(eq(Key.PASSWORD), any(PasswordParser.class));
        verify(model).findParameter(eq(BIRTHDAY), any(BirthDateParser.class));
        verify(userService).createUser(any(User.class));
        verifyNoMoreInteractions(userService, model);
    }

    @Test()
    public void testRegistrationWithException() throws Exception {
        User user = new UserBuilder()
                .withUsername(USERNAME)
                .withBirthDate(BIRTH_DATE)
                .withAuth(new AuthBuilder()
                        .withEmail(EMAIL)
                        .withPassword(PASSWORD)
                        .withRole(UserRole.STUDENT)
                        .build())
                .build();

        when(userService.createUser(any())).thenThrow(UserExistException.class);
        String actual = registrationController.execute(model);
        assertEquals(Page.REGISTRATION, actual);
        verify(model).findParameter(eq(Key.USERNAME), any(NameParser.class));
        verify(model).findParameter(eq(Key.EMAIL), any(EmailParser.class));
        verify(model).findParameter(eq(Key.PASSWORD), any(PasswordParser.class));
        verify(model).findParameter(eq(BIRTHDAY), any(BirthDateParser.class));
        verify(model).setAttributes(argThat(new ErrorMatcher()));
        verify(model).setAttribute(eq(Key.USER), any(User.class));
        verify(userService).createUser(any(User.class));
        verifyNoMoreInteractions(userService, model);
    }

    private class ErrorMatcher extends ArgumentMatcher<Map<String, Object>> {
        private static final String INCORRECT_EMAIL_PASSWORD = "login.error.userAlreadyCreated";;
        private static final String REGISTRATION_ERROR = "registration_error";

        @Override
        public boolean matches(Object argument) {
            Map<String, Object> map = (Map<String, Object>) argument;
            return map.size() == 1 && map.containsKey(REGISTRATION_ERROR)
                    && map.containsValue(INCORRECT_EMAIL_PASSWORD);
        }
    }

    private class UserRegistrationMatcher extends ArgumentMatcher<User> {
        private User user;

        UserRegistrationMatcher(User user) {
            this.user = user;
        }
        @Override
        public boolean matches(Object argument) {
            User otherUser = (User) argument;
            return user.getUsername().equals(otherUser.getUsername())
                    && user.getBirthDate().equals(otherUser.getBirthDate())
                    && user.getAuth().equals(otherUser.getAuth());
        }
    }
}
