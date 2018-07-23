/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.shared.helper.container;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A container object for common business query inputs.
 * 
 * @author Sandu Postaru
 *
 */
public class CommonBQInput {

  private final EObject rowObject;
  private final EStructuralFeature columnFeature;

  public CommonBQInput(EObject rowObject, EStructuralFeature columnFeature) {
    this.rowObject = rowObject;
    this.columnFeature = columnFeature;
  }

  public EObject getRowObject() {
    return rowObject;
  }

  public EStructuralFeature getColumnFeature() {
    return columnFeature;
  }

}
