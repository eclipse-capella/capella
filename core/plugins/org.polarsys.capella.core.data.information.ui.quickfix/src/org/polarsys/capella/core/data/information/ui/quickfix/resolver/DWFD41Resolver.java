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
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

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
	    if ((null != eObj) && (eObj instanceof Class)) {
	    	
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
		    MDEAdapterFactory.getExecutionManager().execute(collectElementsCommand);
	    	
		      try {
		          marker.delete();
		        } catch (CoreException exception) {
		          //Do nothing
		   }
	    }
	  }

}
