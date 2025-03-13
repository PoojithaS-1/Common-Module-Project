package com.xworkz.userapp.repository;

import com.xworkz.userapp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public boolean saveUser(UserEntity entity) {
        EntityManager entityManager =  entityManagerFactory.createEntityManager() ;
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public UserEntity fetchPasswordByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserEntity userEntity = (UserEntity) entityManager.createNamedQuery("getPasswordByEmail").setParameter("email", email).getSingleResult();
        return userEntity;
    }

    @Override
    public UserEntity findByEmail(String email) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(
                            "SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int updateByEmail(String email, String name, String phoneNumber, String location, Integer age, String password) {
        System.out.println("Repo updateByEmail started");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int noOfRowsUpdated = 0;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(
                    "UPDATE UserEntity u SET u.name = :name, u.phoneNumber = :phoneNumber, " +
                            "u.location = :location, u.age = :age, u.password = :password WHERE u.email = :email");

            query.setParameter("name", name);
            query.setParameter("phoneNumber", phoneNumber);
            query.setParameter("location", location);
            query.setParameter("age", age);
            query.setParameter("password", password);
            query.setParameter("email", email);
            noOfRowsUpdated = query.executeUpdate();
            System.out.println("noOfRowsUpdated: " + noOfRowsUpdated);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("error in repo " + e.getMessage());
        } finally {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        }
        System.out.println("Repo updateByEmail ended");
        return noOfRowsUpdated;

    }


    @Override
    public void updateFailedAttempts(String email, int attempts) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE UserEntity u SET u.failedAttempts = :attempts WHERE u.email = :email")
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
        entityManager.createQuery("UPDATE UserEntity u SET u.accountLocked = true, u.lockTime = :lockTime WHERE u.email = :email")
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
        entityManager.createQuery("UPDATE UserEntity u SET u.failedAttempts = 0, u.accountLocked = false, u.lockTime = null WHERE u.email = :email")
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
        entityManager.createQuery("UPDATE UserEntity u SET u.password = :password WHERE u.email = :email")
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
            return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.phoneNumber = :phoneNumber", UserEntity.class)
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
            return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.name = :name", UserEntity.class)
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
            System.out.println("User with email " + email + " deleted successfully.");
        } else {
            System.out.println("No user found with email " + email);
        }

        entityManager.close();
    }

}