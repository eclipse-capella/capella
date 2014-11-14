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
  public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
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
  public Object[] getElements(Object inputElement_p) {
    if (inputElement_p instanceof AbstractFunction) {
      return new Object[] { inputElement_p };
    } else if (inputElement_p instanceof List) {
      return ((List<?>) inputElement_p).toArray();
    }
    return new Object[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getChildren(Object element_p) {
    if (element_p instanceof AbstractFunction) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((AbstractFunction) element_p).getOwnedFunctions());
      children.addAll(((AbstractFunction) element_p).getInputs());
      children.addAll(((AbstractFunction) element_p).getOutputs());
      return children.toArray();
    } else if (element_p instanceof FunctionInputPort) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((FunctionInputPort) element_p).getIncomingExchangeItems());
      return children.toArray();
    } else if (element_p instanceof FunctionOutputPort) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((FunctionOutputPort) element_p).getOutgoingExchangeItems());
      return children.toArray();
    }
    return new Object[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getParent(Object element_p) {
    if (element_p instanceof AbstractFunction) {
      return ((EObject) element_p).eContainer();
    } else if (element_p instanceof FunctionPort) {
      return ((EObject) element_p).eContainer();
    } else if (element_p instanceof ExchangeItem) {
      return EIAllocationModelHelpers.getOwners((ExchangeItem) element_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChildren(Object element_p) {
	  if (element_p instanceof AbstractFunction) {
	    List<ModelElement> children = new ArrayList<ModelElement>();
	    children.addAll(((AbstractFunction) element_p).getOwnedFunctions());
      children.addAll(((AbstractFunction) element_p).getInputs());
      children.addAll(((AbstractFunction) element_p).getOutputs());
		  return !children.isEmpty();
	  } else if (element_p instanceof FunctionInputPort) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((FunctionInputPort) element_p).getIncomingExchangeItems());
      return !children.isEmpty();
    } else if (element_p instanceof FunctionOutputPort) {
      List<ModelElement> children = new ArrayList<ModelElement>();
      children.addAll(((FunctionOutputPort) element_p).getOutgoingExchangeItems());
      return !children.isEmpty();
	  }
	  return false;
  }
}
