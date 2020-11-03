/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.communication.Exception;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.information.communication.MessageReference;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Structure;

/**
 */
public class ServiceExt {

  static public List<Exception> getExceptionsFromRootComponentArchitecture(Service service) {
    List<Exception> list = new ArrayList<Exception>();
    Structure rootPkg = getRootOwnerPkg(service);
    if (null != rootPkg) {
      ComponentArchitecture compArch = StructureExt.getRootComponentArchitecture(rootPkg);
      list.addAll(getExceptionsFromComponentArchitecture(compArch));
    }

    return list;
  }

  static public List<Message> getMessageFromRootComponentArchitecture(Service service) {
    List<Message> list = new ArrayList<Message>(1);
    Structure rootPkg = getRootOwnerPkg(service);
    if (null != rootPkg) {
      ComponentArchitecture compArch = StructureExt.getRootComponentArchitecture(rootPkg);
      list.addAll(getMessagesFromComponentArchitecture(compArch));
    }

    return list;
  }
  
  public static ComponentArchitecture getRootComponentArchitecture(Service service) {
    ComponentArchitecture root = null;
    Structure rootPkg = getRootOwnerPkg(service);
    if (null != rootPkg) {
      root = StructureExt.getRootComponentArchitecture(rootPkg);
    }

    return root;
  }

  static public List<Message> getMessagesFromComponentArchitecture(ComponentArchitecture compArch) {
    List<Message> list = new ArrayList<Message>(1);
    if (null != compArch) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
      if (null != dataPkg) {
        list.addAll(DataPkgExt.getAllMessages(dataPkg));
      }
    }
    return list;
  }
  
  static public List<Exception> getExceptionsFromComponentArchitecture(ComponentArchitecture compArch) {
    List<Exception> list = new ArrayList<Exception>(1);
    if (null != compArch) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
      if (null != dataPkg) {
        list.addAll(DataPkgExt.getAllExceptions(dataPkg));
      }
    }
    return list;
  }

  static public List<Message> getMessageFromRootComponent(Service service) {
    List<Message> list = new ArrayList<Message>(1);
    Component parentComp = getRootComponent(service);
    if (null != parentComp) {
      list.addAll(getMessagesFromComponent(parentComp));
    }
    return list;
  }
  
  static public List<Exception> getExceptionsFromRootComponent(Service service) {
    List<Exception> list = new ArrayList<Exception>(1);
    Component parentComp = getRootComponent(service);
    if (null != parentComp) {
      list.addAll(getExceptionsFromComponent(parentComp));
    }
    return list;
  }

  static public List<Message> getMessagesFromComponent(Component component) {
    List<Message> list = new ArrayList<Message>(1);
    if (null != component) {
      if (component instanceof LogicalComponent) {
        DataPkg dataPkg = ((LogicalComponent)component).getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(DataPkgExt.getAllMessages(dataPkg));
        }
      }
    }
    return list;
  }
  
  static public List<Exception> getExceptionsFromComponent(Component component) {
    List<Exception> list = new ArrayList<Exception>(1);
    if (null != component) {
      if (component instanceof LogicalComponent) {
        DataPkg dataPkg = ((LogicalComponent)component).getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(DataPkgExt.getAllExceptions(dataPkg));
        }
      }
    }
    return list;
  }

  static public boolean isReferencing(Service service, Message message) {
    for (MessageReference ref : service.getMessageReferences()) {
      if (ref.getMessage().equals(message))
        return true;
    }
    return false;
  }
  
  static public boolean isThrowing(Service service, Exception exception) {
    if(null != service) {
      return service.getThrownExceptions().contains(exception);
    }
    return false;
  }
  
  static public List<Message> getFilteredMessages(Service currentService, List<Message> messageList) {
    List<Message> list = new ArrayList<Message>(1);
    for (Message message : messageList) {
      if (ServiceExt.isReferencing(currentService, message))
        continue;
      list.add(message);
    }
    return list;
  }
  
  static public List<Exception> getFilteredExceptions(Service currentService, List<Exception> exceptionList) {
    List<Exception> list = new ArrayList<Exception>(1);
    for (Exception exception : exceptionList) {
      if (ServiceExt.isThrowing(currentService, exception))
        continue;
      list.add(exception);
    }
    return list;
  }
  
  static public List<Message> getMessagesFromParentHierarchy(Service currentService) {
    List<Message> list = new ArrayList<Message>(1);
    Classifier owningClass = (Classifier) currentService.eContainer();
    if (owningClass instanceof Class) {
      DataPkg rootClassPkg = ClassExt.getRootOwnerDataPkg((Class) owningClass);
      list.addAll(DataPkgExt.getMessagesFromParentHierarchy(rootClassPkg));
    }
    else if (owningClass instanceof Interface) {
      InterfacePkg rootInterfacePkg = InterfaceExt.getRootOwnerInterfacePkg((Interface) owningClass);
      list.addAll(DataPkgExt.getMessagesFromParentHierarchy(rootInterfacePkg));
    }
    return list;
  }
  
  static public List<Exception> getExceptionsFromParentHierarchy(Service currentService) {
    List<Exception> list = new ArrayList<Exception>(1);
    Classifier owningClass = (Classifier) currentService.eContainer();
    if (owningClass instanceof Class) {
      DataPkg rootClassPkg = ClassExt.getRootOwnerDataPkg((Class) owningClass);
      list.addAll(DataPkgExt.getExceptionsFromParentHierarchy(rootClassPkg));
    }
    else if (owningClass instanceof Interface) {
      InterfacePkg rootInterfacePkg = InterfaceExt.getRootOwnerInterfacePkg((Interface) owningClass);
      list.addAll(DataPkgExt.getExceptionsFromParentHierarchy(rootInterfacePkg));
    }
    return list;
  }

  static private Structure getRootOwnerPkg(Service service) {
    Structure rootPkg = null;
    Classifier owningClass = (Classifier) service.eContainer();
    if (owningClass instanceof Class) {
      // Get from the owning class
      rootPkg = ClassExt.getRootOwnerDataPkg((Class) owningClass);
    }
    else if (owningClass instanceof Interface) {
      // Get from the owning interface
      rootPkg = InterfaceExt.getRootOwnerInterfacePkg((Interface) owningClass);
    }
    return rootPkg;
  }

  static private Component getRootComponent(Service service) {
	  Component rootCpnt = null;
	  Classifier owningClass = (Classifier) service.eContainer();
	  if (owningClass instanceof Class) {
	    DataPkg rootClassPkg = ClassExt.getRootOwnerDataPkg((Class) owningClass);
	    rootCpnt = DataPkgExt.getRootComponent(rootClassPkg);
	  }
	  else if (owningClass instanceof Interface) {
	    InterfacePkg rootInterfacePkg = InterfaceExt.getRootOwnerInterfacePkg((Interface) owningClass);
	    rootCpnt = InterfacePkgExt.getRootComponent(rootInterfacePkg);
	  }
	  return rootCpnt;
  }
}
