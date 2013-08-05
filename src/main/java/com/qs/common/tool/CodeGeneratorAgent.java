/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.common.tool;

/**
 * @author holylin
 * @since 2008-12-9
 * @version 1.0
 */

public class CodeGeneratorAgent {

	private CodeGeneratorAgent() {

	}

	public static void main(String[] args) {

		CodeGenerator codeGenerator = new CodeGenerator();

	
		codeGenerator.generateAll("services", "IterationWorkList", "chuhaiquan");
		
		

	}
}
