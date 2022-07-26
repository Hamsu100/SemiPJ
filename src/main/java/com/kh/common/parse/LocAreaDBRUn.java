package com.kh.common.parse;

import static com.kh.common.jdbc.JDBCTemplate.commit;
import static com.kh.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class LocAreaDBRUn {
	public static void main(String[] args) {
		GetAreaCode gac = new GetAreaCode();
		Connection conn = getConnection();
		List<AreaCode> acList = gac.getAreaCode();

		List<LocType> locList = new ArrayList<>();

		locList.add(new LocType(0, "없음"));
		locList.add(new LocType(1, "동해"));
		locList.add(new LocType(2, "서해"));
		locList.add(new LocType(3, "남해"));
		locList.add(new LocType(4, "제주"));

		for (LocType loc : locList) {
			int result = gac.locTypeToDB(conn, loc);

			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}
//
		for (AreaCode ac : acList) {
			int result = gac.toDB(conn, ac);
			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}
	}
}
