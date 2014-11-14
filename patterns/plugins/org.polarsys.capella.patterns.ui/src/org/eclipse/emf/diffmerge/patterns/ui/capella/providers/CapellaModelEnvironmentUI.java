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
package org.eclipse.emf.diffmerge.patterns.ui.capella.providers;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.diffmerge.patterns.ui.environment.DefaultModelEnvironmentUI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
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
  public ViewerSorter getSorter(SortingMethod method_p) {
    ViewerSorter result;
    if (method_p == null || method_p == SortingMethod.NONE)
      result = null;
    else
      result = new NameTypeArchitectureViewerSorter(method_p);
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
     * @param method_p the non-null sorting method to apply, either BY_NAME or BY_NAME_AND_TYPE
     */
    public NameTypeArchitectureViewerSorter(SortingMethod method_p) {
      super(method_p);
    }
    /**
     * @see org.eclipse.emf.diffmerge.patterns.ui.environment.DefaultModelEnvironmentUI.NameTypeViewerSorter#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Viewer viewer_p, Object e1_p, Object e2_p) {
      int result;
      if (e1_p instanceof BlockArchitecture && e2_p instanceof BlockArchitecture) {
        // Architectures must be ordered as in the model
        int pos1 = ORDERED_ARCHITECTURES.indexOf(((EObject)e1_p).eClass());
        int pos2 = ORDERED_ARCHITECTURES.indexOf(((EObject)e2_p).eClass());
        result = pos1 - pos2;
      } else {
        result = super.compare(viewer_p, e1_p, e2_p);
      }
      return result;
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.ui.environment.IModelEnvironmentUI#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    if(!(element_p instanceof EObject)){
      return element_p.toString();
    }
    String result;
    try {
      // May fail because a pattern may not be a well-formed Capella fragment
      result = _capellaLP.getText(element_p);
    } catch (Exception e) {
      result = super.getText(element_p);
    }
    return result;
  }

}
