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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BeginLabelStyle;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EndLabelStyle;
import org.eclipse.sirius.diagram.business.internal.dialect.DiagramDialect;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.capellamodeller.util.CapellamodellerResourceImpl;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.ui.danalysis.CapellaAnalysisSelector;

/**
 */
public class DiagramActionsProvider implements IActionsProvider {

  public class OpenDynamicViewer extends DefaultAction {

	  public DAnalysis initDynamicAnalysis() {
	    ResourceSet set = TransactionHelper.getEditingDomain(getSelectedEObjects()).getResourceSet();
	    Resource res = null;

	    for (Resource resE : set.getResources()) {
	      if (resE.getURI().toString().equals("capella://dynamic")) { //$NON-NLS-1$
	        if (resE.getContents().size() > 0) {
	          return (DAnalysis) resE.getContents().get(0);
	        }
	        res = resE;
	      }
	    }

	    if (res == null) {
	      res = new XMLResourceImpl();
	      res.setURI(URI.createURI("capella://dynamic")); //$NON-NLS-1$
	    }

	    //Weird method to avoid.
	    EPackage.Registry.INSTANCE.put("capella://dynamic", null); //$NON-NLS-1$

	    DAnalysis ana = ViewpointFactory.eINSTANCE.createDAnalysis();
	    res.getContents().add(ana);
	    set.getResources().add(res);

	    return ana;
	  }

    @Override
    protected String getIconFile() {
      return "process.gif"; //$NON-NLS-1$
    }

    @Override
    public boolean isSelectionCompatible() {
      if (getSelection(CapellaElement.class).size() >= 0) {
        return true;
      }
      return false;
    }

