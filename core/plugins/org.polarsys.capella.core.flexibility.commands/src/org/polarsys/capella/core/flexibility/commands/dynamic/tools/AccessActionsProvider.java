/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.flexibility.commands.dynamic.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellamodeller.util.CapellamodellerResourceImpl;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.flexibility.commands.Activator;
import org.polarsys.capella.core.flexibility.commands.Messages;
import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.platform.sirius.ui.project.NewProjectWizard;

/**
 */
public class AccessActionsProvider implements IActionsProvider {

  /**
   * @see org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider#getActions()
   */
  public Collection<DefaultAction> getActions(Shell shell, ISelectionProvider selectionProvider) {
    List<DefaultAction> list = new ArrayList<DefaultAction>();

    list.add(new PhysicalLinkDelegation(shell, selectionProvider));
    list.add(new OpenAsText(shell, selectionProvider));
    list.add(new CreateCapellaProject(shell, selectionProvider));
    list.add(new SetAsReadOnly(shell, selectionProvider));
    list.add(new UnsetAsReadOnly(shell, selectionProvider));

    return list;
  }

  @SuppressWarnings("nls")
  private class PhysicalLinkDelegation extends DefaultAction {

    public PhysicalLinkDelegation(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(PhysicalPort.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Retrieve Physical Links delegations";
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.tools.transition";
    }

    @Override
    public void execute() {
      for (EObject source : getSelectedEObjects()) {
        PhysicalPort port = (PhysicalPort) source;
        Collection<PhysicalLink> links = PortExt.getDelegatedPhysicalLinks(port);
        Collection<PhysicalLink> links2 = PortExt.getDelegatingPhysicalLinks(port);

        System.out.println(links);
        System.out.println(links2);
      }
    }
  }

  @SuppressWarnings("nls")
  private class TransitionTest extends DefaultAction {

    public TransitionTest(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(EObject.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Perform transition";
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.tools.transition";
    }

    @Override
    public void execute() {
    }
  }

  @SuppressWarnings("nls")
  private class MakeLogCommands extends DefaultAction {

    public MakeLogCommands(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(EObject.class).size() > 0;
    }

    @Override
    public String getText() {
      return "Log log";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.RETRIEVE_DATA_CATEGORY;
    }

    @Override
    public void execute() {
      for (EObject source : getSelectedEObjects()) {

        Level al = getLogger().getLoggerRepository().getThreshold();
        getLogger().getLoggerRepository().setThreshold(Level.ALL);
        Level a2 = getLogger().getLoggerRepository().getThreshold();
        boolean a = getLogger().isInfoEnabled();
        getLogger().debug("debug");
        getLogger().info("info");
        getLogger().warn("warn");
        getLogger().error("error");
        getLogger().fatal("fatal");

        getLogger().debug(new EmbeddedMessage("debug", "hop", source));
        getLogger().info(new EmbeddedMessage("info", "hop", source));
        getLogger().warn(new EmbeddedMessage("warn", "hop", source));
        getLogger().error(new EmbeddedMessage("error", "hop", source));
        getLogger().fatal(new EmbeddedMessage("fatal", "hop", source));

      }
    }

    public String getText2(Object object) {
      Object obj = null;

      if (object instanceof AbstractNamedElement) {
        AbstractNamedElement e = (AbstractNamedElement) object;

        String name = CapellaElementExt.getName((EObject) object);

        return "mustBeTransitioned(\"" + name + "\", _id_" + name.toLowerCase().replace(" ", "_") + ", container); //$NON-NLS-1$"; //$NON-NLS-1$";
      }
      return "";
    }
  }

  @SuppressWarnings("nls")
  private class MustBeTransitioned extends DefaultAction {

    public MustBeTransitioned(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(EObject.class).size() > 0;
    }

    @Override
    public String getText() {
      return "Must Be Transitioned";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.RETRIEVE_DATA_CATEGORY;
    }

    @Override
    public void execute() {
      for (EObject source : getSelectedEObjects()) {
        println(getText2(source));
      }
    }

    public String getText2(Object object) {
      Object obj = null;

      if (object instanceof AbstractNamedElement) {
        AbstractNamedElement e = (AbstractNamedElement) object;

        String name = CapellaElementExt.getName((EObject) object);

        return "mustBeTransitioned(\"" + name + "\", _id_" + name.toLowerCase().replace(" ", "_") + ", container); //$NON-NLS-1$"; //$NON-NLS-1$";
      }
      return "";
    }
  }

  private class ValidationAccessor extends DefaultAction {

    private class ConstraintDesc {

      String categories;
      String id;
      String name;
      String severity;
      String message;
      String target;
      String description;
      String bundleid;
      String sclass;

