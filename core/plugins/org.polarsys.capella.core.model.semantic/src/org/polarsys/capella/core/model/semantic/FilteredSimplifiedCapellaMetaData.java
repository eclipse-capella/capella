/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.semantic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.model.semantic.internal.SemanticModelActivator;

/**
 * A SimplifiedCapellaMetadata implementation that uses an extension point mechanism that works
 * like a package registry. For a registered package, all its EClasses are considered non-semantic
 * and all its ERefererences are considered non-navigable. This offers a way to override package 
 * annotations/absence of annotations to completely ignore elements from a given package in
 * the simplified capella metamodel.
 */
public class FilteredSimplifiedCapellaMetaData implements SimplifiedCapellaMetadata {

  public static final String NAME_PACKAGE_FILTER_ELEMENT = "packageFilter"; //$NON-NLS-1$
  public static final String ATTRIBUTE_NS_URI = "nsURI"; //$NON-NLS-1$
  public static final String EXTENSION_SEMANTIC_METADATA = "semanticMetadata"; //$NON-NLS-1$
  
  private Collection<String> packageFilters;
  private Set<EPackage> unfilteredEPackages;
  private final SimplifiedCapellaMetadata delegate;

  public FilteredSimplifiedCapellaMetaData(SimplifiedCapellaMetadata delegate){
    this.delegate = delegate;
    reloadFilteredPackagesFromRegistry();
  }
  
  /**
   * @param feature
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isNavigable(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public boolean isNavigable(EStructuralFeature feature) {
    return isFiltered(feature.getEContainingClass().getEPackage()) ? false : delegate.isNavigable(feature);
  }

  /**
   * @param eReference
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isContainment(org.eclipse.emf.ecore.EReference)
   */
  @Override
  public boolean isContainment(EReference eReference) {
    return delegate.isContainment(eReference);
  }

  /**
   * @param eReference
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#getContainment(org.eclipse.emf.ecore.EReference)
   */
  @Override
  public EReference getContainment(EReference eReference) {
    return delegate.getContainment(eReference);
  }

  /**
   * @param eReference
   * @param eContainmentReference
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#setContainment(org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EReference)
   */
  @Override
  public void setContainment(EReference eReference, EReference eContainmentReference) {
    delegate.setContainment(eReference, eContainmentReference);
  }

  /**
   * @param eClassifier
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isSemantic(org.eclipse.emf.ecore.EClassifier)
   */
  @Override
  public boolean isSemantic(EClassifier eClassifier) {
    return isFiltered(eClassifier.getEPackage()) ? false : delegate.isSemantic(eClassifier);
  }

  @Override
  public boolean isSemantic(EStructuralFeature eStructuralFeature) {
    return isFiltered(((EClass)eStructuralFeature.eContainer()).getEPackage()) ? false : delegate.isSemantic(eStructuralFeature);
  }
  
  /**
   * @param eClassifier
   * @param semantic
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#setSemantic(org.eclipse.emf.ecore.EClassifier, boolean)
   */
  @Override
  public void setSemantic(EClassifier eClassifier, boolean semantic) {
    delegate.setSemantic(eClassifier, semantic);
  }

  /**
   * @param feature
   * @param navigable
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#setNavigable(org.eclipse.emf.ecore.EStructuralFeature, boolean)
   */
  @Override
  public void setNavigable(EStructuralFeature feature, boolean navigable) {
    delegate.setNavigable(feature, navigable);
  }

  /**
   * @param ePackage
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#getSimplifiedNsPrefix(org.eclipse.emf.ecore.EPackage)
   */
  @Override
  public String getSimplifiedNsPrefix(EPackage ePackage) {
    return delegate.getSimplifiedNsPrefix(ePackage);
  }

  /**
   * @param ePackage
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#getSimplifiedNsURI(org.eclipse.emf.ecore.EPackage)
   */
  @Override
  public String getSimplifiedNsURI(EPackage ePackage) {
    return delegate.getSimplifiedNsURI(ePackage);
  }

  /**
   * @param eNamedElement
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#getSimplifiedName(org.eclipse.emf.ecore.ENamedElement)
   */
  @Override
  public String getSimplifiedName(ENamedElement eNamedElement) {
    return delegate.getSimplifiedName(eNamedElement);
  }

  /**
   * @param ePackage
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isProcessAnnotations(org.eclipse.emf.ecore.EPackage)
   */
  @Override
  public boolean isProcessAnnotations(EPackage ePackage) {
    return delegate.isProcessAnnotations(ePackage);
  }

  /**
   * @param ePackage
   * @param semantic
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#setProcessAnnotations(org.eclipse.emf.ecore.EPackage, boolean)
   */
  @Override
  public void setProcessAnnotations(EPackage ePackage, boolean semantic) {
    delegate.setProcessAnnotations(ePackage, semantic);
  }

 
  /**
   * @param feature
   * @param processor
   * @return
   * @see org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata#isExcludeFrom(org.eclipse.emf.ecore.EStructuralFeature, java.lang.String)
   */
  @Override
  public boolean isExcludeFrom(EStructuralFeature feature, String processor) {
    return delegate.isExcludeFrom(feature, processor);
  }
  
  
  /**
   * @param ePackage
   * @return
   */
  private boolean isFiltered(EPackage ePackage) {
    if (unfilteredEPackages.contains(ePackage)){
      return false;
    }
    for (String packageFilter : packageFilters){
      if (ePackage.getNsURI().equals(packageFilter)){
        return true;
      }
    }
    unfilteredEPackages.add(ePackage);
    return false;
  }
 
  private void reloadFilteredPackagesFromRegistry(){
    packageFilters = new ArrayList<String>();
    unfilteredEPackages = Collections.synchronizedSet(new HashSet<EPackage>());
    for (IConfigurationElement e : Platform.getExtensionRegistry().getConfigurationElementsFor(SemanticModelActivator.PLUGIN_ID, EXTENSION_SEMANTIC_METADATA)){
      if (NAME_PACKAGE_FILTER_ELEMENT.equals(e.getName())){
        String nsURI = e.getAttribute(ATTRIBUTE_NS_URI);
        if (nsURI != null){
          packageFilters.add(nsURI);
        }
      }
    }
  }


  
}
