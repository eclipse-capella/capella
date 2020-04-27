/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.resources;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;

/**
 * The activator class controls the plug-in life cycle
 */
public class CapellaUIResourcesPlugin extends AbstractUIActivator {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.ui.resources"; //$NON-NLS-1$

  // The shared instance
  private static CapellaUIResourcesPlugin __plugin;

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    __plugin = this;
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    __plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static CapellaUIResourcesPlugin getDefault() {
    return __plugin;
  }

  /**
   * Get an image for given eclass.<br>
   * Images must be located in 'plug-in folder'/icons
   * 
   * @param eclass_p
   * @return an {@link ImageDescriptor} or <code>null</code> if not found
   */
  public ImageDescriptor getPNGImage(EClass eclass_p) {
    String key = "full/png/" + eclass_p.getName() + ".png"; //$NON-NLS-1$ //$NON-NLS-2$
    return getMetaClassImageDescriptor(key);
  }

  /**
   * Get an image for a given eobject.<br>
   * Images must be located in 'plug-in folder'/icons
   * 
   * @param eobject_p
   * @return an {@link ImageDescriptor} or <code>null</code> if not found
   */
  public ImageDescriptor getPNGImage(EObject eobject_p) {
    String key = "full/png/" + getCustomizedImageName(eobject_p) + ".png"; //$NON-NLS-1$ //$NON-NLS-2$
    return getMetaClassImageDescriptor(key);
  }

  /**
   * Get an image for given eclass.<br>
   * Images must be located in 'plug-in folder'/icons
   * 
   * @param eclass_p
   * @return an {@link ImageDescriptor} or <code>null</code> if not found
   */
  public ImageDescriptor getSVGImage(EClass eclass_p) {
    String key = "full/svg/" + eclass_p.getName() + ".svg"; //$NON-NLS-1$ //$NON-NLS-2$
    return getMetaClassImageDescriptor(key);
  }

  /**
   * Get an image descriptor from its key.<br>
   * URL related to key is first checked.
   * 
   * @param metaClassImageDescriptorKey_p
   * @return <code>null</code> if no image descriptor is found.
   */
  private ImageDescriptor getMetaClassImageDescriptor(String metaClassImageDescriptorKey_p) {
    ImageDescriptor result = null;
    if (null != getImageURL(metaClassImageDescriptorKey_p)) {
      result = super.getImageDescriptor(metaClassImageDescriptorKey_p);
    }
    return result;
  }

