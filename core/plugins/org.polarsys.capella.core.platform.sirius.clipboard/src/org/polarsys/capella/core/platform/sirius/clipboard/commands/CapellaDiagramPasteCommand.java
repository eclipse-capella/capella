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
package org.polarsys.capella.core.platform.sirius.clipboard.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.gmf.runtime.emf.clipboard.core.ClipboardUtil;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.platform.sirius.clipboard.Messages;
import org.polarsys.capella.core.platform.sirius.clipboard.util.BusinessHelper;
import org.polarsys.capella.core.platform.sirius.clipboard.util.CapellaDiagramClipboard;
import org.polarsys.capella.core.platform.sirius.clipboard.util.DiagramBusinessHelper;
import org.polarsys.capella.core.platform.sirius.clipboard.util.GmfUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.MiscUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.NamingUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.SiriusUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.StorageLocation;

/**
 * A command which pastes Capella diagrammatic and semantic elements based on business criteria
 */
public class CapellaDiagramPasteCommand extends AbstractResultCommand {

  // The non-null list of GMF elements to paste into
  private List<View> _targets;

  // The suffix of the copied semantic elements, computed during paste
  private String _suffix;

  // Mapping of the actually pasted Sirius elements to their original versions,
  // computed during Semantic/Sirius layers reconciliation
  private Map<DRepresentationElement, DRepresentationElement> _pastedSiriusElementsMapping;

  // Whether the user simply copy/pasted without selecting a paste target
  private boolean _isStandbyUsage;

  /**
   * Constructor
   * @param targets_p the non-null, non-empty list of views into which paste must happen
   */
  public CapellaDiagramPasteCommand(List<? extends View> targets_p) {
    assert targets_p != null;
    _targets = new ArrayList<View>(targets_p);
    _suffix = null;
    _pastedSiriusElementsMapping = new HashMap<DRepresentationElement, DRepresentationElement>();
    _isStandbyUsage = checkStandbyUsage();
  }

  /**
   * @see _pastedSiriusElementsMapping
   */
  public Map<DRepresentationElement, DRepresentationElement> getPastedSiriusElementsOrigins() {
    return Collections.unmodifiableMap(_pastedSiriusElementsMapping);
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    // Capella layer
    EObject semanticSource = getSemanticSource();
    EObject semanticTarget = getSemanticTarget();
    if ((semanticSource == null) || (semanticTarget == null)) {
      return; // failure: empty result
    }
    List<EObject> semanticOrigins = getSemanticRootsForCopy(CapellaDiagramClipboard.getInstance().getSiriusElements(), semanticTarget);
    List<EObject> semanticCopies = pasteCapellaElements(semanticOrigins, semanticSource, semanticTarget);
    if (semanticCopies == null) {
      return; // failure: empty result
    }
    // Sirius layer (GMF layer will be automatically updated)
    List<EObject> rawAllSiriusOrigins = MiscUtil.getContentSet(CapellaDiagramClipboard.getInstance().getSiriusElements());
    List<DSemanticDecorator> allSiriusOrigins = MiscUtil.filter(rawAllSiriusOrigins, DSemanticDecorator.class);
    for (View target : _targets) {
      EcoreUtil.resolveAll(target);
    }
    EObject siriusTarget = LayerUtil.getSiriusElement(getGmfTarget());
    CapellaDiagramClipboard clipboard = CapellaDiagramClipboard.getInstance();
    if (clipboard.isEmpty() || SiriusUtil.layoutIsConstrained(siriusTarget)) {
      // Do not handle graphical layer if the clipboard is empty or
      // the target diagram has a too constrained layout
      setResults(Collections.emptyList());
      return;
    }
    final String data = clipboard.getSiriusData();
    Collection<EObject> pastedSiriusElements = pasteSiriusElements(data, siriusTarget);
    // Reconcile the Sirius and Capella layers
    List<EObject> result = reconcileSiriusAndSemanticLayers(pastedSiriusElements, allSiriusOrigins, semanticOrigins, semanticCopies);
    setResults(result);
  }

  /**
   * Return whether the user simply copy/pasted without selecting a paste target
   */
  protected boolean checkStandbyUsage() {
    Set<EObject> copySelection = new HashSet<EObject>(CapellaDiagramClipboard.getInstance().getSiriusElements());
    Set<EObject> pasteSelection = new HashSet<EObject>(LayerUtil.toSirius(_targets));
    return copySelection.equals(pasteSelection);
  }

