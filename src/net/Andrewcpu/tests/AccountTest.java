package net.Andrewcpu.tests;

import net.AndrewCPU.Accounts.AccountManager;

/**
 * Created by stein on 5/10/2016.
 */
public class AccountTest {
    public static void main(String[] args){
        //573280b49371d
        AccountManager accountManager = new AccountManager("573280b49371d");
        try {
            System.out.println(accountManager.createAccount("AndrewCPU","@Mommy828","stein.andrew.01@gmail.com").toString());
            System.out.println(accountManager.validate("AndrewCPU","@Mommy828").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
