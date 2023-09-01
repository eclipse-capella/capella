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
package org.polarsys.capella.core.model.semantic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;

/**
 * Tools for the Semantic (a.k.a. Simplified) Capella meta model.
 * 
 * The shared instance does not emit any log messages. If you want, you can create your own instance by using one of the
 * static factory methods <code>newXYZInstance(...)</code>, passing a Logger. The tool methods will then log to the
 * given logger, for example, getEAllReferences will log a message for each reference that is invisible in the semantic
 * meta model. To avoid spamming the log, an internal set (per instance) is maintained to avoid logging a message for a
 * given meta model element more than once.
 */
public class SemanticModelUtil {

  /**
   * Annotation source used to indicate semantic epackages, metaclasses and features.
   * 
   * @Deprecated use SemanticCapellaMetadata.SOURCE_SEMANTIC
   */
  @Deprecated
  public static final String SEMANTIC = SimplifiedCapellaMetadata.SOURCE_SEMANTIC;

  /**
   * Annotation key used to indicate containment semantics.
   * 
   * @Deprecated use SemanticCapellaMetadata.KEY_FEATURE
   */
  @Deprecated
  public static final String KEY_CONTAINMENT = SimplifiedCapellaMetadata.KEY_DETAILS_FEATURE;

  /*
   * Our logger
   */
  protected Logger logger;

  /*
   * The shared instance.
   */
  private static final SemanticModelUtil semanticInstance = new SemanticModelUtil(SimplifiedCapellaMetadata.INSTANCE,
      null);

  /*
   * Decides if an EModelElement is visible or not
   */
  private final EcoreSwitch<Boolean> ecoreSwitch;

  /*
   * Remembers for which element we already sent a debug message to avoid spamming the log.
   */
  private final Set<EModelElement> messageSent;

  /*
   * The metadata that backs this instance
   */
  protected final SimplifiedCapellaMetadata metadata;

  /**
   * @deprecated use create() instead.
   * @param logger
   * @return
   */
  @Deprecated
  public static SemanticModelUtil newSemanticInstance(Logger logger) {
    return create(SimplifiedCapellaMetadata.INSTANCE, logger);
  }

  /**
   * Returns the shared non-logging instance.
   */
  public static SemanticModelUtil getInstance() {
    return semanticInstance;
  }

  /**
   * Returns the semantic instance, which always behaves <i>semantically</i>, independent of the value of
   * <code>ISemanticCommonPreferences.isSemanticMode()</code>. if the preference is <code>false</code>
   * 
   * @Deprecated use getInstance()
   */
  @Deprecated
  public static SemanticModelUtil getSemantic() {
    return semanticInstance;
  }

  /**
   * Creates a new instance using supplied metadata and logger.
   * 
   * @param metadata
   *          Never null.
   * @param logger
   *          May be null.
   */
  public static SemanticModelUtil create(SimplifiedCapellaMetadata metadata, Logger logger) {
    return new SemanticModelUtil(metadata, logger);
  }

  SemanticModelUtil(SimplifiedCapellaMetadata metadata, Logger logger) {
    messageSent = new HashSet<EModelElement>();
    ecoreSwitch = new EcoreSwitch<Boolean>() {
      @SuppressWarnings("boxing")
      @Override
      public Boolean caseEStructuralFeature(EStructuralFeature feature) {
        return SemanticModelUtil.this.metadata.isNavigable(feature);
      }

      @SuppressWarnings("boxing")
      @Override
      public Boolean caseEClassifier(EClassifier clazz) {
        return SemanticModelUtil.this.metadata.isSemantic(clazz);
      }

      @Override
      public Boolean defaultCase(EObject e) {
        return Boolean.TRUE;
      }

    };
    this.logger = logger;
    this.metadata = metadata;
  }

  /**
   * Semantic containments are EReferences that are navigable references that either have the containment annotation key
   * set, or are "normal" containment references.
   **/
  public final boolean isSemanticContainment(EReference reference) {
    return metadata.isContainment(reference);
  }

