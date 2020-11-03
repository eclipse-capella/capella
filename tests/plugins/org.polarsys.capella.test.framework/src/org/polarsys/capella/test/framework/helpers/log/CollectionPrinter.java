/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers.log;

import java.util.Collection;

/**
 * @author Erwan Brottier
 */
public class CollectionPrinter implements ObjectPrinter {

	public void print(Object object, IFormatedLogger logger) {
  	Collection<?> collection = (Collection<?>) object;
  	if (collection.size() == 0) {
  		logger.addTextLn("[]"); //$NON-NLS-1$
  	} else {
  		logger.addText("["); //$NON-NLS-1$
  		logger.incIndentLn();
  		for (Object element : collection) {
  			logger.addTextLn(element);
  		}
  		logger.decIndent();
  		logger.addText("]"); //$NON-NLS-1$
  	}
	}

	public boolean appliesOn(Object object) {
		return object instanceof Collection<?>;
	}

}
