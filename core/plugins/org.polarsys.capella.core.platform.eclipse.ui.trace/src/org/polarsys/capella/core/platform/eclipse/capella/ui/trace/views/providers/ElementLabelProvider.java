/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers;

import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceNameHelper;

/**
 * <code>ElementLabelProvider</code> is the label provider of
 * {@link org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.SourceElementContentProvider}
 * 
 */
public class ElementLabelProvider extends MDEAdapterFactoryLabelProvider {


	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getText(Object element) {
		if (element instanceof Class) {
			return TraceNameHelper.getTraceNameFromClass((Class<? extends Trace>) element);
		} else if (element instanceof Trace) {
			return ""; //$NON-NLS-1$
		}
		return super.getText(element);
	}
}
