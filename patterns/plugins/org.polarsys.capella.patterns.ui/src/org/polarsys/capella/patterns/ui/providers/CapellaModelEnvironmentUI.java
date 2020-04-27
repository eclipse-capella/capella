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
package org.polarsys.capella.patterns.ui.providers;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.diffmerge.patterns.ui.environment.DefaultModelEnvironmentUI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.ui.properties.providers.CapellaTransfertViewerLabelProvider;

/**
 * Capella-specific model environment UI.
 */
public class CapellaModelEnvironmentUI extends DefaultModelEnvironmentUI{

  /** The Capella-specific label provider */
  private final CapellaTransfertViewerLabelProvider _capellaLP;

  /**
   * Default constructor
   */
  public CapellaModelEnvironmentUI() {
    _capellaLP = new CapellaTransfertViewerLabelProvider();
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.ui.environment.DefaultModelEnvironmentUI#getSorter(org.eclipse.emf.diffmerge.patterns.ui.environment.IModelEnvironmentUI.SortingMethod)
   */
  @Override
  public ViewerComparator getSorter(SortingMethod method) {
      ViewerComparator result;
    if (method == null || method == SortingMethod.NONE)
      result = null;
    else
      result = new NameTypeArchitectureViewerSorter(method);
    return result;
  }

  /**
   * A viewer sorter that can sort by name or name and type
   */
  public static class NameTypeArchitectureViewerSorter extends NameTypeViewerSorter {
    /** The set of architectures in their natural order */
    private static final List<EClass> ORDERED_ARCHITECTURES = Arrays.asList(
        OaPackage.eINSTANCE.getOperationalAnalysis(),
        CtxPackage.eINSTANCE.getSystemAnalysis(),
        LaPackage.eINSTANCE.getLogicalArchitecture(),
        PaPackage.eINSTANCE.getPhysicalArchitecture(),
        EpbsPackage.eINSTANCE.getEPBSArchitecture());
    /**
     * Constructor
     * @param method the non-null sorting method to apply, either BY_NAME or BY_NAME_AND_TYPE
     */
    public NameTypeArchitectureViewerSorter(SortingMethod method) {
      super(method);
    }
    /**
     * @see org.eclipse.emf.diffmerge.patterns.ui.environment.DefaultModelEnvironmentUI.NameTypeViewerSorter#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
      int result;
      if (e1 instanceof BlockArchitecture && e2 instanceof BlockArchitecture) {
        // Architectures must be ordered as in the model
        int pos1 = ORDERED_ARCHITECTURES.indexOf(((EObject)e1).eClass());
        int pos2 = ORDERED_ARCHITECTURES.indexOf(((EObject)e2).eClass());
        result = pos1 - pos2;
      } else {
        result = super.compare(viewer, e1, e2);
      }
      return result;
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.ui.environment.IModelEnvironmentUI#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element) {
    if(!(element instanceof EObject)){
      return element.toString();
    }
    String result;
    try {
      // May fail because a pattern may not be a well-formed Capella fragment
      result = _capellaLP.getText(element);
    } catch (Exception e) {
      result = super.getText(element);
    }
    return result;
  }

}