      public ConstraintDesc(IConfigurationElement constraint) {
        IConfigurationElement parent = (IConfigurationElement) constraint.getParent();
        categories = parent.getAttribute("categories");
        id = constraint.getAttribute("id");
        name = constraint.getAttribute("name");
        severity = constraint.getAttribute("severity");
        message = constraint.getAttribute("message");
        sclass = constraint.getAttribute("class");
        bundleid = constraint.getNamespaceIdentifier();
        try {
          target = constraint.getChildren("target")[0].getAttribute("class");
        } catch (Exception e) {
        }
        try {
          description = constraint.getChildren("description")[0].getValue();
          description = description.replaceAll("\n", " ");
        } catch (Exception e) {
        }
      }

      @Override
      public String toString() {
        return "" + id + "\t" + target + "\t" + sclass + "\t" + name + "\t" + description;
      }
    }

    public ValidationAccessor(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    /**
     * @return
     */
    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Validation rules";
    }

    @Override
    public String getCategory() {
      return DefaultCategories.DEFAULT_CATEGORY;
    }

    @Override
    public void execute() {

      IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
      IConfigurationElement[] elementsForPlugin = extensionRegistry.getConfigurationElementsFor("org.eclipse.emf.validation.constraintProviders");

      TreeSet<ConstraintDesc> elements = new TreeSet<ConstraintDesc>(new Comparator<ConstraintDesc>() {

        public int compare(ConstraintDesc arg0, ConstraintDesc arg1) {
          int res = arg0.id.compareTo(arg1.id);
          if (res == 0) {
            res = arg0.name.compareTo(arg1.name);
          }
          return res;
        }

      });

      for (IConfigurationElement root : elementsForPlugin) {
        for (IConfigurationElement constraints : root.getChildren("constraints")) {
          for (IConfigurationElement constraint : constraints.getChildren("constraint")) {
            elements.add(new ConstraintDesc(constraint));
          }
        }
      }

      for (ConstraintDesc desc : elements) {
        System.out.println(desc);
      }

    }

  };

  private class DirtyDiagramAccessor extends DefaultAction {

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    public DirtyDiagramAccessor(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Retrieve dirty diagrams";
    }

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getCategory() {
      return DefaultCategories.DIAGRAM_CATEGORY;
    }

    @Override
    public void execute() {

      Logger logger = getLogger();

      boolean noDiagrams = true;

      ArrayList<DDiagram> diagrams = new ArrayList<DDiagram>();
      for (EObject obj : getSelectedEObjects()) {
        Session session = SessionManager.INSTANCE.getSession(obj);
        for (DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
          if (representation instanceof DDiagram) {
            DDiagram diagram = (DDiagram) representation;
            int nbElements = 0;
            for (DDiagramElement element : diagram.getDiagramElements()) {
              if (element.getTarget() == null) {
                if (!diagrams.contains(diagram)) {
                  diagrams.add(diagram);
                }
                nbElements++;
                noDiagrams = false;
              }
            }

            if (nbElements > 0) {
              logger.error(new EmbeddedMessage(NLS.bind("Diagram ''{0}'' ({1} invalid elements)", diagram.getName(), nbElements),
                  IReportManagerDefaultComponents.UI, diagram));
              System.out.println(diagram.getName());
            }
          }
        }

      }

      if (noDiagrams) {
        logger.info(new EmbeddedMessage("All diagrams seems to be valid", IReportManagerDefaultComponents.UI));
      }

    }
  };

  private class EmptyDiagramAccessor extends DefaultAction {

    HashMap<String, EObject> mapNames = new HashMap<String, EObject>();
    HashMap<String, EObject> mapIds = new HashMap<String, EObject>();

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(ModelElement.class).size() > 0;
    }

    public EmptyDiagramAccessor(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Retrieve graphical elements not linked to a semantic element";
    }

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getCategory() {
      return DefaultCategories.DIAGRAM_CATEGORY;
    }

    @Override
    public void execute() {

      Logger logger = getLogger();

      boolean noDiagrams = true;

      logger.info(new EmbeddedMessage("Diagrams checking...", IReportManagerDefaultComponents.UI));

      if (getSelectedEObjects().size() > 0) {
        EObject root = getSelectedEObjects().get(0);
        initMap(root);

        Session session = SessionManager.INSTANCE.getSession(root);
        for (DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
          if (representation instanceof DDiagram) {
            DDiagram diagram = (DDiagram) representation;
            if (performDiagram(diagram)) {
              noDiagrams = false;
            }
          }
        }
      }

      mapNames.clear();
      mapIds.clear();

      if (noDiagrams) {
        logger.info(new EmbeddedMessage("All diagrams seems to be valid", IReportManagerDefaultComponents.UI));
      }

    }

    private void initMap(EObject obj) {
      ResourceSet set = obj.eResource().getResourceSet();
      for (Resource resource : set.getResources()) {
        if (resource instanceof CapellamodellerResourceImpl) {
          Iterator<EObject> res = resource.getAllContents();
          while (res.hasNext()) {
            EObject element = res.next();
            if (element instanceof ModelElement) {
              mapIds.put(((ModelElement) element).getId(), element);
            }
            if (element instanceof AbstractNamedElement) {
              mapNames.put(((AbstractNamedElement) element).getName(), element);
            }
          }
        }
      }
    }

