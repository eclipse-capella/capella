/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *    Altran - Compare Configurations
 *******************************************************************************/

package ms.configuration.services.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.Configuration;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.vp.ms.BooleanOperation;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.InStateExpression;
import org.polarsys.capella.vp.ms.MsFactory;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.capella.vp.ms.selector_Type;
import org.polarsys.capella.vp.ms.provider.MsEditPlugin;
import org.polarsys.capella.vp.ms.ui.preferences.MsPreferenceConstants;
//import org.polarsys.capella.vp.ms.ui.MsUICommandHandler;
import org.polarsys.kitalpha.emde.model.ElementExtension;

public class CsConfigurationServices {

  public static final String DANNOTATION_SOURCE = "http://polarsys.org/capella/vp/ms"; //$NON-NLS-1$
  public static final String DANNOTATION_DETAIL_SHOW_CHILD_RELATIONS = "showChildRelations"; //$NON-NLS-1$

  public static final String DANNOTATION_DETAIL_SHOW_COMPONENTS = "showComponents"; //$NON-NLS-1$
  public static final String DANNOTATION_DETAIL_SHOW_FUNCTIONS = "showFunctions"; //$NON-NLS-1$
  public static final String DANNOTATION_DETAIL_SHOW_PORTS = "showPorts"; //$NON-NLS-1$
  public static final String DANNOTATION_DETAIL_SHOW_FUNCTIONAL_CHAINS = "showFunctionalChains"; //$NON-NLS-1$
  public static final String DANNOTATION_DETAIL_SHOW_SCENARIOS = "showScenarios"; //$NON-NLS-1$

  private static final String CONFIGURATIONS_LAYER_LABEL = Messages.CsConfigurationServices_Configurations_Layer_Name;
  protected List<CSConfiguration> configListFiltered = new ArrayList<CSConfiguration>();
  protected List<CSConfiguration> configList = new ArrayList<CSConfiguration>();

  private Map<CSConfiguration, DNode> getCurrentConfigNodeMap(DDiagram diagram){
    Map<CSConfiguration, DNode> current = new HashMap<CSConfiguration, DNode>();
    for (DNode node : diagram.getNodes()) {
      if (node.getTarget() instanceof CSConfiguration) {
        current.put((CSConfiguration) node.getTarget(), node);
      }
    }
    return current;
  }

  private boolean isWizardCancelled(Object selection) {
    return !(selection instanceof Object[]);
  }

  public EObject msShowHideScenarioConfigurationsTool(Scenario sc, SequenceDDiagram diagram, Object selection) {
    return msShowHideScenarioConfigurationsTool(sc, diagram, null, null, selection);
  }

  public EObject msShowHideScenarioConfigurationsTool(InstanceRole ir, DNode irNode, Object selection) {
    return msShowHideScenarioConfigurationsTool((Scenario) ir.eContainer(), (SequenceDDiagram) irNode.getParentDiagram(), ir, irNode, selection); 
  }



