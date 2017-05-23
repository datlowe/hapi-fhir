package ca.uhn.fhir.jpa.json;

import java.sql.Types;

import org.hibernate.dialect.DerbyTenSevenDialect;

public class DerbyTenSevenDialectJson extends DerbyTenSevenDialect {

	public DerbyTenSevenDialectJson() {
		super();
		
		this.registerColumnType(Types.JAVA_OBJECT, "CLOB");		
	}

}
