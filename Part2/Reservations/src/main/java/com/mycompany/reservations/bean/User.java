/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reservations.bean;

import java.time.LocalDateTime;

/**
 *
 * @author Juneau
 */
public class User {
    
    private String username;
    private LocalDateTime authenticationTime;
    // Business Managers have a business Id
    private Integer businessId;
    
    public User(){}

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the authenticationTime
     */
    public LocalDateTime getAuthenticationTime() {
        return authenticationTime;
    }

    /**
     * @param authenticationTime the authenticationTime to set
     */
    public void setAuthenticationTime(LocalDateTime authenticationTime) {
        this.authenticationTime = authenticationTime;
    }

    /**
     * @return the businessId
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * @param businessId the businessId to set
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }
    
}
