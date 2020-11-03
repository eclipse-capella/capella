/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.flexibility.wizards.renderer;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;

public class RestraintPropertyContentProvider implements IStructuredContentProvider {

  private IPropertyContext context;

  public RestraintPropertyContentProvider(IPropertyContext context) {
    this.context = context;
  }

  @Override
  public void dispose() {
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

  @Override
  public Object[] getElements(Object inputElement) {
    return ((IRestraintProperty) inputElement).getChoiceValues(context).toArray();
  }

}
