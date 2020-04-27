/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class EIAllocationContentProvider implements ITreeContentProvider {

  /**
   * {@inheritDoc}
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // do nothing
  }

  /**
   * {@inheritDoc}
   */
  public void dispose() {
    // do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof AbstractFunction) {
      return new Object[] { inputElement };
    } else if (inputElement instanceof List) {
      return ((List<?>) inputElement).toArray();
    }
    return new Object[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getChildren(Object element) {
    if (element instanceof AbstractFunction) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((AbstractFunction) element).getOwnedFunctions());
      children.addAll(((AbstractFunction) element).getInputs());
      children.addAll(((AbstractFunction) element).getOutputs());
      return children.toArray();
    } else if (element instanceof FunctionInputPort) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((FunctionInputPort) element).getIncomingExchangeItems());
      return children.toArray();
    } else if (element instanceof FunctionOutputPort) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((FunctionOutputPort) element).getOutgoingExchangeItems());
      return children.toArray();
    }
    return new Object[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getParent(Object element) {
    if (element instanceof AbstractFunction) {
      return ((EObject) element).eContainer();
    } else if (element instanceof FunctionPort) {
      return ((EObject) element).eContainer();
    } else if (element instanceof ExchangeItem) {
      return EIAllocationModelHelpers.getOwners((ExchangeItem) element);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChildren(Object element) {
	  if (element instanceof AbstractFunction) {
	    List<ModelElement> children = new ArrayList<ModelElement>();
	    children.addAll(((AbstractFunction) element).getOwnedFunctions());
      children.addAll(((AbstractFunction) element).getInputs());
      children.addAll(((AbstractFunction) element).getOutputs());
		  return !children.isEmpty();
	  } else if (element instanceof FunctionInputPort) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((FunctionInputPort) element).getIncomingExchangeItems());
      return !children.isEmpty();
    } else if (element instanceof FunctionOutputPort) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((FunctionOutputPort) element).getOutgoingExchangeItems());
      return !children.isEmpty();
	  }
	  return false;
  }
}
