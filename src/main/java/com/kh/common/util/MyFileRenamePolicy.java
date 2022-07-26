package com.kh.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	private static int seqNo = 0;

	@Override
	public File rename(File arg0) {
		String dateStr = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
		String seqStr = "" + (seqNo++);
		String randomStr = "" + new Random().nextInt(10);
		String extensionStr = "";

		try {
			String origin = arg0.getName();
			int extensionIndex = origin.lastIndexOf(".");

			extensionStr = origin.substring(extensionIndex, origin.length());
		} catch (Exception e) {}

		String renameFile = dateStr + "_" + seqStr + "_" + randomStr + extensionStr;
		File newFile = new File(arg0.getParent(), renameFile);
		return newFile;
	}

}