  /**
   * Returns the feature that semantically contains the given element. This may look weird, since in semantic mode
   * elements are split into several 'virtual' containment features in order to obtain strongly typed content subsets.
   * Most importantly in semantic mode this may return an EReference that has 'containment' set to false!
   * 
   * @see EObject.eContainingFeature()
   * @param element
   * @throws NoContainmentException
   *           if the element has a non-null container and no navigable containment feature can be found
   */
  public EStructuralFeature eContainingFeature(EObject element) throws NoContainmentException {

    // FIXME this will break probably for feature maps
    EStructuralFeature containingFeature = element.eContainingFeature();

    if (containingFeature == null) {
      return null;
    }

    if (metadata.isNavigable(containingFeature)) {
      return containingFeature;
    }

    // the element's containing feature is not navigable so we must now search
    // all "navigable containment" features of the container and its supertypes
    // the element. This will feel a bit weird now..
    containingFeature = null;
    EObject container = element.eContainer();
    if (container != null) {
      List<EReference> allContainments = container.eClass().getEAllReferences();
      for (EReference candidate : allContainments) {

        if (!container.eIsSet(candidate)) {
          continue;
        }

        // no need to check other containment references
        if (candidate.isContainment()) {
          continue;
        }

        if (!(metadata.isNavigable(candidate) && metadata.isContainment(candidate))) {
          continue;
        }

        // we don't have to search references whose declared type
        // incompatible with this element's type
        if (!candidate.getEReferenceType().isSuperTypeOf(element.eClass())) {
          continue;
        }

        // now search
        if (candidate.isMany()) {
          if (((List<?>) container.eGet(candidate)).contains(element)) {
            containingFeature = candidate;
            break;
          }
        } else if (container.eGet(candidate) == element) {
          containingFeature = candidate;
          break;
        }
      }

      if (containingFeature == null) {
        // None of the container's "annotated containment" features
        // contains this element.
        throw new NoContainmentException();
      }
    }
    return containingFeature;
  }

  /**
   * Only considers semantic contents of the argument.
   * 
   * @see SimplifiedCapellaMetadata.isContainment
   * @see SimplifiedCapellaMetadata.isNavigable
   * @param element
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<EObject> eContents(EObject element) {

    // a set to detect and warn if we find the same element twice (which
    // indicates a bug in the metamodel definition/implementation)
    Set<EObject> result = new LinkedHashSet<EObject>();
    for (EReference ref : element.eClass().getEAllReferences()) {
      if (metadata.isNavigable(ref) && metadata.isContainment(ref)) {
        Object children = element.eGet(ref);
        if (children != null) {
          if (ref.isMany()) {
            for (EObject e : ((List<EObject>) children)) {
              if (metadata.isSemantic(e.eClass())) {
                if (!result.add(e)) {
                  logDuplicateContainment(element, e, ref);
                }
              } else {
                logNonSemanticClass(e.eClass());
              }
            }
          } else if (metadata.isSemantic(((EObject) children).eClass())) {
            if (!result.add((EObject) children)) {
              logDuplicateContainment(element, (EObject) children, ref);
            }
          } else {
            logNonSemanticClass(((EObject) children).eClass());
          }
        }
      } else if (ref.isContainment() && ((logger != null) && logger.isDebugEnabled() && !messageSent.contains(ref))) {
        messageSent.add(ref);
        logger.debug("[Semantic M2] Filtering non-navigable children: " + LogHelper.makeFeatureDescription(ref)); //$NON-NLS-1$
      }
    }
    return new ArrayList<EObject>(result);
  }

  /**
   * @param eClass
   */
  private void logNonSemanticClass(EClass eClass) {
    if (logger != null && logger.isDebugEnabled() && !messageSent.contains(eClass)) {
      logger.debug("[Semantic M2] Filtering instances of non-semantic class: " + eClass.getName()); //$NON-NLS-1$
    }
  }

  /**
   * In semantic mode, non-navigable references are not considered.
   * 
   * @see EObject.getEAllReferences()
   */
  public List<EReference> getEAllReferences(EClass clazz) {
    List<EReference> result = new ArrayList<EReference>();
    for (EReference ref : clazz.getEAllReferences()) {
      if (metadata.isNavigable(ref)) {
        result.add(ref);
      } else if ((logger != null) && logger.isDebugEnabled() && !messageSent.contains(ref)) {
        messageSent.add(ref);
        logger.debug("[Semantic M2] Filtering non-navigable reference: " + LogHelper.makeFeatureDescription(ref)); //$NON-NLS-1$
      }
    }
    return result;
  }

  /**
   * In semantic mode, non-navigable attributes are not considered.
   * 
   * @see EObject.getEAllAttributes()
   */
  public List<EAttribute> getEAllAttributes(EClass clazz) {
    List<EAttribute> result = new ArrayList<EAttribute>();
    for (EAttribute attrib : clazz.getEAllAttributes()) {
      if (metadata.isNavigable(attrib)) {
        result.add(attrib);
      } else if ((logger != null) && logger.isDebugEnabled() && !messageSent.contains(attrib)) {
        messageSent.add(attrib);
        logger.debug("[Semantic M2] Filtering non-navigable attribute: " + LogHelper.makeFeatureDescription(attrib)); //$NON-NLS-1$
      }
    }
    return result;
  }

