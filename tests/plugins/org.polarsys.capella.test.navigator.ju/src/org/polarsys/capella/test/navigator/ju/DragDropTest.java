/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.navigator.ju;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.menu.dynamic.util.DynamicCommandParameter;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.move.MoveHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.drop.DiagramDropAdapterAssistant;
import org.polarsys.capella.core.platform.sirius.ui.navigator.drop.ExplorerDropAdapterAssistant;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.navigator.ju.model.NavigatorEmptyProject;

public class DragDropTest extends NavigatorEmptyProject {

  /**
   * Check customized semantic rules are allowing the drop of current element in the given newTarget
   * 
   * It doesn't check basic EMF rules
   */
  void checkMoveAllowed(EObject current, EObject newTarget) {
    checkMoveAllowed(current, newTarget, "");
  }

  void checkMoveAllowed(EObject current, EObject newTarget, String rule) {
    assertTrue("Move shall be allowed: "+rule, MoveHelper.getInstance().checkSemanticRules(Arrays.asList(current), newTarget).isOK());
  }
  
  /**
   * Check customized semantic rules are disabling the drop of current element in the given newTarget
   * 
   * It doesn't check basic EMF rules
   */
  void checkMoveDisabled(EObject current, EObject newTarget) {
    checkMoveDisabled(current, newTarget, "");
  }

  void checkMoveDisabled(EObject current, EObject newTarget, String rule) {
    assertTrue("Move shall be disabled: "+rule, !MoveHelper.getInstance().checkSemanticRules(Arrays.asList(current), newTarget).isOK());
  }
  
