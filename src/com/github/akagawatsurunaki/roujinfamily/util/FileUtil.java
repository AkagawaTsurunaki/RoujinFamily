package com.github.akagawatsurunaki.roujinfamily.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class FileUtil {

	public static void writeFile(String filePath, String content) throws IOException {
		File file = new File(filePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		PrintWriter printWriter = new PrintWriter(bufferedWriter);
		printWriter.write(content);
		printWriter.flush();
		printWriter.close();
		bufferedWriter.close();
		outputStreamWriter.close();
		fileOutputStream.close();
	}

	public static String readFile(String filePath) throws IOException {
		File file = new File(filePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileInputStream fileInputStream = new FileInputStream(file); 
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8); 
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) { 
			stringBuilder.append(line).append("\r\n");
		}
		bufferedReader.close();
		inputStreamReader.close();
		fileInputStream.close();
		return stringBuilder.toString();
	}

}
