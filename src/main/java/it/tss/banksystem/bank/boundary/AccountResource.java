/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.banksystem.bank.boundary;

import it.tss.banksystem.bank.control.AccountStore;
import it.tss.banksystem.bank.entity.Account;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tss
 */
@Path("/accounts")
public class AccountResource {
    
    @Inject
    AccountStore store;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> search() {
        return store.search();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Account a){
        Account saved = store.create(a);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id){
        Account acc = store.find(id).orElseThrow(()-> new NotFoundException());
        return Response.ok().entity(acc).build();
    }
}