  @Override
  public void test() throws Exception {

    // Check that drop adapters are properly registered on Project Explorer
    CommonViewer viewer = getViewer();
    CommonDropAdapterAssistant[] z = viewer.getNavigatorContentService().getDnDService()
        .findCommonDropAdapterAssistants(ROOT_SYSTEM_FUNCTION, new StructuredSelection(ROOT_SYSTEM_FUNCTION));
    assertTrue(Arrays.asList(z).stream().anyMatch(ExplorerDropAdapterAssistant.class::isInstance));
    assertTrue(Arrays.asList(z).stream().anyMatch(DiagramDropAdapterAssistant.class::isInstance));

    // Check function
    EObject systemFunction = createFunction(ROOT_SYSTEM_FUNCTION);

    AbstractFunction systemFunction2 = createFunction(ROOT_SYSTEM_FUNCTION);
    EObject systemFunctionPkg = createFunctionPkg(ROOT_SYSTEM_FUNCTION);
    EObject systemFunctionPkg2 = createFunctionPkg(ROOT_SYSTEM_FUNCTION);
    EObject logicalFunction = createFunction(ROOT_LOGICAL_FUNCTION);
    EObject logicalFunctionPkg = createFunctionPkg(ROOT_LOGICAL_FUNCTION);

    checkMoveDisabled(systemFunction, SA_SYSTEM_FUNCTIONS, "Root function Pkg must have only one function");
    checkMoveDisabled(systemFunction, LA_LOGICAL_FUNCTIONS, "Root function Pkg must have only one function");
    checkMoveAllowed(systemFunction, systemFunctionPkg);
    checkMoveDisabled(systemFunction, logicalFunctionPkg, "Functions can't be moved to different architecture");
    checkMoveDisabled(systemFunction, logicalFunction, "Functions can't be moved to different architecture");
    checkMoveAllowed(systemFunction, systemFunction2);

    // Check function pkg
    checkMoveAllowed(systemFunctionPkg, systemFunctionPkg2);
    checkMoveDisabled(systemFunctionPkg, logicalFunctionPkg, "Functions can't be moved to different architecture");
    checkMoveDisabled(systemFunctionPkg, EPBS_ARCHITECTURE, "Functions can't be moved to different architecture");

    // Check root components
    checkMoveDisabled(SYSTEM, logicalFunctionPkg, "Components can't be moved into Function Pkgs");
    checkMoveDisabled(LOGICAL_SYSTEM, logicalFunctionPkg, "Components can't be moved into Function Pkgs");
    checkMoveDisabled(PHYSICAL_SYSTEM, logicalFunctionPkg, "Components can't be moved into Function Pkgs");

    // Check functional chain
    EObject chain = createFunctionalChain(ROOT_SYSTEM_FUNCTION);
    createFunctionalChainFunction(chain, systemFunction2);
    EObject capability = createCapability(SA_CAPABILITIES);
    EObject logicalCapability = createCapability(LA_CAPABILITIES);
    checkMoveDisabled(chain, LA_LOGICAL_FUNCTIONS, "Chains can't be moved to different architecture");
    checkCopyDisabled(chain, LA_LOGICAL_FUNCTIONS, "Chains can't be moved to different architecture");
    checkCopyEnabled(chain, capability, "Chains can be moved to capability of same architecture");
    checkMoveAllowed(chain, capability, "Chains can be moved to capability of same architecture");
    checkMoveAllowed(chain, systemFunction2, "Chains can be moved to funcitons of same architecture");
    checkMoveDisabled(chain, logicalCapability, "Chains can't be moved to capability of different architecture");

    // Check chain involvements
    EObject chain2 = createFunctionalChain(ROOT_SYSTEM_FUNCTION);
    checkMoveDisabled(createFunctionalChainLink(chain), chain2, "Involvments can't be moved");
    checkMoveDisabled(createFunctionalChainFunction(chain, systemFunction2), chain2, "Involvments can't be moved");
    checkMoveDisabled(createFunctionalChainReference(chain), chain2, "Involvments can't be moved");

    // Check interface pkg
    checkMoveDisabled(LA_INTERFACES, EPBS_ARCHITECTURE, "InterfacePkg can't be moved into EPBS");

    // Check components and pkgs
    EObject logicalComponent = createComponent(LOGICAL_SYSTEM);
    EObject logicalActor = createActor(LOGICAL_SYSTEM);
    EObject physicalComponent = createComponent(PHYSICAL_SYSTEM);
    EObject logicalComponent2 = createComponent(LOGICAL_SYSTEM);
    EObject physicalComponent2 = createComponent(PHYSICAL_SYSTEM);
    EObject logicalComponentPkg = createComponentPkg(LOGICAL_SYSTEM);
    EObject physicalComponentPkg = createComponentPkg(PHYSICAL_SYSTEM);

    checkMoveAllowed(logicalComponent, logicalComponent2, "LC can be moved into LC");
    checkMoveAllowed(physicalComponent, physicalComponent2, "PC can be moved into PC");
    checkMoveAllowed(logicalComponent, logicalComponentPkg, "LC can be moved into LC packages");
    
    checkMoveDisabled(logicalComponent, SYSTEM, "LC can't be moved into SA");
    checkMoveDisabled(logicalComponent, PHYSICAL_SYSTEM, "LC can't be moved into PA");
    checkMoveDisabled(logicalComponent, physicalComponent2, "LC can't be moved into PC");

    checkMoveDisabled(logicalComponentPkg, LOGICAL_ARCHITECTURE);
    checkMoveDisabled(physicalComponentPkg, PHYSICAL_ARCHITECTURE);
    
    checkMoveDisabled(logicalComponent, LA_STRUCTURE, "Structure can have only one System");
    checkMoveAllowed(logicalActor, LA_STRUCTURE, "Structure can contains actors");
    
    // Check Capability Pkgs
    checkMoveAllowed(OA_OPERATIONAL_CAPABILITIES, OPERATIONAL_ANALYSIS);
    checkMoveDisabled(OA_OPERATIONAL_CAPABILITIES, SYSTEM_ANALYSIS, "Capabilities can't be moved to different architecture");

    checkMoveAllowed(SA_CAPABILITIES, SYSTEM_ANALYSIS);
    checkMoveAllowed(SA_CAPABILITIES, SYSTEM);
    checkMoveDisabled(SA_CAPABILITIES, OPERATIONAL_ANALYSIS, "Capabilities can't be moved to different architecture");
    checkMoveDisabled(SA_CAPABILITIES, LOGICAL_ARCHITECTURE, "Capabilities can't be moved to different architecture");

    checkMoveDisabled(LA_CAPABILITIES, SYSTEM_ANALYSIS, "Capabilities can't be moved to upper architecture");
    checkMoveAllowed(LA_CAPABILITIES, LOGICAL_SYSTEM, "CapabilityRealizations can be moved to next architectures");
    checkMoveAllowed(LA_CAPABILITIES, LOGICAL_ARCHITECTURE, "CapabilityRealizations can be moved to next architectures");
    checkMoveAllowed(LA_CAPABILITIES, PHYSICAL_ARCHITECTURE, "CapabilityRealizations can be moved to next architectures");
    checkMoveAllowed(LA_CAPABILITIES, EPBS_ARCHITECTURE, "CapabilityRealizations can be moved to next architectures");

    // Check capabilities can be moved on Components (for refinement purposes)
    checkMoveDisabled(capability, OA_OPERATIONAL_CAPABILITIES, "Capabilities can't be moved to different architecture");
    checkMoveDisabled(capability, PA_CAPABILITIES, "Capabilities can't be moved to different architecture");
    checkMoveAllowed(logicalCapability, PA_CAPABILITIES, "CapabilityRealizations can be moved to next architectures");
    EObject capabilityPkg = createCapabilityPkg(LOGICAL_SYSTEM);
    checkMoveDisabled(capability, capabilityPkg, "Capabilities can't be moved to different architecture");
    checkMoveAllowed(logicalCapability, capabilityPkg);

    // Check Enumeration Literals
    Enumeration enum1 = createEnumeration(SA_DATA);
    Enumeration enum2 = createEnumeration(SA_DATA);
    BooleanType bool1 = createBoolean(SA_DATA);
    BooleanType bool2 = createBoolean(SA_DATA);
    EnumerationLiteral enumLiteral = createEnumerationLiteral(enum1);
    LiteralBooleanValue boolLiteral = createBooleanLiteral(bool1);
    checkMoveAllowed(enumLiteral, enum2, "Literals of Enum can be moved to another Enum");
    checkMoveDisabled(enumLiteral, bool1, "Literals of Enum can't be moved to Boolean Type");

    // Check Boolean Literals
    checkMoveAllowed(boolLiteral, bool2, "Literals of Boolean can be moved to another Boolean");
    checkMoveDisabled(boolLiteral, enum1, "Literals of Boolean can't be moved to Enum");

  }