  private EObject msShowHideScenarioConfigurationsTool(Scenario scenario, SequenceDDiagram diagram, InstanceRole ir, DNode irNode, Object selection) {

    if (!isWizardCancelled(selection)) {

      Session session = SessionManager.INSTANCE.getSession(diagram);
      Map<CSConfiguration, DNode> shown = getCurrentConfigNodeMap(diagram);

      for (Object o : (Object[]) selection) {

        DNode node = shown.remove(o);

        if (node == null) {
          NodeMapping nodeMapping = MsMappingConstants.getConfigurationNodeMapping(diagram);
          node = nodeMapping.createNode((CSConfiguration) o, diagram, diagram);
          diagram.getOwnedDiagramElements().add(node);
        }

        
        if (ir == null) { // config is applied to whole diagram => remove all existing ir->config edges
          for (DEdge in : node.getIncomingEdges()) {
            if (in.getSourceNode() instanceof DSemanticDecorator && ((DSemanticDecorator) in).getTarget() instanceof InstanceRole) {
              EcoreUtil.remove(in);
            }
          }

        } else {  // config is applied to instance role => ensure an edge ir->config exists

          EdgeMapping edgeMapping = MsMappingConstants.getInstanceRoleConfigurationEdgeMapping(diagram); 
          diagram.getOwnedDiagramElements().add(edgeMapping.createEdge(irNode, node, diagram, ir));
        }

      }

      // the map now has all the nodes that are on the diagram but were not selected in the dialog
      // - if the tool is executed on an instancerole, remove the ir=>node link and the node itself if no other ir=>node links exist
      // - if the tool is executed on the scenario, remove the node unless it has a link to any instance role
      for (Map.Entry<CSConfiguration, DNode> next : shown.entrySet()) {

        DEdge deleteEdge = null;
        boolean hasMoreEdges = false;

        for (DEdge incoming : next.getValue().getIncomingEdges()) {
          if (incoming.getSourceNode() instanceof DSemanticDecorator && ((DSemanticDecorator) incoming.getSourceNode()).getTarget() instanceof InstanceRole) {
            if (incoming.getSourceNode() == irNode) {
              deleteEdge = incoming;
            } else {
              hasMoreEdges = true;
            }
          }
        }

        if (irNode != null) {
          if (deleteEdge != null) {
            EcoreUtil.remove(deleteEdge);
            if (!hasMoreEdges) {
              EcoreUtil.remove(next.getValue());
            }
          }
        } else if (!hasMoreEdges) {
          EcoreUtil.remove(next.getValue());
        }
      }
    }

    return scenario;

  }


  public Collection<CSConfiguration> msScenarioInstanceRoleConfigurationFinder(InstanceRole context){
    return getAllBlockConfigurationsSemantic(context);
  }

  public boolean msScenarioConfigurationSelectionPrecondition(EObject context) {
    boolean result = false;
    if (context instanceof DNode) {
      EObject semantic = ((DNode) context).getTarget();
      if (semantic instanceof InstanceRole) {
        // the execution line under the instance role node also targets the instance role,
        // but we only want the tool to be usable on the instance role header node,
        if (((DNode) context).getActualMapping() instanceof InstanceRoleMapping) {
          result = true;
        } 
      }
    } else if (context instanceof SequenceDDiagram) {
      result = true;
    }
    return result;
  }


  public static boolean canCompleteChildConfigurationRelation(CSConfiguration source, CSConfiguration target) {
    AdapterFactoryEditingDomain domain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain
        .getEditingDomainFor(source);
    IItemPropertySource propertySource = (IItemPropertySource) domain.getAdapterFactory().adapt(source,
        IItemPropertySource.class);
    return propertySource.getPropertyDescriptor(source, MsPackage.Literals.CS_CONFIGURATION__CHILD_CONFIGURATIONS)
        .getChoiceOfValues(source).contains(target);
  }

  /**
   * Find all {@link Configuration}s owned by the type of a given {@link Part} .
   *
   * @param ele
   *          the {@link Part}
   * @return the owned {@link Configuration}s
   */
  public Collection<CSConfiguration> getOwnedConfigurations(EObject ele) {
    if (ele instanceof Component) {
      return getOwnedConfigurations((Component) ele);
    } else if (ele instanceof Part) {
      return getOwnedConfigurations(((Part) ele).getType());
    }
    return Collections.emptyList();
  }

