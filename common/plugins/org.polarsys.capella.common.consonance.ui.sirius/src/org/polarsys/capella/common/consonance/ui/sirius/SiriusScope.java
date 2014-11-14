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
package org.polarsys.capella.common.consonance.ui.sirius;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;


/**
 * A scope for fragmented Sirius files which covers the semantic elements.
 */
public class SiriusScope extends GMFScope {
  
  /** The set of packages which can be used in Viewpoint resources */
  protected static final Collection<? extends EPackage> VIEWPOINT_PACKAGES =
    Arrays.asList(
        ViewpointPackage.eINSTANCE,
        StylePackage.eINSTANCE,
        NotationPackage.eINSTANCE
    );
  
  
  /**
   * Constructor
   * @param resource_p a non-null resource
   */
  public SiriusScope(Resource resource_p) {
    super(resource_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#getCrossReferencesInScope(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Collection<EReference> getCrossReferencesInScope(EObject element_p) {
    Collection<EReference> result = super.getCrossReferencesInScope(element_p);
    if (element_p instanceof DSemanticDecorator) {
      // From Viewpoint to semantic
      result.add(ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target());
    } else if (element_p instanceof DAnalysis) {
      // From Viewpoint analysis to semantic models ...
      result.add(ViewpointPackage.eINSTANCE.getDAnalysis_Models());
      // ... and referenced AIRD fragments
      result.add(ViewpointPackage.eINSTANCE.getDAnalysis_ReferencedAnalysis());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.FragmentedModelScope.MultiResourceModelScope#isSuitableFor(Resource, EObject)
   */
  @Override
  protected boolean isSuitableFor(Resource resource_p, EObject root_p) {
    boolean result = isViewpointResource(resource_p) == isViewpointElement(root_p);
    return result;
  }
  
  /**
   * Return whether the given element can be included in a Viewpoint resource
   * @param element_p a non-null element
   */
  protected boolean isViewpointElement(EObject element_p) {
    EPackage pack = element_p.eClass().getEPackage();
    return VIEWPOINT_PACKAGES.contains(pack);
  }
  
  /**
   * Return whether the given resource is a Viewpoint model or model fragment
   * @param resource_p a non-null resource
   */
  protected boolean isViewpointResource(Resource resource_p) {
    boolean result = false;
    if (resource_p.getURI() != null) {
      String extension = resource_p.getURI().fileExtension();
      if (extension != null) {
        extension = extension.toLowerCase();
        result = SiriusScopeFactory.VIEWPOINT_FILE_EXTENSIONS.contains(extension);
      }
    }
    return result;
  }
  
}
