/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