  public Collection<EObject> getIrregularEObject(EObject ele) {
    // TODO Auto-generated method stub
    this.configList.clear();
    this.configListFiltered.clear();
    List<AbstractFunction> functionListIncluded = new ArrayList<AbstractFunction>();
    List<AbstractFunction> functionListExcluded = new ArrayList<AbstractFunction>();
    List<Component> componentListIncluded = new ArrayList<Component>();
    List<Component> componentListExcluded = new ArrayList<Component>();
    Collection<EObject> objectsIrregularList = new ArrayList<EObject>();
    Situation situCompare = null;

    if (ele instanceof Result) {
      Result result = (Result) ele;
      situCompare = result.getSituation().get(0);
    }
    for (EObject iObj : situCompare.eContents()) {
      if (iObj instanceof BooleanOperation) {
        BooleanOperation boolObj = (BooleanOperation) iObj;
        EObject tObj = boolObj.eContainer();
        EObject vObj = tObj.eContainer();
        for (EObject jObj : vObj.eContents()) {
          if (jObj instanceof CSConfiguration) {
            if (!configList.contains(jObj)) {
              configList.add((CSConfiguration) jObj);
            }
          } else if (jObj instanceof Component) {
            for (EObject childComponent : ((Component) jObj).eContents()) {
              if (childComponent instanceof CSConfiguration) {
                if (!configList.contains(childComponent)) {
                  configList.add((CSConfiguration) childComponent);
                }
              }
            }
          }
        }
        for (EObject jObj : boolObj.eContents()) {
          if (jObj instanceof InStateExpression) {
            for (CSConfiguration configObject : configList) {
              for (AbstractState pObj : configObject.getSupportedModes()) {
                if (((InStateExpression) jObj).getState() instanceof Mode) {
                  Mode modeState = (Mode) ((InStateExpression) jObj).getState();
                  if (modeState.equals(pObj)) {
                    if (!configListFiltered.contains(configObject)) {
                      configListFiltered.add(configObject);
                    }
                  }
                } else if (((InStateExpression) jObj).getState() instanceof State) {
                  State modeState = (State) ((InStateExpression) jObj).getState();
                  if (modeState.equals(pObj)) {
                    if (!configListFiltered.contains(configObject)) {
                      configListFiltered.add(configObject);
                    }
                  }
                }
              }
            }
          }
        }
      }
      for (CSConfiguration configObject : configListFiltered) {
        for (AbstractFunction jObj : configObject.getFunctions()) {
          configObject.getSelector();
          if (configObject.getSelector().equals(selector_Type.INCLUSION)) {
            functionListIncluded.add(jObj);
          } else {
            functionListExcluded.add(jObj);
          }
        }
        for (FunctionalChain jObj : configObject.getFunctionalChains()) {
          configObject.getSelector();
          if (configObject.getSelector().equals(selector_Type.INCLUSION)) {
            for (AbstractFunction tObj : jObj.getInvolvedFunctions()) {
              functionListIncluded.add(tObj);
            }
          } else {
            for (AbstractFunction tObj : jObj.getInvolvedFunctions()) {
              functionListExcluded.add(tObj);
            }
          }
        }

        for (Component jObj : configObject.getComponents()) {
          configObject.getSelector();
          if (configObject.getSelector().equals(selector_Type.INCLUSION)) {
            componentListIncluded.add(jObj);
          } else {
            componentListExcluded.add(jObj);
          }
        }
      }

      for (AbstractFunction fct : functionListIncluded) {
        if (functionListExcluded.contains(fct)) {
          objectsIrregularList.add(fct);
        }
      }
      for (Component jObj : componentListIncluded) {
        if (componentListExcluded.contains(jObj)) {
          objectsIrregularList.add(jObj);
        }
      }

    }

    return objectsIrregularList;
  }

  public Collection<CSConfiguration> getOwnedConfigurations(Component component) {
    Collection<CSConfiguration> result = new ArrayList<CSConfiguration>();
    for (ElementExtension extension : component.getOwnedExtensions()) {
      if (extension instanceof CSConfiguration) {
        result.add((CSConfiguration) extension);
      }
    }
    return result;
  }

  public Collection<EObject> getTheConfiguration(EObject ele) {
    Collection<EObject> result = new ArrayList<EObject>();
    Collection<AbstractState> modeCompareList = new ArrayList<AbstractState>();
    configList.clear();

    if (ele instanceof Comparison) {
      Situation situMode = ((Comparison) ele).getSituation().get(0);
      for (EObject iObj : situMode.eContents()) {
        if (iObj instanceof BooleanOperation) {
          BooleanOperation boolObj = (BooleanOperation) iObj;
          EObject tObj = boolObj.eContainer();
          EObject vObj = tObj.eContainer();
          for (EObject jObj : vObj.eContents()) {
            if (jObj instanceof CSConfiguration) {
              if (!configList.contains(jObj)) {
                configList.add((CSConfiguration) jObj);
              }
            } else if (jObj instanceof Component) {
              for (EObject childComponent : ((Component) jObj).eContents()) {
                if (childComponent instanceof CSConfiguration) {
                  if (!configList.contains(childComponent)) {
                    configList.add((CSConfiguration) childComponent);
                  }
                }
              }
            }
          }
          for (EObject jObj : boolObj.eContents()) {
            if (jObj instanceof InStateExpression) {
              if (((InStateExpression) jObj).getState() instanceof AbstractState) {
                modeCompareList.add(((InStateExpression) jObj).getState());
              }
            }
          }
        }
      }
      CSConfiguration premConfig = ((Comparison) ele).getConfiguration1().get(0);
      for (CSConfiguration csc : configList) {
        if (!csc.equals(premConfig)) {
          for (AbstractState mode : csc.getSupportedModes()) {
            if (modeCompareList.contains(mode)) {
              for (ModelElement iObj : premConfig.getElements()) {
                if (csc.getElements().contains(iObj)) {
                  result.add(iObj);
                }
              }
            }
          }
        }
      }
    }
    return result;
  }

