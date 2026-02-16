package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) throws InvalidDataException {
        // Check if user already exists
        if (storageDao.get(user.getLogin()) != null) {
            throw new InvalidDataException("User already exists");
        }

        // Check if login is at least 6 characters long
        if (user.getLogin().length() < 6) {
            throw new InvalidDataException("Login must be at least 6 characters");
        }

        // Check if password is at least 6 characters long
        if (user.getPassword().length() < 6) {
            throw new InvalidDataException("Password must be at least 6 characters");
        }

        // Check if user is at least 18
        if (user.getAge() < 18) {
            throw new InvalidDataException("User must be at least 18");
        }

        return storageDao.add(user);
    }
}
