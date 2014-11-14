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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * Utility class providing simple reusable services
 */
public final class MiscUtil { 

  private MiscUtil() {
    // Forbids instantiation
  }

  /**
   * Transactional command execution helper
   * @return true if command execution did not generate a runtime exception
   */
  public static boolean transactionallyExecute(Collection<? extends EObject> selection, ICommand cmd_p) {
    boolean result = true;
    try {
      ExecutionManager em = TransactionHelper.getExecutionManager(selection);
      em.execute(cmd_p);
    } catch(RuntimeException e) {
      result = false;
    }
    return result;
  }

  /**
   * Retrieve a cross referencer which covers all domain layers:
   * Capella, Sirius, GMF.
   * It is probably the one which is used by the Query Legacy interpreter.
   * @param objectInSession_p an EObject belonging to the desired session
   */
  public static ECrossReferenceAdapter getGlobalReferencer(EObject objectInSession_p) {
    ECrossReferenceAdapter result = null;
    EObject semanticElement = LayerUtil.toSemanticLevel(objectInSession_p);
    if (semanticElement != null) {
      Session session = SessionManager.INSTANCE.getSession(semanticElement);
      if (session != null) {
        result = session.getSemanticCrossReferencer();
      }
    }
    return result;
  }
  
  /**
   * Retrieve a cross referencer which only covers the semantic layer
   */
  public static ECrossReferenceAdapter getSemanticReferencer(EObject eobject) {
    TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(eobject);
    if (editingDomain instanceof SemanticEditingDomain) {
      return ((SemanticEditingDomain) editingDomain).getCrossReferencer();
    }
    return null;
  }
  
  /**
   * Navigate the non-navigable opposite of a reference
   */
  public static Set<EObject> getOpposites(EObject element_p, EReference ref_p) {
    assert ref_p != null;
    Set<EObject> result = new HashSet<EObject>();
    ECrossReferenceAdapter referencer = getGlobalReferencer(element_p);
    if (referencer != null) {
      Collection<Setting> settings =
        referencer.getNonNavigableInverseReferences(element_p);
      for (Setting setting : settings) {
        if (ref_p.equals(setting.getEStructuralFeature()))
          result.add(setting.getEObject());
      }
    }
    return result;
  }
  
  /**
   * Convenience method for a specific case of getOpposites where the reference
   * is known to be injective, i.e., there exists at most one referencing element
   */
  public static EObject getOpposite(EObject element_p, EReference ref_p) {
    assert ref_p != null;
    EObject result = null;
    Set<EObject> opposites = getOpposites(element_p, ref_p);
    if (!opposites.isEmpty())
      result = opposites.iterator().next();
    return result;
  }
  
  /**
   * From a given element, get all settings supporting addition which are
   * outside the given set of elements or their contents
   */
  public static List<Setting> getExternalSettingsForAddition(EObject element_p, Collection<EObject> internals_p) {
    List<Setting> result = new ArrayList<Setting>();
    ECrossReferenceAdapter referencer = getSemanticReferencer(element_p);
    if (referencer != null) {
      Collection<Setting> settings = referencer.getInverseReferences(element_p);
      for (Setting setting : settings) {
        if (setting.getEStructuralFeature() instanceof EReference) {
          EReference ref = (EReference)setting.getEStructuralFeature();
          if (!ref.isContainer() && !ref.isContainment() &&
              supportsAddition(ref) &&
              BusinessHelper.getInstance().updateWithDuplicatedValues(ref) &&
              !EcoreUtil.isAncestor(internals_p, setting.getEObject()))
            result.add(setting);
        }
      }
    }
    return result;
  }
  
  /**
   * Return whether a given reference can be used to add elements 
   */
  public static boolean supportsAddition(EReference reference_p) {
    return reference_p != null && reference_p.isMany() &&
    !reference_p.isDerived() && reference_p.isChangeable();
  }
  
  /**
   * Convert a parametric collection to another one by filtering out all
   * incompatible elements
   */
  public static <T> List<T> filter(Iterable<?> elements_p, Class<T> type) {
    List<T> result = new ArrayList<T>();
    for (Object current : elements_p) {
      if (type.isInstance(current))
        result.add(type.cast(current));
    }
    return result;
  }
  
  /**
   * From a set of elements, filter out those which are transitively contained
   * in others
   */
  public static <T extends EObject> List<T> getRoots(
      Collection<? extends T> elements_p) {
    List<T> result = new ArrayList<T>();
    Set<T> elements = new HashSet<T>(elements_p);
    for (T element : elements) {
      if (!result.contains(element) && isRootAmong(element, elements))
        result.add(element);
    }
    return result;
  }
  
