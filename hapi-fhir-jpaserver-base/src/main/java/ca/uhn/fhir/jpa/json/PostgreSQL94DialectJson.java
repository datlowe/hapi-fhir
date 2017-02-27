/*******************************************************************************
 * Copyright (c) 2017 Datlowe and/or its affiliates. All rights reserved.
 ******************************************************************************/
package ca.uhn.fhir.jpa.json;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

public class PostgreSQL94DialectJson extends PostgreSQL94Dialect {

	public PostgreSQL94DialectJson() {
		super();
		
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");		
	}

}
