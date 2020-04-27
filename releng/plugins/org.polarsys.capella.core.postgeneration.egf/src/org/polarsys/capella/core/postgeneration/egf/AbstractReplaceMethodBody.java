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