    private boolean performDiagram(DDiagram diagram) {
      Logger logger = getLogger();
      List<DDiagramElement> invalids = new ArrayList<DDiagramElement>();
      List<DDiagramElement> realInvalids = new ArrayList<DDiagramElement>();
      List<DDiagramElement> realPortInvalids = new ArrayList<DDiagramElement>();

      logger.info(new EmbeddedMessage(NLS.bind("Diagram ''{0}'' checking...", diagram.getName()), IReportManagerDefaultComponents.UI, diagram));

      for (DDiagramElement element : diagram.getDiagramElements()) {

        if (element.getTarget() == null) {
          invalids.add(element);
          String label = element.getName();
          
          DiagramElementMapping diagramElementMapping = element.getDiagramElementMapping();

          if (diagramElementMapping != null) {
            if (diagramElementMapping.eContainingFeature() == org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS) {

              if ((element.eContainer() != null) && (element.eContainer() instanceof DDiagramElement)) {
                label = ((DDiagramElement) element.eContainer()).getName();
                if (mapNames.get(label) != null) {
                  realPortInvalids.add(element);
                }
              }
            } else if (mapNames.get(label) != null) {
              realInvalids.add(element);
            }
          }
        }
      }

      logger.info(new EmbeddedMessage(NLS.bind("{0} graphical elements aren't related to a semantic element.", invalids.size()),
          IReportManagerDefaultComponents.UI, diagram));
      if (invalids.size() > 0) {

        logger.info(new EmbeddedMessage(NLS.bind(
            "Diagram contains {0} graphical elements which can potentially be associated to an existing semantic element.", realInvalids.size()
                                                                                                                            + realPortInvalids.size()),
            IReportManagerDefaultComponents.UI, diagram));

        for (DDiagramElement realInvalid : realInvalids) {
          logger.info(new EmbeddedMessage(NLS.bind(
              "--  Graphical element named ''{0}'' is not linked to a semantic element but there is a semantic element with same name in model.",
              realInvalid.getName()), IReportManagerDefaultComponents.UI, new Object[] { mapNames.get(realInvalid.getName()) }));
        }

        for (DDiagramElement realInvalid : realPortInvalids) {
          logger
              .info(new EmbeddedMessage(
                  NLS.bind(
                      "--  Port of graphical element named ''{0}'' is not linked to a semantic element but there is a semantic element with same parent's name in model.",
                      realInvalid.getName()), IReportManagerDefaultComponents.UI, new Object[] { mapNames.get(realInvalid.getName()) }));
        }

      }
      return invalids.size() > 0;
    }
  };

  private class OpenAsText extends DefaultAction {

    public OpenAsText(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(IFile.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Open as text";
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.file";
    }

    @Override
    public void execute() {

      for (Object folder : getSelection(IAdaptable.class)) {

        if (folder instanceof IFile) {
          IFile file = (IFile) folder;
          try {
            IDE.openEditor(Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage(), file,
                org.eclipse.ui.editors.text.EditorsUI.DEFAULT_TEXT_EDITOR_ID, true);
          } catch (PartInitException exception) {
            // Nothing here
          }
        }

      }

    }

  };

  private class CompareAsText extends DefaultAction {

    public CompareAsText(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(IFile.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Compare as text";
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.file";
    }

    @Override
    public void execute() {

    }
  };

  private class UnsetAsReadOnly extends SetAsReadOnly {

    public UnsetAsReadOnly(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Unset read only";
    }

    @Override
    protected boolean isReadOnly() {
      return false;
    }
  };

  private class SetAsReadOnly extends DefaultAction {

    public SetAsReadOnly(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(IResource.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Set read only";
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.file.attributes";
    }

    protected boolean isReadOnly() {
      return true;
    }

    @Override
    public void execute() {

      for (IResource folder : getResourceContents()) {
        if (folder instanceof IFile) {
          IFile file = (IFile) folder;
          try {
            ResourceAttributes attr = new ResourceAttributes();
            attr.setArchive(true);
            attr.setReadOnly(isReadOnly());
            file.setResourceAttributes(attr);
          } catch (CoreException exception) {
            // Nothing here
          }
        }
      }

    }

  };

  private class CreateCapellaProject extends DefaultAction {

    public CreateCapellaProject(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public boolean isSelectionCompatible() {
      return true;
    }

    @Override
    protected String getIconFile() {
      return "capella_16.gif";
    }

    @Override
    public String getText() {
      return "Create new Capella Project";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
      return "This command allows to create a new Capella Project from everywhere";
    }

    @Override
    public String getCategory() {
      return Messages.DefaultCategories_Tools;
    }

    @Override
    public void execute() {
      NewProjectWizard wizard = new NewProjectWizard();
      wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
      // Instantiates the wizard container with the wizard and opens it
      WizardDialog dialog = new WizardDialog(getShell(), wizard);
      dialog.create();
      dialog.open();
    }

  };

}