  /**
   * Return the main GMF element in which paste must happen
   * @return a non-null view
   */
  public View getGmfTarget() {
    View result = _targets.get(0);
    if (_isStandbyUsage && (result.eContainer() instanceof View) && !(result.eContainer() instanceof Edge)) {
      result = (View) result.eContainer();
    }
    return result;
  }

  /**
   * Return the element which is considered to be the source of the copy/paste at the semantic level
   */
  protected EObject getSemanticSource() {
    // Try and compute the common ancestor of the semantic elements
    // of the GMF roots
    List<View> gmfOrigins = CapellaDiagramClipboard.getInstance().getGmfElements();
    List<View> gmfRoots = MiscUtil.getRoots(gmfOrigins);
    List<EObject> semanticGmfRoots = LayerUtil.toSemanticLevel(gmfRoots);
    EObject result = MiscUtil.getCommonAncestor(semanticGmfRoots, false);
    if (result == null) {
      // If none, get the semantic element of the common ancestor of
      // the GMF roots
      View gmfSource = GmfUtil.getCommonViewAncestor(gmfOrigins);
      result = LayerUtil.toSemanticLevel(gmfSource);
    }
    return result;
  }

  /**
   * Return the element which is considered to be the target of the copy/paste at the semantic level
   */
  protected EObject getSemanticTarget() {
    return DiagramBusinessHelper.getInstance().getRepresentedStorage(getGmfTarget());
  }

  /**
   * Given a copy of Sirius elements, corresponding semantic elements and copied semantic elements, bind the Sirius elements to the copied semantic elements,
   * filter out the Sirius elements whose semantic element has no counterpart among the copies, and map the resulting Sirius elements to the original ones.
   * @return the Sirius elements among siriusElements_p which are actually kept in the diagram
   */
  protected List<EObject> reconcileSiriusAndSemanticLayers(Iterable<? extends EObject> siriusElements_p, Iterable<? extends EObject> siriusOrigins_p,
      List<EObject> semanticOrigins_p, List<EObject> semanticCopies_p) {
    List<EObject> result = new LinkedList<EObject>();
    Collection<EObject> toDelete = new ArrayList<EObject>();
    for (EObject siriusElement : siriusElements_p) {
      reconcileSiriusElement(siriusElement, semanticOrigins_p, semanticCopies_p, siriusOrigins_p, result, toDelete);
      Iterator<EObject> it = siriusElement.eAllContents();
      while (it.hasNext()) {
        reconcileSiriusElement(it.next(), semanticOrigins_p, semanticCopies_p, siriusOrigins_p, result, toDelete);
      }
    }
    for (EObject current : toDelete) {
      EcoreUtil.remove(current);
    }
    return result;
  }

  /**
   * Inner computation for reconcileSiriusAndSemanticLayers.
   */
  protected void reconcileSiriusElement(EObject siriusElement_p, List<EObject> semanticOrigins_p, List<EObject> semanticCopies_p,
      Iterable<? extends EObject> allSiriusOrigins_p, List<EObject> result_p, Collection<EObject> toDelete_p) {
    if (siriusElement_p instanceof DRepresentationElement) {
      DRepresentationElement representation = (DRepresentationElement) siriusElement_p;
      EObject currentTarget = representation.getTarget();
      EObject newTarget = MiscUtil.getCorrespondingInStructure(semanticOrigins_p, currentTarget, semanticCopies_p);
      if (newTarget != null) {
        DRepresentationElement origin = getCounterpart(representation, allSiriusOrigins_p);
        // Reconcile target
        representation.setTarget(newTarget);
        // Reconcile semanticElements
        if (origin.getTarget() != null) {
          representation.getSemanticElements().remove(origin.getTarget());
        }
        representation.getSemanticElements().add(newTarget);
        String name = NamingUtil.getName(newTarget);
        if (name != null) {
          representation.setName(name);
        }
        result_p.add(representation);
        _pastedSiriusElementsMapping.put(representation, origin);
      } else {
        toDelete_p.add(representation);
      }
    }
  }