    public OpenDynamicViewer(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getDescription() {
      return "This action opens a dynamic diagram of selected elements"; //$NON-NLS-1$
    }

    @Override
    public String getText() {
      return "Dynamic viewer"; //$NON-NLS-1$
    }

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getCategory() {
      return DefaultCategories.DIAGRAM_CATEGORY;
    }

    public DAnalysis getDynamicAnalysis() {

      DAnalysis ana = initDynamicAnalysis();

      Collection<Viewpoint> vs = getViewpoints();
      for (DView view : ana.getOwnedViews()) {
        vs.remove(view.getViewpoint());
      }

      for (Viewpoint v : vs) {
        DView rep = ViewpointFactory.eINSTANCE.createDView();
        rep.setViewpoint(v);
        ana.getOwnedViews().add(rep);
      }

      return ana;
    }

    public Collection<Viewpoint> getViewpoints() {
      Collection<Viewpoint> vs = new ArrayList<Viewpoint>();
      ResourceSet set = TransactionHelper.getEditingDomain(getSelectedEObjects()).getResourceSet();

      for (Resource odesign : set.getResources()) {
        if (odesign instanceof AirdResource) {
          for (EObject content : odesign.getContents()) {
            if (content instanceof DAnalysis) {
              DAnalysis analysis = (DAnalysis) content;
              for (DView view : analysis.getOwnedViews()) {
                if (view.getViewpoint() != null) {
                  if (!vs.contains(view.getViewpoint())) {
                    vs.add(view.getViewpoint());
                  }
                }
              }
            }
          }
        }
      }
      return vs;
    }

    public DiagramDescription getDescription(String name) {
      for (Viewpoint v : getViewpoints()) {
        for (EObject o : v.eContents()) {
          if (o instanceof DiagramDescription) {
            DiagramDescription des = (DiagramDescription) o;
            if (des.getName().equals(name)) {
              return des;
            }
          }
        }
      }
      return null;
    }

    IEditorPart editor = null;

    public DRepresentation getDynamicRepresentation(EObject root) {
      DRepresentation representation = null;

      final DAnalysis analysis = getDynamicAnalysis();

      try {
        boolean delete = false;
        Session session = SessionManager.INSTANCE.getSession(root);
        DAnalysisSessionImpl a = ((DAnalysisSessionImpl) session);

        String viewpointName = ""; //$NON-NLS-1$
        if (root instanceof LogicalFunction) {
          viewpointName = IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;

        } else if (root instanceof SystemFunction) {
          viewpointName = IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME;

        } else if (root instanceof PhysicalFunction) {
          viewpointName = IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;

        } else if (root instanceof OperationalActivity) {
          viewpointName = IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME;

        } else if ((root instanceof Actor) || (root instanceof org.polarsys.capella.core.data.ctx.System)) {
          viewpointName = IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME;

        } else if (root instanceof LogicalComponent) {
          viewpointName = IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;

        } else if (root instanceof PhysicalComponent) {
          viewpointName = IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;

        } else if (root instanceof Entity) {
          viewpointName = IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME;

        } else if (root instanceof Interface) {
          viewpointName = IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME;
        }

        DiagramDescription desc = getDescription(viewpointName);
        if (desc == null) {
          return null;
        }
        Viewpoint v = (Viewpoint) desc.eContainer();

        //Retrieve an already existing representation if any
        for (DView view : analysis.getOwnedViews()) {
          if (view.getViewpoint().equals(v)) {
            for (DRepresentation aaa : new DViewQuery(view).getLoadedRepresentations()) {
              if (aaa instanceof DDiagram) {
                DDiagram diagram = (DDiagram) aaa;
                if (diagram.getDescription().equals(desc)) {
                  representation = diagram;
                  break;
                }
              }
            }
          }
        }

        if (representation == null) {

          a.addAnalysis(analysis.eResource());
          delete = true;
          //We want to store this diagram in our analysis
          a.setAnalysisSelector(new DAnalysisSelector() {

            /**
             * {@inheritDoc}
             */
            public DAnalysis selectSmartlyAnalysisForCreatedView(Viewpoint viewpoint, Collection<DAnalysis> allAnalysis) {
              if ((analysis != null) && allAnalysis.contains(analysis)) {
                return analysis;
              }
              return allAnalysis.iterator().next();
            }

            /**
             * {@inheritDoc}
             */
            public DAnalysis selectSmartlyAnalysisForAddedResource(Resource resource, Collection<DAnalysis> allAnalysis) {
              if ((analysis != null) && allAnalysis.contains(analysis)) {
                return analysis;
              }
              return allAnalysis.iterator().next();
            }

            /**
             * {@inheritDoc}
             */
            public DAnalysis selectSmartlyAnalysisForAddedRepresentation(DRepresentation representation, Collection<DAnalysis> allAnalysis) {
              if ((analysis != null) && allAnalysis.contains(analysis)) {
                return analysis;
              }
              return allAnalysis.iterator().next();
            }
          });

          DiagramDialect dialect = new DiagramDialect();

          representation = dialect.getServices().createRepresentation("[DYNAMIC] " + desc.getName(), root, desc, session, new NullProgressMonitor()); //$NON-NLS-1$
          a.setAnalysisSelector(new CapellaAnalysisSelector() {

            /**
             * {@inheritDoc}
             */
            @Override
            public DAnalysis selectSmartlyAnalysisForAddedRepresentation(DRepresentation representation, Collection<DAnalysis> allAnalysis) {
              allAnalysis.remove(analysis);
              return super.selectSmartlyAnalysisForAddedRepresentation(representation, allAnalysis);
            }
          });
        }

        if (session != null) {
          editor = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        }
        ((DSemanticDecorator) representation).setTarget(root);
        if (delete) {
        }
      } catch (Exception ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      }

      return representation;
    }

    @Override
    public void execute() {
      HashMap<EObject, DRepresentation> diagrams = new HashMap<EObject, DRepresentation>();
      try {

        for (EObject element : getSelectedEObjects()) {
          DRepresentation representation = getDynamicRepresentation(element);
          diagrams.put(element, representation);
          ContextualDiagramHelper.getService().setContextualElements(representation, new ArrayList<EObject>());
        }

        for (EObject element : diagrams.keySet()) {
          DRepresentation representation = diagrams.get(element);
          Collection<EObject> contextual = ContextualDiagramHelper.getService().getContextualElements(representation);
          contextual.add(element);
          ContextualDiagramHelper.getService().setContextualElements(representation, contextual);
        }

        for (DRepresentation representation : diagrams.values()) {
          //Clean diagram and refresh
          DDiagram diagram = (DDiagram) representation;
          for (DDiagramElement view : DiagramServices.getDiagramServices().getDiagramElements(diagram)) {
            if (view instanceof AbstractDNode) {
              DiagramServices.getDiagramServices().removeAbstractDNodeView((AbstractDNode) view);
            } else if (view instanceof DEdge) {
              DiagramServices.getDiagramServices().removeEdgeView((DEdge) view);
            }
          }
          CapellaServices.getService().forceRefresh(diagram);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  /**
   * @see org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider#getActions()
   */
  public Collection<DefaultAction> getActions(Shell shell, ISelectionProvider selectionProvider) {
    List<DefaultAction> list = new ArrayList<DefaultAction>();

    list.add(new ReColorEdgesAction(shell, selectionProvider));
    list.add(new DiagramProxyReparatorAccessor(shell, selectionProvider));
    list.add(new RefreshDiagramAction(shell, selectionProvider));
    list.add(new RepairViewpointDefinition(shell, selectionProvider));

    list.add(new OpenDynamicViewer(shell, selectionProvider));

    return list;
  }

  public class RepairViewpointDefinition extends DefaultAction {

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getDescription() {
      return NLS
          .bind(
              "This action enables all viewpoints in each aird fragments. Sometimes, aird fragment doesn't have all viewpoint definitions and it is not possible to create a diagram in this fragment.", //$NON-NLS-1$
              getText());
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

    public RepairViewpointDefinition(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Repair viewpoint definition on fragments"; //$NON-NLS-1$
    }

    Collection<Resource> getResources() {
      Collection<Resource> diagrams = new HashSet<Resource>();

      for (EObject object : getSelectedEObjects()) {
        Resource re = object.eResource();

        if (object instanceof DDiagram) {
          diagrams.add(object.eResource());

        } else if (object instanceof SystemEngineering) {
          Session session = SessionManager.INSTANCE.getSession(object);
          if (session != null) {
            diagrams.addAll(session.getSemanticResources());
            diagrams.addAll(session.getReferencedSessionResources());
            diagrams.add(session.getSessionResource());
          }
        }
      }
      return diagrams;
    }

    HashMap<String, Viewpoint> viewpoints = new HashMap<String, Viewpoint>();
    HashMap<String, Boolean> viewpointsEnabled = new HashMap<String, Boolean>();

    @Override
    public void execute() {

      IRunnableWithProgress runnable = new IRunnableWithProgress() {

        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          boolean hasProceed = false;
          int nbProceed = 0;
          Collection<Resource> objects = getResources();
          List<DAnalysis> analysises = new ArrayList<DAnalysis>();

          getLogger().info(new EmbeddedMessage(objects.size() + " resources to be opened", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$

          //Retrieve all viewpoints definition
          for (Resource container : objects) {
            if (container instanceof AirdResource) {
              for (EObject content : container.getContents()) {
                if (content instanceof DAnalysis) {
                  DAnalysis analysis = (DAnalysis) content;

                  if (!analysises.contains(analysis)) {
                    analysises.add(analysis);

                    for (DView view : analysis.getOwnedViews()) {
                      if (view.getViewpoint() != null) {
                        String key = getKey(view.getViewpoint());
                        if (!viewpoints.containsKey(key)) {
                          viewpoints.put(key, view.getViewpoint());

                          getLogger().info(
                              new EmbeddedMessage(NLS.bind("Get viewpoint definition ''{0}'' in ''{1}''", getName(view.getViewpoint()), //$NON-NLS-1$
                                  getName(analysis.eResource()).toString()), IReportManagerDefaultComponents.UI));
                          viewpointsEnabled.put(key, Boolean.valueOf(analysis.getSelectedViews().contains(view)));
                        } else {
                          if ((viewpointsEnabled.get(key) != null) && !viewpointsEnabled.get(key).booleanValue()) {
                            viewpointsEnabled.put(key, Boolean.valueOf(analysis.getSelectedViews().contains(view)));
                          }
                        }
                      }
                    }

                    for (DView view : analysis.getOwnedViews()) {
                      if (view.getViewpoint() != null) {
                        viewpoints.put(getKey(view.getViewpoint()), view.getViewpoint());

                      }
                    }
                  }
                }
              }
            }
          }

          //Add viewpoints on analysis
          for (DAnalysis analysis : analysises) {

            for (Viewpoint viewpoint : viewpoints.values()) {
              if (isMissing(viewpoint, analysis)) {
                DView rep = ViewpointFactory.eINSTANCE.createDView();
                rep.setViewpoint(viewpoint);
                analysis.getOwnedViews().add(rep);

                Boolean enabled = viewpointsEnabled.get(getKey(viewpoint));
                if ((enabled != null) && enabled.booleanValue()) {
                  analysis.getSelectedViews().add(rep);
                }
                getLogger().info(
                    new EmbeddedMessage(NLS.bind("Add viewpoint ''{0}'' in ''{1}''", getName(viewpoint), getName(analysis.eResource())), //$NON-NLS-1$
                        IReportManagerDefaultComponents.UI));
                hasProceed = true;
                nbProceed++;
              }
            }
          }

          if (!hasProceed) {
            getLogger().info(new EmbeddedMessage("No viewpoint definition have been added", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          } else {
            getLogger().info(new EmbeddedMessage(nbProceed + " viewpoint have been added", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
          }

          monitor.done();
        }

        private String getKey(Viewpoint viewpoint) {
          if (viewpoint instanceof InternalEObject) {
            if (viewpoint.eIsProxy()) {
              return ((InternalEObject) viewpoint).eProxyURI().lastSegment();
            }
          }
          return viewpoint.getName() + viewpoint.eResource().getURI().lastSegment();
        }

        private String getName(Object eResource) {
          if (eResource instanceof InternalEObject) {
            if (((InternalEObject) eResource).eIsProxy()) {
              return ((InternalEObject) eResource).eProxyURI().toString();
            }
          }
          if (eResource instanceof Viewpoint) {
            return ((Viewpoint) eResource).getName();

          }
          if (eResource instanceof Resource) {
            return ((Resource) eResource).getURI().lastSegment();

          }

          return eResource.toString();
        }

        private boolean isMissing(Viewpoint viewpoint, DAnalysis analysis) {
          String key = getKey(viewpoint);

          for (DView view : analysis.getOwnedViews()) {
            if (view.getViewpoint() != null) {
              String definedKey = getKey(view.getViewpoint());
              if (key.equals(definedKey)) {
                return false;
              }
            }
          }
          return true;
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

        EObject semantic = ((DSemanticDecorator) object).getTarget();
        Session session = SessionManager.INSTANCE.getSession(semantic);

        if (session != null) {
          IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, object, new NullProgressMonitor());
          editor.getSite().getPage().closeEditor(editor, false);
        }

      } catch (Exception ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));

      }
      return hasProceed;
    }
  }

  private class ReColorEdgesAction extends DefaultAction {

    @Override
    public String getDescription() {
      return NLS.bind("This action set default color on edges of all diagrams.", getText()); //$NON-NLS-1$
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

    public ReColorEdgesAction(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Set default color on edges"; //$NON-NLS-1$
    }

    @Override
    public void execute() {
      boolean hasProceed = false;
      for (EObject object : getSelectedEObjects()) {
        Resource re = object.eResource();

        if (true) {
          return;
        }
        if (object instanceof DDiagram) {
          if (process((DDiagram) object)) {
            hasProceed = true;
          }

        } else if (object instanceof SystemEngineering) {
          Session session = SessionManager.INSTANCE.getSession(object);
          if (session != null) {
            for (DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
              if (representation instanceof DDiagram) {
                if (process((DDiagram) representation)) {
                  hasProceed = true;
                }
              }
            }
          }
        }
      }

      if (!hasProceed) {
        getLogger().info(new EmbeddedMessage("No edges have been re-colored", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$

      }
    }

    /**
     * @param object
     */
    private boolean process(DDiagram object) {
      boolean hasProceed = false;

      for (DEdge edge : object.getEdges()) {

        if ((edge.getActualMapping() != null) && (edge.getActualMapping() instanceof EdgeMapping)) {
          EdgeMapping mapping = (EdgeMapping) edge.getActualMapping();
          EdgeStyleDescription description = mapping.getStyle();

          if (description != null) {

            EdgeStyle edgeStyle = edge.getOwnedStyle();

            if (edgeStyle != null) {

              if (mapping.getStyle().getStrokeColor() != null) {
                // Set style for the edge
                RGBValues newRGBValues = edgeStyle.getStrokeColor();
                if (newRGBValues == null) {
                  newRGBValues = ViewpointFactory.eINSTANCE.createRGBValues();
                  edgeStyle.setStrokeColor(newRGBValues);
                }

                hasProceed = true;
                newRGBValues = setColor(mapping.getStyle().getStrokeColor());
              }

              if (mapping.getStyle().getBeginLabelStyleDescription() != null) {
                // Set style for the begin label
                BeginLabelStyle labelStyle = edgeStyle.getBeginLabelStyle();
                ColorDescription colorDescription = mapping.getStyle().getBeginLabelStyleDescription().getLabelColor();

                if (labelStyle == null) {
                  labelStyle = DiagramFactory.eINSTANCE.createBeginLabelStyle();
                  edgeStyle.setBeginLabelStyle(labelStyle);
                }

                RGBValues newRGBValues = labelStyle.getLabelColor();
                if (newRGBValues == null) {
                  newRGBValues = ViewpointFactory.eINSTANCE.createRGBValues();
                  labelStyle.setLabelColor(newRGBValues);
                }

                hasProceed = true;
                newRGBValues = setColor(colorDescription);
              }

              if (mapping.getStyle().getCenterLabelStyleDescription() != null) {
                // Set style for the begin label
                CenterLabelStyle labelStyle = edgeStyle.getCenterLabelStyle();
                ColorDescription colorDescription = mapping.getStyle().getCenterLabelStyleDescription().getLabelColor();

                if (labelStyle == null) {
                  labelStyle = DiagramFactory.eINSTANCE.createCenterLabelStyle();
                  edgeStyle.setCenterLabelStyle(labelStyle);
                }

                RGBValues newRGBValues = labelStyle.getLabelColor();
                if (newRGBValues == null) {
                  newRGBValues = ViewpointFactory.eINSTANCE.createRGBValues();
                  labelStyle.setLabelColor(newRGBValues);
                }

                hasProceed = true;
                newRGBValues = setColor(colorDescription);
              }
              if (mapping.getStyle().getEndLabelStyleDescription() != null) {
                // Set style for the begin label
                EndLabelStyle labelStyle = edgeStyle.getEndLabelStyle();
                ColorDescription colorDescription = mapping.getStyle().getEndLabelStyleDescription().getLabelColor();

                if (labelStyle == null) {
                  labelStyle = DiagramFactory.eINSTANCE.createEndLabelStyle();
                  edgeStyle.setEndLabelStyle(labelStyle);
                }

                RGBValues newRGBValues = labelStyle.getLabelColor();
                if (newRGBValues == null) {
                  newRGBValues = ViewpointFactory.eINSTANCE.createRGBValues();
                  labelStyle.setLabelColor(newRGBValues);
                }

                hasProceed = true;
                newRGBValues = setColor(colorDescription);
              }
            }
          }
        }
      }

      if (hasProceed) {
        getLogger().info(
            new EmbeddedMessage(NLS.bind("Edges on diagram ''{0}'' have recovered default color.", object.getName()), IReportManagerDefaultComponents.UI, //$NON-NLS-1$
                object));
        try {
          object.refresh();
        } catch (Exception exceptionP) {
          getLogger().warn(
              new EmbeddedMessage(NLS.bind("An error occured while refreshing diagram ''{0}''.", object.getName()), IReportManagerDefaultComponents.UI, //$NON-NLS-1$
                  object));
        }
      }

      return hasProceed;
    }

    /**
     * Set color
     * @param strokeColor
     */
    private RGBValues setColor(ColorDescription strokeColor) {
      if (strokeColor instanceof FixedColor) {
        FixedColor sysColor = (FixedColor) strokeColor;
        return RGBValues.create(sysColor.getRed(), sysColor.getGreen(), sysColor.getBlue());
      }
      return RGBValues.create(0, 0, 0);
    }
  }

  public class RefreshDiagramAction extends DefaultAction {

    @Override
    public String getReportComponent() {
      return IReportManagerDefaultComponents.UI;
    }

    @Override
    public String getDescription() {
      return "This action opens any diagrams to fix filter problems"; //$NON-NLS-1$
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

    public RefreshDiagramAction(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Repair dirty diagrams"; //$NON-NLS-1$
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

        EObject semantic = ((DSemanticDecorator) object).getTarget();
        Session session = SessionManager.INSTANCE.getSession(semantic);

        if (session != null) {
          IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, object, new NullProgressMonitor());
          editor.getSite().getPage().closeEditor(editor, false);
        }

      } catch (Exception ex) {
        getLogger().warn(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));

      }
      return hasProceed;
    }
  }

  public class DiagramProxyReparatorAccessor extends DefaultAction {

    HashMap<String, EObject> mapIds = new HashMap<String, EObject>();

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

    public DiagramProxyReparatorAccessor(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getDescription() {
      return "This action fixes graphical elements linked to a semantic element but this semantic element has been moved to another fragment and its graphical element has not yet been updated."; //$NON-NLS-1$
    }

    @Override
    public String getText() {
      return "Repair graphical elements linked to an invalid semantic element"; //$NON-NLS-1$
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

      logger.info(new EmbeddedMessage("Diagrams checking...", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$

      if (getSelectedEObjects().size() > 0) {
        EObject root = getSelectedEObjects().get(0);

        initMap(root);

        if (root instanceof DDiagram) {
          if (performDiagram((DDiagram) root)) {
            noDiagrams = false;
          }
        } else {
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
      }

      mapIds.clear();

      if (noDiagrams) {
        logger.info(new EmbeddedMessage("No diagrams can be fixed.", IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
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
          }
        }
      }
    }

    private int fix(EObject element, EObject proxy, EStructuralFeature feature) {
      InternalEObject obj = (InternalEObject) proxy;
      if (obj.eProxyURI() != null) {
        InternalEObject resolved = (InternalEObject) ((InternalEObject) element).eResolveProxy(obj);
        if (resolved == obj) {
          InternalEObject iproxy = (InternalEObject) proxy;
          String value = iproxy.eProxyURI().fragment();
          if (mapIds.get(value) != null) {
            EObject uri = mapIds.get(value);
            setElement(element, proxy, uri, feature);

            String info = ""; //$NON-NLS-1$
            if (element instanceof DDiagramElement) {
              info += ((AbstractNamedElement) uri).getName();
            }
            getLogger().info(
                new EmbeddedMessage(NLS.bind("''{0}'' has been replaced by ''{1}'' [{2}]", new Object[] { obj.eProxyURI().toString(), EcoreUtil.getURI(uri), //$NON-NLS-1$
                                                                                                         uri.eClass().getName(), info }),
                    IReportManagerDefaultComponents.UI, element));
            return 1;
          }

          String info = ""; //$NON-NLS-1$
          if (element instanceof DDiagramElement) {
            info += ((DDiagramElement) element).getName() + " contained by " + ((DDiagramElement) (element.eContainer())).getName(); //$NON-NLS-1$
          }
          getLogger().warn(
              new EmbeddedMessage(NLS.bind("''{0}'' couldn't be resolved. [{1}]", new Object[] { obj.eProxyURI().toString(), info }), //$NON-NLS-1$
                  IReportManagerDefaultComponents.UI, element));
          return 2;
        }
      }
      return 0;
    }

    /**
     * @param element
     * @param proxy
     * @param uri
     * @param feature
     */
    private void setElement(EObject element, EObject proxy, EObject uri, EStructuralFeature feature) {
      if (feature.isChangeable()) {
        if (feature.isMany()) {
          EList list = ((EList) element.eGet(feature));
          list.remove(proxy);
          list.add(uri);
        } else {
          element.eSet(feature, uri);
        }
      }
    }

    private boolean performDiagram(DDiagram diagram) {
      Logger logger = getLogger();
      List<DDiagramElement> proxys = new ArrayList<DDiagramElement>();
      int nbProblem = 0;
      int nbFix = 0;
      int nbNotFix = 0;

      logger.info(new EmbeddedMessage(NLS.bind("Diagram ''{0}'' checking...", diagram.getName()), IReportManagerDefaultComponents.UI, diagram)); //$NON-NLS-1$

      for (DDiagramElement element : diagram.getDiagramElements()) {
        if (element.getTarget() != null) {
          int fix = fix(element, element.getTarget(), ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET);
          if (fix == 1) {
            nbFix++;
          } else if (fix == 2) {
            nbNotFix++;
          }

          if (fix != 0) {
            nbProblem++;
          }
        }
        if (element.getSemanticElements() != null) {
          for (EObject semantic : element.getSemanticElements()) {
            int fix = fix(element, semantic, ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS);
            if (fix == 1) {
              nbFix++;
            } else if (fix == 2) {
              nbNotFix++;
            }
            if (fix != 0) {
              nbProblem++;
            }

          }
        }
      }

      logger.info(new EmbeddedMessage(NLS.bind("{0} problems potentially fixable have been found in this diagram.", nbProblem), //$NON-NLS-1$
          IReportManagerDefaultComponents.UI, diagram));
      if (nbProblem > 0) {
        logger.info(new EmbeddedMessage(NLS.bind("{0} problems in theses elements have been fixed.", nbFix), IReportManagerDefaultComponents.UI, diagram)); //$NON-NLS-1$
        logger.info(new EmbeddedMessage(NLS.bind("{0} problems in theses elements could not be fixed.", nbNotFix), IReportManagerDefaultComponents.UI, //$NON-NLS-1$
            diagram));
      }
      return nbFix != 0;
    }
  }
}
