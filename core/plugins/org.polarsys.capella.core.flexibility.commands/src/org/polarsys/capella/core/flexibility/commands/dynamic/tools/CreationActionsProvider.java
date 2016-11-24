/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.flexibility.commands.dynamic.tools;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.helper.task.DeleteDRepresentationTask;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.capellamodeller.util.CapellamodellerResourceImpl;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider;
import org.polarsys.capella.core.model.handler.command.DeleteStructureCommand;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;

/**
 */
public class CreationActionsProvider implements IActionsProvider {

  /**
   * @see org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider#getActions()
   */
  public Collection<DefaultAction> getActions(Shell shell, ISelectionProvider selectionProvider) {
    List<DefaultAction> list = new ArrayList<DefaultAction>();

    list.add(new CreateFunctions(shell, selectionProvider));

    return list;
  }

  private class CreateFunctions extends DefaultAction {

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getDescription() {
      return "This action unsynchronizes diagrams and removes hidden elements"; //$NON-NLS-1$
    }

    @Override
    public String getCategory() {
      return DefaultCategories.DIAGRAM_CATEGORY;
    }

    @Override
    protected String getIconFile() {
      return "process.gif"; //$NON-NLS-1$
    }

    @Override
    public boolean isSelectionCompatible() {
      if (getSelection(EObject.class).size() == 1) {
        EObject element = (EObject) getSelection(EObject.class).get(0);
        return (element instanceof SystemFunction);
      }
      return false;
    }

    public CreateFunctions(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Create Functions"; //$NON-NLS-1$
    }

    public void proceed(SystemFunction function, int a) {

      for (int i = 0; i < 10; i++) {
        SystemFunction aa = CtxFactory.eINSTANCE.createSystemFunction("SF_" + a + "_" + i); //$NON-NLS-1$ //$NON-NLS-2$
        function.getOwnedFunctions().add(aa);
        if (a > 0) {
          proceed(aa, a - 1);
        }
      }
    }

    @Override
    public void execute() {

      for (EObject object : getSelectedEObjects()) {
        SystemFunction function = (SystemFunction) object;
        proceed(function, 3);
      }

    }

  }

  public class ChangeID extends DefaultAction {

    String SEP = "  "; //$NON-NLS-1$

    public ChangeID(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif"; //$NON-NLS-1$
    }

    @Override
    public String getText() {
      return "Change ID of elements with SID"; //$NON-NLS-1$
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.tools.clean.id"; //$NON-NLS-1$
    }

    @Override
    public void execute() {
      for (EObject object : getEObjectContents()) {
        if (object instanceof AbstractNamedElement) {
          if (((ModelElement) object).getSid() != null) {
            String id = org.polarsys.capella.common.lib.IdGenerator.createId();
            ((ModelElement) object).setId(id);
            ((Resource.Internal) object.eResource()).attached(object);
          }
        }
      }
    }

    /**
     * Get all representations where specified semantic element is displayed.
     * @param semanticElement
     * @param filteringRepresentationDescriptionClass
     * @return a not <code>null</code> collection.
     */
    public Collection<EObject> getAllRepresentationsWhereSemanticElementIsDisplayed(EObject semanticElement, RunnableWithBooleanResult filteringCondition) {
      Set<EObject> result = new HashSet<EObject>(0);
      Session session = SessionManager.INSTANCE.getSession(semanticElement);
      if (null == session) {
        return result;
      }
      for (Viewpoint currentSelectedViewpoint : session.getSelectedViewpoints(false)) {
        for (RepresentationDescription representationDescription : currentSelectedViewpoint.getOwnedRepresentations()) {
          boolean search = true;
          // If a condition is given, used to filter out or not current representation description.
          if (null != filteringCondition) {
            filteringCondition.setObject(representationDescription);
            filteringCondition.run();
            search = filteringCondition.getResult().booleanValue();
          }
          if (search) {
            // Get all representations for current representation description.
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(representationDescription, session);
            // Loop over representations, to search the ones that contain our model element.
            for (DRepresentation representation : representations) {
              if (representation instanceof DSemanticDecorator) {
                EObject target = ((DSemanticDecorator) representation).getTarget();
                if (semanticElement.equals(target)) {
                  result.add(representation);
                }
              }
              for (DRepresentationElement representationElement : representation.getRepresentationElements()) {
                EObject target = representationElement.getTarget();
                if (semanticElement.equals(target)) {
                  result.add(representationElement);
                  break;
                }

                for (EObject semanticElt : representationElement.getSemanticElements()) {
                  if (semanticElement.equals(semanticElt)) {
                    result.add(representationElement);
                    break;
                  }
                }
              }
            }
          }
        }
      }
      return result;
    }

  }