  /**
   * Return whether the given element is not transitively contained by any
   * of the given elements, unless it is one of the given elements.
   */
  private static boolean isRootAmong(EObject element_p,
      Collection<? extends EObject> elements_p) {
    Collection<EObject> filtered = new ArrayList<EObject>(elements_p);
    filtered.remove(element_p);
    return !EcoreUtil.isAncestor(filtered, element_p);
  }
  
  /**
   * Return the lowest common ancestor, in the containment hierarchy,
   * of the given set of elements
   * @param acceptSelf_p whether the result can be any of the given elements
   */
  public static EObject getCommonAncestor(
      Collection<? extends EObject> elements_p, boolean acceptSelf_p) {
    if (elements_p == null || elements_p.isEmpty()) return null;
    Iterator<? extends EObject> it = elements_p.iterator();
    List<EObject> commonHierarchy = getAncestors(it.next());
    while (it.hasNext()) {
      List<EObject> currentHierarchy = getAncestors(it.next());
      // Compute intersection of ancestors
      commonHierarchy.retainAll(currentHierarchy);
    }
    // Exclude the given elements
    if (!acceptSelf_p) commonHierarchy.removeAll(elements_p);
    // Take lowest ancestor in common hierarchy
    if (commonHierarchy.isEmpty()) return null;
    return commonHierarchy.get(commonHierarchy.size()-1);
  }
  
  /**
   * Special case of getCommonAncestor(Collection)
   */
  public static EObject getCommonAncestor(EObject first_p, EObject second_p) {
    if (null == first_p || null == second_p) return null;
    return getCommonAncestor(Arrays.asList(new EObject[] {first_p, second_p}), true);
  }
  
  /**
   * Return the list of ancestors including self, from higher to deeper.
   * The result is not immutable but modifying it has no other impact.
   */
  private static List<EObject> getAncestors(EObject element_p) {
    if (element_p == null) return new ArrayList<EObject>();
    List<EObject> containerList = getAncestors(element_p.eContainer());
    containerList.add(element_p);
    return containerList;
  }
  
  /**
   * Given two root EObjects and one EObject among the eAllContents of the first root,
   * find the corresponding EObject in the eAllContents of the second based on structure
   * similarity, modulo a filter on elements for ignoring certain parts of the structure.
   * This is a more generic mechanism than the (original, copy) Map of EcoreUtil2.Copier:
   * it is bidirectional and it may relate otherwise independent elements.
   * Complexity is O(N) where N is the depth of the first root's containment tree.
   * @param originRoot_p the first root EObject
   * @param origin_p the EObject belonging to the eAllContents of the first EObject
   * @param targetRoot_p the second root EObject
   * @param filter_p an optional filter (null is accepted)
   * @return the corresponding EObject, or null if none was found
   */
  public static EObject getCorrespondingInStructure(EObject originRoot_p,
      EObject origin_p, EObject targetRoot_p, Filter filter_p) {
    assert originRoot_p != null && origin_p != null && targetRoot_p != null;
    List<Object> path = getContainmentPath(originRoot_p, origin_p, filter_p);
    EObject result = getFromPath(targetRoot_p, path, filter_p);
    return result;
  }
  
  /**
   * Given two root EObjects and one EObject among the eAllContents of the first root,
   * find the corresponding EObject in the eAllContents of the second based on structure
   * similarity, modulo a filter on elements for ignoring certain parts of the structure.
   * This is a more generic mechanism than the (original, copy) Map of EcoreUtil2.Copier:
   * it is bidirectional and it may relate otherwise independent elements.
   * Complexity is O(N) where N is the depth of the first root's containment tree.
   * @param originRoot_p the first root EObject
   * @param origin_p the EObject belonging to the eAllContents of the first EObject
   * @param targetRoot_p the second root EObject
   * @return the corresponding EObject, or null if none was found
   */
  public static EObject getCorrespondingInStructure(EObject originRoot_p,
      EObject origin_p, EObject targetRoot_p) {
    return getCorrespondingInStructure(originRoot_p, origin_p, targetRoot_p, null);
  }
  
  /**
   * This is a generalization of getCorrespondingInCopy(EObject originRoot_p,
   * EObject origin_p, EObject targetRoot_p, Filter filter_p) with collections.
   */
  public static EObject getCorrespondingInStructure(List<? extends EObject> originRoots_p,
      EObject origin_p, List<? extends EObject> targetRoots_p, Filter filter_p) {
    assert originRoots_p != null && origin_p != null && targetRoots_p != null;
    List<? extends EObject> filteredOriginRoots =
      (null == filter_p)? originRoots_p: filter_p.filter(originRoots_p);
    List<? extends EObject> filteredTargetRoots =
      (null == filter_p)? targetRoots_p: filter_p.filter(targetRoots_p);
    int max = Math.min(filteredOriginRoots.size(), filteredTargetRoots.size());
    for (int i=0; i<max; i++) {
      if (EcoreUtil.isAncestor(filteredOriginRoots.get(i), origin_p)) {
        EObject found = getCorrespondingInStructure(filteredOriginRoots.get(i), origin_p,
            filteredTargetRoots.get(i), filter_p);
        return found;
      }
    }
    return null;
  }
  public static EObject getCorrespondingInStructure(List<? extends EObject> originRoots_p,
      EObject origin_p, List<? extends EObject> targetRoots_p) {
    return getCorrespondingInStructure(originRoots_p, origin_p, targetRoots_p, null);
  }
  
