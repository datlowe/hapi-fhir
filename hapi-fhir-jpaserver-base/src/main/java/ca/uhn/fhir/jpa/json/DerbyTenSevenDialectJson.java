/*******************************************************************************
 * Copyright (c) 2017 Datlowe and/or its affiliates. All rights reserved.
 ******************************************************************************/
package ca.uhn.fhir.jpa.json;

import java.sql.Types;

import org.hibernate.dialect.DerbyTenSevenDialect;

public class DerbyTenSevenDialectJson extends DerbyTenSevenDialect {

	public DerbyTenSevenDialectJson() {
		super();
		
		this.registerColumnType(Types.JAVA_OBJECT, "LONG VARCHAR");		
	}

}
