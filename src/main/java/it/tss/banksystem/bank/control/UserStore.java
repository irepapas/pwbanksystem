/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.banksystem.bank.control;

import it.tss.banksystem.bank.entity.Account;
import it.tss.banksystem.bank.entity.User;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author alfonso
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class UserStore {

    @PersistenceContext
    private EntityManager em;

    @Inject
    AccountStore accountStore;
    
    public Optional<User> aggiorna(Long id){
        
    }
    
    public void delete (Long id){
        em.remove(em.find(User.class, id));
    }
    
    public Optional<User> find(Long id){
        User found = em.find(User.class, id);
        return found == null ? Optional.empty(): Optional.of(found);
    }

    public List<User> search() {
        return em.createQuery("select e from User e order by e.usr ", User.class)
                .getResultList();
    }

    public User create(User u) {
        User saved = em.merge(u);
        Account account = new Account(0d, 0l, saved);
        accountStore.create(account);
        return saved;
    }

}
