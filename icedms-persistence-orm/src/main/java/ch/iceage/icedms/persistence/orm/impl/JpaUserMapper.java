/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.orm.impl;

import ch.iceage.icedms.core.business.DefaultUser;
import ch.iceage.icedms.core.business.User;
import ch.iceage.icedms.core.persistence.UserMapper;
import ch.iceage.icedms.persistence.orm.JpaGenericMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enea
 */
@Repository
public class JpaUserMapper extends JpaGenericMapper<User, Long> implements UserMapper {

    @Override
    public User getByKey(Long key) {
        if (key != null) {
            return em.find(DefaultUser.class, key);
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("Select u from DefaultUser u").getResultList();
    }

    @Override
    public List<User> get(User criteria) {
        if (criteria != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<DefaultUser> user = cq.from(DefaultUser.class);
            Predicate p = cb.conjunction();

            if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("email"), criteria.getEmail()));
            }
            if (criteria.getFirstName() != null && !criteria.getFirstName().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("firstName"), criteria.getFirstName()));
            }
            if (criteria.getLastName() != null && !criteria.getLastName().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("lastName"), criteria.getLastName()));
            }
            if (criteria.getUserName() != null && !criteria.getUserName().isEmpty()) {
                p = cb.and(p, cb.equal(user.get("userName"), criteria.getUserName()));
            }

            cq.select(user.alias("u")).where(p);
            return em.createQuery(cq).getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public User getByEmail(String email) {
        if (email != null) {
            TypedQuery<User> q = em.createQuery("Select u "
                    + "from DefaultUser u "
                    + "where u.email like :email", User.class)
                    .setParameter("email", email);
            return super.getSingleResult(q, em);
        } else {
            return null;
        }
    }

    @Override
    public User getByUserName(String userName) {
        if (userName != null) {
            TypedQuery<User> q = em.createQuery("Select u from DefaultUser u where u.userName like :userName", User.class)
                    .setParameter("userName", userName);
            return super.getSingleResult(q, em);
        } else {
            return null;
        }
    }

    @Override
    public void removeByKey(Long key) {
        if (key != null) {
            DefaultUser user = em.find(DefaultUser.class, key);
            if (user != null) {
                em.remove(user);
            }
        }
    }

}
