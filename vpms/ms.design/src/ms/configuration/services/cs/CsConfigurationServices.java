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
import java.util.LinkedHashSet;
import java.util.List;

import javax.security.auth.login.Configuration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
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
          if (configObject.getSelector().equals(configObject.getSelector().INCLUSION)) {
            functionListIncluded.add(jObj);
          } else {
            functionListExcluded.add(jObj);
          }
        }
        for (FunctionalChain jObj : configObject.getFunctionalChains()) {
          if (configObject.getSelector().equals(configObject.getSelector().INCLUSION)) {
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
          if (configObject.getSelector().equals(configObject.getSelector().INCLUSION)) {
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

  private void getSelectedConfigurationsImpl(DNodeContainer view, boolean includeParents, Collection<CSConfiguration> result) {

    DNodeContainer current = view;

    for (DNode dNode : current.getOwnedBorderedNodes()) {
      if (dNode.getTarget() instanceof CSConfiguration) {
        result.add((CSConfiguration) dNode.getTarget());
      }
    }

    if (includeParents) {

      if (current.eContainer() instanceof DNodeContainer) {

        getSelectedConfigurationsImpl((DNodeContainer) current.eContainer(), includeParents, result);

      } else if (current.eContainer() instanceof DSemanticDiagram) {

        // configurations on canvas dont apply to actors
        if (!(current.getTarget() instanceof Part && (((Part) current.getTarget()).getType() instanceof Actor))) {
          getSelectedConfigurationsImpl((DSemanticDiagram) current.eContainer(), result);
        }

      }
    }
  }

  /**
   *
   * @param view
   * @return
   */
  public Collection<CSConfiguration> getSelectedConfigurations(DNodeContainer view, Boolean includeParents) {
    Collection<CSConfiguration> result = new ArrayList<CSConfiguration>();
    getSelectedConfigurationsImpl(view, includeParents, result);
    return result;
  }

  public Collection<CSConfiguration> getSelectedConfigurations(DSemanticDiagram diagram){
    ArrayList<CSConfiguration> result = new ArrayList<CSConfiguration>();
    getSelectedConfigurationsImpl(diagram, result);
    return result;
  }

  public Collection<CSConfiguration> getSelectedConfigurations(DNodeContainer node){
    return getSelectedConfigurations(node, false);
  }

  private void getSelectedConfigurationsImpl(DSemanticDiagram diagram, Collection<CSConfiguration> result) {
    for (DDiagramElement dnode : diagram.getOwnedDiagramElements()) {
      if (dnode.getTarget() instanceof CSConfiguration) {
        result.add((CSConfiguration) dnode.getTarget());
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

  /*
   * The selectable configurations on a part are the configurations of the parts component.
   */
  public Collection<CSConfiguration> getSelectableConfigurations(DNodeContainer container){
    if (container.getTarget() instanceof Part && ((Part)container.getTarget()).getType() instanceof Component) {
      return getOwnedConfigurations((Component) ((Part)container.getTarget()).getType());
    }
    return Collections.emptyList();
  }

  /**
   * Verify if a given {@link AbstractFunction} is available at least in one of the graphically selected
   * {@link Configuration}s of the {@link Component} container of the {@link AbstractFunction}
   *
   * @param context
   *          the {@link AbstractFunction}
   * @param view
   *          the {@link DNode} of the {@link AbstractFunction}
   * @return <code>true</code> if the {@link AbstractFunction} is available, else <code>false</code> .
   */
  public boolean isAvailableInSelectedConfiguration(ModelElement context, DDiagramElement view) {

    if (!isConfigurationsLayerActive(view.getParentDiagram())) {
      return true;
    }
    return isAvailableInSelectedConfigurationImpl(context, view);
  }

  private boolean isAvailableInSelectedConfigurationImpl(ComponentExchange context, DDiagramElement view) {

    Component source = ComponentExchangeExt.getSourceComponent(context);
    Component target = ComponentExchangeExt.getTargetComponent(context);

    return isAvailableInSelectedConfigurationImpl(context, view, source, target);

  }

  private boolean isAvailableInSelectedConfigurationImpl(PhysicalLink context, DDiagramElement view) {

    PhysicalPort source = context.getSourcePhysicalPort();
    PhysicalPort target = context.getTargetPhysicalPort();

    Component sourceComponent = (Component) source.eContainer();
    Component targetComponent = (Component) target.eContainer();

    return isAvailableInSelectedConfigurationImpl(context, view, sourceComponent, targetComponent);

  }

  private boolean isAvailableInSelectedConfigurationImpl(ModelElement link, DDiagramElement view, Component sourceComponent, Component targetComponent) {

    DDiagramElement sourceComponentNode = null;
    DDiagramElement targetComponentNode = null;

    for (DDiagramElement elem : view.getParentDiagram().getDiagramElements()) {
      if (elem.getTarget() instanceof Part) {
        if (((Part) elem.getTarget()).getType() == sourceComponent) {
          sourceComponentNode = elem;
        }
        if (((Part) elem.getTarget()).getType() == targetComponent) {
          targetComponentNode = elem;
        }
      }
    }

    boolean result = true;

    if (sourceComponentNode instanceof DNodeContainer && targetComponentNode instanceof DNodeContainer) {

      Collection<CSConfiguration> sourceConfigs = getSelectedConfigurations((DNodeContainer) sourceComponentNode, true);
      Collection<CSConfiguration> targetConfigs = getSelectedConfigurations((DNodeContainer) targetComponentNode, true);

      boolean sResult = sourceConfigs.isEmpty();
      boolean tResult = targetConfigs.isEmpty();

      for (CSConfiguration sc : sourceConfigs) {
        if (sc.includes(link)) {
          sResult = true;
          break;
        }
      }

      for (CSConfiguration tc : targetConfigs) {
        if (tc.includes(link)) {
          tResult = true;
          break;
        }
      }

      result = sResult && tResult;

    }

    return result;

  }

  /*
   * At least one of the displayed configurations on each 'side' of the exchange must include the exchange,
   * or it is greyed out.
   *
   * @param context
   * @param view
   * @return
   */
  private boolean isAvailableInSelectedConfigurationImpl(FunctionalExchange context, DDiagramElement view) {

    FunctionOutputPort source = context.getSourceFunctionOutputPort();
    FunctionInputPort target = context.getTargetFunctionInputPort();

    AbstractFunction sourceFunction = (AbstractFunction) source.eContainer();
    AbstractFunction targetFunction = (AbstractFunction) target.eContainer();

    Component sourceComponent = null;
    Component targetComponent = null;

    for (AbstractFunctionalBlock block : sourceFunction.getAllocationBlocks()) {
      if (block instanceof Component) {
        sourceComponent = (Component) block;
      }
    }

    for (AbstractFunctionalBlock block : targetFunction.getAllocationBlocks()) {
      if (block instanceof Component) {
        targetComponent = (Component) block;
      }
    }

    return isAvailableInSelectedConfigurationImpl(context, view, sourceComponent, targetComponent);

  }


  private boolean isAvailableInSelectedConfigurationImpl(ModelElement context, DDiagramElement view) {

    boolean result = true;

    if (context instanceof FunctionalExchange) {

      result = isAvailableInSelectedConfigurationImpl((FunctionalExchange) context, view);

    } else if (context instanceof ComponentExchange) {

      result = isAvailableInSelectedConfigurationImpl((ComponentExchange) context, view);

    } else if (context instanceof PhysicalLink) {

      result = isAvailableInSelectedConfigurationImpl((PhysicalLink) context, view);

    } else {

    if (view.eContainer() instanceof DNodeContainer) {
      ModelElement target = context instanceof Part ? ((Part) context).getType() : context;
      Collection<CSConfiguration> configs = getSelectedConfigurations((DNodeContainer) view.eContainer(), true);
      result = configs.isEmpty();
      for (CSConfiguration c : configs) {
        if (c.includes(target)) {
          result = true;
          break;
        }
      }
    }
  }
    return result;

  }

  /**
   * FIXME doc Verify if a given {@link AbstractFunction} is not available in all graphically selected
   * {@link Configuration}s of the {@link Component} container of the {@link AbstractFunction}
   *
   * @param context
   *          the {@link AbstractFunction}
   * @param view
   *          the {@link DNode} of the {@link AbstractFunction}
   * @return <code>true</code> if the {@link AbstractFunction} is not available, else <code>false</code> .
   */
  public boolean isNotAvailableInSelectedConfiguration(ModelElement context, DDiagramElement view) {
    return !isAvailableInSelectedConfiguration(context, view);
  }

  public int disabledElementColor(ModelElement context, DDiagramElement view) {

    ModelElement target = context instanceof Part ? ((Part) context).getType() : context;

    for (CSConfiguration c : getSelectedConfigurations((DNodeContainer) view.eContainer(), true)) {
      if (c.getSelector() == selector_Type.EXCLUSION && c.getElements().contains(target)) {
        return 150;
      }
    }
    return 220;
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

}
