package utils;

import dao.UserDAO;
import models.User;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import services.PwnedPasswordService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RegisterValidatorTest {

    @Test
    void validate_shouldReturnError_whenEmailFormatIsInvalid() {
        try (MockedStatic<UserDAO> userDaoMock = Mockito.mockStatic(UserDAO.class);
             MockedStatic<PwnedPasswordService> pwnedMock = Mockito.mockStatic(PwnedPasswordService.class)) {

            userDaoMock.when(() -> UserDAO.getRowByEmail("test-invalide")).thenReturn(null);
            pwnedMock.when(() -> PwnedPasswordService.isSafe("Motdepasse123!")).thenReturn(true);

            String error = RegisterValidator.validate(
                    "Dupont",
                    "test-invalide",
                    "0612345678",
                    "Motdepasse123!",
                    "Motdepasse123!"
            );

            assertEquals("Format d'email invalide", error);
        }
    }

    @Test
    void validate_shouldReturnError_whenEmailIsAlreadyUsed() {
        User existingUser = new User(
                "Martin",
                "test@example.com",
                "hashed-password",
                "0612345678",
                "client"
        );
        existingUser.setId(1);

        try (MockedStatic<UserDAO> userDaoMock = Mockito.mockStatic(UserDAO.class);
             MockedStatic<PwnedPasswordService> pwnedMock = Mockito.mockStatic(PwnedPasswordService.class)) {

            userDaoMock.when(() -> UserDAO.getRowByEmail("test@example.com")).thenReturn(existingUser);
            pwnedMock.when(() -> PwnedPasswordService.isSafe("Motdepasse123!")).thenReturn(true);

            String error = RegisterValidator.validate(
                    "Dupont",
                    "test@example.com",
                    "0612345678",
                    "Motdepasse123!",
                    "Motdepasse123!"
            );

            assertEquals("Email déjà utilisé", error);
        }
    }

    @Test
    void validate_shouldReturnError_whenPasswordIsTooShort() {
        try (MockedStatic<UserDAO> userDaoMock = Mockito.mockStatic(UserDAO.class);
             MockedStatic<PwnedPasswordService> pwnedMock = Mockito.mockStatic(PwnedPasswordService.class)) {

            userDaoMock.when(() -> UserDAO.getRowByEmail("test@example.com")).thenReturn(null);
            pwnedMock.when(() -> PwnedPasswordService.isSafe("Court1!")).thenReturn(true);

            String error = RegisterValidator.validate(
                    "Dupont",
                    "test@example.com",
                    "0612345678",
                    "Court1!",
                    "Court1!"
            );

            assertEquals("Le mot de passe doit contenir au moins 12 caractères", error);
        }
    }

    @Test
    void validate_shouldReturnError_whenPasswordHasNoUppercase() {
        try (MockedStatic<UserDAO> userDaoMock = Mockito.mockStatic(UserDAO.class);
             MockedStatic<PwnedPasswordService> pwnedMock = Mockito.mockStatic(PwnedPasswordService.class)) {

            userDaoMock.when(() -> UserDAO.getRowByEmail("test@example.com")).thenReturn(null);
            pwnedMock.when(() -> PwnedPasswordService.isSafe("motdepasse123!")).thenReturn(true);

            String error = RegisterValidator.validate(
                    "Dupont",
                    "test@example.com",
                    "0612345678",
                    "motdepasse123!",
                    "motdepasse123!"
            );

            assertEquals("Le mot de passe doit contenir au moins une majuscule", error);
        }
    }

    @Test
    void validate_shouldReturnError_whenPasswordIsCompromised() {
        try (MockedStatic<UserDAO> userDaoMock = Mockito.mockStatic(UserDAO.class);
             MockedStatic<PwnedPasswordService> pwnedMock = Mockito.mockStatic(PwnedPasswordService.class)) {

            userDaoMock.when(() -> UserDAO.getRowByEmail("test@example.com")).thenReturn(null);
            pwnedMock.when(() -> PwnedPasswordService.isSafe("Motdepasse123!")).thenReturn(false);

            String error = RegisterValidator.validate(
                    "Dupont",
                    "test@example.com",
                    "0612345678",
                    "Motdepasse123!",
                    "Motdepasse123!"
            );

            assertEquals("Ce mot de passe a déjà fuité, choisis-en un autre", error);
        }
    }

    @Test
    void validate_shouldReturnNull_whenAllDataIsValid() {
        try (MockedStatic<UserDAO> userDaoMock = Mockito.mockStatic(UserDAO.class);
             MockedStatic<PwnedPasswordService> pwnedMock = Mockito.mockStatic(PwnedPasswordService.class)) {

            userDaoMock.when(() -> UserDAO.getRowByEmail("test@example.com")).thenReturn(null);
            pwnedMock.when(() -> PwnedPasswordService.isSafe("Motdepasse123!")).thenReturn(true);

            String error = RegisterValidator.validate(
                    "Dupont",
                    "test@example.com",
                    "0612345678",
                    "Motdepasse123!",
                    "Motdepasse123!"
            );

            assertNull(error);
        }
    }
}
