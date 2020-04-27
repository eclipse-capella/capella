/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.postgeneration.egf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.egf.core.producer.InvocationException;
import org.eclipse.egf.ftask.producer.context.ITaskProductionContext;
import org.eclipse.egf.ftask.producer.invocation.ITaskProduction;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

/**
 */
public abstract class AbstractModifyMethodBody implements ITaskProduction {

	/**
	 */
	private final class MethodVisitor extends ASTVisitor {
		/**
		 * 
		 */
		private MethodDeclaration _method;

		/**
		 * @return the visited method
		 */
		public MethodDeclaration getMyMethod() {
			return _method;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void endVisit(MethodDeclaration method) {
			if (getMethodName().equals(method.getName().toString())) {
				_method = method;
			}
		}
	}

	/**
	 * @return the method body
	 */
	protected abstract String getMethodBody();

	/**
	 * @return the method name
	 */
	protected abstract String getMethodName();

	/**
	 * @return the file path
	 */
	protected abstract String getFilePath();

	/**
	 * 
	 */
	protected abstract Block buildNewBlock(ASTParser astParser_p, MethodDeclaration method_p, IProgressMonitor monitor_p);

	/**
	 * @param astParser_p
	 * @param method_p
	 * @param monitor_p
	 */
	protected ASTRewrite doModify(ASTParser astParser_p, MethodDeclaration method_p, IProgressMonitor monitor_p) {
		Block newBlock = buildNewBlock(astParser_p, method_p, monitor_p);

		ASTRewrite astRewrite = ASTRewrite.create(method_p.getAST());
		astRewrite.replace(method_p.getBody(), newBlock, null);

		return astRewrite;
	}

	/**
	 * @param content_p
	 * @param astRewrite_p
	 */
	protected void writeFile(String content_p, ASTRewrite astRewrite_p) {
		try {
			Document document = new Document(content_p);
			TextEdit edits = astRewrite_p.rewriteAST(document, Collections.EMPTY_MAP);
			edits.apply(document);
			writeFile(getFilePath(), document.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see org.eclipse.egf.ftask.producer.invocation.ITaskProduction#doExecute(org.eclipse.egf.ftask.producer.context.ITaskProductionContext, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
		String content = readFile(getFilePath());
		ASTParser astParser = ASTParser.newParser(AST.JLS3);

		astParser.setSource(content.toCharArray());
		ASTNode astNode = astParser.createAST(monitor);
		MethodVisitor visitor = new MethodVisitor();
		astNode.accept(visitor);

		ASTRewrite astRewrite = doModify(astParser, visitor.getMyMethod(), monitor);
		writeFile(content, astRewrite);
	}

	private String readFile(String filePath) throws InvocationException {
		try {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath));
			InputStream inputStream = file.getContents();
			byte[] data = new byte[inputStream.available()];
	        inputStream.read(data);
	        return new String(data);
		} catch (Exception e) {
			throw new InvocationException(e);
		}
	}

	private void writeFile(String filePath, String string) throws InvocationException {
		try {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath));
			ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes());
			file.setContents(inputStream, true, true, new NullProgressMonitor());
		} catch (Exception e) {
			throw new InvocationException(e);
		}
	}

	/**
	 * @see org.eclipse.egf.ftask.producer.invocation.ITaskProduction#preExecute(org.eclipse.egf.ftask.producer.context.ITaskProductionContext, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void preExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
	}

	/**
	 * @see org.eclipse.egf.ftask.producer.invocation.ITaskProduction#postExecute(org.eclipse.egf.ftask.producer.context.ITaskProductionContext, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void postExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
	}
}
