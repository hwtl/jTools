package com.goodlaike.tools.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.goodlaike.tools.enums.FileType;

/**
 * Created with IntelliJ IDEA. Create: GuSi (2013-05-14 08:29) Description: To
 * change this template use File | Settings | File Templates.
 */
public class FileUtil {

	/**
	 * image 文件后缀集合
	 */
	private static List<String> images = Arrays.asList(new String[] { "bmp",
			"jpg", "gif", "bmp", "jfif", "tiff", "jpe", "ico", "jpeg", "png" });

	/**
	 * pad 文件后缀集合
	 */
	private static List<String> pdfs = Arrays.asList(new String[] { "pdf" });

	/**
	 * doc 文件后缀集合
	 */
	private static List<String> docs = Arrays.asList(new String[] { "doc",
			"docx" });

	/**
	 * txt 文件后缀集合
	 */
	private static List<String> txts = Arrays.asList(new String[] { "txt" });

	/**
	 * xls 文件后缀集合
	 */
	private static List<String> xlss = Arrays.asList(new String[] { "xls",
			"xlsx" });

	/**
	 * video 文件后缀集合
	 */
	private static List<String> videos = Arrays.asList(new String[] { "mp4",
			"flv", "dat", "mkv", "avi", "wmv", "mov" });

	/**
	 * 获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffixes(String fileName) {
		if (fileName.lastIndexOf(".") >= 0) {
			return fileName.substring(fileName.lastIndexOf("."),
					fileName.length());
		}
		return "";
	}

	/**
	 * 获取文件后缀，不包含 "." 号
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getSuffix(String fileName) throws IOException {
		if (fileName.lastIndexOf(".") >= 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1,
					fileName.length());
		}
		throw new IOException("wrong file name");
	}

	/**
	 * 获取文件类型
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static FileType getFileType(String fileName) throws IOException {
		String suffix = getSuffix(fileName).toLowerCase();
		if (images.contains(suffix)) {
			return FileType.IMAGE;
		} else if (pdfs.contains(suffix)) {
			return FileType.PDF;
		} else if (docs.contains(suffix)) {
			return FileType.WORD;
		} else if (txts.contains(suffix)) {
			return FileType.TXT;
		} else if (xlss.contains(suffix)) {
			return FileType.EXCEL;
		} else if (videos.contains(suffix)) {
			return FileType.VIDEO;
		}
		throw new IOException("wrong file type");
	}
}