  private void checkCopyDisabled(EObject current, EObject newTarget, String rule) {
    try {
      GuiActions.copyElement(" ", current);
      assertTrue("Copy shall be disabled: "+rule, !GuiActions.canPasteElement(" ", newTarget));
      
      System.out.println();
    } catch (Exception e) {
      assertTrue(e.getMessage(), false);
    }
  }

  private void checkCopyEnabled(EObject current, EObject newTarget, String rule) {
    try {
      GuiActions.copyElement(" ", current);
      assertTrue("Copy shall be disabled: "+rule, GuiActions.canPasteElement(" ", newTarget));
      
      System.out.println();
    } catch (Exception e) {
      assertTrue(e.getMessage(), false);
    }
  }
  private EObject createCapabilityPkg(EObject container) {
    if (container instanceof Component) {
      return create(container, LaPackage.Literals.CAPABILITY_REALIZATION_PKG);
    }
    return null;
  }

  private EObject createCapability(EObject container) {
    if (container instanceof CapabilityPkg) {
      return create(container, CtxPackage.Literals.CAPABILITY);

    } else if (container instanceof CapabilityRealizationPkg) {
      return create(container, LaPackage.Literals.CAPABILITY_REALIZATION);
    }
    return null;
  }

  private BooleanType createBoolean(DataPkg container) {
    return create(container, DatatypePackage.Literals.BOOLEAN_TYPE);
  }

  private Enumeration createEnumeration(DataPkg container) {
    return create(container, DatatypePackage.Literals.ENUMERATION);
  }

  private EnumerationLiteral createEnumerationLiteral(Enumeration container) {
    return create(container, DatavaluePackage.Literals.ENUMERATION_LITERAL,
        DatatypePackage.Literals.ENUMERATION__OWNED_LITERALS, null);
  }

  private LiteralBooleanValue createBooleanLiteral(BooleanType container) {
    return create(container, DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE,
        DatatypePackage.Literals.BOOLEAN_TYPE__OWNED_LITERALS, null);
  }

  private EObject createComponentPkg(Component container) {
    if (container instanceof LogicalComponent) {
      return create(container, LaPackage.Literals.LOGICAL_COMPONENT_PKG);

    } else if (container instanceof PhysicalComponent) {
      return create(container, PaPackage.Literals.PHYSICAL_COMPONENT_PKG);
    }
    return null;
  }

