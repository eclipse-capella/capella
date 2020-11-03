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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.platform.sirius.clipboard.Activator;
import org.polarsys.capella.core.platform.sirius.clipboard.Messages;

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
  public static boolean transactionallyExecute(Collection<? extends EObject> selection, ICommand cmd) {
    boolean result = true;
    try {
      ExecutionManager em = TransactionHelper.getExecutionManager(selection);
      em.execute(cmd);
      
    } catch(Exception e) {
      String message = Messages.CapellaDiagramPasteAction_Failure;
      if (e.getMessage() != null) {
        message = e.getMessage();
      }
      StatusManager.getManager().handle(new Status(IStatus.WARNING, Activator.PLUGIN_ID, message, e));
      result = false;
    }
    return result;
  }

  /**
   * Retrieve a cross referencer which covers all domain layers:
   * Capella, Sirius, GMF.
   * It is probably the one which is used by the Query Legacy interpreter.
   * @param objectInSession an EObject belonging to the desired session
   */
  public static ECrossReferenceAdapter getGlobalReferencer(EObject objectInSession) {
    ECrossReferenceAdapter result = null;
    EObject semanticElement = LayerUtil.toSemanticLevel(objectInSession);
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
  public static Set<EObject> getOpposites(EObject element, EReference ref) {
    assert ref != null;
    Set<EObject> result = new HashSet<EObject>();
    ECrossReferenceAdapter referencer = getGlobalReferencer(element);
    if (referencer != null) {
      Collection<Setting> settings =
        referencer.getNonNavigableInverseReferences(element);
      for (Setting setting : settings) {
        if (ref.equals(setting.getEStructuralFeature()))
          result.add(setting.getEObject());
      }
    }
    return result;
  }
  
  /**
   * Convenience method for a specific case of getOpposites where the reference
   * is known to be injective, i.e., there exists at most one referencing element
   */
  public static EObject getOpposite(EObject element, EReference ref) {
    assert ref != null;
    EObject result = null;
    Set<EObject> opposites = getOpposites(element, ref);
    if (!opposites.isEmpty())
      result = opposites.iterator().next();
    return result;
  }
  
  /**
   * From a given element, get all settings supporting addition which are
   * outside the given set of elements or their contents
   */
  public static List<Setting> getExternalSettingsForAddition(EObject element, Collection<EObject> internals) {
    List<Setting> result = new ArrayList<Setting>();
    ECrossReferenceAdapter referencer = getSemanticReferencer(element);
    if (referencer != null) {
      Collection<Setting> settings = referencer.getInverseReferences(element);
      for (Setting setting : settings) {
        if (setting.getEStructuralFeature() instanceof EReference) {
          EReference ref = (EReference)setting.getEStructuralFeature();
          if (!ref.isContainer() && !ref.isContainment() &&
              supportsAddition(ref) &&
              BusinessHelper.getInstance().updateWithDuplicatedValues(ref) &&
              !EcoreUtil.isAncestor(internals, setting.getEObject()))
            result.add(setting);
        }
      }
    }
    return result;
  }
  
  /**
   * Return whether a given reference can be used to add elements 
   */
  public static boolean supportsAddition(EReference reference) {
    return reference != null && reference.isMany() &&
    !reference.isDerived() && reference.isChangeable();
  }
  
  /**
   * Convert a parametric collection to another one by filtering out all
   * incompatible elements
   */
  public static <T> List<T> filter(Iterable<?> elements, Class<T> type) {
    List<T> result = new ArrayList<T>();
    for (Object current : elements) {
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
      Collection<? extends T> elements) {
    List<T> result = new ArrayList<T>();
    Set<T> elts = new HashSet<T>(elements);
    for (T element : elts) {
      if (!result.contains(element) && isRootAmong(element, elts))
        result.add(element);
    }
    return result;
  }
  
  /**
   * Return whether the given element is not transitively contained by any
   * of the given elements, unless it is one of the given elements.
   */
  private static boolean isRootAmong(EObject element,
      Collection<? extends EObject> elements) {
    Collection<EObject> filtered = new ArrayList<EObject>(elements);
    filtered.remove(element);
    return !EcoreUtil.isAncestor(filtered, element);
  }
  
  /**
   * Return the lowest common ancestor, in the containment hierarchy,
   * of the given set of elements
   * @param acceptSelf whether the result can be any of the given elements
   */
  public static EObject getCommonAncestor(
      Collection<? extends EObject> elements, boolean acceptSelf) {
    if (elements == null || elements.isEmpty()) return null;
    Iterator<? extends EObject> it = elements.iterator();
    List<EObject> commonHierarchy = getAncestors(it.next());
    while (it.hasNext()) {
      List<EObject> currentHierarchy = getAncestors(it.next());
      // Compute intersection of ancestors
      commonHierarchy.retainAll(currentHierarchy);
    }
    // Exclude the given elements
    if (!acceptSelf) commonHierarchy.removeAll(elements);
    // Take lowest ancestor in common hierarchy
    if (commonHierarchy.isEmpty()) return null;
    return commonHierarchy.get(commonHierarchy.size()-1);
  }
  
  /**
   * Special case of getCommonAncestor(Collection)
   */
  public static EObject getCommonAncestor(EObject first, EObject second) {
    if (null == first || null == second) return null;
    return getCommonAncestor(Arrays.asList(new EObject[] {first, second}), true);
  }
  
  /**
   * Return the list of ancestors including self, from higher to deeper.
   * The result is not immutable but modifying it has no other impact.
   */
  private static List<EObject> getAncestors(EObject element) {
    if (element == null) return new ArrayList<EObject>();
    List<EObject> containerList = getAncestors(element.eContainer());
    containerList.add(element);
    return containerList;
  }
  
  /**
   * Given two root EObjects and one EObject among the eAllContents of the first root,
   * find the corresponding EObject in the eAllContents of the second based on structure
   * similarity, modulo a filter on elements for ignoring certain parts of the structure.
   * This is a more generic mechanism than the (original, copy) Map of EcoreUtil2.Copier:
   * it is bidirectional and it may relate otherwise independent elements.
   * Complexity is O(N) where N is the depth of the first root's containment tree.
   * @param originRoot the first root EObject
   * @param origin the EObject belonging to the eAllContents of the first EObject
   * @param targetRoot the second root EObject
   * @param filter an optional filter (null is accepted)
   * @return the corresponding EObject, or null if none was found
   */
  public static EObject getCorrespondingInStructure(EObject originRoot,
      EObject origin, EObject targetRoot, Filter filter) {
    assert originRoot != null && origin != null && targetRoot != null;
    List<Object> path = getContainmentPath(originRoot, origin, filter);
    EObject result = getFromPath(targetRoot, path, filter);
    return result;
  }
  
  /**
   * Given two root EObjects and one EObject among the eAllContents of the first root,
   * find the corresponding EObject in the eAllContents of the second based on structure
   * similarity, modulo a filter on elements for ignoring certain parts of the structure.
   * This is a more generic mechanism than the (original, copy) Map of EcoreUtil2.Copier:
   * it is bidirectional and it may relate otherwise independent elements.
   * Complexity is O(N) where N is the depth of the first root's containment tree.
   * @param originRoot the first root EObject
   * @param origin the EObject belonging to the eAllContents of the first EObject
   * @param targetRoot the second root EObject
   * @return the corresponding EObject, or null if none was found
   */
  public static EObject getCorrespondingInStructure(EObject originRoot,
      EObject origin, EObject targetRoot) {
    return getCorrespondingInStructure(originRoot, origin, targetRoot, null);
  }
  
  /**
   * This is a generalization of getCorrespondingInCopy(EObject originRoot,
   * EObject origin, EObject targetRoot, Filter filter) with collections.
   */
  public static EObject getCorrespondingInStructure(List<? extends EObject> originRoots,
      EObject origin, List<? extends EObject> targetRoots, Filter filter) {
    assert originRoots != null && origin != null && targetRoots != null;
    List<? extends EObject> filteredOriginRoots =
      (null == filter)? originRoots: filter.filter(originRoots);
    List<? extends EObject> filteredTargetRoots =
      (null == filter)? targetRoots: filter.filter(targetRoots);
    int max = Math.min(filteredOriginRoots.size(), filteredTargetRoots.size());
    for (int i=0; i<max; i++) {
      if (EcoreUtil.isAncestor(filteredOriginRoots.get(i), origin)) {
        EObject found = getCorrespondingInStructure(filteredOriginRoots.get(i), origin,
            filteredTargetRoots.get(i), filter);
        return found;
      }
    }
    return null;
  }
  public static EObject getCorrespondingInStructure(List<? extends EObject> originRoots,
      EObject origin, List<? extends EObject> targetRoots) {
    return getCorrespondingInStructure(originRoots, origin, targetRoots, null);
  }
  
  /**
   * Recursive tree-directed deletion
   */
  public static void deleteRec(EObject elt) {
    List<EObject> contents = elt.eContents();
    for(EObject child : contents)
      deleteRec(child);
    EcoreUtil.delete(elt);
  }
  
  /**
   * Return the union of the content trees of the given elements as an ordered set.
   */
  public static UniqueEList<EObject> getContentSet(
      Collection<? extends EObject> elts) {
    UniqueEList<EObject> result = new UniqueEList<EObject>();
    TreeIterator<EObject> iter;
    EObject current;
    for(EObject root : elts) {
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
   * @param root the ancestor
   * @param child the EObject belonging to the eAllContents of the ancestor
   * @return the containment path (empty iff root == child) or null if failure
   */
  private static List<Object> getContainmentPath(EObject root, EObject child,
      Filter filter) {
    LinkedList<Object> result = new LinkedList<Object>(); // A stack would be nicer, but java.util.Stack inherits from Vector
    boolean success = getContainmentPathRec(root, child, result, filter);
    if (success) return result;
    return null;
  }
  private static boolean getContainmentPathRec(EObject root, EObject child,
      LinkedList<Object> path, Filter filter) {
    if (root != child) {
      EObject parent = child.eContainer();
      if (parent != null) {
        EReference ref = child.eContainmentFeature();
        if (ref.isMany()) {
          // Add index
          @SuppressWarnings("unchecked")
          List<EObject> values = (List<EObject>)parent.eGet(ref);
          List<EObject> filteredValues =
            (null == filter)? values: filter.filter(values);
          int index = filteredValues.indexOf(child);
          path.addFirst(Integer.valueOf(index));
        }
        // Add owning ref
        path.addFirst(ref);
        return getContainmentPathRec(root, parent, path, filter);
      }
      // root does not contain child
      path.clear();
      return false;
    }
    // else recursion ends
    return true;
  }
  
  /**
   * Get the EObject belonging to the eAllContents of the given root EObject and
   * located at the end of the given containment path.
   * @param root the root EObject
   * @param path the containment path
   */
  private static EObject getFromPath(EObject root, List<Object> path,
      Filter filter) {
    if (path == null) return null;
    Iterator<Object> it = path.iterator();
    EObject current = root;
    while (it.hasNext() && current != null) {
      try {
        EReference ref = (EReference)it.next();
        if (ref.isMany()) {
          Integer index = (Integer)it.next();
          @SuppressWarnings("unchecked")
          List<EObject> values = (List<EObject>)current.eGet(ref);
          List<EObject> filteredValues =
            (null == filter)? values: filter.filter(values);
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
  public static void setNewId(EObject elt) {
    try {
      EcoreUtil.setID(elt, IdGenerator.createId());
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
    public abstract boolean qualifies(EObject obj);
    
    /**
     * Return the filtered version of the given collection
     */
    public final <T extends EObject> List<T> filter(Collection<T> collection) {
      List<T> result = new ArrayList<T>();
      for (T current : collection) {
        if (qualifies(current))
          result.add(current);
      }
      return result;
    }
  }
}
