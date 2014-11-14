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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.ui.toolkit.editors.AbstractTabDescriptor;
import org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;

/**
 *
 */
public class TraceGeneralTabDescriptor extends AbstractTabDescriptor {

  /**
   * @param id_p
   * @param name_p
   */
  public TraceGeneralTabDescriptor() {
    super("actor.tab.general", Messages.getString("TraceGeneralTabDescriptor.tab.title"));  //$NON-NLS-1$//$NON-NLS-2$
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.editors.ITabDescriptor#getContent(org.eclipse.swt.widgets.Composite)
   */
  public FieldsViewer getContent(Composite parent_p) {
    return new TraceGeneralViewer(parent_p);
  }
}
