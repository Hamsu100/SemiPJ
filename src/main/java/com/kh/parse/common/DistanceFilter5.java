package com.kh.parse.common;

import java.util.List;
import java.util.Map;

public class DistanceFilter5 {

	private CommonParse cp = new CommonParse();
	private List<Map<String, String>> bchList = cp.parseCSV("beachCode.csv");

	public boolean calDistance(double lat, double lng) {
		double standardDistance = 5.0;

		for (Map<String, String> map : bchList) {
			double blat = Double.parseDouble(map.get("lat"));
			double blng = Double.parseDouble(map.get("lng"));
			double d = distance(blat, blng, lat, lng, "kilometer");
			if (d <= standardDistance) {
				return true;
			}
		}
		return false;
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		if (unit == "kilometer") {
			dist = dist * 1.609344;
		} else if (unit == "meter") {
			dist = dist * 1609.344;
		}

		return (dist);
	}

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// This function converts radians to decimal degrees
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}
