package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.User;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    private final RegistrationServiceImpl testRegistrationService = new RegistrationServiceImpl();

    @Test
    void register_uniqueLogin_Ok() {
        User testUser = new User("TestUser", "TestPassword", 18);
        assertDoesNotThrow(() -> testRegistrationService.register(testUser));
    }

    @Test
    void register_duplicateLogin_notOk() {
        User testUser = new User("TestUser2", "TestPassword", 18);
        testRegistrationService.register(testUser);
        User testUser2 = new User("TestUser2", "TestPassword", 18);
        assertThrows(InvalidDataException.class, () -> testRegistrationService.register(testUser2));
    }

    @Test
    void register_loginIsLongEnough_Ok() {
        User testUser = new User("TestUser3", "TestPassword", 18);
        assertDoesNotThrow(() -> testRegistrationService.register(testUser));
    }

    @Test
    void register_loginIsNotLongEnough_notOk() {
        User testUser = new User("Test", "TestPassword", 18);
        assertThrows(InvalidDataException.class, () -> testRegistrationService.register(testUser));
    }

    @Test
    void register_passwordIsNotLongEnough_notOk() {
        User testUser = new User("TestUser4", "Test", 18);
        assertThrows(InvalidDataException.class, () -> testRegistrationService.register(testUser));
    }

    @Test
    void register_passwordIsLongEnough_Ok() {
        User testUser = new User("TestUser5", "TestPassword", 18);
        assertDoesNotThrow(() -> testRegistrationService.register(testUser));
    }

    @Test
    void register_userIsAdult_Ok() {
        User testUser = new User("TestUser6", "TestPassword", 18);
        assertDoesNotThrow(() -> testRegistrationService.register(testUser));
    }

    @Test
    void register_userIsChild_notOk() {
        User testUser = new User("TestUser7", "TestPassword", 13);
        assertThrows(InvalidDataException.class, () -> testRegistrationService.register(testUser));
    }

}
