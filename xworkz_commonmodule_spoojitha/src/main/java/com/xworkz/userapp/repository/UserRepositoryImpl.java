package com.xworkz.userapp.repository;

import com.xworkz.userapp.constant.LocationEnum;
import com.xworkz.userapp.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveUser(UserEntity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public UserEntity fetchPasswordByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createNamedQuery("UserEntity.getPasswordByEmail", UserEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserEntity findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createNamedQuery("UserEntity.findByEmail", UserEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public int updateByEmail(String email, String name, String phoneNumber, LocationEnum location, Integer age, String password) {
        log.info("Repo updateByEmail started");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int noOfRowsUpdated = 0;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("UserEntity.updateByEmail")
                    .setParameter("name", name)
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("location", location)
                    .setParameter("age", age)
                    .setParameter("password", password)
                    .setParameter("email", email);

            noOfRowsUpdated = query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error in repo: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return noOfRowsUpdated;
    }

    @Override
    public void updateFailedAttempts(String email, int attempts) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNamedQuery("UserEntity.updateFailedAttempts")
                .setParameter("attempts", attempts)
                .setParameter("email", email)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void lockAccount(String email, LocalDateTime lockTime) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNamedQuery("UserEntity.lockAccount")
                .setParameter("lockTime", lockTime)
                .setParameter("email", email)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void resetAttempts(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNamedQuery("UserEntity.resetAttempts")
                .setParameter("email", email)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    @Transactional
    public void updatePassword(String email, String newPassword) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNamedQuery("UserEntity.updatePassword")
                .setParameter("password", newPassword)
                .setParameter("email", email)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public UserEntity findByPhoneNumber(String phoneNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createNamedQuery("UserEntity.findByPhoneNumber", UserEntity.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createNamedQuery("UserEntity.findByName", UserEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("UserEntity.deleteByEmail")
                .setParameter("email", email);
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        if (rowsDeleted > 0) {
            log.info("User with email " + email + " deleted successfully.");
        } else {
            log.error("No user found with email " + email);
        }
        entityManager.close();
    }
}
