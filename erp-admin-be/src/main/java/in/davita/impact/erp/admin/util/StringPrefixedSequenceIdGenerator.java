package in.davita.impact.erp.admin.util;

import java.io.Serializable;
import java.sql.SQLException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import in.davita.impact.erp.admin.model.UserCredentials;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import org.hibernate.id.IdentifierGenerator;
import java.sql.*;
import org.hibernate.HibernateException;

public class StringPrefixedSequenceIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		System.out.println(object);
		String prefix = "";
		if (object instanceof UserRegistrationDetail) {
			prefix = "URID-";
		}
		if (object instanceof UserCredentials) {
			prefix = "UCID";
		}
		Connection connection = session.connection();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = null;

			if (object instanceof UserRegistrationDetail) {
				rs = statement.executeQuery("select count(user_id) as Id from davitademo.user_registration_detail");

			}
			if (object instanceof UserCredentials) {
				rs = statement.executeQuery("select count(credential_id) as Id from davitademo.user_credentials");

			}

			if (rs.next()) {
				int id = rs.getInt(1) + 101;
				String generatedId = prefix + new Integer(id).toString();
				System.out.println("Generated Id: " + generatedId);
				return generatedId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * public static final String VALUE_PREFIX_PARAMETER = "valuePrefix"; public
	 * static final String VALUE_PREFIX_DEFAULT = ""; private String valuePrefix;
	 * 
	 * public static final String NUMBER_FORMAT_PARAMETER = "numberFormat"; public
	 * static final String NUMBER_FORMAT_DEFAULT = "%d"; private String
	 * numberFormat;
	 * 
	 * @Override public Serializable generate(SharedSessionContractImplementor
	 * session, Object object) throws HibernateException { return valuePrefix +
	 * String.format(numberFormat, super.generate(session, object)); }
	 * 
	 * @Override public void configure(Type type, Properties params, ServiceRegistry
	 * serviceRegistry) throws MappingException { super.configure(LongType.INSTANCE,
	 * params, serviceRegistry); valuePrefix =
	 * ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params,
	 * VALUE_PREFIX_DEFAULT); numberFormat =
	 * ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params,
	 * NUMBER_FORMAT_DEFAULT); }
	 * 
	 */

}