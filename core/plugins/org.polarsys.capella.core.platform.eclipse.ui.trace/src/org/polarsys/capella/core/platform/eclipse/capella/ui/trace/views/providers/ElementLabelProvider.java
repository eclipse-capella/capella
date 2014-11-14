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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers;

import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceNameHelper;
import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;
import org.polarsys.capella.core.data.capellacore.Trace;

/**
 * <code>ElementLabelProvider</code> is the label provider of
 * {@link org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.SourceElementContentProvider}
 * 
 */
public class ElementLabelProvider extends CapellaElementLabelProvider {

	/**
	 * Constructor
	 */
	public ElementLabelProvider() {
		super();
	}

	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element_p) {
		return super.getImage(element_p);
	}

	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getText(Object element_p) {
		if (element_p instanceof Class) {
			return TraceNameHelper.getTraceNameFromClass((Class<? extends Trace>) element_p);
		} else if (element_p instanceof Trace) {
			return ""; //$NON-NLS-1$
		}
		return super.getText(element_p);
	}
}
