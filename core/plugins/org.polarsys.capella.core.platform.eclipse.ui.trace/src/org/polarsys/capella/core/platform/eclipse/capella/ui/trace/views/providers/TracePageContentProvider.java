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

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.ui.toolkit.editors.IPageContentProvider;
import org.polarsys.capella.common.ui.toolkit.editors.ITabDescriptor;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.TraceGeneralTabDescriptor;

/**
 *
 */
public class TracePageContentProvider implements IPageContentProvider {

  /**
   * @see org.polarsys.capella.common.ui.toolkit.editors.IPageContentProvider#getId()
   */
  public String getId() {
    return "trace.page"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.editors.IPageContentProvider#getPageDescription()
   */
  public String getPageDescription() {
    return Messages.getString("TracePageContentProvider.page.description"); //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.editors.IPageContentProvider#getPageTitle()
   */
  public String getPageTitle() {
    return Messages.getString("TracePageContentProvider.page.title"); //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.editors.IPageContentProvider#getTabDescriptors()
   */
  public ITabDescriptor[] getTabDescriptors() {
 // Builds descriptors list.
    ITabDescriptor[] descriptors = new ITabDescriptor[] {new TraceGeneralTabDescriptor()};
    return descriptors;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.editors.IPageContentProvider#getElementType()
   */
  public EClass getElementType() {
    return CapellacorePackage.Literals.TRACE;
  }
}