  private EObject createComponent(Component container) {
    Predicate<CommandParameter> predicate = new Predicate<CommandParameter>() {
      @Override
      public boolean test(CommandParameter t) {
        return (t instanceof DynamicCommandParameter ? ((DynamicCommandParameter)t).getLabel().contains("Component"): false);
      }
    };
    
    if (container instanceof LogicalComponent) {
      return create(container, LaPackage.Literals.LOGICAL_COMPONENT, null, predicate);

    } else if (container instanceof PhysicalComponent) {
      return create(container, PaPackage.Literals.PHYSICAL_COMPONENT, null, predicate);
    }
    return null;
  }

  private EObject createActor(Component container) {
    Predicate<CommandParameter> predicate = new Predicate<CommandParameter>() {
      @Override
      public boolean test(CommandParameter t) {
        return (t instanceof DynamicCommandParameter ? ((DynamicCommandParameter)t).getLabel().contains("Actor"): false);
      }
    };
    
    if (container instanceof LogicalComponent) {
      return create(container, LaPackage.Literals.LOGICAL_COMPONENT, null, predicate);

    } else if (container instanceof PhysicalComponent) {
      return create(container, PaPackage.Literals.PHYSICAL_COMPONENT, null, predicate);
    }
    return null;
  }
  
  private AbstractFunction createFunction(EObject container) {
    return create(container, CtxPackage.Literals.SYSTEM_FUNCTION);
  }

  private FunctionPkg createFunctionPkg(EObject container) {
    if (container instanceof SystemFunction) {
      return create(container, CtxPackage.Literals.SYSTEM_FUNCTION_PKG);

    } else if (container instanceof LogicalFunction) {
      return create(container, LaPackage.Literals.LOGICAL_FUNCTION_PKG);

    } else if (container instanceof SystemFunctionPkg) {
      return create(container, CtxPackage.Literals.SYSTEM_FUNCTION_PKG);

    } else if (container instanceof LogicalFunctionPkg) {
      return create(container, LaPackage.Literals.LOGICAL_FUNCTION_PKG);
    }
    return null;
  }

  private FunctionalChain createFunctionalChain(EObject container) {
    return create(container, FaPackage.Literals.FUNCTIONAL_CHAIN);
  }

  private FunctionalChainInvolvementLink createFunctionalChainLink(EObject container) {
    return create(container, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK);
  }

  private FunctionalChainInvolvementFunction createFunctionalChainFunction(EObject container, AbstractFunction function) {
    FunctionalChainInvolvementFunction result = create(container, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION);
    AbstractReadWriteCommand create = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        result.setInvolved(function);
      }
    };
    TestHelper.getExecutionManager(container).execute(create);
    return result;
  }

  private FunctionalChainReference createFunctionalChainReference(EObject container) {
    return create(container, FaPackage.Literals.FUNCTIONAL_CHAIN_REFERENCE);
  }

  /**
   * @return
   */
  protected CommonViewer getViewer() {
    final CommonViewer[] viewer = new CommonViewer[1];
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {
        IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
            .findView(CapellaCommonNavigator.ID);

        viewer[0] = ((CapellaCommonNavigator) part).getCommonViewer();
      }
    });
    return viewer[0];
  }

  private <T> T create(final EObject container, EClass clazz) {
    return create(container, clazz, null, null);
  }

  private <T> T create(final EObject container, EClass clazz, EStructuralFeature f, Predicate<CommandParameter> predicate) {
    Collection<CommandParameter> commands = (Collection<CommandParameter>) TransactionHelper.getEditingDomain(container)
        .getNewChildDescriptors(container, null);
    Optional<CommandParameter> cp = commands.stream()
        .filter(c -> clazz.isInstance(c.getValue()) && (f == null || c.getEStructuralFeature() == f)).filter(x -> (predicate != null ? predicate.test(x): true)).findFirst();
    if (cp.isPresent()) {
      Command cmd = CreateChildCommand.create(TransactionHelper.getEditingDomain(container), container, cp.get(),
          Arrays.asList(container));
      if (cmd.canExecute()) {
        AbstractReadWriteCommand create = new AbstractReadWriteCommand() {
          @Override
          public void run() {
            cmd.execute();
          }
        };
        TestHelper.getExecutionManager(container).execute(create);
      }
      return (T) cmd.getAffectedObjects().iterator().next();
    }
    assertTrue(false);
    return null;
  }

}
