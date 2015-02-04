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
import java.util.List;

/**
 * @author Erwan Brottier
 */
public class FormatedSysoutLogger extends FormatedLogger {

  public FormatedSysoutLogger(List<ObjectPrinter> printers) {
  	super(printers);
  }
  
  public FormatedSysoutLogger() {
  	super();
  }

	
  @SuppressWarnings("nls")
	@Override
  protected void basicPrint(Object object) {
    if (object instanceof Collection<?>) {
    	Collection<?> collection = (Collection<?>) object;
    	if (collection.size() == 0) {
    		addTextLn("[]");
    	} else {
    		addText("[");
    		incIndentLn();
    		for (Object element : collection) {
    			addTextLn(element);
    		}
    		decIndent();
    		addText("]");
    	}
    } else {
    	System.out.print(object);
    }    
  }

}
