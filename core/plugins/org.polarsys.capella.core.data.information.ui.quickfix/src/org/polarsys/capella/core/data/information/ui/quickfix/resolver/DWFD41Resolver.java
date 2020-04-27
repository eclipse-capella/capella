/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 *  Consider a concrete class <b>A</b> with an abstract property <b>p</b> 
 * 	The Quick fix action on the error will set a class A as an abstract class
 *
 */
public class DWFD41Resolver extends AbstractCapellaMarkerResolution{
	
	/**
	   * {@inheritDoc}
	   */
	  public void run(IMarker marker) {
		  
	    final EObject eObj = getModelElements(marker).get(0);
	    if (eObj instanceof Class) {
	    	
	    	//read write command
		    AbstractReadWriteCommand collectElementsCommand = new AbstractReadWriteCommand() {

				public void run() {
					Class clazz = (Class) eObj;
			    	clazz.setAbstract(true);
					
				}
		    	
				 /**
		         * {@inheritDoc}
		         */
		        @Override
		        public void commandRolledBack() {
		        	super.commandRolledBack();
		        	
		        }
		    };
		    // execute the command
		    TransactionHelper.getExecutionManager(eObj).execute(collectElementsCommand);
	    	
		      try {
		          marker.delete();
		        } catch (CoreException exception) {
		          //Do nothing
		   }
	    }
	  }

}
