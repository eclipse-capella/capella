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
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import static org.junit.Assert.assertFalse;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.listener.Notification;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.CopyFormatAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteFormatAction;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.editor.ISiriusEditor;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.framework.api.CommonTestMessages;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Useful tools for diagram
 */
public class DiagramHelper {

  /**
   * Change the synchronize state on a diagram
   * 
   * @param diagram
   *          a given DDiagram
   * @param isSynchronized
   *          change the state
   */
  public static void setSynchronized(final DDiagram diagram, final boolean isSynchronized) {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      @Override
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
   * 
   * @apiNote that this method will affect the running instance (not specific to a given project)
   * @see org.eclipse.sirius.business.api.session.Session.getSiriusPreferences
   * @param value
   */
  public static void setPreferenceAutoRefresh(final boolean value) {
    IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getCorePreferenceStore();
    preferenceStore.setValue(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), value);
  }

  /**
   * Set the refresh on opening preference for diagram
   * 
   * @apiNote that this method will affect the running instance (not specific to a given project)
   * @see org.eclipse.sirius.business.api.session.Session.getSiriusPreferences
   * @param value
   */
  public static void setPrefereneRefreshOnOpening(final boolean value) {
    IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
    preferenceStore.setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), value);
  }

  /**
   * Set the preference to display prompt dialog display
   * 
   * @apiNote that this method will affect the running instance (not specific to a given project)
   */
  public static void setPreferenceCopyLayoutPrompt(final boolean value) {
    IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
    preferenceStore.setValue(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name(), value);
  }
  
  /**
   * Set the preference for Copy Layout Mode
   * 
   * @param absolute: whether the copy paste is absolute or bounding box
   * @apiNote that this method will affect the running instance (not specific to a given project)
   */
  public static void setPreferenceCopyLayoutMode(final boolean absolute) {
    IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
    preferenceStore.setValue(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name(), absolute);
  }
  
  /**
   * Return the DRepresention with the given name, null otherwise
   * 
   * @param session
   *          the current Session.
   * @param name
   * @return <code>null</code> if an error occurred.
   */
  public static DRepresentation getDRepresentation(final Session session, final String name) {
    DRepresentationDescriptor result = null;
    Collection<DRepresentationDescriptor> dRepresentations = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
    Iterator<DRepresentationDescriptor> it = dRepresentations.iterator();
    while (it.hasNext() && (null == result)) {
      result = it.next();
      if (!result.getName().equals(name)) {
        result = null;
      }
    }
    return result.getRepresentation();
  }

  /**
   * Return the DRepresention with the given diagram name and ID, null otherwise To be used if several diagrams have the
   * same name
   * 
   * @param session
   *          the current Session.
   * @param name
   * @param id
   * @return <code>null</code> if an error occurred.
   */
  public static DRepresentation getDRepresentationByID(final Session session, final String name, final String id) {
    DRepresentation result = null;
    EStructuralFeature featureToCompare = ModellingcorePackage.Literals.MODEL_ELEMENT__ID;

    Collection<DRepresentationDescriptor> dRepresentations = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
    for (DRepresentationDescriptor dRepresentation : dRepresentations) {
      // find DReprensentation with the given diagram name
      if (dRepresentation.getName().equals(name)) {
        DSemanticDiagram dRepresentationDiagram = (DSemanticDiagram) dRepresentation;
        // check the diagram id with the id given in input
        EObject semanticTarget = dRepresentationDiagram.getTarget();
        if (semanticTarget.eGet(featureToCompare).equals(id)) {
          // if the diagram id is the same than the given id, the
          // current DRepresentation is the right one
          result = dRepresentation.getRepresentation();
        }
      }
    }
    return result;
  }

  /**
   * Checks if the session given by its AIRD file path contains the given representation (Diagram)
   * 
   * @param representation
   *          , the representation to test
   * @param filepath
   *          , the aird file path to open the related session
   */
  @SuppressWarnings("nls")
  public static void checkDiagramInSession(DRepresentation representation, String filepath) {
    URI representationURI = representation.eResource().getURI();
    boolean containsRepresentation = representationURI.equals(FileHelper.getFileFullUri(filepath));

    Assert.assertTrue(
        MessageFormat.format("The representation {0}  is not in  the resource {1}", representation, filepath),
        containsRepresentation);
    // Assert.assertTrue(MessageFormat.format(HelperMessages.diagramNotContainedInSession, representation.getName(),
    // file.getName()), containsRepresentation);
  }

  /**
   * Return the DRepresention with the given name and platformSpecificElementId ( ie
   * next.eResource().getURIFragment(next)), null otherwise. Use in case, we have diagrams with the same name in
   * session.
   * 
   * @param session
   *          the current Session.
   * @param n
   * @param platformSpecificElementId
   * @return <code>null</code> if an error occurred.
   */
  public static DRepresentation getDRepresentation(final Session session, final String name,
      final String platformSpecificElementId) {
    DRepresentation result = null;
    Collection<DRepresentationDescriptor> dRepresentations = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
    Iterator<DRepresentationDescriptor> it = dRepresentations.iterator();
    while (it.hasNext()) {
      DRepresentationDescriptor next = it.next();
      if (next.getName().equalsIgnoreCase(name)
          && next.eResource().getURIFragment(next).equalsIgnoreCase(platformSpecificElementId)) {
        result = next.getRepresentation();
        break;
      }
    }
    return result;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the first occurrence found for a given semantic Object,
   * null otherwise
   * 
   * @param diagram
   *          the target diagram
   * @param semanticObject
   *          the semantic object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagram(final DDiagram diagram, final EObject semanticObject) {

    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getTarget() == semanticObject) {
        return element;
      }
    }
    return null;
  }

  public static DDiagramElement getOnDiagram(final DDiagram diagram, final EObject semanticObject, EObject container) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (container.equals(element.eContainer())) {
        if (element.getTarget() == semanticObject) {
          return element;
        }
      }
    }
    return null;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the first occurrence found for a given ID, null otherwise
   * 
   * @param diagram
   *          the target diagram
   * @param anID
   *          , the id of the object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagramByID(final DDiagram diagram, final String anID) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getTarget().eGet(ModellingcorePackage.Literals.MODEL_ELEMENT__ID).equals(anID)) {
        return element;
      }
    }
    return null;
  }
  
  /**
   * Return the first {@link DDiagramElement} corresponding to the first occurrence found for a given UID, null otherwise
   * 
   * @param diagram
   *          the target diagram
   * @param anUID
   *          , the Uid of the object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagramByUID(final DDiagram diagram, final String anUID) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getUid().equals(anUID)) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the first occurrence found for a given ID, null otherwise
   * 
   * @param diagram
   *          the target diagram
   * @param anID
   *          , the id of the object to reach
   * @param aName
   *          , the name of the object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagramByIDAndName(final DDiagram diagram, final String anID, final String aName) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getTarget().eGet(ModellingcorePackage.Literals.MODEL_ELEMENT__ID).equals(anID)
          && element.getName().equals(aName)) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the {@link DDiagramElement} corresponding to the first occurrence found for the given ID and the given
   * Container, null otherwise
   * <p>
   * Useful to select an InstanceRole
   * 
   * @param diagram
   *          the target diagram
   * @param semanticObject
   *          the semantic object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagramByIDandContainer(final DDiagram diagram, final String anID,
      EObject container) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getTarget().eGet(ModellingcorePackage.Literals.MODEL_ELEMENT__ID).equals(anID)
          && element.eContainer().equals(container)) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the first occurrence found for a given semantic Object
   * and which is not a DEdge, null otherwise
   * 
   * @param diagram
   *          the target diagram
   * @param semanticObject
   *          the semantic object to reach
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getOnDiagramNodeElement(final DDiagram diagram, final EObject semanticObject) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if ((element.getTarget() == semanticObject) && !(element instanceof DEdge)) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the first {@link DDiagramElement} corresponding to the given EClass and to the first occurrence found for a
   * given semantic Object, null otherwise
   * 
   * @param diagram
   *          the target diagram
   * @param semanticObject
   *          the semantic object to reach
   * @param expectedSemanticMetaClass
   *          the expected EClass of the found DDiagramElement
   * @return null if not represented on the diagram
   */
  public static DDiagramElement getDDiagramElementByEClass(final DDiagram diagram, final EObject semanticObject,
      EClass expectedSemanticMetaClass) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if ((element.getTarget() == semanticObject) && expectedSemanticMetaClass.isSuperTypeOf(element.eClass())) {
        return element;
      }
    }
    return null;
  }

  /**
   * Return the {@link DDiagramElement} corresponding to the given semanticObject, edge source and edge target
   * 
   * @param diagram
   * @param semanticObject
   * @param source
   * @param target
   * @return
   */
  public static DDiagramElement getDDiagramElementBySourceAndTarget(final DDiagram diagram,
      final EObject semanticObject, EObject source, EObject target) {
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element instanceof DEdge) {
        DEdge edge = (DEdge) element;
        if ((element.getTarget() == semanticObject) && edge.getSourceNode().equals(source)
            && edge.getTargetNode().equals(target)) {
          return element;
        }
      }
    }
    return null;
  }

  /**
   * return the list of available {@link RepresentationDescription} on which a given semantic element can be created.
   * 
   * @param session
   * @param semanticElement
   * @return
   */
  public static List<RepresentationDescription> getMatchingRepresentationDescription(final Session session,
      final EObject semanticElement) {
    List<RepresentationDescription> result = new ArrayList<RepresentationDescription>();

    // Get selected viewpoints.
    Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
    // Get descriptions.
    Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE
        .getAvailableRepresentationDescriptions(selectedViewpoints, semanticElement);
    // Loop over representation descriptions, stop looping as soon as an
    // appropriate description is found.
    for (RepresentationDescription current : descriptions) {
      if (DialectManager.INSTANCE.canCreate(semanticElement, current)) {
        result.add(current);
      }
    }

    return result;
  }

  /**
   * @param session
   * @param semanticElement
   * @param repDescName
   * @return null whether no matching {@link RepresentationDescription} are found
   */
  public static RepresentationDescription getRepresentationDescription(final Session session,
      final EObject semanticElement, final String repDescName) {

    RepresentationDescription result = null;

    List<RepresentationDescription> list = getMatchingRepresentationDescription(session, semanticElement);

    if (!list.isEmpty()) {
      if (null != repDescName) { // Let's try to find it using name
        for (RepresentationDescription current : list) {
          if (current.getName().equals(repDescName)) {
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
   * 
   * @param diagram
   * @return true if operation is ok (i.e. no exception trapped), false otherwise
   */
  public static boolean refreshDiagram(final DDiagram diagram) {
    boolean ret = true;

    try {

      final AbstractCommand cmd = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
        }
      };

      // Let's perform the job
      TestHelper.getExecutionManager(diagram).execute(cmd);

    } catch (Exception e) {
      ret = false;
    }

    return ret;
  }

  /**
   * Utility method in order to (de)activate layers onto diagram
   * 
   * @param targetLayer
   *          the target {@link Layer}
   * @param diagram
   *          the target {@link DDiagram}
   * @param activate
   *          should we activate or deactivate the target layer.
   */
  public static void activateDiagramLayer(final Layer targetLayer, final DDiagram diagram, final boolean activate) {
    final AbstractCommand cmd = new AbstractNonDirtyingCommand() {
      /**
       * Code duplicated from the AbstractChangeLayerActivation class.
       * 
       * @see org.eclipse.sirius.diagram.tools.internal.handler.AbstractChangeLayerActivation#run()
       */
      @Override
      public void run() {

        NotificationUtil.sendNotification(diagram, Notification.Kind.STOP, Notification.VISIBILITY);

        final List<Layer> activatedLayers = diagram.getActivatedLayers();
        final boolean shouldAddLayer = activate && !EqualityHelper.contains(activatedLayers, targetLayer);
        final boolean shouldRemoveLayer = !shouldAddLayer && EqualityHelper.contains(activatedLayers, targetLayer);

        if (shouldAddLayer) {
          activatedLayers.add(targetLayer);
        } else if (shouldRemoveLayer) {
          // Cannot rely on equals an hashcode methods, since layer can be
          // "proxied" (not really that concept, but result is the same :
          // equals and hashcode cannot be used to remove element in this list)
          final Iterator<Layer> iterator = activatedLayers.iterator();
          boolean removed = false;
          while (iterator.hasNext() && !removed) {
            final Layer layerToRemove = iterator.next();
            if (EqualityHelper.areEquals(targetLayer, layerToRemove)) {
              iterator.remove();
              // Only one layer to remove in that list event if it is a list, not a set
              removed = true;
            }
          }
        }

        CapellaServices.getService().forceRefresh(diagram);

        DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility(diagram);

        NotificationUtil.sendNotification(diagram, Notification.Kind.START, Notification.VISIBILITY);

      }
    };
    // Let's perform the job
    TestHelper.getExecutionManager(diagram).execute(cmd);
  }

  /**
   * All the contribution layers of the diagram
   * 
   * @param diagramDescription
   * @param viewpoints
   * @return contributed layers
   */
  public static List<Layer> getContributedLayers(DiagramDescription diagramDescription,
      Collection<Viewpoint> viewpoints) {
    return new DiagramComponentizationManager().getAllLayers(viewpoints, diagramDescription);
  }

  /**
   * Get The Diagram Editor
   * 
   * @param session
   * @param diagram
   * @return diagram editor
   */
  public static IEditorPart getDiagramEditor(Session session, DDiagram diagram) {
    /* retrieve editing session from session */
    IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);

    /* retrieve opened editors from editing session */
    DialectEditor editor = editingSession.getEditor(diagram);

    return editor;
  }

  public static void copyLayout(DDiagram diagram) {
    Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) diagram).getTarget());
    IWorkbenchPart part = DiagramHelper.getDiagramEditor(session, diagram);
    if (part == null) {
      assertFalse("Diagram is not open, can't copy layout", true);
    }
    CopyFormatAction action = new CopyFormatAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(),
        part);
    action.init(); // if we don't do init, action.getWorkbenchPart will be empty, even if we give the part in the
                   // constructor. Due to AbstractCopyPasteFormatAction constructor calling the wrong super().
    action.run();
    GuiActions.flushASyncGuiThread();
  }

  public static void pasteLayout(DDiagram diagram) {
    Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) diagram).getTarget());
    IWorkbenchPart part = DiagramHelper.getDiagramEditor(session, diagram);
    if (part == null) {
      assertFalse("Diagram is not open, can't paste layout", true);
    }
    PasteFormatAction action = new PasteFormatAction(
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), part);
    action.init();
    action.run();
    GuiActions.flushASyncGuiThread();
  }

  /**
   * Close a 'diagram'
   * 
   * @param session
   * @param diagram
   * @return
   */
  public static void closeEditor(Session session, DDiagram diagram) {
    IEditorPart editor = getDiagramEditor(session, diagram);
    if (editor instanceof ISiriusEditor) {
      SessionUIManager.INSTANCE.getUISession(session).closeEditors(true, Arrays.asList((ISiriusEditor) editor));
    }
  }

  /**
   * Open a 'diagram' e.g. a {@link DRepresentation}.
   * 
   * @param session
   * @param drep
   * @return
   */
  public static IEditorPart opendiagramEditor(Session session, DRepresentation drep) {
    return DialectUIManager.INSTANCE.openEditor(session, drep, new NullProgressMonitor());
  }

  /**
   * @param diagram
   * @return
   */
  public static Collection<DDiagramElement> getDiagramElements(DDiagram diagram) {
    return diagram.getDiagramElements();
  }

  /**
   * @param element
   */
  public static void removeView(DDiagramElement element) {
    if (element instanceof AbstractDNode) {
      DiagramServices.getDiagramServices().removeAbstractDNodeView((AbstractDNode) element);
    } else if (element instanceof DEdge) {
      DiagramServices.getDiagramServices().removeEdgeView((DEdge) element);
    } else if ((element instanceof DDiagram) || (element instanceof DDiagramElementContainer)) {
      DiagramServices.getDiagramServices().removeContainerView(element);
    } else if (element instanceof DNodeListElement) {
      DiagramServices.getDiagramServices().removeNodeListElementView((DNodeListElement) element);
    } else if (element instanceof DNode) {
      DiagramServices.getDiagramServices().removeNodeView((DNode) element);
    }
  }

  /**
   * Test if the input diagram is synchronized
   * 
   * @param diagram
   *          a given DDiagram
   */
  public static void checkIsSynchronized(DDiagram diagram) {
    boolean isSynchronized = diagram.isSynchronized();
    Assert.assertTrue(MessageFormat.format(Messages.failedIsSynchronized, EObjectExt.getText(diagram)), isSynchronized);
  }

  /**
   * Test if the input diagram is unsynchronized
   * 
   * @param diagram
   *          a given DDiagram
   */
  public static void checkIsUnSynchronized(DDiagram diagram) {
    boolean isUnSynchronized = !(diagram.isSynchronized());
    Assert.assertTrue(MessageFormat.format(Messages.failedIsUnSynchronized, EObjectExt.getText(diagram)), isUnSynchronized);
  }

  /**
   * Get the visible elements of a diagram
   * 
   * @param diagram
   *          a given DDiagram
   */
  public static List<DDiagramElement> getVisibleDiagramElements(DDiagram diagram) {
    List<DDiagramElement> result = new ArrayList<DDiagramElement>();
    EList<DDiagramElement> diagramElements = diagram.getDiagramElements();
    for (DDiagramElement currentElement : diagramElements) {
      if (currentElement.isVisible()) {
        result.add(currentElement);
      }
    }
    return result;
  }

  /**
   * Get the invisible elements of a diagram (hidden and not visible diagram elements)
   * 
   * @param diagram
   *          a given DDiagram
   */
  public static List<DDiagramElement> getInvisibleDiagramElements(DDiagram diagram) {
    List<DDiagramElement> result = new ArrayList<DDiagramElement>();
    EList<DDiagramElement> diagramElements = diagram.getDiagramElements();
    for (DDiagramElement currentElement : diagramElements) {
      if (!(currentElement.isVisible())) {
        result.add(currentElement);
      }
    }
    return result;
  }

  /**
   * Get the note element of a diagram by its description
   * 
   * @param diagram
   *          , which contains the note
   * @param description
   *          , the description of the note
   * @return
   */

  public static ShapeStyle getNote(DDiagram diagram, String description) {
    EList<AnnotationEntry> ownedAnnotationEntries = diagram.getOwnedAnnotationEntries();

    AnnotationEntry annotationEntry = ownedAnnotationEntries.get(0);
    EObject data = annotationEntry.getData();
    Assert.assertTrue(data instanceof Diagram);
    final Diagram diag = (Diagram) data;

    TreeIterator<EObject> eAllContents = diag.eAllContents();
    TreeIterator<EObject> persistedChildren = eAllContents;

    while (persistedChildren.hasNext()) {
      EObject next = persistedChildren.next();
      if (next instanceof Shape) {
        Shape note = (Shape) next;
        if (note.getDescription().equalsIgnoreCase(description)) {
          return note;
        }
      }
      if (next instanceof ShapeStyle) {
        ShapeStyle note = (ShapeStyle) next;
        if (note.getDescription().equalsIgnoreCase(description)) {
          return note;
        }
      }
    }
    return null;
  }

  /**
   * Get all note elements of a diagram
   * 
   * @param diagram
   *          , which contains the note
   * @param description
   *          , the description of the note
   * @return
   */

  @SuppressWarnings("nls")
  public static List<ShapeStyle> getDiagramNotes(DDiagram diagram) {
    List<ShapeStyle> resultList = new ArrayList<ShapeStyle>();
    EList<AnnotationEntry> ownedAnnotationEntries = diagram.getOwnedAnnotationEntries();

    AnnotationEntry annotationEntry = null;
    for (AnnotationEntry entry : ownedAnnotationEntries) {
      if ("GMF_DIAGRAMS".equals(entry.getSource())) {
        annotationEntry = entry;
      }
    }
    EObject data = annotationEntry.getData();
    Assert.assertTrue(data instanceof Diagram);
    final Diagram diag = (Diagram) data;

    TreeIterator<EObject> eAllContents = diag.eAllContents();
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
  public static List<Edge> getDiagramNoteAttachments(DDiagram diagram) {
    List<Edge> result = new ArrayList<Edge>();

    EList<AnnotationEntry> ownedAnnotationEntries = diagram.getOwnedAnnotationEntries();

    AnnotationEntry annotationEntry = ownedAnnotationEntries.get(0);
    EObject data = annotationEntry.getData();
    Assert.assertTrue(data instanceof Diagram);
    final Diagram diag = (Diagram) data;

    EList edges = diag.getPersistedEdges();
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
   * 
   * @param diagram
   *          , the diagram to test
   * @param expectedContextualElementsList
   *          , list of expected contextual elements
   */
  @SuppressWarnings("boxing")
  public static void checkDiagramContextualElements(DDiagram diagram, List<EObject> expectedContextualElementsList) {
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    boolean hasContextualElements = ContextualDiagramHelper.getService().hasContextualElements(descriptor);
    Assert.assertTrue(MessageFormat.format(Messages.noContextualElement, EObjectExt.getText(diagram)), hasContextualElements);
    List<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(descriptor);
    int expectedNumberOfContextualElements = expectedContextualElementsList.size();
    boolean sameSize = contextualElements.size() == expectedNumberOfContextualElements;
    Assert.assertTrue(MessageFormat.format(Messages.wrongNumberOfContextualElement, EObjectExt.getText(diagram),
        contextualElements.size(), expectedNumberOfContextualElements), sameSize);
    boolean sameElements = contextualElements.containsAll(expectedContextualElementsList)
        && expectedContextualElementsList.containsAll(contextualElements);
    Assert.assertTrue(Messages.wrongContextualElement, sameElements);
  }

  /**
   * @param seqDiagram
   * @return
   */
  public static Diagram getDiagram(DDiagram seqDiagram) {
    EList<AnnotationEntry> ownedAnnotationEntries = seqDiagram.getOwnedAnnotationEntries();
    EObject data = null;
    for (AnnotationEntry annotationEntry : ownedAnnotationEntries) {
      if ((annotationEntry != null) && (annotationEntry.getData() instanceof Diagram)) {
        data = annotationEntry.getData();
      }
    }
    Assert.assertTrue(data instanceof Diagram);
    final Diagram diagram = (Diagram) data;
    return diagram;
  }

  public static boolean isDiagramElementSelectable(DDiagramElement element) {
    DDiagramElementQuery query = new DDiagramElementQuery(element);
    return !(query.isIndirectlyCollapsed() || query.isFiltered() || query.isIndirectlyHidden()
        || query.isIndirectlyFolded());
  }

  public static boolean isDiagramElementFiltered(DDiagramElement element) {
    return new DDiagramElementQuery(element).isFiltered() || new DDiagramElementQuery(element).isIndirectlyFiltered();
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
      @Override
      public void run() {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        activatedFilters.add(filter);
        CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diagram);
        builder.computeCompositeFilterApplications();
      }
    };
    TestHelper.getExecutionManager(diagram).execute(cmd);
  }
  
  public static void removeFilterInDiagram(final DDiagram diagram, final FilterDescription filter) {
    AbstractCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        activatedFilters.remove(filter);
        CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diagram);
        builder.computeCompositeFilterApplications();
      }
    };
    TestHelper.getExecutionManager(diagram).execute(cmd);
  }
  
  /**
   * Utility method that checks whether some semantics objects should have a representation (or not) onto a diagram.
   * 
   * @param diagram
   *          the target {@link DDiagram}
   * @param list
   *          the list of semantic Object
   * @param shouldBeAvailable
   *          defines whether the objects contained into the list should have a representation or not on the target
   *          diagram
   */
  @Deprecated
  public static void assertCheckObjectOnDiagram(DDiagram diagram, List<EObject> list, boolean shouldBeAvailable) {

    String errMsg;
    EObject eObject = null;

    errMsg = shouldBeAvailable ? CommonTestMessages.objectRepresentationNotAvailableOnDiagram
        : CommonTestMessages.objectRepresentationStillAvailableOnDiagram;

    for (EObject current : list) {
      eObject = DiagramHelper.getOnDiagram(diagram, current);
      Assert.assertTrue(
          NLS.bind(errMsg,
              new Object[] { current instanceof AbstractNamedElement ? ((AbstractNamedElement) current).getName()
                  : current.eClass().getName(), EObjectExt.getText(diagram) }),
          shouldBeAvailable ? eObject != null : eObject == null);
    }
  }

  public static void isOnDiagram(DDiagram diagram, List<EObject> list, boolean shouldBeAvailable) {

    String errMsg;
    EObject eObject = null;

    errMsg = shouldBeAvailable ? CommonTestMessages.objectRepresentationNotAvailableOnDiagram
        : CommonTestMessages.objectRepresentationStillAvailableOnDiagram;

    for (EObject current : list) {
      eObject = DiagramHelper.getOnDiagram(diagram, current);
      Assert.assertTrue(
          NLS.bind(errMsg,
              new Object[] { current instanceof AbstractNamedElement ? ((AbstractNamedElement) current).getName()
                  : current.eClass().getName(), EObjectExt.getText(diagram) }),
          shouldBeAvailable ? eObject != null : eObject == null);
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
    if (element instanceof DNodeList) {
      ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();
      for (DDiagramElement view : ((DNodeList) element).getOwnedElements()) {
        if (view instanceof AbstractDNode) {
          views.add(view);
        }
      }
      return views;
    }
    if (element instanceof AbstractDNode) {
      return DiagramServices.getDiagramServices().getOwnedAbstractNodes(element);
    }
    return Collections.emptyList();
  }

  public static List<DEdge> getEdges(DDiagram diagram, String... elementIds) {
    List<DEdge> edgeList = new ArrayList<DEdge>();
    List<String> ids = Arrays.stream(elementIds).collect(Collectors.toList());
    for (DDiagramElement element : diagram.getDiagramElements()) {
      if ((element instanceof DEdge) && (element.getTarget() instanceof ModelElement)
          && ids.contains(((ModelElement) element.getTarget()).getId())) {
        edgeList.add((DEdge) element);
      }
    }
    return edgeList;
  }
}