  private class Unsynchronizer extends DefaultAction {

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getDescription() {
      return "This action unsynchronizes diagrams and removes hidden elements"; //$NON-NLS-1$
    }

    @Override
    public String getCategory() {
      return DefaultCategories.DIAGRAM_CATEGORY;
    }

    @Override
    protected String getIconFile() {
      return "process.gif"; //$NON-NLS-1$
    }

    @Override
    public boolean isSelectionCompatible() {
      if (getSelection(EObject.class).size() == 1) {
        EObject element = (EObject) getSelection(EObject.class).get(0);
        return (element instanceof SystemEngineering) || (element instanceof DDiagram);
      }
      return false;
    }

    public Unsynchronizer(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Unsynchronize diagrams"; //$NON-NLS-1$
    }

    List<DDiagram> getDiagrams() {
      List<DDiagram> diagrams = new ArrayList<DDiagram>();

      for (EObject object : getSelectedEObjects()) {
        Resource re = object.eResource();

        if (object instanceof DDiagram) {
          diagrams.add((DDiagram) object);

        } else if (object instanceof SystemEngineering) {
          Session session = SessionManager.INSTANCE.getSession(object);
          if (session != null) {
            for (DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
              if (representation instanceof DDiagram) {
                diagrams.add((DDiagram) representation);
              }
            }
          }
        }
      }
      return diagrams;
    }

