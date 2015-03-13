/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
