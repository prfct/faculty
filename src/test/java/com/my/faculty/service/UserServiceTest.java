package com.my.faculty.service;

import com.my.faculty.common.builders.AuthBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistence.dao.AuthDao;
import com.my.faculty.persistence.dao.DaoFactory;
import com.my.faculty.persistence.dao.UserDao;
import com.my.faculty.persistence.dao.DaoConnection;
import com.my.faculty.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static final LocalDate BIRTH_DATE = LocalDate.parse("1933-11-13");
    private static final String USERNAME = "testUsername";
    private static final String EMAIL = "test@mail.ru";
    private static final String PASSWORD = "testPassword";
    @Mock
    private DaoFactory daoFactory;
    @Mock
    private UserDao userDao;
    @Mock
    private AuthDao authDao;
    @Mock
    private DaoConnection connection;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setupUserService() {
        when(daoFactory.getDaoConnection()).thenReturn(connection);
        when(daoFactory.getUserDao(connection)).thenReturn(userDao);
        when(daoFactory.getAuthDao(connection)).thenReturn(authDao);
    }

    @Test
    public void testCreateSuccessUser() throws Exception {
        User user = new UserBuilder()
                .withUsername(USERNAME)
                .withBirthDate(BIRTH_DATE)
                .withAuth(new AuthBuilder()
                        .withEmail(EMAIL)
                        .withPassword(PASSWORD)
                        .withRole(UserRole.STUDENT)
                        .build())
                .build();
        User createdUser = new UserBuilder()
                .withId(1L)
                .withUsername(USERNAME)
                .withBirthDate(BIRTH_DATE)
                .build();

        when(authDao.findByEmail(any(Auth.class))).thenReturn(null);
        when(userDao.create(user)).thenReturn(createdUser);
        User actual = userService.createUser(user);
        assertEquals(createdUser, actual);
        verify(authDao, atLeastOnce()).findByEmail(user.getAuth());
        verify(authDao, atLeastOnce()).createAuth(user.getAuth());
        verify(userDao, atLeastOnce()).create(user);
        verifyNoMoreInteractions(authDao, userDao);
    }

}