  public Collection<EObject> getTheAbstractState(EObject ele) {
    Collection<EObject> result = new ArrayList<EObject>();
    if (ele instanceof Comparison) {
      CSConfiguration csc = ((Comparison) ele).getConfiguration1().get(0);
      for (ModelElement iObj : csc.getElements()) {
        result.add(iObj);
      }
    }
    return result;
  }

  public String getConfigurationIrregular(EObject ele) {
    String result = "";
    for (CSConfiguration confEle : configListFiltered) {
      for (AbstractFunction jObj : confEle.getFunctions()) {
        if (jObj.equals(ele)) {
          if (result.length() == 0) {
            result = confEle.getName();
          } else {
            result = result + "; " + confEle.getName();
          }

        }
      }
    }
    return result;
  }

  public String getElementsIrregular(EObject ele) {
    return "X";
  }

  public boolean verifCell(EObject ele) {
    if (ele instanceof EObject) {
      return true;
    }
    return false;
  }

  public List<EObject> getOwnedConfigurationsFromType(EObject ele) {
    List<EObject> result = new ArrayList<EObject>();
    if (ele instanceof Component) {
      Component cmp = ((Component) ele);

      EList<ElementExtension> extensions = cmp.getOwnedExtensions();
      ArrayList<CSConfiguration> configurations = new ArrayList<>();
      for (ElementExtension extension : extensions) {
        if (extension instanceof CSConfiguration) {
          configurations.add((CSConfiguration) extension);
        }
      }

      result.addAll(configurations);
    }
    return result;
  }

  public List<EObject> getConfigurationFromElement(EObject ele) {
    SemanticEditingDomain domain = (SemanticEditingDomain) TransactionUtil.getEditingDomain(ele);

    Collection<EStructuralFeature.Setting> refs = domain.getCrossReferencer().getInverseReferences(ele,
        MsPackage.Literals.CS_CONFIGURATION__ELEMENTS, true);
    List<EObject> result = new ArrayList<EObject>();
    for (EStructuralFeature.Setting setting : refs) {
      result.add(setting.getEObject());
    }
    return result;
  }

  public Collection<EObject> getConfigurationsFromMode(AbstractState state) {

    SemanticEditingDomain domain = (SemanticEditingDomain) TransactionUtil.getEditingDomain(state);
    Collection<EStructuralFeature.Setting> refs = domain.getCrossReferencer().getInverseReferences(state,
        MsPackage.Literals.CS_CONFIGURATION__SUPPORTED_MODES, true);
    List<EObject> result = new ArrayList<EObject>();
    for (EStructuralFeature.Setting setting : refs) {
      result.add(setting.getEObject());
    }
    return result;
  }

  public Collection<EObject> getConfigurationsFromEObject(EObject ele) {

    List<EObject> result = new ArrayList<EObject>();
    result.add(ele);
    return result;
  }

  public Collection<EObject> getConfigurationsFromModesandStates(EObject ele) {

    List<EObject> result = new ArrayList<EObject>();
    result.add(ele);
    return result;
  }

  public static boolean isConfigurationsLayerActive(DDiagram diag) {
    for (Layer l : diag.getActivatedLayers()) {
      if (CONFIGURATIONS_LAYER_LABEL.equals(l.getLabel())) {
        return true;
      }
    }
    return false;
  }

