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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import static org.polarsys.capella.common.helpers.EObjectLabelProviderHelper.getText;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;


public class PortReferenceUpdater {

  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
  private final InterfaceInfo info;
  
  PortReferenceUpdater(InterfaceInfo info){
    this.info = info;
  }
  
  private Object[] getArgs(EObject... args) {
    Object[] result = new Object[args.length];
    for (int i = 0; i < args.length; i++){
      result[i] = getText(args[i]);
    }
    return result;
  }


  void updatePortReferences(){
    Interface iface = info.getInterface(false);

    if (iface != null){
      for (EObject pre : EObjectExt.getReferencers(iface, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)){
        if (info.getProvider() == null || info.getProvider().getEObject() != pre){
          ((Port) pre).getProvidedInterfaces().remove(iface);
          if (logger.isInfoEnabled()){
            logger.info(new EmbeddedMessage(
                NLS.bind(Messages.PortReferenceUpdater_remove_provided_interface, getArgs(pre,pre.eContainer(), iface)) , logger.getName(), new Object[] { pre, iface }));
          }
        }
      }

      if (info.getProvider() != null && info.getProvider().addProvidedInterface(iface)){
        EObject port = info.getProvider().getEObject();
        if (logger.isInfoEnabled()){
          logger.info(new EmbeddedMessage(
              NLS.bind(Messages.PortReferenceUpdater_add_provided_interface, getArgs(port, port.eContainer(), iface)), logger.getName(), new Object[] { port, iface }));
        }
      }
      

      for (EObject pre : EObjectExt.getReferencers(iface, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)){
        if (info.getRequirer() == null || info.getRequirer().getEObject() != pre){
          ((Port) pre).getRequiredInterfaces().remove(iface);
          if (logger.isInfoEnabled()){
            logger.info(new EmbeddedMessage(
                NLS.bind(Messages.PortReferenceUpdater_remove_required_interface, getArgs(pre, pre.eContainer(), iface)), logger.getName(), new Object[] { pre, iface }));
          }
        }
      }

      if (info.getRequirer() != null && info.getRequirer().addRequiredInterface(iface)){
        if (logger.isInfoEnabled()){
          EObject port = info.getRequirer().getEObject();
          logger.info(new EmbeddedMessage(
              NLS.bind(Messages.PortReferenceUpdater_add_required_interface, getArgs(port, port.eContainer(), iface)), logger.getName(), new Object[] { port, iface }));     
        }
      }
    }
  
  }
}