  /**
   * Given a Sirius element E1 and a set of Sirius element copies S, return E2 in S such that E2 represents the counterpart of E1 in S
   */
  protected DRepresentationElement getCounterpart(DRepresentationElement element_p, Iterable<? extends EObject> candidates_p) {
    for (EObject candidate : candidates_p) {
      if ((candidate instanceof DRepresentationElement) && areSimilarSiriusElements(element_p, (DRepresentationElement) candidate)) {
        return (DRepresentationElement) candidate;
      }
    }
    return null;
  }

  protected boolean areSimilarSiriusElements(DRepresentationElement element1_p, DRepresentationElement element2_p) {
    return (element1_p.getTarget() == element2_p.getTarget()) && (element1_p.getMapping() == element2_p.getMapping());
  }

  /**
   * Copy/paste the given Sirius elements (typically DSemanticDecorators) using the GMF copy/paste mechanism.
   * @param data_p the GMF clipboard data to use
   * @param target_p the Sirius element in which to paste
   * @return the new elements generated by the paste operation
   */
  @SuppressWarnings("unchecked")
  protected Collection<EObject> pasteSiriusElements(final String data_p, EObject target_p) {
    Collection<EObject> result = ClipboardUtil.pasteElementsFromString(data_p, target_p, null, null);
    if ((result != null) && !result.isEmpty()) {
      // Relocate DEdges in DDiagram instead of target_p if needed
      if (!(target_p instanceof DDiagram)) {
        DDiagram siriusDiagram = SiriusUtil.getDiagram(target_p);
        if (siriusDiagram != null) {
          for (EObject pasted : result) {
            if ((pasted instanceof DEdge) && (pasted.eContainer() != siriusDiagram)) {
              siriusDiagram.getOwnedDiagramElements().add((DEdge) pasted);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Copy/paste the given Capella elements, storing them at the same location using the PatternUtil copy/paste mechanism, except that locations in the source are
   * replaced by locations in the target when possible.
   * @param origins_p the list of Capella elements to copy
   * @param source_p the Capella element which is considered as the main source of the copy
   * @param target_p the Capella element which is considered as the target of the paste
   * @return the duplicated elements, or null if the operation failed
   */
  protected List<EObject> pasteCapellaElements(List<EObject> origins_p, EObject source_p, EObject target_p) {
    List<EObject> result = null;
    // Get locations of original elements
    List<StorageLocation> locations = getInstantiationLocationsForAddition(origins_p, source_p, target_p);
    if (locations != null) {
      assert origins_p.size() == locations.size();
      // Compute suffix for names uniqueness
      _suffix = getCommonSuffix(origins_p, locations);
      // Duplicate elements
      List<EObject> copies = copyAll(origins_p, _suffix);
      // Store copies at the original locations if applicable
      List<EObject> orderedOrigins = sortBySibling(origins_p);
      for (EObject orderedOrigin : orderedOrigins) {
        int index = origins_p.indexOf(orderedOrigin);
        locations.get(index).applyOn(copies.get(index));
      }
      // Add relevant external references to and from the copies
      duplicateExternalReferences(origins_p, copies);
      result = copies;
      // Filter out non-meaningful elements
      filterOutUnrelevantSemanticElements(copies);
      // Add implicit elements if needed, according to context
      DSemanticDecorator siriusContext = null;
      View target = getGmfTarget();
      if (target.getElement() instanceof DSemanticDecorator) {
    	siriusContext = (DSemanticDecorator) target.getElement();
      }
      for (EObject copy : copies) {
        BusinessHelper.getInstance().addImplicitElements(copy, target_p, siriusContext);
      }
    }
    return result;
  }

  /**
   * Copy the given elements using the given name suffix and business criteria
   * @param elts_p a non-null, potentially empty collection
   * @param suffix_p an optional string
   * @return a non-null, potentially empty, modifiable list
   */
  @SuppressWarnings("serial")
  protected <T extends EObject> List<T> copyAll(final Collection<? extends T> elts_p, final String suffix_p) {
    Copier copier = new Copier() {
      // Support IDs and name suffixes
      @Override
      public EObject copy(EObject eObject_p) {
        EObject copy = super.copy(eObject_p);
        MiscUtil.setNewId(copy);
        NamingUtil.suffixName(copy, suffix_p);
        return copy;
      }

      // Support business-based filtering of references
      @Override
      @SuppressWarnings("unchecked")
      protected void copyReference(EReference ref_p, EObject eObject_p, EObject copyEObject_p) {
        super.copyReference(ref_p, eObject_p, copyEObject_p);
        if (!ref_p.isContainment() && MiscUtil.supportsAddition(ref_p) && !BusinessHelper.getInstance().updateWithDuplicatedValues(ref_p)) {
          EList<EObject> refValues = (EList<EObject>) copyEObject_p.eGet(ref_p);
          refValues.retainAll(values());
        }
      }
    };
    Collection<T> result = copier.copyAll(elts_p);
    copier.copyReferences();
    return new BasicEList<T>(result) {
      @Override
      protected boolean useEquals() {
        return false;
      }
    };
  }

  /**
   * Return a sorted copy of the given collection where
   */
  protected List<EObject> sortBySibling(Collection<EObject> elements_p) {
    ArrayList<EObject> result = new ArrayList<EObject>(elements_p.size());
    // Classify elements by location
    Map<StorageLocation, List<EObject>> siblings = new HashMap<StorageLocation, List<EObject>>();
    for (EObject element : elements_p) {
      StorageLocation location = new StorageLocation(element.eContainer(), element.eContainmentFeature());
      List<EObject> localSiblings = siblings.get(location);
      if (null == localSiblings) {
        localSiblings = new ArrayList<EObject>();
        siblings.put(location, localSiblings);
      }
      localSiblings.add(element);
    }
    // Sort every list according to ownership
    Collection<List<EObject>> partialLists = siblings.values();
    final Comparator<EObject> siblingsComparator = new Comparator<EObject>() {
      public int compare(EObject o1_p, EObject o2_p) {
        return getContainmentIndex(o1_p) - getContainmentIndex(o2_p);
      }

      @SuppressWarnings("unchecked")
      private int getContainmentIndex(EObject element_p) {
        int index = 0;
        if (null != element_p) {
          EReference containment = element_p.eContainmentFeature();
          if ((null != containment) && containment.isMany()) {
            List<EObject> peers = (List<EObject>) element_p.eContainer().eGet(containment);
            index = peers.indexOf(element_p);
          }
        }
        return index;
      }
    };
    for (List<EObject> partialList : partialLists) {
      Collections.sort(partialList, siblingsComparator);
    }
    // Concatenate all partial lists into one
    for (List<EObject> partialList : partialLists) {
      result.addAll(partialList);
    }
    return result;
  }

  /**
   * Among the given copies, remove those which are not meaningful according to business criteria
   */
  protected void filterOutUnrelevantSemanticElements(List<EObject> copies_p) {
    Collection<EObject> toDelete = new ArrayList<EObject>();
    for (EObject copy : copies_p) {
      // Keep roots, only consider their content
      TreeIterator<EObject> it = copy.eAllContents();
      while (it.hasNext()) {
        EObject current = it.next();
        if (!BusinessHelper.getInstance().isMeaningfulWithin(current, copies_p)) {
          it.prune();
          toDelete.add(current);
        }
      }
    }
    for (EObject deletionTarget : toDelete) {
      MiscUtil.deleteRec(deletionTarget);
    }
  }

  /**
   * From a set of elements S and their copies S': duplicate in S' all references supporting addition from elements outside of S to elements of S or their
   * contents
   */
  protected void duplicateExternalReferences(List<EObject> origins_p, List<EObject> copies_p) {
    assert origins_p.size() == copies_p.size();
    for (EObject origin : origins_p) {
      duplicateExternalIncomingReferences(origins_p, copies_p, origin);
      Iterator<EObject> it = origin.eAllContents();
      while (it.hasNext()) {
        EObject child = it.next();
        duplicateExternalIncomingReferences(origins_p, copies_p, child);
      }
    }
  }

  /**
   * Inner computation for duplicateExternalReferences
   */
  protected void duplicateExternalIncomingReferences(List<EObject> origins_p, List<EObject> copies_p, EObject origin_p) {
    assert origins_p.size() == copies_p.size();
    EObject copy = MiscUtil.getCorrespondingInStructure(origins_p, origin_p, copies_p);
    if (copy == null) {
      return;
    }
    Collection<Setting> settings = MiscUtil.getExternalSettingsForAddition(origin_p, origins_p);
    for (Setting setting : settings) {
      @SuppressWarnings("unchecked")
      List<EObject> opposites = (List<EObject>) setting.getEObject().eGet(setting.getEStructuralFeature());
      opposites.add(copy);
    }
  }

  /**
   * From a set of elements and corresponding locations, compute a suffix that would provide unique names to copies of the given elements at the given locations
   */
  protected String getCommonSuffix(List<EObject> origins_p, List<StorageLocation> locations) {
    final List<List<EObject>> childrenList = new ArrayList<List<EObject>>(origins_p.size());
    final List<String> names = new ArrayList<String>(origins_p.size());
    for (int i = 0; i < origins_p.size(); i++) {
      names.add(NamingUtil.getName(origins_p.get(i)));
      childrenList.add(getElementsAtLocation(locations.get(i)));
    }
    final String result = NamingUtil.getSuffixForUniqueNames(childrenList, names);
    return result;
  }

  /**
   * From a set of user-selected Sirius elements, get the corresponding (possibly smaller) set of semantic roots which must be copied. This is based on the
   * principle that whenever a Sirius element is selected, the whole containment tree of its represented semantic element is copied.
   */
  protected List<EObject> getSemanticRootsForCopy(Collection<? extends EObject> selectedSiriusElements_p, EObject semanticTarget_p) {
    List<EObject> result = new ArrayList<EObject>();
    for (EObject siriusElement : selectedSiriusElements_p) {
      if (siriusElement instanceof DSemanticDecorator) {
        EObject semanticElement = ((DSemanticDecorator) siriusElement).getTarget();
        result.add(semanticElement);
      }
    }
    result = MiscUtil.getRoots(result);
    // Adding implicit elements
    Set<EObject> implicitAdditions = BusinessHelper.getInstance().getImplicitElements(result, semanticTarget_p);
    result.addAll(implicitAdditions);
    result = MiscUtil.getRoots(result);
    return result;
  }

  /**
   * Return a list of storage locations supporting containment addition, one per element in the parameter modulo replacement of source_p by target_p
   * @return a list of locations, or null if no solution was found
   */
  protected List<StorageLocation> getInstantiationLocationsForAddition(List<EObject> elements_p, EObject source_p, EObject target_p) {
    List<StorageLocation> result = new ArrayList<StorageLocation>();
    for (EObject element : elements_p) {
      StorageLocation location = getInstantiationLocation(element, source_p, target_p);
      if (location.supportsAddition()) {
        result.add(location);
      } else {
        return null;
      }
    }
    return result;
  }

  /**
   * Given an InstantiationLocation location (container, containment) in a model, return all elements owned by container via containment
   */
  @SuppressWarnings("unchecked")
  protected List<EObject> getElementsAtLocation(StorageLocation location_p) {
    assert location_p != null;
    assert (location_p.getContainer() != null) && (location_p.getContainment() != null);
    List<EObject> result = null;
    if (location_p.getContainment().isMany()) {
      result = (List<EObject>) location_p.getContainer().eGet(location_p.getContainment());
    }
    return result;
  }

  /**
   * Return either a well-formed InstantiationLocation (i.e. with no null value) corresponding to that of the given element modulo replacement of source by
   * target, or null
   */
  protected StorageLocation getInstantiationLocation(EObject element_p, EObject source_p, EObject target_p) {
    StorageLocation result = null;
    EReference containment = null;
    EObject container = null;
    if (element_p != null) {
      containment = element_p.eContainmentFeature();
      if (!_isStandbyUsage && (element_p.eContainer() == source_p) && target_p.eClass().getEAllContainments().contains(containment)
          && !elementIsPart(element_p)) {
        // Replace source by target since it is applicable
        container = target_p;
      } else {
        container = element_p.eContainer();
      }
    }
    if ((container != null) && (containment != null)) {
      StorageLocation location = new StorageLocation(container, containment);
      if (BusinessHelper.getInstance().isMeaningfulStorageFor(location, element_p)) {
        result = location;
      }
    }
    return result;
  }

  /**
   * Checks if an EOject is a Part
   * @param element_p
   * @return
   */
  private boolean elementIsPart(EObject element_p) {
    return element_p instanceof Part;
  }

  /**
   * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.PasteCommand_Name;
  }

}
