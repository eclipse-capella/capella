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
package org.polarsys.capella.core.ui.fastlinker.view.providers;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerManager;



public class FastLinkerLabelProvider extends CapellaNavigatorLabelProvider {

	@Override
	public Image getImage(Object object_p) {
		if (object_p instanceof Collection) {
			if (((Collection) object_p).isEmpty())
				return null;
			if (((Collection) object_p).size() == 1)
				return super
						.getImage(((Collection) object_p).iterator().next());
			else {
				EClass eClass = null;
				Iterator it = ((Collection) object_p).iterator();
				while (it.hasNext()) {
					Object current = it.next();
					if (current instanceof EObject) {
						if (eClass == null)
							eClass = ((EObject) current).eClass();
						else if (!((EObject) current).eClass().equals(eClass))
							return null;
					} else
						return null;
					return super.getImage(((Collection) object_p).iterator()
							.next());
				}
			}
		}
		return super.getImage(object_p);
	}

	@Override
	public String getText(Object object_p) {
		if (object_p instanceof Collection) {
			if (((Collection) object_p).isEmpty())
				return null;
			if (((Collection) object_p).size() == 1)
				return super.getText(((Collection) object_p).iterator().next());
			else {
				EClass eClass = FastLinkerManager.getCommonType((Collection) object_p);
				if (eClass != null) {
				Iterator it = ((Collection) object_p).iterator();
				String array = "";
				while (it.hasNext()) {
					Object current = it.next();
					if (current instanceof EObject) {
						
						array += ", " + super.getText(current);
					} else
						return null;

				}
				return eClass.getName() + " [ " + array.substring(2) + " ]";
				}
			}
		}
		return super.getText(object_p);
	}

}
