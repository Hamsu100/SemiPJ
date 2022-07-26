package com.kh.locationdata.model.service;


import static com.kh.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.kh.locationdata.model.dao.LocationDataDAO;
public class LocationService {

	private Connection conn = null;
	
	private LocationDataDAO ld = new LocationDataDAO();
	
	public LocationService() {
		super();
		conn = getConnection();
	}
	
	public int getLocNo (String locName) {
		return ld.getLocNumberByLocName(conn, locName);
	}
	
}
