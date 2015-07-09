/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.listener.Notification;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorPart;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.framework.api.CommonTestMessages;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Useful tools for diagram
 */
public class DiagramHelper {

  /**
   * Change the synchronize state on a diagram
   * @param diagram a given DDiagram
   * @param isSynchronized change the state
   */
  public static void setSynchronized(final DDiagram diagram, final boolean isSynchronized) {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        diagram.setSynchronized(isSynchronized);
        refreshDiagram(diagram);
      }
    };
    // Let's perform the job
    TestHelper.getExecutionManager(diagram).execute(cmd);
  }

  /**
   * Set the auto refresh preference for diagram
   * @param value_p
   */
  public static void setPreferenceAutoRefresh(final boolean value_p) {
    IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
    preferenceStore.setValue(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), value_p);
  }

  /**
   * Set the refresh on opening preference for diagram
   * @param value_p
   */
  public static void setPrefereneRefreshOnOpening(final boolean value_p) {
    IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
    preferenceStore.setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), value_p);
  }

  /**
   * Return the DRepresention with the given name, null otherwise
   * @param session_p the current Session.
   * @param name_p
   * @return <code>null</code> if an error occurred.
   */
  public static DRepresentation getDRepresentation(final Session session_p, final String name_p) {
    DRepresentation result = null;
    Collection<DRepresentation> dRepresentations = DialectManager.INSTANCE.getAllRepresentations(session_p);
    Iterator<DRepresentation> it = dRepresentations.iterator();
    while (it.hasNext() && (null == result)) {
      result = it.next();
      if (!result.getName().equals(name_p)) {
        result = null;
      }
    }
    return result;
  }

  /**
   * Return the DRepresention with the given diagram name and ID, null otherwise To be used if several diagrams have the same name
   * @param session_p the current Session.
   * @param name_p
   * @param id_p
   * @return <code>null</code> if an error occurred.
   */
  public static DRepresentation getDRepresentationByID(final Session session_p, final String name_p, final String id_p) {
    DRepresentation result = null;
    EStructuralFeature featureToCompare = ModellingcorePackage.Literals.MODEL_ELEMENT__ID;

    Collection<DRepresentation> dRepresentations = DialectManager.INSTANCE.getAllRepresentations(session_p);
    for (DRepresentation dRepresentation : dRepresentations) {
      // find DReprensentation with the given diagram name
      if (dRepresentation.getName().equals(name_p)) {
        DSemanticDiagram dRepresentationDiagram = (DSemanticDiagram) dRepresentation;
        // check the diagram id with the id given in input
        EObject semanticTarget = dRepresentationDiagram.getTarget();
        if (semanticTarget.eGet(featureToCompare).equals(id_p)) {
          // if the diagram id is the same than the given id, the
          // current DRepresentation is the right one
          result = dRepresentation;
        }
      }
    }
    return result;
  }

  /**
   * Checks if the session given by its AIRD file path contains the given representation (Diagram)
   * @param representation_p, the representation to test
   * @param filepath_p, the aird file path to open the related session
   */
  @SuppressWarnings("nls")
  public static void checkDiagramInSession(DRepresentation representation_p, String filepath_p) {
    URI representationURI = representation_p.eResource().getURI();
    boolean containsRepresentation = representationURI.equals(FileHelper.getFileFullUri(filepath_p));

    Assert.assertTrue(MessageFormat.format("The representation {0}  is not in  the resource {1}", representation_p, filepath_p), containsRepresentation);
    // Assert.assertTrue(MessageFormat.format(HelperMessages.diagramNotContainedInSession, representation_p.getName(), file.getName()), containsRepresentation);
  }

  /**
   * Return the DRepresention with the given name and platformSpecificElementId ( ie next.eResource().getURIFragment(next)), null otherwise. Use in case, we
   * have diagrams with the same name in session.
   * @param session_p the current Session.
   * @param name_p
   * @param platformSpecificElementId_p
   * @return <code>null</code> if an error occurred.
   */
  public static DRepresentation getDRepresentation(final Session session_p, final String name_p, final String platformSpecificElementId_p) {
    DRepresentation result = null;
    Collection<DRepresentation> dRepresentations = DialectManager.INSTANCE.getAllRepresentations(session_p);
    Iterator<DRepresentation> it = dRepresentations.iterator();
    while (it.hasNext()) {
      DRepresentation next = it.next();
      if (next.getName().equalsIgnoreCase(name_p) && next.eResource().getURIFragment(next).equalsIgnoreCase(platformSpecificElementId_p)) {
        result = next;
        break;
      }
    }
    return result;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the first occurrence found for a given semantic Object, null otherwise
   * @param diagram_p the target diagram
   * @param semanticObject_p the semantic object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagram(final DDiagram diagram_p, final EObject semanticObject_p) {

    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram_p.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getTarget() == semanticObject_p) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the first occurrence found for a given ID, null otherwise
   * @param diagram_p the target diagram
   * @param anID, the id of the object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagramByID(final DDiagram diagram_p, final String anID) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram_p.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getTarget().eGet(ModellingcorePackage.Literals.MODEL_ELEMENT__ID).equals(anID)) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the first occurrence found for a given ID, null otherwise
   * @param diagram_p the target diagram
   * @param anID, the id of the object to reach
   * @param aName, the name of the object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagramByIDAndName(final DDiagram diagram_p, final String anID, final String aName) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram_p.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getTarget().eGet(ModellingcorePackage.Literals.MODEL_ELEMENT__ID).equals(anID) && element.getName().equals(aName)) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the {@link DDiagramElement} corresponding to the first occurrence found for the given ID and the given Container, null otherwise
   * <p>
   * Useful to select an InstanceRole
   * @param diagram_p the target diagram
   * @param semanticObject_p the semantic object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagramByIDandContainer(final DDiagram diagram_p, final String anID, EObject container_p) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram_p.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getTarget().eGet(ModellingcorePackage.Literals.MODEL_ELEMENT__ID).equals(anID) && element.eContainer().equals(container_p)) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the first occurrence found for a given semantic Object and which is not a DEdge, null otherwise
   * @param diagram_p the target diagram
   * @param semanticObject_p the semantic object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagramNodeElement(final DDiagram diagram_p, final EObject semanticObject_p) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram_p.getDiagramElements());
    for (DDiagramElement element : elements) {
      if ((element.getTarget() == semanticObject_p) && !(element instanceof DEdge)) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the given EClass and to the first occurrence found for a given semantic Object, null otherwise
   * @param diagram_p the target diagram
   * @param semanticObject_p the semantic object to reach
   * @param expectedSemanticMetaClass_p the expected EClass of the found DDiagramElement
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getDDiagramElementByEClass(final DDiagram diagram_p, final EObject semanticObject_p, EClass expectedSemanticMetaClass_p) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram_p.getDiagramElements());
    for (DDiagramElement element : elements) {
      if ((element.getTarget() == semanticObject_p) && expectedSemanticMetaClass_p.isSuperTypeOf(element.eClass())) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the {@link DDiagramElement} corresponding to the given semanticObject, edge source and edge target
   * @param diagram_p
   * @param semanticObject_p
   * @param source_p
   * @param target_p
   * @return
   */
  public static DDiagramElement getDDiagramElementBySourceAndTarget(final DDiagram diagram_p, final EObject semanticObject_p, EObject source_p, EObject target_p) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram_p.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element instanceof DEdge) {
        DEdge edge = (DEdge) element;
        if ((element.getTarget() == semanticObject_p) && edge.getSourceNode().equals(source_p) && edge.getTargetNode().equals(target_p)) {
          return element;
        }
      }
    }
    return null;
  }

  /**
   * return the list of available {@link RepresentationDescription} on which a given semantic element can be created.
   * @param session_p
   * @param semanticElement_p
   * @return
   */
  public static List<RepresentationDescription> getMatchingRepresentationDescription(final Session session_p, final EObject semanticElement_p) {
    List<RepresentationDescription> result = new ArrayList<RepresentationDescription>();

    // Get selected viewpoints.
    Collection<Viewpoint> selectedViewpoints = session_p.getSelectedViewpoints(false);
    // Get descriptions.
    Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(selectedViewpoints, semanticElement_p);
    // Loop over representation descriptions, stop looping as soon as an
    // appropriate description is found.
    for (RepresentationDescription current : descriptions) {
      if (DialectManager.INSTANCE.canCreate(semanticElement_p, current)) {
        result.add(current);
      }
    }

    return result;
  }

  /**
   * @param session_p
   * @param semanticElement_p
   * @param repDescName_p
   * @return null whether no matching {@link RepresentationDescription} are found
   */
  public static RepresentationDescription getRepresentationDescription(final Session session_p, final EObject semanticElement_p, final String repDescName_p) {

    RepresentationDescription result = null;

    List<RepresentationDescription> list = getMatchingRepresentationDescription(session_p, semanticElement_p);

    if (!list.isEmpty()) {
      if (null != repDescName_p) { // Let's try to find it using name
        for (RepresentationDescription current : list) {
          if (current.getName().equals(repDescName_p)) {
            result = current;
            break;
          }
        }
      } else { // Let's take the first available item.
        result = list.get(0);
      }
    }

    return result;
  }

  /**
   * Refresh the given diagram
   * @param diagram_p
   * @return true if operation is ok (i.e. no exception trapped), false otherwise
   */
  public static boolean refreshDiagram(final DDiagram diagram_p) {
    boolean ret = true;

    try {

      final AbstractCommand cmd = new AbstractReadWriteCommand() {
        public void run() {
          DialectManager.INSTANCE.refresh(diagram_p, new NullProgressMonitor());
        }
      };

      // Let's perform the job
      TestHelper.getExecutionManager(diagram_p).execute(cmd);

    } catch (Exception e) {
      ret = false;
    }

    return ret;
  }

  /**
   * Utility method in order to (de)activate layers onto diagram
   * @param targetLayer_p the target {@link Layer}
   * @param diagram_p the target {@link DDiagram}
   * @param activate_p should we activate or deactivate the target layer.
   */
  public static void activateDiagramLayer(final Layer targetLayer_p, final DDiagram diagram_p, final boolean activate_p) {
    final AbstractCommand cmd = new AbstractNonDirtyingCommand() {
      /**
       * Code duplicated from the AbstractChangeLayerActivation class.
       * @see org.eclipse.sirius.diagram.tools.internal.handler.AbstractChangeLayerActivation#run()
       */
      public void run() {

        NotificationUtil.sendNotification(diagram_p, Notification.Kind.STOP, Notification.VISIBILITY);

        final List<Layer> activatedLayers = diagram_p.getActivatedLayers();
        final boolean shouldAddLayer = activate_p && !EqualityHelper.contains(activatedLayers, targetLayer_p);
        final boolean shouldRemoveLayer = !shouldAddLayer && EqualityHelper.contains(activatedLayers, targetLayer_p);

        if (shouldAddLayer) {
          activatedLayers.add(targetLayer_p);
        } else if (shouldRemoveLayer) {
          // Cannot rely on equals an hashcode methods, since layer can be
          // "proxied" (not really that concept, but result is the same :
          // equals and hashcode cannot be used to remove element in this list)
          final Iterator<Layer> iterator = activatedLayers.iterator();
          boolean removed = false;
          while (iterator.hasNext() && !removed) {
            final Layer layerToRemove = iterator.next();
            if (EqualityHelper.areEquals(targetLayer_p, layerToRemove)) {
              iterator.remove();
              // Only one layer to remove in that list event if it is a list, not a set
              removed = true;
            }
          }
        }

        CapellaServices.getService().forceRefresh(diagram_p);

        DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility(diagram_p);

        NotificationUtil.sendNotification(diagram_p, Notification.Kind.START, Notification.VISIBILITY);

      }
    };
    // Let's perform the job
    TestHelper.getExecutionManager(diagram_p).execute(cmd);
  }

  /**
   * All the contribution layers of the diagram
   * @param diagramDescription_p
   * @param viewpoints_p
   * @return contributed layers
   */
  public static List<Layer> getContributedLayers(DiagramDescription diagramDescription_p, Collection<Viewpoint> viewpoints_p) {
    return new DiagramComponentizationManager().getAllLayers(viewpoints_p, diagramDescription_p);
  }

  /**
   * Get The Diagram Editor
   * @param session_p
   * @param diagram_p
   * @return diagram editor
   */
  public static IEditorPart getDiagramEditor(Session session_p, DDiagram diagram_p) {
    /* retrieve editing session from session */
    IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session_p);

    /* retrieve opened editors from editing session */
    DialectEditor editor = editingSession.getEditor(diagram_p);

    return editor;
  }

  /**
   * Open a 'diagram' e.g. a {@link DRepresentation}.
   * @param session_p
   * @param drep_p
   * @return
   */
  public static IEditorPart opendiagramEditor(Session session_p, DRepresentation drep_p) {
    return DialectUIManager.INSTANCE.openEditor(session_p, drep_p, new NullProgressMonitor());
  }

  /**
   * @param diagram_p
   * @return
   */
  public static Collection<DDiagramElement> getDiagramElements(DDiagram diagram_p) {
    return diagram_p.getDiagramElements();
  }

  /**
   * @param element_p
   */
  public static void removeView(DDiagramElement element_p) {
    if (element_p instanceof AbstractDNode) {
      DiagramServices.getDiagramServices().removeAbstractDNodeView((AbstractDNode) element_p);
    } else if (element_p instanceof DEdge) {
      DiagramServices.getDiagramServices().removeEdgeView((DEdge) element_p);
    } else if ((element_p instanceof DDiagram) || (element_p instanceof DDiagramElementContainer)) {
      DiagramServices.getDiagramServices().removeContainerView((EObject) element_p);
    } else if (element_p instanceof DNodeListElement) {
      DiagramServices.getDiagramServices().removeNodeListElementView((DNodeListElement) element_p);
    } else if (element_p instanceof DNode) {
      DiagramServices.getDiagramServices().removeNodeView((DNode) element_p);
    }
  }

  /**
   * Test if the input diagram is synchronized
   * @param diagram a given DDiagram
   */
  public static void checkIsSynchronized(DDiagram diagram_p) {
    boolean isSynchronized = diagram_p.isSynchronized();
    Assert.assertTrue(MessageFormat.format(Messages.failedIsSynchronized, diagram_p.getName()), isSynchronized);
  }

  /**
   * Test if the input diagram is unsynchronized
   * @param diagram a given DDiagram
   */
  public static void checkIsUnSynchronized(DDiagram diagram_p) {
    boolean isUnSynchronized = !(diagram_p.isSynchronized());
    Assert.assertTrue(MessageFormat.format(Messages.failedIsUnSynchronized, diagram_p.getName()), isUnSynchronized);
  }

  /**
   * Get the visible elements of a diagram
   * @param diagram a given DDiagram
   */
  public static List<DDiagramElement> getVisibleDiagramElements(DDiagram diagram_p) {
    List<DDiagramElement> result = new ArrayList<DDiagramElement>();
    EList<DDiagramElement> diagramElements = diagram_p.getDiagramElements();
    for (DDiagramElement currentElement : diagramElements) {
      if (currentElement.isVisible()) {
        result.add(currentElement);
      }
    }
    return result;
  }

  /**
   * Get the invisible elements of a diagram (hidden and not visible diagram elements)
   * @param diagram a given DDiagram
   */
  public static List<DDiagramElement> getInvisibleDiagramElements(DDiagram diagram_p) {
    List<DDiagramElement> result = new ArrayList<DDiagramElement>();
    EList<DDiagramElement> diagramElements = diagram_p.getDiagramElements();
    for (DDiagramElement currentElement : diagramElements) {
      if (!(currentElement.isVisible())) {
        result.add(currentElement);
      }
    }
    return result;
  }

  /**
   * Get the note element of a diagram by its description
   * @param diagram_p, which contains the note
   * @param description_p, the description of the note
   * @return
   */

  public static ShapeStyle getNote(DDiagram diagram_p, String description_p) {
    EList<AnnotationEntry> ownedAnnotationEntries = diagram_p.getOwnedAnnotationEntries();

    AnnotationEntry annotationEntry = ownedAnnotationEntries.get(0);
    EObject data = annotationEntry.getData();
    Assert.assertTrue(data instanceof Diagram);
    final Diagram diagram = (Diagram) data;

    TreeIterator<EObject> eAllContents = diagram.eAllContents();
    TreeIterator<EObject> persistedChildren = eAllContents;

    while (persistedChildren.hasNext()) {
      EObject next = persistedChildren.next();
      if (next instanceof Shape) {
        Shape note = (Shape) next;
        if (note.getDescription().equalsIgnoreCase(description_p)) {
          return note;
        }
      }
      if (next instanceof ShapeStyle) {
        ShapeStyle note = (ShapeStyle) next;
        if (note.getDescription().equalsIgnoreCase(description_p)) {
          return note;
        }
      }
    }
    return null;
  }

  /**
   * Get all note elements of a diagram
   * @param diagram_p, which contains the note
   * @param description_p, the description of the note
   * @return
   */

  @SuppressWarnings("nls")
  public static List<ShapeStyle> getDiagramNotes(DDiagram diagram_p) {
    List<ShapeStyle> resultList = new ArrayList<ShapeStyle>();
    EList<AnnotationEntry> ownedAnnotationEntries = diagram_p.getOwnedAnnotationEntries();

    AnnotationEntry annotationEntry = ownedAnnotationEntries.get(0);
    EObject data = annotationEntry.getData();
    Assert.assertTrue(data instanceof Diagram);
    final Diagram diagram = (Diagram) data;

    TreeIterator<EObject> eAllContents = diagram.eAllContents();
    TreeIterator<EObject> persistedChildren = eAllContents;

    while (persistedChildren.hasNext()) {
      EObject next = persistedChildren.next();
      if (next instanceof Shape) {
        Shape note = (Shape) next;
        if (note.getType().equals("note")) {
          resultList.add(note);
        }
      }
      if (next instanceof ShapeStyle) {
        ShapeStyle note = (ShapeStyle) next;
        if (!(note.getDescription().equals(""))) {
          resultList.add(note);
        }
      }
    }
    return resultList;
  }

  /**
   * get the list of note attachments of a diagram
   */
  @SuppressWarnings("rawtypes")
  public static List<Edge> getDiagramNoteAttachments(DDiagram diagram_p) {
    List<Edge> result = new ArrayList<Edge>();

    EList<AnnotationEntry> ownedAnnotationEntries = diagram_p.getOwnedAnnotationEntries();

    AnnotationEntry annotationEntry = ownedAnnotationEntries.get(0);
    EObject data = annotationEntry.getData();
    Assert.assertTrue(data instanceof Diagram);
    final Diagram diagram = (Diagram) data;

    EList edges = diagram.getPersistedEdges();
    for (Object element : edges) {
      if (element instanceof Edge) {
        Edge currentEdge = (Edge) element;
        if (currentEdge.getType().equals("NoteAttachment")) { //$NON-NLS-1$
          result.add(currentEdge);
        }
      }
    }
    return result;
  }

  /**
   * check if the given contextual elements of a diagram are the same than the calculated ones
   * @param diagram_p, the diagram to test
   * @param expectedContextualElementsList_p, list of expected contextual elements
   */
  @SuppressWarnings("boxing")
  public static void checkDiagramContextualElements(DDiagram diagram_p, List<EObject> expectedContextualElementsList_p) {
    boolean hasContextualElements = ContextualDiagramHelper.getService().hasContextualElements(diagram_p);
    Assert.assertTrue(MessageFormat.format(Messages.noContextualElement, diagram_p.getName()), hasContextualElements);
    List<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(diagram_p);
    int expectedNumberOfContextualElements = expectedContextualElementsList_p.size();
    boolean sameSize = contextualElements.size() == expectedNumberOfContextualElements;
    Assert.assertTrue(
        MessageFormat.format(Messages.wrongNumberOfContextualElement, diagram_p.getName(), contextualElements.size(), expectedNumberOfContextualElements),
        sameSize);
    boolean sameElements = contextualElements.containsAll(expectedContextualElementsList_p) && expectedContextualElementsList_p.containsAll(contextualElements);
    Assert.assertTrue(Messages.wrongContextualElement, sameElements);
  }

  /**
   * @param seqDiagram
   * @return
   */
  public static Diagram getDiagram(DDiagram seqDiagram) {
    EList<AnnotationEntry> ownedAnnotationEntries = seqDiagram.getOwnedAnnotationEntries();
    AnnotationEntry annotationEntry = ownedAnnotationEntries.get(0);
    EObject data = annotationEntry.getData();
    Assert.assertTrue(data instanceof Diagram);
    final Diagram diagram = (Diagram) data;
    return diagram;
  }

  public static boolean isDiagramElementFiltered(DDiagramElement element) {
    return new DDiagramElementQuery(element).isFiltered();
  }

  public static FilterDescription getFilterForDiagram(DDiagram diagram, String filterName) {
    DiagramDescription description = diagram.getDescription();
    EList<FilterDescription> filters = description.getFilters();
    for (FilterDescription filter : filters) {
      if (filter.getName().equalsIgnoreCase(filterName)) {
        return filter;
      }
    }
    return null;
  }

  public static void addFilterInDiagram(final DDiagram diagram, final FilterDescription filter) {
    AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        activatedFilters.add(filter);
        CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diagram);
        builder.computeCompositeFilterApplications();
        DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());// refreshes
                                                                            // the
                                                                            // diagram
      }
    };
    TestHelper.getExecutionManager(diagram).execute(cmd);
  }

  /**
   * Utility method that checks whether some semantics objects should have a representation (or not) onto a diagram.
   * @param diagram_p the target {@link DDiagram}
   * @param list_p the list of semantic Object
   * @param shouldBeAvailable_p defines whether the objects contained into the list should have a representation or not on the target diagram
   */
  @Deprecated
  public static void assertCheckObjectOnDiagram(DDiagram diagram_p, List<EObject> list_p, boolean shouldBeAvailable_p) {

    String errMsg;
    EObject eObject = null;

    errMsg =
        shouldBeAvailable_p ? CommonTestMessages.objectRepresentationNotAvailableOnDiagram : CommonTestMessages.objectRepresentationStillAvailableOnDiagram;

    for (EObject current : list_p) {
      eObject = DiagramHelper.getOnDiagram(diagram_p, current);
      Assert.assertTrue(
          NLS.bind(errMsg, new Object[] { current instanceof AbstractNamedElement ? ((AbstractNamedElement) current).getName() : current.eClass().getName(),
                                         diagram_p.getName() }), shouldBeAvailable_p ? eObject != null : eObject == null);
    }
  }

  public static void isOnDiagram(DDiagram diagram_p, List<EObject> list_p, boolean shouldBeAvailable_p) {

    String errMsg;
    EObject eObject = null;

    errMsg =
        shouldBeAvailable_p ? CommonTestMessages.objectRepresentationNotAvailableOnDiagram : CommonTestMessages.objectRepresentationStillAvailableOnDiagram;

    for (EObject current : list_p) {
      eObject = DiagramHelper.getOnDiagram(diagram_p, current);
      Assert.assertTrue(
          NLS.bind(errMsg, new Object[] { current instanceof AbstractNamedElement ? ((AbstractNamedElement) current).getName() : current.eClass().getName(),
                                         diagram_p.getName() }), shouldBeAvailable_p ? eObject != null : eObject == null);
    }
  }

  /**
   * @param element
   * @return
   */
  public static Collection<DDiagramElement> getOwnedElements(DSemanticDecorator element) {
    if (element instanceof DDiagram) {
      return new ArrayList<DDiagramElement>(((DDiagram) element).getOwnedDiagramElements());
    }
    if (element instanceof DNodeContainer) {
      return new ArrayList<DDiagramElement>(((DNodeContainer) element).getOwnedDiagramElements());
    }
    return null;
  }

}
