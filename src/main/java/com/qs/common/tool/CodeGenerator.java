/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */

package com.qs.common.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * @author holylin
 * @since 2008-12-8
 * @version 1.0
 */

public class CodeGenerator {

	// : 项目路径参数
	private static final String PROJECT_BUILD_ROOT_FILE_PATH = Thread
			.currentThread().getContextClassLoader().getResource("").getFile();

	private static final String GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY = PROJECT_BUILD_ROOT_FILE_PATH
			+ "../../src/main/java/com/qs/gx";

	private VelocityContext mainContext = null;
	private Template mainTemplate = null;
	private Properties p = new Properties();
	private VelocityEngine engine = new VelocityEngine();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unused")
	private String packagePartName = null;

	public CodeGenerator() {

		// ：得到模板文件所在的文件夹路径
		String currentClassPath = Thread.currentThread()
				.getContextClassLoader().getResource("").getFile();
		String vmDirPath = currentClassPath
				+ this.getClass().getPackage().getName().replaceAll("\\.", "/");
		// :~

		// :初始化Velocity引擎
		p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, vmDirPath);
		p.setProperty(Velocity.INPUT_ENCODING, "utf-8");
		p.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
		try {
			engine.init(p);
		} catch (Exception e) {
			System.out.println("初始化Velocity引擎出现错误！原因：" + e.getMessage());
		}
		// :~

		// : 初始化项目根目录
		File file = new File(GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY);
		if (!file.exists()) {
			if (file.mkdirs())
				System.out.println("Generate  root directory("
						+ GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY
						+ ") successfully!");
		}
		// :~
	}

	private void addToContext(String key, Object value) {
		if (mainContext == null)
			mainContext = new VelocityContext();
		mainContext.put(key, value);
	}

	private void generateDirectory(String packageDestFilePath) {

		File packageDestFile = new File(packageDestFilePath);
		if (!packageDestFile.exists()) {
			if (packageDestFile.mkdirs())
				System.out.println("Generate  directory(" + packageDestFilePath
						+ ") successfully!");
		}
	}

	private void generateCodeToFile(String templateFile, String codeDestFilePath) {

		try {
			mainTemplate = engine.getTemplate(templateFile);

			File codeDestFile = new File(codeDestFilePath);
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(codeDestFile));
			if (mainTemplate != null)
				mainTemplate.merge(mainContext, writer);
			writer.flush();
			writer.close();

			System.out.println("Generate  file(" + codeDestFilePath
					+ ") successfully!");

		} catch (Exception e) {
			System.out.println("Error processing  template file: "
					+ templateFile + ".");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * @param packagePartName
	 *            包路径名
	 * @param pojoName
	 *            pojo名称
	 * @param authorName
	 *            代码作者
	 */
	public void generateAll(String packagePartName, String pojoName,
			String authorName) {

		this.packagePartName = packagePartName;

		String lowerCasePojoName = pojoName.substring(0, 1).toLowerCase()
				+ pojoName.substring(1);

		addToContext("Date", format.format(new java.util.Date()));

		addToContext("Author", authorName);

		addToContext("PojoClasspath", "com.qs.gx." + packagePartName
				+ ".model." + pojoName);

		addToContext("IPojoDAOClasspath", "com.qs.gx." + packagePartName
				+ ".dao." + "I" + pojoName + "DAO");

		addToContext("IPojoServiceClasspath", "com.qs.gx." + packagePartName
				+ ".service." + "I" + pojoName + "Service");

		addToContext("PojoPackage", "com.qs.gx." + packagePartName + ".model");
		addToContext("PojoDAOPackage", "com.qs.gx." + packagePartName + ".dao");
		addToContext("PojoServicePackage", "com.qs.gx." + packagePartName
				+ ".service");
		addToContext("PojoControlPackage", "com.qs.gx." + packagePartName
				+ ".web");

		addToContext("Pojo", pojoName);
		addToContext("pojo", lowerCasePojoName);
		addToContext("IPojoDAO", "I" + pojoName + "DAO");
		addToContext("PojoDAO", pojoName + "DAO");
		addToContext("pojoDAO", lowerCasePojoName + "DAO");
		addToContext("IPojoService", "I" + pojoName + "Service");
		addToContext("PojoService", pojoName + "Service");
		addToContext("pojoService", lowerCasePojoName + "Service");
		addToContext("PojoController", pojoName + "Controller");

		// : 生成包目录
		generateDirectory(GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY + "/"
				+ packagePartName + "/model/");
		generateDirectory(GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY + "/"
				+ packagePartName + "/dao/");
		generateDirectory(GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY + "/"
				+ packagePartName + "/service/");
		generateDirectory(GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY + "/"
				+ packagePartName + "/web/");

		// :~

		// : 生成代码

		generateCodeToFile("pojo.vm", GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY
				+ "/" + packagePartName + "/model/" + pojoName + ".java");
		generateCodeToFile("dao.vm", GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY
				+ "/" + packagePartName + "/dao/I" + pojoName + "DAO.java");
		generateCodeToFile("service.vm", GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY
				+ "/" + packagePartName + "/service/I" + pojoName
				+ "Service.java");
		generateCodeToFile("serviceImpl.vm",
				GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY + "/" + packagePartName
						+ "/service/" + pojoName + "Service.java");
		generateCodeToFile("control.vm", GENERATE_JAVA_CODE_FILE_ROOT_DIRECTORY
				+ "/" + packagePartName + "/web/" + pojoName
				+ "Controller.java");
	}

}
