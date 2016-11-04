/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;


/**
 * A scope for fragmented Viewpoint files which covers the semantic elements.
 */
public class SiriusScope extends GMFScope {
  
  /** The set of packages which can be used in Viewpoint resources */
  protected static final Collection<? extends EPackage> VIEWPOINT_PACKAGES =
    Arrays.asList(
        ViewpointPackage.eINSTANCE,
        StylePackage.eINSTANCE,
        NotationPackage.eINSTANCE,
        DiagramPackage.eINSTANCE
    );
  
  
  /**
   * Constructor
   * @param uri a non-null URI
   * @param domain a non-null editing domain
   * @param readOnly whether the scope is read-only
   */
  public SiriusScope(URI uri, EditingDomain domain, boolean readOnly) {
    super(uri, domain, readOnly);
  }
  
  /**
   * Constructor
   * @param uri a non-null URI
   * @param resourceSet a non-null resource set
   * @param readOnly whether the scope is read-only
   */
  public SiriusScope(URI uri, ResourceSet resourceSet, boolean readOnly) {
    super(uri, resourceSet, readOnly);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#getCrossReferencesInScope(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Collection<EReference> getCrossReferencesInScope(EObject element) {
    Collection<EReference> result = super.getCrossReferencesInScope(element);
    if (element instanceof DSemanticDecorator) {
      // From Viewpoint to semantic
      result.add(ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target());
    } else if (element instanceof DAnalysis) {
      // From Viewpoint analysis to referenced AIRD fragments
      result.add(ViewpointPackage.eINSTANCE.getDAnalysis_ReferencedAnalysis());
      // From Viewpoint analysis to semantic models
      result.add(ViewpointPackage.eINSTANCE.getDAnalysis_Models());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#isSuitableFor(org.eclipse.emf.ecore.resource.Resource, org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected boolean isSuitableFor(Resource resource, EObject root) {
    boolean result = isViewpointResource(resource) && isViewpointElement(root);
    return result;
  }
  
  /**
   * Return whether the given element can be included in a Viewpoint resource
   * @param element a non-null element
   */
  protected boolean isViewpointElement(EObject element) {
    EPackage pack = element.eClass().getEPackage();
    return VIEWPOINT_PACKAGES.contains(pack);
  }
  
  /**
   * Return whether the given resource is a Viewpoint model or model fragment
   * @param resource a non-null resource
   */
  protected boolean isViewpointResource(Resource resource) {
    boolean result = false;
    if (resource.getURI() != null) {
      String extension = resource.getURI().fileExtension();
      if (extension != null) {
        extension = extension.toLowerCase();
        result = SiriusScopeFactory.VIEWPOINT_FILE_EXTENSIONS.contains(extension);
      }
    }
    return result;
  }
  
}
