/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.projection.ju;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.interfaces.InterfaceGeneration;
import org.polarsys.capella.core.projection.interfaces.InterfaceGenerationPreferences;
import org.polarsys.capella.core.projection.interfaces.generateInterfaces.GenerateInterfacesCommand;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

import com.google.common.base.Predicate;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

/**
 * A basic test for the generate interfaces from allocated functions command using a self-describing test model, given
 * the name of a component on which the command should be executed.
 * 
 * The test model itself must contain all required information for the test to execute:
 * 
 * The test model is simply the expected result of the command execution. The test runs then as follows:
 * <ol>
 * <li>The generate interface command is executed. If that execution causes a change in the model, the test fails. This
 * tests that repeated execution does not update the model (the command is thus idempotent)</li>
 * <li>For all component ports of the test component and their connected ports, all required/provided interface
 * references are cleared and stored in the test. Also clears the source reference of all owned transfo links of these
 * interfaces, otherwise the transformation engine will think the interfaces have already been transformed and do
 * nothing.
 * <li>The generate interfaces command is executed on the test component</li>
 * <li>For all component ports of the test component and their connected component ports, all required/provided
 * interfaces are compared with the interfaces stored during 2)</li>
 * <li>The test succeeds if no differences are detected, otherwise it fails.</li>
 */
public class GenerateInterfacesTest extends BasicTestCase {

  protected Resource modelResource;

  private final Predicate<Component> testComponentPredicate;
  private final String testComponentName;

  private final Multimap<ComponentPort, Interface> expectedRequired = LinkedHashMultimap.create();
  private final Multimap<ComponentPort, Interface> expectedProvided = LinkedHashMultimap.create();
  private final Multimap<Interface, Trace> expectedTraces = LinkedHashMultimap.create();

  public GenerateInterfacesTest(String testComponentName) {
    this.testComponentPredicate = hasName(testComponentName);
    this.testComponentName = testComponentName;
  }

  @Override
  public String getName() {
    return testComponentName;
  }

  @Override
  public void test() throws Exception {

    Component component = find(getModelResource(), Component.class, testComponentPredicate);

    verifyNoChangeOnExecute(component);

    for (ComponentPort port : component.getContainedComponentPorts()) {
      clearPort(port);
      for (ComponentPort connected : PortExt.getConnectedComponentPorts(port)) {
        clearPort(connected);
      }

      // make sure to clear also the ports that can only be reached by following FP->CP allocations
      for (FunctionPort fp : port.getAllocatedFunctionPorts()) {
        if (fp instanceof FunctionOutputPort) {
          for (FunctionalExchange fe : ((FunctionOutputPort) fp).getOutgoingFunctionalExchanges()) {
            for (ComponentPort otherPort : fe.getTargetFunctionInputPort().getAllocatorComponentPorts()) {
              clearPort(otherPort);
            }
          }
        }
        if (fp instanceof FunctionInputPort) {
          for (FunctionalExchange fe : ((FunctionInputPort) fp).getIncomingFunctionalExchanges()) {
            for (ComponentPort otherPort : fe.getSourceFunctionOutputPort().getAllocatorComponentPorts()) {
              clearPort(otherPort);
            }
          }
        }
      }
    }

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(component);
    GenerateInterfacesCommand command = new GenerateInterfacesCommand(Collections.<EObject> singleton(component));

    ExecutionManager manager = ExecutionManagerRegistry.getInstance().getExecutionManager(domain);
    manager.execute(command);

    for (ComponentPort port : component.getContainedComponentPorts()) {

      verify(port, expectedProvided, expectedRequired, expectedTraces);

      for (ComponentPort connected : PortExt.getConnectedComponentPorts(port)) {
        verify(connected, expectedProvided, expectedRequired, expectedTraces);
      }

    }

    assertTrue(HoldingResourceHelper.getHoldingResource(domain).getContents().isEmpty());

  }

  private void verifyNoChangeOnExecute(Component component) {
    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(component);
    ResourceSetChangeListener listener = new ResourceSetChangeListener();
    domain.addResourceSetListener(listener);
    try {
      GenerateInterfacesCommand command = createCommand(Collections.<EObject> singleton(component));
      ExecutionManagerRegistry.getInstance().getExecutionManager(domain).execute(command);
    } finally {
      domain.removeResourceSetListener(listener);
    }
    assertFalse(listener.hasChanged());
  }

  private void clearTraces(final Interface namespace, Multimap<Interface, Trace> expectedTraces) {
    for (Iterator< Trace>it = namespace.getOwnedTraces().iterator(); it.hasNext();) {
      expectedTraces.put(namespace, it.next());
      it.remove();
    }
  }

  private void clearPort(final ComponentPort port) {

    expectedRequired.putAll(port, port.getRequiredInterfaces());
    expectedProvided.putAll(port, port.getProvidedInterfaces());

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(port);
    domain.getCommandStack().execute(new RecordingCommand(domain) {
      @Override
      protected void doExecute() {
        for (Iterator< Interface>it = port.getRequiredInterfaces().iterator(); it.hasNext();) {
          clearTraces(it.next(), expectedTraces);
          it.remove();
        }
        for (Iterator< Interface>it = port.getProvidedInterfaces().iterator(); it.hasNext();) {
          clearTraces(it.next(), expectedTraces);
          it.remove();
        }
      }
    });

  }

