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
package org.polarsys.capella.core.platform.sirius.clipboard.commands;

import java.util.ArrayList;
import java.util.Arrays;
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

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.business.internal.query.AbstractNodeMappingApplicabilityTester;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.helpers.EObjectExt;
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
@SuppressWarnings("restriction")
public class CapellaDiagramPasteCommand extends AbstractResultCommand {

  // The non-null list of GMF elements to paste into
  private List<View> targets;

  // The suffix of the copied semantic elements, computed during paste
  private String suffix;

  // Mapping of the actually pasted Sirius elements to their original versions,
  // computed during Semantic/Sirius layers reconciliation
  private Map<DRepresentationElement, DRepresentationElement> pastedSiriusElementsMapping;

  // Whether the user simply copy/pasted without selecting a paste target
  private boolean isStandbyUsage;

  private boolean isRestoreStyles;

  /**
   * Constructor
   * 
   * @param targets
   *          the non-null, non-empty list of views into which paste must happen
   */
  public CapellaDiagramPasteCommand(List<? extends View> targets) {
    this(targets, false);
  }

  /**
   * 
   */
  public CapellaDiagramPasteCommand(List<? extends View> targets, boolean restoreStyles) {
    Assert.isNotNull(targets);
    this.isRestoreStyles = restoreStyles;
    this.targets = new ArrayList<>(targets);
    this.suffix = null;
    this.pastedSiriusElementsMapping = new HashMap<>();
    this.isStandbyUsage = checkStandbyUsage();
  }

  /**
   * @see pastedSiriusElementsMapping
   */
  public Map<DRepresentationElement, DRepresentationElement> getPastedSiriusElementsOrigins() {
    return Collections.unmodifiableMap(pastedSiriusElementsMapping);
  }

  /**
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {

    // Check the diagram from where the objects are copied and the diagram where to paste if they are if different kind
    DSemanticDecorator siriusTarget = LayerUtil.getSiriusElement(getGmfTarget());
    String copyCtxtDiagram = CapellaDiagramClipboard.getInstance().getContextDiagram();
    String pasteCtxtDiagram = SiriusUtil.getContextDiagram(siriusTarget);
    if (copyCtxtDiagram != null && pasteCtxtDiagram != null && !copyCtxtDiagram.equals(pasteCtxtDiagram)) {
      throw new RuntimeException(
          NLS.bind(Messages.CapellaDiagramPasteAction_Unsupported, pasteCtxtDiagram, copyCtxtDiagram));
    }

    // Capella layer
    EObject semanticSource = getSemanticSource();
    EObject semanticTarget = getSemanticTarget();
    if ((semanticSource == null) || (semanticTarget == null)) {
      return; // failure: empty result
    }

    List<EObject> semanticOrigins = getSemanticRootsForCopy(CapellaDiagramClipboard.getInstance().getSiriusElements(),
        semanticTarget);
    List<EObject> semanticCopies = pasteCapellaElements(semanticOrigins, semanticSource, semanticTarget);
    if (semanticCopies == null) {
      return; // failure: empty result
    }

    // Sirius layer (GMF layer will be automatically updated)
    List<EObject> rawAllSiriusOrigins = MiscUtil
        .getContentSet(CapellaDiagramClipboard.getInstance().getSiriusElements());
    List<DSemanticDecorator> allSiriusOrigins = MiscUtil.filter(rawAllSiriusOrigins, DSemanticDecorator.class);
    for (View target : targets) {
      EcoreUtil.resolveAll(target);
    }

    CapellaDiagramClipboard clipboard = CapellaDiagramClipboard.getInstance();
    if (clipboard.isEmpty() || SiriusUtil.layoutIsConstrained(siriusTarget)) {
      // Do not handle graphical layer if the clipboard is empty or
      // the target diagram has a too constrained layout
      setResults(Collections.emptyList());
      return;
    }

    ensureCopyPasteValidity(siriusTarget, clipboard);

    final String data = clipboard.getSiriusData();
    Collection<EObject> pastedSiriusElements = pasteSiriusElements(data, siriusTarget);
    // Reconcile the Sirius and Capella layers
    List<EObject> result = reconcileSiriusAndSemanticLayers(pastedSiriusElements, allSiriusOrigins, semanticOrigins,
        semanticCopies);

    // Resolve incoherences before returning the result
    for (EObject eObj : result)
      resolveIncoherences(eObj);

    setResults(result);
  }

  /**
   * Ensures that the copied elements from clipboard (that need to be pasted) have a valid mapping that is compatible
   * with the paste target.
   * 
   * @param siriusPasteTarget
   *          the sirius paste target
   * @param clipboard
   *          the clipboard containing the copy elements that need to be pasted
   */
  protected void ensureCopyPasteValidity(DSemanticDecorator siriusPasteTarget, CapellaDiagramClipboard clipboard) {

    // the elements than need to be pasted
    List<DSemanticDecorator> siriusElements = clipboard.getSiriusElements();

    // the root of the elements that need to be pasted (the lowest containers)
    List<DSemanticDecorator> siriusElementsRoots = MiscUtil.getRoots(siriusElements);

    // for each of the roots, ensure that their mapping is valid in the context of the paste target
    for (DSemanticDecorator siriusElementRoot : siriusElementsRoots) {
      if (siriusElementRoot instanceof DDiagramElement) {
        DDiagramElement diagramElementRoot = (DDiagramElement) siriusElementRoot;
        DiagramElementMapping diagramElementRootMapping = diagramElementRoot.getDiagramElementMapping();

        if (diagramElementRootMapping instanceof AbstractNodeMapping) {
          AbstractNodeMapping abstractRootElementMapping = (AbstractNodeMapping) diagramElementRootMapping;

          IStatus validityStatus = checkPastedElementValidity(diagramElementRoot, abstractRootElementMapping,
              siriusPasteTarget);

          if (!validityStatus.isOK()) {
            log(validityStatus);
            throw new OperationCanceledException(validityStatus.getMessage());
          }
        }
      }
    }
  }

