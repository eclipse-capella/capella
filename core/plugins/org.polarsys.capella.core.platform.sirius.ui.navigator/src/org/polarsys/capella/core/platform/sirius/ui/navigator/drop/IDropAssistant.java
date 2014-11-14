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
package org.polarsys.capella.core.platform.sirius.ui.navigator.drop;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;

/**
 * Describe an assistant for drop management
 * Eclipse default implementation doesn't provides interfaces and is dependant of common.navigator
 */
public interface IDropAssistant {

  public IStatus handleDrop(Object target_p, int operation_p, DropTargetEvent dropTargetEvent_p);

  public IStatus validateDrop(Object target_p, int operation_p, TransferData transferType_p);

}
