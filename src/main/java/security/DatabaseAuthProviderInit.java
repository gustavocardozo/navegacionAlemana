package security;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;


public class DatabaseAuthProviderInit {

	public static Realm init(DataSource dataSource) throws NamingException {

		JdbcRealm jdbcRealm = new JdbcRealm();

		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName(Sha1Hash.ALGORITHM_NAME);
		credentialsMatcher.setStoredCredentialsHexEncoded(false);

		jdbcRealm.setPermissionsLookupEnabled(true);
		jdbcRealm.setAuthenticationQuery("select PASSWORD from USER where USERNAME = ?");
		jdbcRealm.setCredentialsMatcher(credentialsMatcher);
		jdbcRealm.setDataSource(dataSource);

		return jdbcRealm;
	}
	
}
