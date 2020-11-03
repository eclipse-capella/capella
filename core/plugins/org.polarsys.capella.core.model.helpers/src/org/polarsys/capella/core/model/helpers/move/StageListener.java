/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.move;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

public abstract class StageListener {

  public abstract void stageChanged(Stage s);
  public abstract void elementsAdded(Collection<EObject> elements);
  public abstract void parentChanged(EObject staged, EObject oldParent, EObject newParent);
  public abstract void elementsRemoved(Collection<? extends EObject> elements);

}
