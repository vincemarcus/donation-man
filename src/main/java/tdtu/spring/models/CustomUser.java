package tdtu.spring.models;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
		// to retrieve logged in Account ID from Spring Security Settings

    private final int userId;
    private final String name;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int userId, String name) {
        super(username, password, authorities);
        this.userId = userId;
        this.name = name;
    }

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString() + "accountId: " + userId;
		}

		public int getUserId() {
			return userId;
		}   

		public String getName() {
			return name;
		}   
}