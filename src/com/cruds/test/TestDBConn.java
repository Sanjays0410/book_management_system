package com.cruds.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.cruds.db.DBconnectionManager;

public class TestDBConn {

	@Test
	public void test() {
		Connection conn=DBconnectionManager.getConnection();
		assertNotNull(conn);
	}

}
