/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.interfaces.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.internal.service.AbstractValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.projection.interfaces.InterfaceGeneration;
import org.polarsys.capella.core.projection.interfaces.InterfaceGenerationActivator;
import org.polarsys.capella.core.projection.interfaces.InterfaceGenerationPreferences;
import org.polarsys.capella.core.projection.interfaces.generateInterfaces.InterfaceGenerationResult;

public class DWF_I_23_GenerateInterfacesValidator extends AbstractModelConstraint {

  private static boolean loggedWorkaroundError = false;
  
  public static final String RULE_ID = "org.polarsys.capella.core.projection.interfaces.DWF_I_23"; //$NON-NLS-1$
  public static final String RULE_ID_UNQUALIFIED = "DWF_I_23"; //$NON-NLS-1$

  public static final int MISSING_EI_ON_INTERFACE = 0;
  public static final int UNKNOWN_EI_ON_INTERFACE = 1;

  @Override
  public IStatus validate(IValidationContext ctx) {

    final ComponentPort port = (ComponentPort) ctx.getTarget();
    Collection<IStatus> results = new ArrayList<IStatus>();

    // execute generation in dryRun mode to find out about updated interfaces
    InterfaceGeneration gen = new InterfaceGeneration(new InterfaceGenerationPreferences(), true);
    gen.setContext(port);
    gen.execute();

    InterfaceGenerationResult result = gen.getResult();
    Collection<Interface> alreadyChecked = getAlreadyCheckedInterfaces(ctx);

    for (Interface iface : result.getUpdatedInterfaces()) {
      if (alreadyChecked.add(iface)){
        results.addAll(validate(ctx, port, iface, result));
      }
    }

    if (results.size() > 0) {
      return ConstraintStatus.createMultiStatus(ctx, results);
    }

    return ctx.createSuccessStatus();

  }

  @SuppressWarnings("unchecked")
  // this is a workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=513438
  private Collection<Interface> getAlreadyCheckedInterfaces(IValidationContext ctx) {

    Collection<Interface> alreadyChecked = (Collection<Interface>) ctx.getCurrentConstraintData();

    if (alreadyChecked == null){
      AbstractValidationContext actx = (AbstractValidationContext) ctx;
      Field f = null;
      try {
        f = AbstractValidationContext.class.getDeclaredField("constraintData"); //$NON-NLS-1$
        f.setAccessible(true);
        Map<IModelConstraint,Object> map = (Map<IModelConstraint, Object>) f.get(actx);
        for (IModelConstraint c : map.keySet()) {
          if (c.getDescriptor().getId() == ctx.getCurrentConstraintId()){
            alreadyChecked = (Collection<Interface>) map.get(c);
            break;
          }
        }
      } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
        if (!loggedWorkaroundError){
          loggedWorkaroundError = true;
          Platform.getLog(InterfaceGenerationActivator.class).log(new Status(IStatus.ERROR, InterfaceGenerationActivator.getDefault().getBundle().getSymbolicName(),  "Cannot use cache for rule DWF_I_23. Some results for this rule may appear multiple times.", e)); //$NON-NLS-1$
        }
      } finally {
        if (f != null) {
          f.setAccessible(false);
        }
      }
    }
 
    if (alreadyChecked == null) {
      alreadyChecked = new HashSet<Interface>();
    }

    ctx.putCurrentConstraintData(alreadyChecked);
    return alreadyChecked;
  }

  private Collection<IStatus> validate(IValidationContext ctx, ComponentPort port, Interface iface, InterfaceGenerationResult generationResult) {

    Collection<IStatus> result = new ArrayList<IStatus>();

    for (ExchangeItem ei : generationResult.getRemovedExchangeItems(iface)) {
      Collection<EObject> resultLocus = new ArrayList<EObject>();
      resultLocus.add(ei);
      resultLocus.add(iface);
      resultLocus.add(port);
      resultLocus.addAll(generationResult.getInterfaceInfo(iface).getFunctionalExchanges());
      resultLocus.addAll(generationResult.getInterfaceInfo(iface).getComponentExchanges());
      result.add(ConstraintStatus.createStatus(ctx, ei, resultLocus, IStatus.ERROR, UNKNOWN_EI_ON_INTERFACE,
          Messages.DWF_I_23_GenerateInterfacesValidator_unknownEI, iface, ei));
    }

    for (ExchangeItem ei : generationResult.getAddedExchangeItems(iface)) {

      // where does this missing ei comme from?
      Collection<ComponentExchange> ce = new ArrayList<ComponentExchange>(generationResult.getInterfaceInfo(iface).getComponentExchanges());
      for (Iterator<ComponentExchange> it = ce.iterator(); it.hasNext();){
        ComponentExchange c = it.next();
        if (!c.getConvoyedInformations().contains(ei)){
          it.remove();
        }
      }

      Collection<FunctionalExchange> fe = new ArrayList<FunctionalExchange>(generationResult.getInterfaceInfo(iface).getFunctionalExchanges());
      for (Iterator<FunctionalExchange> it = fe.iterator(); it.hasNext();){
        FunctionalExchange f = it.next();
        if (!f.getExchangedItems().contains(ei)){
          it.remove();
        }
      }

      Collection<EObject> resultLocus = new ArrayList<EObject>();
      resultLocus.add(ei);
      resultLocus.add(iface);
      resultLocus.add(port);
      resultLocus.addAll(generationResult.getInterfaceInfo(iface).getFunctionalExchanges());
      resultLocus.addAll(generationResult.getInterfaceInfo(iface).getComponentExchanges());
      result.add(ConstraintStatus.createStatus(ctx, ei, resultLocus, IStatus.ERROR, MISSING_EI_ON_INTERFACE,
          Messages.DWF_I_23_GenerateInterfacesValidator_missingEI, iface, ei, getConveyorsText(ce, fe)));
    }

    return result;
  }

  private String[] getConveyorsText(Collection<ComponentExchange> ce, Collection<FunctionalExchange> fe) {

    String[] result = new String[ce.size() + fe.size()];
    int i = 0;
    for (ComponentExchange c : ce){
      result[i] = String.format("\"%s\" %s", EObjectLabelProviderHelper.getText(c),Messages.DWF_I_23_GenerateInterfacesValidator_suffix_ComponentExchange); //$NON-NLS-1$
      i++;
    }

    for (FunctionalExchange f : fe){
      result[i] = String.format("\"%s\" %s", EObjectLabelProviderHelper.getText(f),Messages.DWF_I_23_GenerateInterfacesValidator_suffix_FunctionalExchange); //$NON-NLS-1$
      i++;
    }
    return result;
  }

}