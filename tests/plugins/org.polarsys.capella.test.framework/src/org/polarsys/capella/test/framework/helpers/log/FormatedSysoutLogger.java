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
