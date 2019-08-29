/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.capella;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticCrossReferencer;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;

public class FunctionalChainMigrationContribution extends AbstractMigrationContribution {

  HashSet<MigrationContext> proceedingContexts = new HashSet<MigrationContext>();

  // Temporary map of FCI and next FCIs
  Map<FunctionalChainInvolvement, Set<FunctionalChainInvolvement>> fci2NextFcis = new HashMap<>();
  // Map of FCILink and FCIFunction to replace it
  Map<FunctionalChainInvolvementLink, FunctionalChainInvolvementFunction> fciLink2FciFunctions = new HashMap<>();

  @Override
  public String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource,
      XMLHelper helper, MigrationContext context) {
    // Since the class FunctionalChainInvolvement is now abstract, we temporarily create FunctionalChainInvolvementLink
    // instead
    if (typeQName.equals("org.polarsys.capella.core.data.fa:FunctionalChainInvolvement")) {
      proceedingContexts.add(context);
      return "org.polarsys.capella.core.data.fa:FunctionalChainInvolvementLink";
    }
    return super.getQName(peekObject, typeQName, feature, resource, helper, context);
  }

  @Override
  public boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position,
      XMLResource resource, MigrationContext context) {
    // Since the reference nextFunctionalChainInvolvements is now derived, it should be ignored
    if (proceedingContexts.contains(context) && peekObject instanceof FunctionalChainInvolvement
        && feature == FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS) {
      Set<FunctionalChainInvolvement> nextFCIs = fci2NextFcis.computeIfAbsent((FunctionalChainInvolvement) peekObject,
          key -> new HashSet<>());
      nextFCIs.add((FunctionalChainInvolvement) value);
      return true;
    }
    return super.ignoreSetFeatureValue(peekObject, feature, value, position, resource, context);
  }

  @Override
  public void unaryMigrationExecute(EObject object, MigrationContext context) {
    if (proceedingContexts.contains(context)) {
      if (object instanceof FunctionalChainInvolvementLink) {
        FunctionalChainInvolvementLink fcil = (FunctionalChainInvolvementLink) object;
        InvolvedElement involved = fcil.getInvolved();
        if (involved instanceof AbstractFunction) {
          FunctionalChainInvolvementFunction fcif = FaFactory.eINSTANCE.createFunctionalChainInvolvementFunction();
          fciLink2FciFunctions.put(fcil, fcif);
        }
      }
    }
    super.unaryMigrationExecute(object, context);
  }

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
    if (proceedingContexts.contains(context)) {
      // Replace temporary FunctionalChainInvolvementLink by a FunctionalChainInvolvementFunction while keeping its
      // features
      fciLink2FciFunctions.forEach(this::replaceFCILinkByFCIFunction);
      // Update link's source and target
      updateSourceTarget();
      // Update hierarchical context for FCILinks
      updateHierarchicalContext();
    }
    super.postMigrationExecute(executionManager, resourceSet, context);
  }

  protected void replaceFCILinkByFCIFunction(FunctionalChainInvolvementLink fciLink,
      FunctionalChainInvolvementFunction fciFunction) {
    // Replace its own features
    EList<EStructuralFeature> eAllStructuralFeatures = fciLink.eClass().getEAllStructuralFeatures();
    for (EStructuralFeature feature : eAllStructuralFeatures) {
      if (!feature.isDerived() && fciFunction.eClass().getEAllStructuralFeatures().contains(feature)) {
        fciFunction.eSet(feature, fciLink.eGet(feature));
      }
    }
    // Replace referencing features
    TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(fciLink);
    if (domain instanceof SemanticEditingDomain) {
      SemanticEditingDomain editingDomain = (SemanticEditingDomain) domain;
      SemanticCrossReferencer crossReferencer = editingDomain.getCrossReferencer();
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(fciLink,
          editingDomain.getCrossReferencer().isResolveProxyEnabled());
      for (Setting setting : inverseReferences) {
        EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
        if (!eStructuralFeature.isMany())
          setting.getEObject().eSet(eStructuralFeature, fciFunction);
        else {
          EList list = ((EList) setting.getEObject().eGet(eStructuralFeature));
          if (list.contains(fciLink)) {
            list.remove(fciLink);
            list.add(fciFunction);
          }
        }
      }
    }
    // Update fci2NextFcis map
    if (fci2NextFcis.keySet().contains(fciLink)) {
      fci2NextFcis.put(fciFunction, fci2NextFcis.get(fciLink));
      fci2NextFcis.remove(fciLink);
    }
    for (Set<FunctionalChainInvolvement> nextFcis : fci2NextFcis.values()) {
      if (nextFcis.contains(fciLink)) {
        nextFcis.remove(fciLink);
        nextFcis.add(fciFunction);
      }
    }
  }

  protected void updateSourceTarget() {
    for (Entry<FunctionalChainInvolvement, Set<FunctionalChainInvolvement>> entry : fci2NextFcis.entrySet()) {
      if (entry.getKey() instanceof FunctionalChainInvolvementLink) {
        for (FunctionalChainInvolvement fci : entry.getValue()) {
          if (fci instanceof FunctionalChainInvolvementFunction)
            ((FunctionalChainInvolvementLink) entry.getKey()).setTarget((FunctionalChainInvolvementFunction) fci);
        }

      }
      if (entry.getKey() instanceof FunctionalChainInvolvementFunction) {
        for (FunctionalChainInvolvement link : entry.getValue()) {
          if (link instanceof FunctionalChainInvolvementLink)
            ((FunctionalChainInvolvementLink) link).setSource((FunctionalChainInvolvementFunction) entry.getKey());
        }
      }
    }
  }

  protected void updateHierarchicalContext() {
    // These maps are used to avoid calculate many times first/last FCIs and their hierarchical context
    Map<FunctionalChainReference, List<List<FunctionalChainInvolvement>>> firstFlatHierachicalContextsMap = new HashMap<>();
    Map<FunctionalChainReference, List<List<FunctionalChainInvolvement>>> lastFlatHierachicalContextsMap = new HashMap<>();

    Map<FunctionalChainInvolvementLink, List<FunctionalChainInvolvement>> fciLink2SourceFciAndContext = new HashMap<>();
    Map<FunctionalChainInvolvementLink, List<FunctionalChainInvolvement>> fciLink2TargetFciAndContext = new HashMap<>();

    for (Entry<FunctionalChainInvolvement, Set<FunctionalChainInvolvement>> entry : fci2NextFcis.entrySet()) {
      // Calculate source hierarchical context for FCILinks which have FunctionalChainReference as source
      if (entry.getKey() instanceof FunctionalChainReference) {
        for (FunctionalChainInvolvement fci : entry.getValue()) {
          InvolvedElement involved = fci.getInvolved();
          if (fci instanceof FunctionalChainInvolvementLink && involved instanceof FunctionalExchange) {
            calculateSourceHierarchicalContext(lastFlatHierachicalContextsMap, fciLink2SourceFciAndContext,
                (FunctionalChainReference) entry.getKey(), (FunctionalExchange) involved,
                (FunctionalChainInvolvementLink) fci);
          }
        }
      }
      // Calculate source hierarchical context for FCILinks which have FunctionalChainReference as target
      if (entry.getKey() instanceof FunctionalChainInvolvementLink) {
        FunctionalChainInvolvementLink fciLink = ((FunctionalChainInvolvementLink) entry.getKey());
        for (FunctionalChainInvolvement involvement : entry.getValue()) {
          if (involvement instanceof FunctionalChainReference) {
            InvolvedElement involved = fciLink.getInvolved();
            if (involved instanceof FunctionalExchange) {
              calculateTargetHierarchicalContext(firstFlatHierachicalContextsMap, fciLink2TargetFciAndContext, fciLink,
                  (FunctionalChainReference) involvement, (FunctionalExchange) involved);
            }
          }
        }
      }
    }
    // Hierarchical context must be updated after above calculations to avoid side-effects on first/last FCI calculation
    updateSourceHierarchicalContext(fciLink2SourceFciAndContext);
    updateTargetHierarchicalContext(fciLink2TargetFciAndContext);
  }

  /**
   * Update FCILink's target and hierarchical context
   * 
   * @param fciLink2TargetFciAndContext
   */
  protected void updateTargetHierarchicalContext(
      Map<FunctionalChainInvolvementLink, List<FunctionalChainInvolvement>> fciLink2TargetFciAndContext) {
    for (Entry<FunctionalChainInvolvementLink, List<FunctionalChainInvolvement>> entry : fciLink2TargetFciAndContext
        .entrySet()) {
      FunctionalChainInvolvementLink fciLink = entry.getKey();
      List<FunctionalChainInvolvement> targetFciAndContext = entry.getValue();
      fciLink.setTarget((FunctionalChainInvolvementFunction) targetFciAndContext.get(0));
      for (int i = 1; i < targetFciAndContext.size(); i++) {
        fciLink.getTargetReferenceHierarchy().add((FunctionalChainReference) targetFciAndContext.get(i));
      }
    }
  }

  /**
   * Update FCILink's source and hierarchical context
   * 
   * @param fciLink2SourceFciAndContext
   */
  protected void updateSourceHierarchicalContext(
      Map<FunctionalChainInvolvementLink, List<FunctionalChainInvolvement>> fciLink2SourceFciAndContext) {
    for (Entry<FunctionalChainInvolvementLink, List<FunctionalChainInvolvement>> entry : fciLink2SourceFciAndContext
        .entrySet()) {
      FunctionalChainInvolvementLink fciLink = entry.getKey();
      List<FunctionalChainInvolvement> sourceFciAndContext = entry.getValue();
      fciLink.setSource((FunctionalChainInvolvementFunction) sourceFciAndContext.get(0));
      for (int i = 1; i < sourceFciAndContext.size(); i++) {
        fciLink.getSourceReferenceHierarchy().add((FunctionalChainReference) sourceFciAndContext.get(i));
      }
    }
  }

  /**
   * Calculate the target hierarchical context for a FCILink and update corresponding maps
   * 
   * @param firstFlatHierachicalContextsMap
   * @param fciLink2TargetFciAndContext
   * @param fcil
   * @param fcr
   * @param involved
   */
  protected void calculateTargetHierarchicalContext(
      Map<FunctionalChainReference, List<List<FunctionalChainInvolvement>>> firstFlatHierachicalContextsMap,
      Map<FunctionalChainInvolvementLink, List<FunctionalChainInvolvement>> fciLink2TargetFciAndContext,
      FunctionalChainInvolvementLink fcil, FunctionalChainReference fcr, FunctionalExchange fe) {
    AbstractFunction targetFunction = FunctionalExchangeExt.getTargetFunction(fe);
    InvolvedElement fcInvolved = fcr.getInvolved();
    if (fcInvolved instanceof FunctionalChain) {
      List<List<FunctionalChainInvolvement>> firstFlatHierachicalContexts = firstFlatHierachicalContextsMap
          .computeIfAbsent(fcr, FunctionalChainExt::getFirstFlatHierachicalContexts);
      // Choose one of the first FCIs which involves the target function
      List<FunctionalChainInvolvement> firstFCIAndContext = firstFlatHierachicalContexts.stream()
          .filter(fciHierarchicalContext -> fciHierarchicalContext.get(0).getInvolved() == targetFunction).findFirst()
          .orElse(Collections.emptyList());
      if (!firstFCIAndContext.isEmpty())
        fciLink2TargetFciAndContext.put(fcil, firstFCIAndContext);
    }
  }

  /**
   * Calculate the source hierarchical context for a FCILink and update corresponding maps
   * 
   * @param lastFlatHierachicalContextsMap
   * @param fciLink2SourceFciAndContext
   * @param fcr
   * @param fe
   * @param fcil
   */
  protected void calculateSourceHierarchicalContext(
      Map<FunctionalChainReference, List<List<FunctionalChainInvolvement>>> lastFlatHierachicalContextsMap,
      Map<FunctionalChainInvolvementLink, List<FunctionalChainInvolvement>> fciLink2SourceFciAndContext,
      FunctionalChainReference fcr, FunctionalExchange fe, FunctionalChainInvolvementLink fcil) {
    AbstractFunction sourceFunction = FunctionalExchangeExt.getSourceFunction(fe);
    if (fcr.getInvolved() instanceof FunctionalChain) {
      List<List<FunctionalChainInvolvement>> lastFlatHierachicalContexts = lastFlatHierachicalContextsMap
          .computeIfAbsent(fcr, FunctionalChainExt::getLastFlatHierachicalContexts);
      // Choose one of the last FCIs which involves the source function
      List<FunctionalChainInvolvement> lastFCIAndContext = lastFlatHierachicalContexts.stream()
          .filter(fciHierarchicalContext -> fciHierarchicalContext.get(0).getInvolved() == sourceFunction).findFirst()
          .orElse(Collections.emptyList());
      if (!lastFCIAndContext.isEmpty())
        fciLink2SourceFciAndContext.put(fcil, lastFCIAndContext);
    }
  }

  @Override
  public void dispose(MigrationContext context) {
    super.dispose(context);
    proceedingContexts.clear();
    fci2NextFcis.clear();
    fciLink2FciFunctions.clear();
  }
}
