/*******************************************************************************
 * Copyright (c) 2026 Obeo.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.layout.ju.testcases.elk;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Verify if 2 Objects have the same content.
 * <p>
 * For metamodel with MapFeature, textual serialization is not usable for comparison:
 * The order of keys is not predictable.
 * </p>
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class EObjectVerification {

  private final EObject sourceRoot;
  private final EObject targetRoot;
  
  private List<? extends EStructuralFeature> redundancies = List.of();

  private final Map<EObject, EObject> mapping = new HashMap<>();

  public EObjectVerification(EObject source, EObject target) {
    this.sourceRoot = source;
    this.targetRoot = target;
  }
  
  public void setRedundancies(List<? extends EStructuralFeature> redundancies) {
    this.redundancies = Objects.requireNonNull(redundancies);
  }

  /**
   * Verifies if the content of source and target are the same.
   * 
   * @throws IllegalStateException if the content is different
   */
  public void perform() throws IllegalStateException {
    verifySameStructure(sourceRoot, targetRoot);
    
    mapping.forEach((source, target) -> verifySameReferences(source, target));
  }

  private void triggerDifference(String property, Object sourceValue, Object targetValue) {
    throw new IllegalStateException(
        MessageFormat.format(
            "Unexpected value for {0} : source is {1} but target is {2}", //$NON-NLS-1$
            property, sourceValue, targetValue));
  }

  private void verifySameStructure(EObject source, EObject target) {
    if (source.eClass() != target.eClass()) {
      triggerDifference("EClass", source.eClass().getName(), target.eClass().getName());
    }
    mapping.put(source, target);

    for (var att : source.eClass().getEAllAttributes()) {
      verifySameAttribute(att, source, target);
    }

    for (var ref : source.eClass().getEAllReferences()) {
      if (ref.isContainment()) {
        verifySameContainment(ref, source, target);
      }
    }
  }

  private boolean verifyNonNulls(EReference ref, EObject source, EObject target) {
    if (source == null && target == null) {
      return false;
    }
    if (source == null) {
      triggerDifference(ref.getName(), null, toText(target));
    }
    if (target == null) {
      triggerDifference(ref.getName(), toText(source), null);
    }
    return true;
  }

  private String toText(EObject element) {
    return "[" + element.eClass().getName() + "]"; //$NON-NLS-1$  //$NON-NLS-2$
  }
  
  private void verifySameContainment(EReference ref, EObject source, EObject target) {
    Object sourceValue = source.eGet(ref);
    Object targetValue = target.eGet(ref);
    if (ref.getEType().getInstanceClass() == Map.Entry.class) {
      @SuppressWarnings("unchecked")
      var sourceValues = (EMap<?, ? extends EObject>) sourceValue;
      @SuppressWarnings("unchecked")
      var targetValues = (EMap<?, ? extends EObject>) targetValue;
      verifySameMap(ref, sourceValues, targetValues);
    } else if (!ref.isMany()) {
      if (verifyNonNulls(ref, (EObject) sourceValue, (EObject) targetValue)) {
        verifySameStructure((EObject) sourceValue, (EObject) targetValue);
      }
    } else {
      @SuppressWarnings("unchecked")
      List<? extends EObject> sourceValues = (List<? extends EObject>) sourceValue;
      @SuppressWarnings("unchecked")
      List<? extends EObject> targetValues = (List<? extends EObject>) targetValue;
      verifySameSize(ref, sourceValues, targetValues);
      int size = sourceValues.size();
      for (int index = 0; index < size; index++) {
        verifySameStructure(sourceValues.get(index), targetValues.get(index));
      }
    }
  }

  private void verifySameMap(EReference ref, EMap<?, ?> sourceValues, EMap<?, ?> targetValues) {
    // Unsupported EObjects
    // Map entry orders may differ
    verifySameSize(ref, sourceValues, targetValues);

    sourceValues.forEach(sourceEntry -> {
      Object targetValue = targetValues.get(sourceEntry.getKey());
      if (!Objects.equals(sourceEntry.getValue(), targetValue)) {
        triggerDifference(ref.getName(), sourceEntry.getValue(), targetValue);
      }
    });
  }

  private void verifySameSize(EReference ref, Collection<?> ones, Collection<?> others) {
    if (ones.size() != others.size()) {
      triggerDifference(ref.getName() + " size", ones.size(), others.size()); //$NON-NLS-1$
    }
  }
  
  private void verifySameAttribute(EAttribute att, EObject source, EObject target) {
    if (!att.isTransient() && !redundancies.contains(att)) {
      Object sourceValue = source.eGet(att);
      Object targetValue = target.eGet(att);

      if (!Objects.equals(sourceValue, targetValue)) {
        triggerDifference(att.getName(), sourceValue, targetValue);
      }
    }
  }

  private void verifySameReferences(EObject source, EObject target) {
    for (var ref : source.eClass().getEAllReferences()) {
      if (!ref.isContainment() && !ref.isTransient() && !ref.isDerived() && ref.isChangeable()
          && !redundancies.contains(ref)) {
        verifySameReference(ref, source, target);
      }
    }
  }

  private void verifySameReference(EReference ref, EObject source, EObject target) {
    Object sourceValue = source.eGet(ref);
    Object targetValue = target.eGet(ref);
    
    if (!ref.isMany()) {
      EObject sourceRef = (EObject) sourceValue;
      EObject targetRef = (EObject) targetValue;
      if (verifyNonNulls(ref, sourceRef, targetRef)) {
        EObject mapped = mapping.get(sourceRef);
        if (mapped != targetValue) {
          triggerDifference(ref.getName(), toText(mapped), toText(targetRef));
        }
      }
      return;
    }
    
    EReference opposite = ref.getEOpposite();
    if (opposite != null
        && (!opposite.isMany() || opposite.isContainment())) {
      return; // skip, opposite will have a simpler verification.
    }

    @SuppressWarnings("unchecked")
    List<? extends EObject> sourceValues = (List<? extends EObject>) sourceValue;
    @SuppressWarnings("unchecked")
    List<? extends EObject> targetValues = (List<? extends EObject>) targetValue;
    int size = sourceValues.size();
    if (size != targetValues.size()) {
      triggerDifference(ref.getName() + " size", size, targetValues.size());
    }
  
    if (ref.isOrdered()) {
      for (int index = 0; index < size; index++) {
        EObject mapped = mapping.get(sourceValues.get(index));
        EObject targetChild = targetValues.get(index);
        
        if (mapped != targetChild) {
          triggerDifference(ref.getName(), toText(mapped), toText(targetChild));
        }
      }
    } else {
      // Compare without order.
      Set<EObject> mappeds = sourceValues.stream()
        .map(mapping::get)
        .collect(Collectors.toSet());
      
      if (!mappeds.equals(Set.copyOf(targetValues))) {
        triggerDifference(ref.getName(), toText(source), toText(target));
      }
    }
  
  }

}