  /**
   * Recursive tree-directed deletion
   */
  public static void deleteRec(EObject elt_p) {
    List<EObject> contents = elt_p.eContents();
    for(EObject child : contents)
      deleteRec(child);
    EcoreUtil.delete(elt_p);
  }
  
  /**
   * Return the union of the content trees of the given elements as an ordered set.
   */
  public static UniqueEList<EObject> getContentSet(
      Collection<? extends EObject> elts_p) {
    UniqueEList<EObject> result = new UniqueEList<EObject>();
    TreeIterator<EObject> iter;
    EObject current;
    for(EObject root : elts_p) {
      result.add(root);
      iter = root.eAllContents();
      while(iter.hasNext()) {
        current = iter.next();
        result.add(current);
      }
    }
    return result;
  }
  
  /**
   * Get the containment path of a given EObject w.r.t. one of its ancestors.
   * The containment path is defined as a sequence of EReference and Integer
   * (iff the preceding EReference has isMany() == true) corresponding to the
   * top-down navigation of the containment tree.
   * @param root_p the ancestor
   * @param child_p the EObject belonging to the eAllContents of the ancestor
   * @return the containment path (empty iff root_p == child_p) or null if failure
   */
  private static List<Object> getContainmentPath(EObject root_p, EObject child_p,
      Filter filter_p) {
    LinkedList<Object> result = new LinkedList<Object>(); // A stack would be nicer, but java.util.Stack inherits from Vector
    boolean success = getContainmentPathRec(root_p, child_p, result, filter_p);
    if (success) return result;
    return null;
  }
  private static boolean getContainmentPathRec(EObject root_p, EObject child_p,
      LinkedList<Object> path_p, Filter filter_p) {
    if (root_p != child_p) {
      EObject parent = child_p.eContainer();
      if (parent != null) {
        EReference ref = child_p.eContainmentFeature();
        if (ref.isMany()) {
          // Add index
          @SuppressWarnings("unchecked")
          List<EObject> values = (List<EObject>)parent.eGet(ref);
          List<EObject> filteredValues =
            (null == filter_p)? values: filter_p.filter(values);
          int index = filteredValues.indexOf(child_p);
          path_p.addFirst(new Integer(index));
        }
        // Add owning ref
        path_p.addFirst(ref);
        return getContainmentPathRec(root_p, parent, path_p, filter_p);
      }
      // root_p does not contain child_p
      path_p.clear();
      return false;
    }
    // else recursion ends
    return true;
  }
  
  /**
   * Get the EObject belonging to the eAllContents of the given root EObject and
   * located at the end of the given containment path.
   * @param root_p the root EObject
   * @param path_p the containment path
   */
  private static EObject getFromPath(EObject root_p, List<Object> path_p,
      Filter filter_p) {
    if (path_p == null) return null;
    Iterator<Object> it = path_p.iterator();
    EObject current = root_p;
    while (it.hasNext() && current != null) {
      try {
        EReference ref = (EReference)it.next();
        if (ref.isMany()) {
          Integer index = (Integer)it.next();
          @SuppressWarnings("unchecked")
          List<EObject> values = (List<EObject>)current.eGet(ref);
          List<EObject> filteredValues =
            (null == filter_p)? values: filter_p.filter(values);
          current = filteredValues.get(index.intValue());
        } else {
          current = (EObject)current.eGet(ref);
        }
      } catch (RuntimeException e) {
        current = null;
      }
    }
    return current;
  }
  
  /**
   * Assign a new unique ID to the element, if applicable.
   */
  public static void setNewId(EObject elt_p) {
    try {
      EcoreUtil.setID(elt_p, IdGenerator.createId());
    } catch (IllegalArgumentException e) {
      // No ID attribute: proceed
    }
  }
  
  
  /**
   * This abstract class defines a filter which allows ignoring certain elements
   * while navigating a content tree
   */
  public static abstract class Filter {
    /**
     * Return whether the given element is accepted by the filter
     */
    public abstract boolean qualifies(EObject obj_p);
    
    /**
     * Return the filtered version of the given collection
     */
    public final <T extends EObject> List<T> filter(Collection<T> collection_p) {
      List<T> result = new ArrayList<T>();
      for (T current : collection_p) {
        if (qualifies(current))
          result.add(current);
      }
      return result;
    }
  }
}
