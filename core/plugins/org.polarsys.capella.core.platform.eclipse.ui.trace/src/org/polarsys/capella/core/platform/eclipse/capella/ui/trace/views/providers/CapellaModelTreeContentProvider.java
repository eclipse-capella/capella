/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * 
 *
 */
public class CapellaModelTreeContentProvider implements ITreeContentProvider {

  private ModelElement _rootPkg;

  /**
   * Constructs the model tree content provider.
   */
  public CapellaModelTreeContentProvider() {
    // Do nothing.
  }

  /**
   * 
   */
  public void setRootPkg(ModelElement rootPkg_p) {
    _rootPkg = rootPkg_p;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  public Object[] getChildren(Object parentElement_p) {
    if (parentElement_p.equals(_rootPkg.eContainer())) {
      if (_rootPkg instanceof SharedPkg) {
        return new Object[] {_rootPkg};
      }
      else if (_rootPkg instanceof SystemEngineering) {
        SystemEngineering sysEng = (SystemEngineering) _rootPkg;
        List<ModelElement> children = new ArrayList<ModelElement>();
        children.add(_rootPkg);
        children.addAll(SystemEngineeringExt.getSharedPkgs(sysEng));
        return children.toArray();
      }
    }
    else if (parentElement_p instanceof CapellaElement) {
      return ((CapellaElement) parentElement_p).eContents().toArray();
    }
    return new Object[] {parentElement_p};
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  public Object getParent(Object element_p) {
    if (element_p instanceof CapellaElement) {
      return ((CapellaElement) element_p).eContainer();
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  public boolean hasChildren(Object element_p) {
    return (element_p instanceof CapellaElement);
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  public Object[] getElements(Object inputElement_p) {
    return getChildren(inputElement_p);
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  public void dispose() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
    // do nothing
  }
}