  private void getSelectedConfigurationsImpl(AbstractDNode view, boolean includeParents, Collection<CSConfiguration> result) {

    AbstractDNode current = view;

    for (DNode dNode : current.getOwnedBorderedNodes()) {
      if (dNode.getTarget() instanceof CSConfiguration) {
        result.add((CSConfiguration) dNode.getTarget());
      }
    }

    // 
    // For InstanceRoles, the selected configs are the ones connected via edge
    //
    if (view.getTarget() instanceof InstanceRole) {
      
      for (DEdge edge : ((EdgeTarget)view).getOutgoingEdges()) {
        if (edge.getTargetNode() instanceof DNode && ((DNode)edge.getTargetNode()).getTarget() instanceof CSConfiguration) {
          result.add((CSConfiguration) ((DNode) edge.getTargetNode()).getTarget());
        }
      }
    }

    if (includeParents) {

      if (current.eContainer() instanceof AbstractDNode) {

        getSelectedConfigurationsImpl((AbstractDNode) current.eContainer(), includeParents, result);

      } else if (current.eContainer() instanceof DSemanticDiagram) {

        getSelectedConfigurationsImpl((DSemanticDiagram) current.eContainer(), result);

      }
    }
  }

  /**
   *
   * @param view
   * @return
   */
  public Collection<CSConfiguration> getSelectedConfigurations(AbstractDNode view, boolean includeParents) {
    Collection<CSConfiguration> result = new ArrayList<CSConfiguration>();
    getSelectedConfigurationsImpl(view, includeParents, result);
    return result;
  }

  public Collection<CSConfiguration> getSelectedConfigurations(DSemanticDiagram diagram){
    ArrayList<CSConfiguration> result = new ArrayList<CSConfiguration>();
    getSelectedConfigurationsImpl(diagram, result);
    return result;
  }
  
  public Collection<CSConfiguration> getSelectedConfigurations(AbstractDNode node){
    return getSelectedConfigurations(node, false);
  }

  // all configurations on the diagram canvas, but not those who are connected with an instance role
  private void getSelectedConfigurationsImpl(DSemanticDiagram diagram, Collection<CSConfiguration> result) {
    for (DDiagramElement de : diagram.getOwnedDiagramElements()) {
      if (de.getTarget() instanceof CSConfiguration) {
        boolean include = true;
        if (de instanceof EdgeTarget) {
          for (DEdge edge : ((DNode)de).getIncomingEdges()) {
            if (edge.getSourceNode() instanceof DSemanticDecorator && ((DSemanticDecorator) edge.getSourceNode()).getTarget() instanceof InstanceRole) {
              include = false;
            }
          }
        }
        if (include) {
          result.add((CSConfiguration) de.getTarget());
        }
      }
    }
  }

  /**
   * Find all {@link DNode} which are not selected
   *
   * @param view
   * @param selection
   * @return
   */
  public List<EObject> getNotSelectedConfigurations(EObject view, List<EObject> selection) {
    if (view instanceof DNodeContainer) {
      return getNotSelectedConfigurations((DNodeContainer) view, selection);
    } else if (view instanceof DDiagram) {
      return getNotSelectedConfigurations((DDiagram) view, selection);
    }
    throw new IllegalArgumentException();
  }

  // sirius gets confused if this is public
  private List<EObject> getNotSelectedConfigurations(DNodeContainer view, List<EObject> selection){
    List<EObject> result = new ArrayList<EObject>();
    for (DNode dNode : view.getOwnedBorderedNodes()) {
      if (dNode.getTarget() instanceof CSConfiguration && !selection.contains(dNode.getTarget())) {
        result.add(dNode);
      }
    }
    return result;
  }

  // sirius gets confused if this is public
  private List<EObject> getNotSelectedConfigurations(DDiagram diagram, List<EObject> selection){
    List<EObject> result = new ArrayList<EObject>();
    for (DDiagramElement element : diagram.getOwnedDiagramElements()) {
      if (element.getTarget() instanceof CSConfiguration && !selection.contains(element.getTarget())) {
        result.add(element);
      }
    }
    return result;
  }

  /*
   * The selectable configurations on a diagram are the configurations of the component that the diagram targets
   * and the configurations of that component's ancestor component.
   */
  public Collection<CSConfiguration> getSelectableConfigurations(DSemanticDiagram diagram){
    Collection<CSConfiguration> result = new ArrayList<CSConfiguration>();
    EObject c = diagram.getTarget();
    while (c instanceof Component) {
      result.addAll(getOwnedConfigurations((Component)c));
      c = c.eContainer();
    }
    return result;
  }