    @Override
    public void execute() {

      IRunnableWithProgress runnable = new IRunnableWithProgress() {

        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          boolean hasProceed = false;
          int nbProceed = 0;
          List<DDiagram> objects = getDiagrams();
          getLogger().info(new EmbeddedMessage(objects.size() + " diagrams to be opened", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$

          monitor.beginTask("Open diagrams", objects.size()); //$NON-NLS-1$
          for (DDiagram object : objects) {
            monitor.setTaskName("Opening : " + object.getName()); //$NON-NLS-1$
            if (process(object)) {
              hasProceed = true;
              nbProceed++;
            }
            monitor.worked(1);
          }

          if (!hasProceed) {
            getLogger().info(new EmbeddedMessage("No diagram have been opened", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          } else {
            getLogger().info(new EmbeddedMessage(nbProceed + " diagrams have been opened", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          }

          monitor.done();
        }
      };

      ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
      try {
        progressDialog.run(false, false, runnable);
      } catch (InvocationTargetException ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      } catch (InterruptedException ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      }

    }

    /**
     * @param object
     */
    private boolean process(DDiagram object) {
      boolean hasProceed = true;

      try {

        if (object.isSynchronized()) {
          getLogger().info(new EmbeddedMessage(NLS.bind("{0} to be unsynchronized ", object.getName()), IReportManagerDefaultComponents.UI)); //$NON-NLS-1$

          object.setSynchronized(false);

          ArrayList<DDiagramElement> elements = new ArrayList<DDiagramElement>();

          for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(object)) {
            if (!element.isVisible()) {
              elements.add(element);

            }
          }

          getLogger().info(new EmbeddedMessage(NLS.bind("{0} diagram elements removed", elements.size()), IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          for (DDiagramElement element : elements) {
            if ((element instanceof DDiagram) || (element instanceof DDiagramElementContainer)) {
              DiagramServices.getDiagramServices().removeContainerView(element);

            } else if (element instanceof DNode) {
              DiagramServices.getDiagramServices().removeNodeView((DNode) element);

            } else if (element instanceof DEdge) {
              DiagramServices.getDiagramServices().removeEdgeView((DEdge) element);

            }

          }

        }

      } catch (Exception ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));

      }
      return hasProceed;
    }
  };

  public class CleanSID extends DefaultAction {

    String SEP = "  "; //$NON-NLS-1$

    public CleanSID(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
      return "Remove all SID of selected/contained elements"; //$NON-NLS-1$
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif"; //$NON-NLS-1$
    }

    @Override
    public String getText() {
      return "Remove SID"; //$NON-NLS-1$
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.tools.clean"; //$NON-NLS-1$
    }

    @Override
    public void execute() {

      for (EObject object : getSelectedEObjects()) {
        if (object instanceof CapellaElement) {
          if (((CapellaElement) object).getSid() != null) {
            ((CapellaElement) object).setSid(null);
          }
        }
      }
      for (EObject object : getSelectedEObjects()) {
        browse(object, ""); //$NON-NLS-1$
      }

    }

    /**
     * @param object
     */
    private void browse(EObject object, String sep) {

      for (EObject content : object.eContents()) {
        browse(content, SEP + sep);
        if (content instanceof CapellaElement) {
          if (((CapellaElement) content).getSid() != null) {
            ((CapellaElement) content).setSid(null);
          }
        }
      }
    }

  }

  private class RemoveUselessFragments extends DefaultAction {

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getDescription() {
      return "This action delete unloaded files"; //$NON-NLS-1$
    }

    @Override
    public String getCategory() {
      return DefaultCategories.DIAGRAM_CATEGORY;
    }

    @Override
    protected String getIconFile() {
      return "process.gif"; //$NON-NLS-1$
    }

    @Override
    public boolean isSelectionCompatible() {
      return (getSelection(EObject.class).size() >= 1);
    }

    public RemoveUselessFragments(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Delete unused files"; //$NON-NLS-1$
    }

    List<IFile> getFiles() {
      final List<IFile> filesLoaded = new ArrayList<IFile>();
      final List<IFile> files = new ArrayList<IFile>();

      Resource capella = null;

      for (EObject object : getSelectedEObjects()) {
        ResourceSet set = object.eResource().getResourceSet();
        for (Resource resource : set.getResources()) {
          IFile file = EcoreUtil2.getFile(resource);
          if (file != null) {
            filesLoaded.add(file);
          }
        }

        if (object.eResource() instanceof CapellamodellerResourceImpl) {
          capella = object.eResource();
        }
      }
      if (capella != null) {
        IFile file = EcoreUtil2.getFile(capella);
        IProject project = file.getProject();

        try {
          project.accept(new IResourceVisitor() {
            public boolean visit(IResource resource) throws CoreException {
              if (!filesLoaded.contains(resource) && (resource instanceof IFile)) {

                if (!".project".equals(resource.getName())) { //$NON-NLS-1$
                  files.add((IFile) resource);

                }
              }
              return true;
            }
          });

        } catch (CoreException exception) {
        }
      }

      return files;
    }

    @Override
    public void execute() {

      IRunnableWithProgress runnable = new IRunnableWithProgress() {

        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          boolean hasProceed = false;
          int nbProceed = 0;
          List<IFile> objects = getFiles();
          getLogger().info(new EmbeddedMessage(objects.size() + " files to be deleted", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          monitor.beginTask("Delete files", objects.size()); //$NON-NLS-1$

          for (IFile object : objects) {

            try {
              if (object.isAccessible() && !object.isPhantom()) {
                object.delete(true, new NullProgressMonitor());
              }
              hasProceed = true;
            } catch (CoreException exception) {
            }

          }

          if (!hasProceed) {
            getLogger().info(new EmbeddedMessage("No files have been deleted", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          } else {
            getLogger().info(new EmbeddedMessage(nbProceed + " files have been deleted", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          }

          monitor.done();
        }
      };

      ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
      try {
        progressDialog.run(false, false, runnable);
      } catch (InvocationTargetException ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      } catch (InterruptedException ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      }

    }

  };

  private class RemoveUselessSemanticForDiagrams extends DefaultAction {

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getDescription() {
      return "This action delete all semantic elements not used into diagrams"; //$NON-NLS-1$
    }

    @Override
    public String getCategory() {
      return DefaultCategories.DIAGRAM_CATEGORY;
    }

    @Override
    protected String getIconFile() {
      return "process.gif"; //$NON-NLS-1$
    }

    @Override
    public boolean isSelectionCompatible() {
      return (getSelection(DDiagram.class).size() >= 1);
    }

    public RemoveUselessSemanticForDiagrams(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Delete useless semantics for diagrams"; //$NON-NLS-1$
    }

    List<DDiagram> getDiagrams() {
      List<DDiagram> diagrams = new ArrayList<DDiagram>();

      for (EObject object : getSelectedEObjects()) {
        if (object instanceof DDiagram) {
          diagrams.add((DDiagram) object);
        }
      }

      return diagrams;
    }

    List<EObject> getRelatedEObjects() {
      List<EObject> related = new ArrayList<EObject>();
      for (DDiagram object : getDiagrams()) {
        for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(object)) {
          if (element.getTarget() != null) {
            related.add(element.getTarget());

          }
        }
      }
      return related;
    }

    List<EObject> getToDeleteEObjects() {
      List<EObject> toDelete = new ArrayList<EObject>();
      List<EObject> relateds = getRelatedEObjects();
      if (relateds.size() == 0) {
        return toDelete;
      }

      HashMap<EObject, Integer> value = new HashMap<EObject, Integer>();
      EObject root = relateds.iterator().next();

      for (EObject object : relateds) {
        if (root == null) {
          root = object;
        }
        LinkedList<EObject> parents = new LinkedList<EObject>();
        parents.add(object);
        while (parents.size() > 0) {
          EObject parent = parents.removeFirst();

          if (value.containsKey(parents)) {
            value.put(parent, value.get(parent) + 1);
          } else {
            value.put(parent, 1);
          }
          if (parent.eContainer() != null) {
            parents.add(parent.eContainer());
          }
        }
      }

      SystemEngineering eng = SystemEngineeringExt.getSystemEngineering((CapellaElement) root);
      for (ModellingArchitecture archi : eng.getOwnedArchitectures()) {
        value.put(archi, 1);
        for (EObject content : archi.eContents()) {
          value.put(content, 1);
        }
      }

      TreeIterator<EObject> childs = eng.eAllContents();
      while (childs.hasNext()) {
        EObject child = childs.next();
        if (!value.containsKey(child)) {
          toDelete.add(child);
        }
      }

      return toDelete;
    }

    @Override
    public void execute() {

      IRunnableWithProgress runnable = new IRunnableWithProgress() {

        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          boolean hasProceed = false;
          int nbProceed = 0;
          List<EObject> objects = getToDeleteEObjects();
          getLogger().info(new EmbeddedMessage(objects.size() + " objects to be deleted", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          monitor.beginTask("Delete objects", objects.size()); //$NON-NLS-1$

          for (EObject object : objects) {
            DeleteStructureCommand comand = new DeleteStructureCommand(TransactionHelper.getEditingDomain(object), Collections.singleton(object), false);
            if (comand.canExecute()) {
              monitor.setTaskName("Deleting : " + object); //$NON-NLS-1$
              comand.execute();
              hasProceed = true;
            }

          }

          if (!hasProceed) {
            getLogger().info(new EmbeddedMessage("No objects have been deleted", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          } else {
            getLogger().info(new EmbeddedMessage(nbProceed + " objects have been deleted", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          }

          monitor.done();
        }
      };

      ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
      try {
        progressDialog.run(false, false, runnable);
      } catch (InvocationTargetException ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      } catch (InterruptedException ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      }

    }

  };

  private class RemoveDiagrams extends DefaultAction {

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getDescription() {
      return "This action delete all others diagrams"; //$NON-NLS-1$
    }

    @Override
    public String getCategory() {
      return DefaultCategories.DIAGRAM_CATEGORY;
    }

    @Override
    protected String getIconFile() {
      return "process.gif"; //$NON-NLS-1$
    }

    @Override
    public boolean isSelectionCompatible() {
      return (getSelection(DDiagram.class).size() >= 1);
    }

    public RemoveDiagrams(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Delete other diagrams"; //$NON-NLS-1$
    }

    List<DDiagram> getDiagrams() {
      List<DDiagram> diagrams = new ArrayList<DDiagram>();
      EObject source = null;

      for (EObject object : getSelectedEObjects()) {
        if (object instanceof DSemanticDecorator) {
          source = ((DSemanticDecorator) object).getTarget();
          break;
        }
      }

      Session session = SessionManager.INSTANCE.getSession(source);
      if (session != null) {
        for (DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
          if ((representation instanceof DDiagram) && !getSelectedEObjects().contains(representation)) {
            diagrams.add((DDiagram) representation);
          }
        }
      }
      return diagrams;
    }

    @Override
    public void execute() {

      IRunnableWithProgress runnable = new IRunnableWithProgress() {

        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          boolean hasProceed = false;
          int nbProceed = 0;
          List<DDiagram> objects = getDiagrams();
          getLogger().info(new EmbeddedMessage(objects.size() + " diagrams to be deleted", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$

          monitor.beginTask("Delete diagrams", objects.size()); //$NON-NLS-1$
          for (DDiagram object : objects) {
            monitor.setTaskName("Deleting : " + object.getName()); //$NON-NLS-1$
            if (process(object)) {
              hasProceed = true;
              nbProceed++;
            }
            monitor.worked(1);
          }

          if (!hasProceed) {
            getLogger().info(new EmbeddedMessage("No diagram have been deleted", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          } else {
            getLogger().info(new EmbeddedMessage(nbProceed + " diagrams have been deleted", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          }

          monitor.done();
        }
      };

      ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
      try {
        progressDialog.run(false, false, runnable);
      } catch (InvocationTargetException ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      } catch (InterruptedException ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      }

    }

    /**
     * @param object
     */
    private boolean process(DDiagram object) {
      boolean hasProceed = true;

      try {
        (new DeleteDRepresentationTask(object)).execute();
      } catch (Exception ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));

      }
      return hasProceed;
    }
  };

}
