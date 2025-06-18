package org.example.tlias.service;

public interface AccountService {
    public Integer insertUserRole(Integer userId);
    public void grantMutePermission(Integer userId);
    public void revokeMutePermission(Integer userId);
    public void grantBanPermission(Integer userId);
    public void revokeBanPermission(Integer userId);
    public Integer setUserRole4(Integer userId);
    Integer setUserRole1(Integer userId);
    Integer setUserRole5(Integer userId);
    Integer setUserRole3(Integer userId);
}