  public Collection<CSConfiguration> getAllBlockConfigurationsSemantic(EObject semantic){
    Collection<CSConfiguration> result = new ArrayList<CSConfiguration>();
    BlockArchitecture ba = BlockArchitectureExt.getRootBlockArchitecture(semantic);
    if (ba != null) {
      result.addAll((Collection<? extends CSConfiguration>) EObjectExt.getAll(ba,MsPackage.Literals.CS_CONFIGURATION));
    }
    return result;
  }
  
  public Collection<CSConfiguration> getAllBlockConfigurations(DSemanticDecorator decorator){
    return getAllBlockConfigurationsSemantic(decorator.getTarget());
  }

  public boolean isDiagram(EObject e) {
    return e instanceof DSemanticDiagram;
  }

  /*
   * The selectable configurations on a part are the configurations of the parts component.
   */
  public Collection<CSConfiguration> getSelectableConfigurations(DNodeContainer container){

    Collection<CSConfiguration> result = Collections.emptyList();
    Component comp = null;
    
    if (container.getTarget() instanceof Component) {
      comp = (Component) container.getTarget();
    } else if (container.getTarget() instanceof Part && ((Part)container.getTarget()).getType() instanceof Component) {
      comp = (Component) ((Part)container.getTarget()).getType();
    }

    if (comp != null) {
      result = getOwnedConfigurations(comp);
    } 
    
    return result;
  }

  
  
  public boolean msStyleCustomizationPredicate(ModelElement context, DDiagramElement view) {
    return false;
  }

  public int disabledElementColor(ModelElement context, DDiagramElement view) {
    boolean isComponent = (context instanceof Part ? ((Part) context).getType() : context) instanceof Component;
    if (isComponent) {
      return 200;
    } else {
      return 220;
    }
  }


  public static boolean isConsistentIncludeRequired() {
    return Platform.getPreferencesService().getBoolean(
       org.polarsys.capella.vp.ms.ui.preferences.Activator.PLUGIN_ID,
       MsPreferenceConstants.PREF_DEFAULT_CONSISTEN_INCLUDE_REQUIRED, true, null); //$NON-NLS-1$ 
  }

  public static boolean isMarkConflictingInclusions() {
    return Platform.getPreferencesService().getBoolean(
        org.polarsys.capella.vp.ms.ui.preferences.Activator.PLUGIN_ID,
        MsPreferenceConstants.PREF_DEFAULT_MARK_CONFLICTS, true, null); //$NON-NLS-1$ 
  }

  public boolean isShowDisabledPortOverlay(ModelElement context, DDiagramElement view) {
    
    throw new UnsupportedOperationException("Overlays not working with Capella 1.2.1, see https://bugs.polarsys.org/show_bug.cgi?id=2115");
    
    // some edges map to port, so don't add the overlay here
//    if (view instanceof DEdge) {
//      return false;
//    }
//    
//    // don't overlay provided/required ports
//    String imagePath = Images.getImagePath(context, view);
//    if (imagePath != null && (imagePath.contains("provided") || imagePath.contains("required"))) {
//      return false;
//    }
//
//    return isNotAvailableInSelectedConfiguration(context, view);
  }

  public int disabledElementLabelColor(ModelElement context, DDiagramElement view) {
    return 120;
  }

  public String disabledElementWorkspacePathImage(ModelElement context, DDiagramElement view) {
    return Images.getImagePath(context, view);
  }
  
  /**
   * Create a new {@link Configuration} for the type of a given {@link Part}
   *
   * @param context
   * @return
   */
  public CSConfiguration createConfiguration(EObject context) {
    Component cmp = null;
    if (context instanceof Component) {
      cmp = (Component) context;
    } else if (context instanceof Part && ((Part) context).getType() instanceof Component) {
      cmp = (Component) ((Part) context).getType();
    } else {
      BlockArchitecture ba = BlockArchitectureExt.getRootBlockArchitecture(context);
      if (ba instanceof SystemAnalysis) {
        cmp = ((SystemAnalysis) ba).getOwnedSystem();
      } else if (ba instanceof LogicalArchitecture) {
        cmp = ((LogicalArchitecture)ba).getOwnedLogicalComponent();
      } else if (ba instanceof PhysicalArchitecture) {
        cmp = ((PhysicalArchitecture)ba).getOwnedPhysicalComponent();
      }
    }

    if (cmp != null) {
      CSConfiguration configuration = MsFactory.eINSTANCE.createCSConfiguration();
      cmp.getOwnedExtensions().add(configuration);
      msCreationService(configuration);
      return configuration;
    }

    return null;
  }