  private void verify(ComponentPort port, Multimap<ComponentPort, Interface> expectedProvided,
      Multimap<ComponentPort, Interface> expectedRequired, Multimap<Interface, Trace> expectedTraces) {

    Interface[] expectedProvidedArray = expectedProvided.get(port).toArray(new Interface[0]);

    assertSame(expectedProvidedArray.length, port.getProvidedInterfaces().size());
    for (int i = 0; i < expectedProvidedArray.length; i++) {

      Interface expected = expectedProvidedArray[i];
      Interface actual = port.getProvidedInterfaces().get(i);
      assertTrue(equals(expected, actual));
      verifyTraces(expected, actual, expectedTraces.get(expected));
    }

    Interface[] expectedRequiredArray = expectedRequired.get(port).toArray(new Interface[0]);
    assertSame(expectedRequiredArray.length, port.getRequiredInterfaces().size());
    for (int i = 0; i < expectedRequiredArray.length; i++) {
      Interface expected = expectedRequiredArray[i];
      Interface actual = port.getRequiredInterfaces().get(i);
      assertTrue(equals(expected, actual));
      verifyTraces(expected, actual, expectedTraces.get(expected));
    }
  }

  private void verifyTraces(Interface expected, Interface actual, Collection<Trace> expectedTraces) {
    assertSame(expectedTraces.size(), actual.getOwnedTraces().size());
    outer: for (Trace et : expectedTraces) {
      for (Trace at : actual.getOwnedTraces()) {
        if (at.getTargetElement() == et.getTargetElement()) {
          continue outer;
        }
      }
      fail("Could not find expected trace to " + et.getTargetElement() + " on interface" + actual.getName()); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  private boolean equals(Interface expected, Interface actual) {
    boolean result = new InterfaceEquality().equals(expected, actual);
    return result;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList("testInterfacesFromAllocatedFunctions"); //$NON-NLS-1$
  }

  /**
   * Iterates over the contents of a Resource, returning the first element which is an instance of clazz that holds the
   * given predicate.
   * 
   * @param res
   * @param clazz
   * @param predicate
   * @return
   */
  // TODO move to framework
  <T> T find(Resource res, Class<T> clazz, Predicate<T> predicate) {
    for (Iterator< EObject>it = res.getAllContents(); it.hasNext();) {
      EObject next = it.next();
      if (clazz.isInstance(next)) {
        T candidate = clazz.cast(next);
        if (predicate.apply(candidate)) {
          return candidate;
        }
      }
    }
    return null;
  }

  // turn off ce generation for now
  private GenerateInterfacesCommand createCommand(Collection<EObject> rootElements) {
    return new GenerateInterfacesCommand(rootElements) {
      @Override
      protected AbstractTransform getTransformation(EObject element_p) {
        return new InterfaceGeneration(new InterfaceGenerationPreferences() {
          public boolean isGenerateComponentExchanges() {
            return false;
          }
        }, false);
      }
    };
  }

  /**
   * Iterates over the contents of a Resource, returning all elements which are an instance of clazz that hold the given
   * predicate.
   * 
   * @param res
   * @param clazz
   * @param predicate
   * @return
   */
  // TODO move to framework
  <T> Collection<T> findAll(Resource res, Class<T> clazz, Predicate<T> predicate) {
    Collection<T> result = new ArrayList<T>();
    for (Iterator< EObject>it = res.getAllContents(); it.hasNext();) {
      EObject next = it.next();
      if (clazz.isInstance(next)) {
        T candidate = clazz.cast(next);
        if (predicate.apply(candidate)) {
          result.add(candidate);
        }
      }
    }
    return result;
  }

  // TODO move to framework
  <T extends NamedElement> Predicate<T> hasName(final String name) {
    return new Predicate<T>() {
      @Override
      public boolean apply(T input) {
        return StringUtils.equals(name, input.getName());
      }
    };
  }

  protected Resource getModelResource() {
    if (modelResource == null) {
      Session session = getSessionForTestModel(getRequiredTestModels().get(0));
      modelResource = TestHelper.getSemanticResource(session);
    }
    return modelResource;
  }

  static class InterfaceEquality extends EcoreUtil.EqualityHelper {

    private static final long serialVersionUID = -5651855427813503525L;

    @Override
    protected boolean haveEqualFeature(EObject expected, EObject actual, EStructuralFeature feature) {
      if (feature == CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES) {
        return true;
      }
      if (feature == ModellingcorePackage.Literals.MODEL_ELEMENT__ID) {
        return true;
      }
      return super.haveEqualFeature(expected, actual, feature);
    }
  }

  public static class ResourceSetChangeListener extends ResourceSetListenerImpl {

    boolean changed = false;

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
      changed = true;
    }

    public boolean hasChanged() {
      return changed;
    }
  }

}