  /**
   * In semantic mode, non navigable features are not considered.
   * 
   * @see EObject.getEStructuralFeatures()
   * @param clazz
   * @return
   */
  public List<EStructuralFeature> getEStructuralFeatures(EClass clazz) {
    List<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
    for (EStructuralFeature feature : clazz.getEStructuralFeatures()) {
      if (metadata.isNavigable(feature)) {
        result.add(feature);
      }
    }
    return result;
  }

  /**
   * If semantic mode is disabled, returns true in all cases. If semantic mode is enabled, different rules apply for
   * different types of elements: - An EClassifier is visible if it is tagged with the 'SEMANTIC' annotation. - An
   * EStructuralFeature is visible if it is tagged with the 'NAVIGABLE' annotation. - All other types of elements are
   * visible. FIXME spec?
   * 
   * @param element
   *          the element to check
   * @return whether the element is visible or not.
   */
  @SuppressWarnings("boxing")
  public boolean isVisible(EModelElement element) {
    return ecoreSwitch.doSwitch(element);
  }

  public static class NoContainmentException extends Exception {
    private static final long serialVersionUID = 1L;
  }

  private void logDuplicateContainment(EObject parent, EObject child, EReference one) {
    if (logger != null) {
      logger.error("[Semantic M2] Containment error: " + child); //$NON-NLS-1$
      logger.error("[Semantic M2] Containment error: " + child.eContainingFeature()); //$NON-NLS-1$
      logger.error("[Semantic M2] Containment error: " + one); //$NON-NLS-1$
    }
  }

  /**
   * 
   * @return all semantic classes in Capella meta-model
   */
  public List<EClass> getAllCapellaSemanticClasses() {
    List<EClass> capellaSemanticClasses = new ArrayList<EClass>();
    capellaSemanticClasses.addAll(getSemanticClasses(CsPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(CtxPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(EpbsPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(FaPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(InformationPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(InteractionPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(LaPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(CapellacommonPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(CapellacorePackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(CapellamodellerPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(OaPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(PaPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(SharedmodelPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(ActivityPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(BehaviorPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(ModellingcorePackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(LibrariesPackage.eINSTANCE));
    capellaSemanticClasses.addAll(getSemanticClasses(RePackage.eINSTANCE));
    return capellaSemanticClasses;
  }

  /**
   * 
   * @return all semantic classes in Capella meta-model
   */
  public List<EStructuralFeature> getAllCapellaSemanticStructuralFeatures() {
    List<EStructuralFeature> capellaSemanticStructuralFeatures = new ArrayList<EStructuralFeature>();
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(CsPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(CtxPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(EpbsPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(FaPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(InformationPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(InteractionPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(LaPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(CapellacommonPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(CapellacorePackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(CapellamodellerPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(OaPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(PaPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(SharedmodelPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(ActivityPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(BehaviorPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(ModellingcorePackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(LibrariesPackage.eINSTANCE));
    capellaSemanticStructuralFeatures.addAll(getSemanticStructuralFeatures(RePackage.eINSTANCE));
    return capellaSemanticStructuralFeatures;
  }

  /**
   * 
   * @param ePackage
   * @return all semantic classes in a package
   */
  public List<EClass> getSemanticClasses(EPackage ePackage) {
    List<EClass> result = new ArrayList<EClass>();
    List<EClassifier> classifiers = ePackage.getEClassifiers();
    for (EClassifier classifer : classifiers) {
      if (classifer instanceof EClass && metadata.isSemantic(classifer)) {
        result.add((EClass) classifer);
      }
    }
    // Search also in sub-packages
    List<EPackage> subPackages = ePackage.getESubpackages();
    for (EPackage subPkg : subPackages) {
      result.addAll(getSemanticClasses(subPkg));
    }
    return result;
  }

  /**
   * 
   * @param ePackage
   * @return all semantic structural features in a package
   */
  public List<EStructuralFeature> getSemanticStructuralFeatures(EPackage ePackage) {
    List<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
    List<EClassifier> classifiers = ePackage.getEClassifiers();
    for (EClassifier classifer : classifiers) {
      if (classifer instanceof EClass) {
        for (EStructuralFeature structuralFeature : ((EClass) classifer).getEStructuralFeatures())
          if (metadata.isSemantic(structuralFeature))
            result.add(structuralFeature);
      }
    }
    // Search also in sub-packages
    List<EPackage> subPackages = ePackage.getESubpackages();
    for (EPackage subPkg : subPackages) {
      result.addAll(getSemanticStructuralFeatures(subPkg));
    }
    return result;
  }

}