  /**
   * Verify if the {@link Configuration}s of a given {@link State} can be modified.
   *
   * @param state
   *          the given {@link State}
   * @return <code>true</code> if the feature 'configurations' of the {@link State} can be modified, else
   *         <code>false</code>
   */
  public boolean canEditStateConfiguration(EObject state) {
    if (state instanceof FinalState) {
      return false;
    }

    return true;
  }

  /**
   * Find all allocated {@link AbstractFunction}s to a given {@link Component} .
   *
   * @param component
   *          the {@link Component}.
   * @return the allocated {@link AbstractFunction}.
   */
  public List<AbstractFunction> getAllocatedFunctions(EObject component) {
    List<AbstractFunction> result = new ArrayList<AbstractFunction>();
    if (component instanceof Component) {
      result.addAll(((Component) component).getAllocatedFunctions());
    }
    return result;
  }

  public Collection<? extends EObject> msCrossTableComponentsRecurse(DTable table, System component, System root) {
    return Collections.emptyList();
  }

  public Collection<? extends EObject> msCrossTableComponentsRecurse(DTable table, LogicalComponent component,
      LogicalComponent root) {
    if (isShowComponents(table)) {
      return component.getOwnedLogicalComponents();
    }
    return Collections.emptyList();
  }

  public Collection<? extends EObject> msCrossTableComponentsRecurse(DTable table, PhysicalComponent component,
      PhysicalComponent root) {
    if (!isShowComponents(table)) {
      return Collections.emptyList();
    }

    Collection<PhysicalComponent> result = new LinkedHashSet<PhysicalComponent>();

    result.addAll(component.getOwnedPhysicalComponents());

    // to avoid duplication of rows
    // only show deployed components that are outside the containment hierarchy of the table's root component
    for (PhysicalComponent deployed : component.getDeployedPhysicalComponents()) {
      if (!EcoreUtil.isAncestor(root, deployed)) {
        result.add(deployed);
      }
    }

    return result;
  }

  public Collection<?> msCrossTableFunctionalChains(DTable table, Component component) {
    return msCrossTableElements(table, component, MsPackage.Literals.CS_CONFIGURATION__FUNCTIONAL_CHAINS);
  }

  public Collection<?> msCrossTableScenarios(DTable table, Component component) {
    return msCrossTableElements(table, component, MsPackage.Literals.CS_CONFIGURATION__SCENARIOS);
  }

  private Collection<?> msCrossTableElements(DTable table, Component component, EStructuralFeature feature){
    if (!isShowScenarios(table)) {
      return Collections.emptyList();
    }

    if (getOwnedConfigurations(component).isEmpty()) {
      return Collections.emptyList(); // FIXME, thats' a small hack
    }

    // we can get at the available FCs through one of the component's configurations item property descriptors
    CSConfiguration configuration = getOwnedConfigurations(component).iterator().next();

    AdapterFactoryEditingDomain domain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain
        .getEditingDomainFor(configuration);
    IItemPropertySource propertySource = (IItemPropertySource) domain.getAdapterFactory().adapt(configuration,
        IItemPropertySource.class);
    IItemPropertyDescriptor descriptor = propertySource.getPropertyDescriptor(configuration,
        feature);

    return new LinkedHashSet<Object>(descriptor.getChoiceOfValues(configuration)); // FIXME why would there be duplicates?
  }