  /**
   * @param eobject
   *          the element from which an image name will be evaluated
   * @return a calculated image name
   */
  public String getCustomizedImageName(EObject eobject) {
    String eClassName = eobject.eClass().getName();

    if (eobject instanceof Part) {
      eClassName = "Part"; //$NON-NLS-1$
    } else if (eobject instanceof CommunicationLink) {
      CommunicationLinkKind kind = ((CommunicationLink) eobject).getKind();
      if (CommunicationLinkKind.PRODUCE.equals(kind)) {
        eClassName = "CommunicationLinkProduce"; //$NON-NLS-1$
      } else if (CommunicationLinkKind.CONSUME.equals(kind)) {
        eClassName = "CommunicationLinkConsume"; //$NON-NLS-1$
      } else if (CommunicationLinkKind.SEND.equals(kind)) {
        eClassName = "CommunicationLinkSend"; //$NON-NLS-1$
      } else if (CommunicationLinkKind.RECEIVE.equals(kind)) {
        eClassName = "CommunicationLinkReceive"; //$NON-NLS-1$
      } else if (CommunicationLinkKind.CALL.equals(kind)) {
        eClassName = "CommunicationLinkCall"; //$NON-NLS-1$
      } else if (CommunicationLinkKind.EXECUTE.equals(kind)) {
        eClassName = "CommunicationLinkExecute"; //$NON-NLS-1$
      } else if (CommunicationLinkKind.WRITE.equals(kind)) {
        eClassName = "CommunicationLinkWrite"; //$NON-NLS-1$
      } else if (CommunicationLinkKind.ACCESS.equals(kind)) {
        eClassName = "CommunicationLinkAccess"; //$NON-NLS-1$
      } else if (CommunicationLinkKind.ACQUIRE.equals(kind)) {
        eClassName = "CommunicationLinkAcquire"; //$NON-NLS-1$
      } else if (CommunicationLinkKind.TRANSMIT.equals(kind)) {
        eClassName = "CommunicationLinkTransmit"; //$NON-NLS-1$
      }
    } else if (eobject instanceof ExchangeItem) {
      if (((ExchangeItem) eobject).getExchangeMechanism() == ExchangeMechanism.EVENT) {
        eClassName = "ExchangeItemEvent"; //$NON-NLS-1$
      } else if (ExchangeMechanism.FLOW.equals(((ExchangeItem) eobject).getExchangeMechanism())) {
        eClassName = "ExchangeItemFlow"; //$NON-NLS-1$
      } else if (ExchangeMechanism.OPERATION.equals(((ExchangeItem) eobject).getExchangeMechanism())) {
        eClassName = "ExchangeItemOperation"; //$NON-NLS-1$
      } else if (ExchangeMechanism.SHARED_DATA.equals(((ExchangeItem) eobject).getExchangeMechanism())) {
        eClassName = "ExchangeItemData"; //$NON-NLS-1$
      }
    } else if (eobject instanceof FunctionalExchange) {
      if (((FunctionalExchange) eobject).getSource() instanceof OperationalActivity
          && ((FunctionalExchange) eobject).getTarget() instanceof OperationalActivity) {
        eClassName = "FunctionalExchange_OA"; //$NON-NLS-1$
      }
    } else if (eobject instanceof ComponentPort) {
      ComponentPortKind kind = ((ComponentPort) eobject).getKind();
      if (ComponentPortKind.STANDARD.equals(kind)) {
        eClassName = "StandardPort"; //$NON-NLS-1$
      } else if (ComponentPortKind.FLOW.equals(kind)) {
        eClassName = "FlowPort"; //$NON-NLS-1$
        if (OrientationPortKind.IN.equals(((ComponentPort) eobject).getOrientation())) {
          eClassName = "InFlowPort"; //$NON-NLS-1$
        } else if (OrientationPortKind.OUT.equals(((ComponentPort) eobject).getOrientation())) {
          eClassName = "OutFlowPort"; //$NON-NLS-1$
        }
      }
    } else if (eobject instanceof AbstractFunction) {
      FunctionKind kind = ((AbstractFunction) eobject).getKind();
      if (FunctionKind.DUPLICATE.equals(kind)) {
        eClassName = "FunctionKind_Duplicate"; //$NON-NLS-1$
      } else if (FunctionKind.GATHER.equals(kind)) {
        eClassName = "FunctionKind_Gather"; //$NON-NLS-1$
      } else if (FunctionKind.ROUTE.equals(kind)) {
        eClassName = "FunctionKind_Route"; //$NON-NLS-1$
      } else if (FunctionKind.SELECT.equals(kind)) {
        eClassName = "FunctionKind_Select"; //$NON-NLS-1$
      } else if (FunctionKind.SPLIT.equals(kind)) {
        eClassName = "FunctionKind_Split"; //$NON-NLS-1$
      }
    }

    else if (eobject instanceof Component) {
      Component component = (Component) eobject;
      if (component.isActor() && component.isHuman()) {
        eClassName = "ActorHuman";

      } else if (component instanceof Entity && component.isActor() && !component.isHuman()) {
        eClassName = "OperationalActor";

      } else if (component instanceof SystemComponent && component.isActor() && !component.isHuman()) {
        eClassName = "SystemActor";

      } else if (component instanceof LogicalComponent) {
        if (!component.isActor() && component.isHuman()) {
          eClassName = "LogicalComponentHuman";

        } else if (component.isActor() && !component.isHuman()) {
          eClassName = "LogicalActor";
        }
      } else if (component instanceof PhysicalComponent) {

        if (component.isActor() && !component.isHuman()) {
          eClassName = "PhysicalActor";

        } else {
          PhysicalComponentNature nature = ((PhysicalComponent) component).getNature();

          if (nature == PhysicalComponentNature.NODE) {
            if (component.isHuman()) {
              eClassName = "PhysicalComponentHumanNode";
            } else {
              eClassName = "PhysicalComponentNode";
            }
          } else if (nature == PhysicalComponentNature.BEHAVIOR) {
            if (component.isHuman()) {
              eClassName = "PhysicalComponentHumanBehavior";
            } else {
              eClassName = "PhysicalComponentBehavior";
            }
          } else {
            if (component.isHuman()) {
              eClassName = "PhysicalComponentHumanUnset";
            }
          }
        }
      }
    }

    return eClassName;
  }
}