  protected void log(IStatus validityStatus) {
      Bundle bundle = FrameworkUtil.getBundle(this.getClass());
      Platform.getLog(bundle).log(validityStatus);
  }

  /**
   * Check that the pastedElementMapping of the pastedElement is valid in regards to the pasteTarget.
   * 
   * @param pastedElement
   *          the pasted element
   * @param pastedElementMapping
   *          the pasted element mapping
   * @param pasteTarget
   *          the paste target
   * @return a status containing the validity of the current operation
   */
  public IStatus checkPastedElementValidity(DDiagramElement pastedElement, AbstractNodeMapping pastedElementMapping,
      DSemanticDecorator pasteTarget) {

    AbstractNodeMappingApplicabilityTester abstractNodeMappingApplicabilityTester = new AbstractNodeMappingApplicabilityTester(
        Arrays.asList(pastedElementMapping));

    String elementTargetType = pastedElement.getTarget() != null ? pastedElement.getTarget().eClass().getName() : "";

    String elementName = pastedElement.getName();

    if (pasteTarget instanceof DDiagram) {
      DDiagram diagram = (DDiagram) pasteTarget;
      if (!abstractNodeMappingApplicabilityTester.canCreateIn(diagram)) {
        String statusMessage = NLS.bind(Messages.CapellaDiagramPasteAction_InvalidDiagramTarget,
            new String[] { elementName, elementTargetType, EObjectExt.getText(diagram) });
        return new Status(Status.WARNING, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), statusMessage);
      }

    } else if (pasteTarget instanceof DNodeContainer) {
      DNodeContainer nodeContainer = (DNodeContainer) pasteTarget;
      if (!abstractNodeMappingApplicabilityTester.canCreateIn(nodeContainer)) {

        String statusMessage = NLS.bind(Messages.CapellaDiagramPasteAction_InvalidNodeContainerTarget,
            new String[] { elementName, elementTargetType, nodeContainer.getName() });
        return new Status(Status.WARNING, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), statusMessage);
      }

    } else if (pasteTarget instanceof DNode) {
      DNode node = (DNode) pasteTarget;
      if (!abstractNodeMappingApplicabilityTester.canCreateIn(node)) {

        String statusMessage = NLS.bind(Messages.CapellaDiagramPasteAction_InvalidNodeTarget,
            new String[] { elementName, elementTargetType, node.getName() });
        return new Status(Status.WARNING, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), statusMessage);
      }

    }

    return Status.OK_STATUS;
  }

  /**
   * Resolve incoherences if exist
   * 
   * @param result
   */
  private void resolveIncoherences(EObject eObj) {
    // If a Node references an Edge not coming from that Node, remove the reference
    if (eObj instanceof DNode) {
      DNode aNode = (DNode) eObj;
      // Check incoming edges
      List<DEdge> incomingEdgesToRemove = new ArrayList<>();
      for (DEdge anEdge : aNode.getIncomingEdges())
        if (anEdge.getTargetNode() != aNode)
          incomingEdgesToRemove.add(anEdge);
      for (DEdge toRemove : incomingEdgesToRemove)
        aNode.getIncomingEdges().remove(toRemove);

      // Check outgoing edges
      List<DEdge> outgoingEdgesToRemove = new ArrayList<>();
      for (DEdge anEdge : aNode.getOutgoingEdges())
        if (anEdge.getSourceNode() != aNode)
          outgoingEdgesToRemove.add(anEdge);
      for (DEdge toRemove : outgoingEdgesToRemove)
        aNode.getOutgoingEdges().remove(toRemove);
    }

    for (EObject child : eObj.eContents())
      resolveIncoherences(child);

  }

  /**
   * Return whether the user simply copy/pasted without selecting a paste target
   */
  protected boolean checkStandbyUsage() {
    Set<EObject> copySelection = new HashSet<>(CapellaDiagramClipboard.getInstance().getSiriusElements());
    Set<EObject> pasteSelection = new HashSet<>(LayerUtil.toSirius(targets));
    return copySelection.equals(pasteSelection);
  }

  /**
   * Return the main GMF element in which paste must happen
   * 
   * @return a non-null view
   */
  public View getGmfTarget() {
    View result = targets.get(0);
    if (isStandbyUsage && (result.eContainer() instanceof View) && !(result.eContainer() instanceof Edge)) {
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
   * Given a copy of Sirius elements, corresponding semantic elements and copied semantic elements, bind the Sirius
   * elements to the copied semantic elements, filter out the Sirius elements whose semantic element has no counterpart
   * among the copies, and map the resulting Sirius elements to the original ones.
   * 
   * @return the Sirius elements among siriusElements which are actually kept in the diagram
   */
  protected List<EObject> reconcileSiriusAndSemanticLayers(Iterable<? extends EObject> siriusElements,
      Iterable<? extends EObject> siriusOrigins, List<EObject> semanticOrigins, List<EObject> semanticCopies) {
    List<EObject> result = new LinkedList<>();
    Collection<EObject> toDelete = new ArrayList<>();
    for (EObject siriusElement : siriusElements) {
      reconcileSiriusElement(siriusElement, semanticOrigins, semanticCopies, siriusOrigins, result, toDelete);
      Iterator<EObject> it = siriusElement.eAllContents();
      while (it.hasNext()) {
        reconcileSiriusElement(it.next(), semanticOrigins, semanticCopies, siriusOrigins, result, toDelete);
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
  protected void reconcileSiriusElement(EObject siriusElement, List<EObject> semanticOrigins,
      List<EObject> semanticCopies, Iterable<? extends EObject> allSiriusOrigins, List<EObject> result,
      Collection<EObject> toDelete) {
    if (siriusElement instanceof DRepresentationElement) {
      DRepresentationElement representation = (DRepresentationElement) siriusElement;
      EObject currentTarget = representation.getTarget();
      EObject newTarget = MiscUtil.getCorrespondingInStructure(semanticOrigins, currentTarget, semanticCopies);
      if (newTarget != null) {
        DRepresentationElement origin = getCounterpart(representation, allSiriusOrigins);
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
        result.add(representation);
        pastedSiriusElementsMapping.put(representation, origin);
      } else {
        toDelete.add(representation);
      }
    }
  }

  /**
   * Given a Sirius element E1 and a set of Sirius element copies S, return E2 in S such that E2 represents the
   * counterpart of E1 in S
   */
  protected DRepresentationElement getCounterpart(DRepresentationElement element,
      Iterable<? extends EObject> candidates) {
    for (EObject candidate : candidates) {
      if ((candidate instanceof DRepresentationElement)
          && areSimilarSiriusElements(element, (DRepresentationElement) candidate)) {
        return (DRepresentationElement) candidate;
      }
    }
    return null;
  }

  protected boolean areSimilarSiriusElements(DRepresentationElement element1, DRepresentationElement element2) {
    return (element1.getTarget() == element2.getTarget()) && (element1.getMapping() == element2.getMapping());
  }

  /**
   * Copy/paste the given Sirius elements (typically DSemanticDecorators) using the GMF copy/paste mechanism.
   * 
   * @param data
   *          the GMF clipboard data to use
   * @param target
   *          the Sirius element in which to paste
   * @return the new elements generated by the paste operation
   */
  @SuppressWarnings("unchecked")
  protected Collection<EObject> pasteSiriusElements(final String data, EObject target) {

    Collection<EObject> result = ClipboardUtil.pasteElementsFromString(data, target, null, null);
    if ((result != null) && !result.isEmpty()) {
      relocateEdges(target, result);
    }
    return result;
  }

  private void relocateEdges(EObject target, Collection<EObject> result) {
    // Relocate DEdges in DDiagram instead of target if needed
    if (!(target instanceof DDiagram)) {
      DDiagram siriusDiagram = SiriusUtil.getDiagram(target);
      if (siriusDiagram != null) {
        for (EObject pasted : result) {
          if ((pasted instanceof DEdge) && (pasted.eContainer() != siriusDiagram)) {
            siriusDiagram.getOwnedDiagramElements().add((DEdge) pasted);
          }
        }
      }
    }

    if (isRestoreStyles) {
      restoreStyles(result);
    }
  }

  private void restoreStyles(Collection<EObject> result) {
    // We replace the current style
    for (EObject pasted : result) {
      if (pasted instanceof DDiagramElement) {
        DDiagramElement element = (DDiagramElement) pasted;
        Session session = SessionManager.INSTANCE.getSession(element.getTarget());
        MappingWithInterpreterHelper mappingHelper = new MappingWithInterpreterHelper(session.getInterpreter());
        Style bestStyle = mappingHelper.getBestStyle(element.getDiagramElementMapping(), element.getTarget(), pasted,
            pasted.eContainer(), element.getParentDiagram());

        new SetStyleSwitch(bestStyle).doSwitch(pasted);
      }
    }
  }

  /**
   * Copy/paste the given Capella elements, storing them at the same location using the PatternUtil copy/paste
   * mechanism, except that locations in the source are replaced by locations in the target when possible.
   * 
   * @param origins
   *          the list of Capella elements to copy
   * @param source
   *          the Capella element which is considered as the main source of the copy
   * @param target
   *          the Capella element which is considered as the target of the paste
   * @return the duplicated elements, or null if the operation failed
   */
  protected List<EObject> pasteCapellaElements(List<EObject> origins, EObject source, EObject target) {
    List<EObject> result = null;
    // Get locations of original elements
    List<StorageLocation> locations = getInstantiationLocationsForAddition(origins, source, target);
    if (!locations.isEmpty()) {
      assert origins.size() == locations.size();
      // Compute suffix for names uniqueness
      suffix = getCommonSuffix(origins, locations);
      // Duplicate elements
      List<EObject> copies = copyAll(origins, suffix);
      // Store copies at the original locations if applicable
      List<EObject> orderedOrigins = sortBySibling(origins);
      for (EObject orderedOrigin : orderedOrigins) {
        int index = origins.indexOf(orderedOrigin);
        locations.get(index).applyOn(copies.get(index));
      }
      // Add relevant external references to and from the copies
      duplicateExternalReferences(origins, copies);
      result = copies;
      // Filter out non-meaningful elements
      filterOutUnrelevantSemanticElements(copies);
      // Add implicit elements if needed, according to context
      DSemanticDecorator siriusContext = null;
      View view = getGmfTarget();
      if (view.getElement() instanceof DSemanticDecorator) {
        siriusContext = (DSemanticDecorator) view.getElement();
      }
      for (EObject copy : copies) {
        BusinessHelper.getInstance().addImplicitElements(copy, target, siriusContext);
      }
    }
    return result;
  }

  /**
   * Copy the given elements using the given name suffix and business criteria
   * 
   * @param elts
   *          a non-null, potentially empty collection
   * @param suffix
   *          an optional string
   * @return a non-null, potentially empty, modifiable list
   */
  @SuppressWarnings("serial")
  protected <T extends EObject> List<T> copyAll(final Collection<? extends T> elts, final String suffix) {
    Copier copier = new Copier() {
      // Support IDs and name suffixes
      @Override
      public EObject copy(EObject eObject) {
        EObject copy = super.copy(eObject);
        MiscUtil.setNewId(copy);
        NamingUtil.suffixName(copy, suffix);
        return copy;
      }

      // Support business-based filtering of references
      @Override
      @SuppressWarnings("unchecked")
      protected void copyReference(EReference ref, EObject eObject, EObject copyEObject) {
        super.copyReference(ref, eObject, copyEObject);
        if (!ref.isContainment() && MiscUtil.supportsAddition(ref)
            && !BusinessHelper.getInstance().updateWithDuplicatedValues(ref)) {
          EList<EObject> refValues = (EList<EObject>) copyEObject.eGet(ref);
          refValues.retainAll(values());
        }
      }
    };
    Collection<T> result = copier.copyAll(elts);
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
  protected List<EObject> sortBySibling(Collection<EObject> elements) {
    ArrayList<EObject> result = new ArrayList<>(elements.size());
    // Classify elements by location
    Map<StorageLocation, List<EObject>> siblings = new HashMap<>();
    for (EObject element : elements) {
      StorageLocation location = new StorageLocation(element.eContainer(), element.eContainmentFeature());
      List<EObject> localSiblings = siblings.get(location);
      if (null == localSiblings) {
        localSiblings = new ArrayList<>();
        siblings.put(location, localSiblings);
      }
      localSiblings.add(element);
    }
    // Sort every list according to ownership
    Collection<List<EObject>> partialLists = siblings.values();
    final Comparator<EObject> siblingsComparator = new Comparator<EObject>() {
      @Override
      public int compare(EObject o1, EObject o2) {
        return getContainmentIndex(o1) - getContainmentIndex(o2);
      }

      @SuppressWarnings("unchecked")
      private int getContainmentIndex(EObject element) {
        int index = 0;
        if (null != element) {
          EReference containment = element.eContainmentFeature();
          if ((null != containment) && containment.isMany()) {
            List<EObject> peers = (List<EObject>) element.eContainer().eGet(containment);
            index = peers.indexOf(element);
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
  protected void filterOutUnrelevantSemanticElements(List<EObject> copies) {
    Collection<EObject> toDelete = new ArrayList<>();
    for (EObject copy : copies) {
      // Keep roots, only consider their content
      TreeIterator<EObject> it = copy.eAllContents();
      while (it.hasNext()) {
        EObject current = it.next();
        if (!BusinessHelper.getInstance().isMeaningfulWithin(current, copies)) {
          it.prune();
          toDelete.add(current);
        }
      }
    }

    EcoreUtil.deleteAll(toDelete, true);
  }

  /**
   * From a set of elements S and their copies S': duplicate in S' all references supporting addition from elements
   * outside of S to elements of S or their contents
   */
  protected void duplicateExternalReferences(List<EObject> origins, List<EObject> copies) {
    assert origins.size() == copies.size();
    for (EObject origin : origins) {
      duplicateExternalIncomingReferences(origins, copies, origin);
      Iterator<EObject> it = origin.eAllContents();
      while (it.hasNext()) {
        EObject child = it.next();
        duplicateExternalIncomingReferences(origins, copies, child);
      }
    }
  }

  /**
   * Inner computation for duplicateExternalReferences
   */
  protected void duplicateExternalIncomingReferences(List<EObject> origins, List<EObject> copies, EObject origin) {
    assert origins.size() == copies.size();
    EObject copy = MiscUtil.getCorrespondingInStructure(origins, origin, copies);
    if (copy == null) {
      return;
    }
    Collection<Setting> settings = MiscUtil.getExternalSettingsForAddition(origin, origins);
    for (Setting setting : settings) {
      @SuppressWarnings("unchecked")
      List<EObject> opposites = (List<EObject>) setting.getEObject().eGet(setting.getEStructuralFeature());
      opposites.add(copy);
    }
  }

  /**
   * From a set of elements and corresponding locations, compute a suffix that would provide unique names to copies of
   * the given elements at the given locations
   */
  protected String getCommonSuffix(List<EObject> origins, List<StorageLocation> locations) {
    final List<List<EObject>> childrenList = new ArrayList<>(origins.size());
    final List<String> names = new ArrayList<>(origins.size());
    for (int i = 0; i < origins.size(); i++) {
      names.add(NamingUtil.getName(origins.get(i)));
      childrenList.add(getElementsAtLocation(locations.get(i)));
    }
    return NamingUtil.getSuffixForUniqueNames(childrenList, names);
  }

  /**
   * From a set of user-selected Sirius elements, get the corresponding (possibly smaller) set of semantic roots which
   * must be copied. This is based on the principle that whenever a Sirius element is selected, the whole containment
   * tree of its represented semantic element is copied.
   */
  protected List<EObject> getSemanticRootsForCopy(Collection<? extends EObject> selectedSiriusElements,
      EObject semanticTarget) {
    List<EObject> result = new ArrayList<>();
    for (EObject siriusElement : selectedSiriusElements) {
      if (siriusElement instanceof DSemanticDecorator) {
        EObject semanticElement = ((DSemanticDecorator) siriusElement).getTarget();
        result.add(semanticElement);
      }
    }
    result = MiscUtil.getRoots(result);
    // Adding implicit elements
    Set<EObject> implicitAdditions = BusinessHelper.getInstance().getImplicitElements(result, semanticTarget);
    result.addAll(implicitAdditions);
    result = MiscUtil.getRoots(result);
    return result;
  }

  /**
   * Return a list of storage locations supporting containment addition, one per element in the parameter modulo
   * replacement of source by target
   * 
   * @return a list of locations, or null if no solution was found
   */
  protected List<StorageLocation> getInstantiationLocationsForAddition(List<EObject> elements, EObject source,
      EObject target) {
    List<StorageLocation> result = new ArrayList<>();
    for (EObject element : elements) {
      StorageLocation location = getInstantiationLocation(element, source, target);
      if (location != null && location.supportsAddition()) {
        result.add(location);
      }
    }
    return result;
  }

  /**
   * Given an InstantiationLocation location (container, containment) in a model, return all elements owned by container
   * via containment
   */
  @SuppressWarnings("unchecked")
  protected List<EObject> getElementsAtLocation(StorageLocation location) {
    assert location != null;
    assert (location.getContainer() != null) && (location.getContainment() != null);
    List<EObject> result = null;
    if (location.getContainment().isMany()) {
      result = (List<EObject>) location.getContainer().eGet(location.getContainment());
    }
    return result;
  }

  /**
   * Return either a well-formed InstantiationLocation (i.e. with no null value) corresponding to that of the given
   * element modulo replacement of source by target, or null
   */
  protected StorageLocation getInstantiationLocation(EObject element, EObject source, EObject target) {
    StorageLocation result = null;
    EReference containment = null;
    EObject container = null;
    if (element != null) {
      containment = element.eContainmentFeature();
      if (!isStandbyUsage && (element.eContainer() == source)
          && target.eClass().getEAllContainments().contains(containment) && !elementIsPart(element)) {
        // Replace source by target since it is applicable
        container = target;
      } else {
        container = element.eContainer();
      }
    }
    if ((container != null) && (containment != null)) {
      StorageLocation location = new StorageLocation(container, containment);
      if (BusinessHelper.getInstance().isMeaningfulStorageFor(location, element)) {
        result = location;
      }
    }
    return result;
  }

  /**
   * Checks if an EOject is a Part
   * 
   * @param element
   * @return
   */
  private boolean elementIsPart(EObject element) {
    return element instanceof Part;
  }

  /**
   * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.PasteCommand_Name;
  }

}