  public void msCreationService(CSConfiguration configuration) {
    CapellaElementExt.creationService(configuration, MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_type")); //$NON-NLS-1$
  }

  public Collection<InputPin> msInputs(DTable table, AbstractFunction function) {
    if (isShowPorts(table)) {
      return function.getInputs();
    }
    return Collections.emptyList();
  }

  public Collection<OutputPin> msOutputs(DTable table, AbstractFunction function) {
    if (isShowPorts(table)) {
      return function.getOutputs();
    }
    return Collections.emptyList();
  }

  public Collection<AbstractFunction> msAllocatedFunctions(DTable table, Component component) {
    if (isShowFunctions(table)) {
      return component.getAllocatedFunctions();
    }
    return Collections.emptyList();
  }

  public Collection<? extends EObject> msContainedComponentPorts(DTable table, Component component) {
    if (isShowPorts(table)) {
      return component.getContainedComponentPorts();
    }
    return Collections.emptyList();
  }

  public Collection<PhysicalPort> msContainedPhysicalPorts(DTable table, Component component) {
    if (isShowPorts(table)) {
      return component.getContainedPhysicalPorts();
    }
    return Collections.emptyList();
  }

  public Collection<Component> msComponentRoot(DTable table, Component component) {
    if (isShowComponents(table)) {
      return Collections.singletonList(component);
    }
    return Collections.emptyList();
  }

  public static boolean isShowChildConfigurationRelationships(DDiagram diagram) {
    return isAnnotationDetailSet(diagram, DANNOTATION_DETAIL_SHOW_CHILD_RELATIONS, true);
  }

  public static void setShowChildConfigurationRelationships(DDiagram diagram, boolean b) {
    setAnnotationDetail(diagram, DANNOTATION_DETAIL_SHOW_CHILD_RELATIONS, b);
  }

  public static void setShowComponents(DTable table, boolean b) {
    setAnnotationDetail(table, DANNOTATION_DETAIL_SHOW_COMPONENTS, b);
  }

  public static boolean isShowComponents(DTable table) {
    return isAnnotationDetailSet(table, DANNOTATION_DETAIL_SHOW_COMPONENTS, true);
  }

  public static void setShowFunctions(DTable table, boolean b) {
    setAnnotationDetail(table, DANNOTATION_DETAIL_SHOW_FUNCTIONS, b);
  }

  public static boolean isShowFunctions(DTable table) {
    return isAnnotationDetailSet(table, DANNOTATION_DETAIL_SHOW_FUNCTIONS, true);
  }

  public static void setShowPorts(DTable table, boolean b) {
    setAnnotationDetail(table, DANNOTATION_DETAIL_SHOW_PORTS, b);
  }

  public static boolean isShowPorts(DTable table) {
    return isAnnotationDetailSet(table, DANNOTATION_DETAIL_SHOW_PORTS, true);
  }

  public static void setShowFunctionalChains(DTable table, boolean b) {
    setAnnotationDetail(table, DANNOTATION_DETAIL_SHOW_FUNCTIONAL_CHAINS, b);
  }

  public static boolean isShowFunctionalChains(DTable table) {
    return isAnnotationDetailSet(table, DANNOTATION_DETAIL_SHOW_FUNCTIONAL_CHAINS, true);
  }

  public static boolean isShowScenarios(DTable table) {
    return isAnnotationDetailSet(table, DANNOTATION_DETAIL_SHOW_SCENARIOS, true);
  }

  public static void setShowScenarios(DTable table, boolean b) {
    setAnnotationDetail(table, DANNOTATION_DETAIL_SHOW_SCENARIOS, b);
  }

  private static boolean isAnnotationDetailSet(DModelElement element, String key, boolean defaultValue) {
    DAnnotation annot = getAnnotation(element, false);
    if (annot != null && annot.getDetails().containsKey(key)) {
      return Boolean.valueOf(annot.getDetails().get(key));
    }
    return defaultValue;
  }

  private static void setAnnotationDetail(DModelElement element, String key, boolean value) {
    DAnnotation annot = getAnnotation(element, true);
    annot.getDetails().put(key, String.valueOf(value));
  }

  private static DAnnotation getAnnotation(DModelElement element, boolean create) {
    DAnnotation result = element.getDAnnotation(DANNOTATION_SOURCE);
    if (result == null && create) {
      result = DescriptionFactory.eINSTANCE.createDAnnotation();
      result.setSource(DANNOTATION_SOURCE);
      element.getEAnnotations().add(result);
    }

    return result;
  }
  
  

  public boolean msConfigurationNodeCreationPrecondition(EObject container) {
    return container instanceof Component | container instanceof Part;
  }

}
