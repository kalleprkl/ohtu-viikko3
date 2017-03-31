/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author perkoila
 */
public class FileUserDao implements UserDao {

    private List<User> users;
    private String filename;
    
    public FileUserDao(String name) {
        users = new ArrayList();
        filename = name;
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String uname = scanner.nextLine();
                if (uname.isEmpty()) {
                    break;
                }
                String pword = scanner.nextLine();
                users.add(new User(uname, pword));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Ai saatana!");
        }
    }

    @Override
    public List<User> listAll() {
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User u : users) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
        try {
            FileWriter writer = new FileWriter(filename);
            for (User u : users) {
                writer.write(u.getUsername() + "\n");
                writer.write(u.getPassword() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Ei perkele!");
        }
    }

}
