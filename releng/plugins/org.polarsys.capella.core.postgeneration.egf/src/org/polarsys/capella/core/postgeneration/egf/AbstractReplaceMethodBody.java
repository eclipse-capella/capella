/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.postgeneration.egf;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 */
public abstract class AbstractReplaceMethodBody extends AbstractModifyMethodBody {

	/**
	 * {@inheritDoc}
	 */
	protected Block buildNewBlock(ASTParser astParser_p, MethodDeclaration method_p, IProgressMonitor monitor_p) {
		astParser_p.setSource(getMethodBody().toCharArray());
		astParser_p.setKind(ASTParser.K_STATEMENTS);
		return (Block) astParser_p.createAST(monitor_p);
	}
}
