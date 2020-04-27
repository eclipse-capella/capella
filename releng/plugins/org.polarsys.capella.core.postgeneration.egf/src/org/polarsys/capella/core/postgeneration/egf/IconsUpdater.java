/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.postgeneration.egf;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.operation.IRunnableWithProgress;


/**
 */
@SuppressWarnings("nls")
public class IconsUpdater implements IRunnableWithProgress {

  private ResourceSet _resourceSet;
  private IFile _metamodelFile;
  private IFolder _srcIconsFolder;
  private IFolder _destIconsFolder;
  private boolean _clearFirst;
  private boolean _verbose;
  private int _mcHandled;
  private int _mcSuccessful;
  private int _iconsCreated;
  private Map<EClass, Set<EReference>> _class2Refs;
  
  
  public IconsUpdater(ResourceSet rs_p, Map<EClass, Set<EReference>> class2Refs_p,
      IFile metamodelFile_p, IFolder srcIconsFolder_p, IFolder destIconsFolder_p, boolean verbose_p) {
    Assert.isNotNull(rs_p);
    Assert.isNotNull(class2Refs_p);
    Assert.isNotNull(metamodelFile_p);
    Assert.isNotNull(srcIconsFolder_p);
    Assert.isNotNull(destIconsFolder_p);
    _resourceSet = rs_p;
    _class2Refs = class2Refs_p;
    _metamodelFile = metamodelFile_p;
    _srcIconsFolder = srcIconsFolder_p;
    _destIconsFolder = destIconsFolder_p;
    _clearFirst = false;
    _verbose = verbose_p;
    _mcSuccessful = 0;
    _mcHandled = 0;
    _iconsCreated = 0;
    _class2Refs = class2Refs_p; 
  }

  public int getHandledMetaclassesNb() {
    return _mcHandled;
  }
  public int getSuccesfullyHandledMetaclassesNb() {
    return _mcSuccessful;
  }
  public int getCreatedIconsNb() {
    return _iconsCreated;
  }
  
  /**
   * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run(IProgressMonitor monitor_p) throws InvocationTargetException,
      InterruptedException {
    try {
      String msg = UpdateIconsUtil.buildString("Getting metamodel fragment ", _metamodelFile.getName(), "...");
      monitor_p.beginTask(msg, 1);
      monitor_p.worked(1);
      Resource res = UpdateIconsUtil.getResourceFrom(_metamodelFile, _resourceSet, _verbose);
      if (monitor_p.isCanceled() || res == null) throw new OperationCanceledException();
      
      if (_clearFirst) {
        try {
          IResource[] members = _destIconsFolder.members();
          int nb = members.length;
          monitor_p.beginTask("Clearing target icons folder...", nb);
          for (IResource member : members) {
            member.delete(true, null);
          }
          monitor_p.worked(1);
        } catch(CoreException e) {
          if (_verbose) e.printStackTrace();
        }
        if (monitor_p.isCanceled()) throw new OperationCanceledException();
      }

      monitor_p.beginTask("Computing workload...", 1);
      monitor_p.worked(1);
      if (monitor_p.isCanceled()) throw new OperationCanceledException();

      List<EClass> allConcrete = getConcreteMetaclassesInResource(res);
      if (!allConcrete.isEmpty()) {
        monitor_p.beginTask("Handling metaclasses...", allConcrete.size());
        for (EClass eClass : allConcrete) {
          _mcHandled++;
          handleMetaclass(eClass);
          monitor_p.worked(1);
          if (monitor_p.isCanceled()) throw new OperationCanceledException();
        }
      }
    } finally {
      monitor_p.done();
    }
  }


  private List<EClass> getConcreteMetaclassesInResource(Resource res_p) {
    List<EClass> result = new LinkedList<EClass>();
    // We do it the basic way to be sure
    TreeIterator<EObject> it = res_p.getAllContents();
    while (it.hasNext()) {
      EObject current = it.next();
      if (current instanceof EClass) {
        EClass eClass = (EClass)current;
        if (!eClass.isAbstract()) result.add(eClass);
      }
    }
    return result;
  }

  private Set<EReference> getContainmentsTo(EClass eClass_p) {
    Set<EReference> result = new HashSet<EReference>();
    List<EClass> clan = new LinkedList<EClass>(eClass_p.getEAllSuperTypes());
    clan.add(eClass_p);
    // For each class in hierarchy including self
    for (EClass currentClass : clan) {
      Set<EReference> currentRefs = _class2Refs.get(currentClass);
      if (currentRefs != null) result.addAll(currentRefs);
    }
    return result;
  }
  
//  private Set<EReference> getContainmentsTo(EClass eClass_p) {
//    Set<EReference> result = new HashSet<EReference>();
//    List<EClass> clan = new LinkedList<EClass>(eClass_p.getEAllSuperTypes());
//    clan.add(eClass_p);
//    // For each class in hierarchy
//    for (EClass currentClass : clan) {
//      // Get unidirectional assocs to currentClass
//      Set<EObject> crossRefs = new HashSet<EObject>(currentClass.eCrossReferences());
//      Collection<EStructuralFeature.Setting> settings =
//        EcoreUtil.UsageCrossReferencer.find(currentClass, _resourceSet);
//      if (settings != null) {
//        for (EStructuralFeature.Setting setting : settings) {
//          crossRefs.add(setting.getEStructuralFeature());
//        }
//      }
//      for (EObject currentCr : crossRefs) {
//        if (currentCr instanceof EReference) {
//          EReference ref = (EReference)currentCr;
//          if (ref.isContainment() && ref.getEReferenceType() == currentClass)
//            result.add(ref);
//        }
//      }
//      // Get bidirectional assocs to currentClass
//      List<EReference> refs = currentClass.getEReferences();
//      for (EReference ref : refs) {
//        if (ref.isContainer() && null != ref.getEOpposite())
//          result.add(ref.getEOpposite());
//      }
//    }
//    return result;
//  }

  private void handleMetaclass(EClass eClass_p) {
    try {
      IFile source = getSourceIcon(eClass_p);
      if (null != source) {
        Set<EReference> applicableContainments = getContainmentsTo(eClass_p);
        for (EReference cont : applicableContainments) {
          IPath destination = getDestinationIcon(eClass_p, cont);
          //if (_verbose) System.out.println("Saving " + destination.lastSegment());
          try {
        	  source.copy(destination, true, null);
          }
          catch (CoreException ex) {
        	  //ex.printStackTrace();
          }
          _iconsCreated++;
        }
        _mcSuccessful++;
      } else if (_verbose) {
        System.out.println("Cannot find icon file for class " + eClass_p.getName());
      }
    } catch(Exception e) {
      if (_verbose) e.printStackTrace();
    }
  }
  
  private IFile getSourceIcon(EClass eClass_p) {
    IFile result = null;
    String iconName = getSourceIconName(eClass_p);
    IResource res = _srcIconsFolder.findMember(iconName);
    if (res != null && res instanceof IFile && res.exists())
      result = (IFile)res;
    return result;
  }
  
  private IPath getDestinationIcon(EClass eClass_p, EReference containment_p) {
    String iconName = getDestinationIconName(eClass_p, containment_p);
    IPath result = _destIconsFolder.getFullPath().append(iconName);
    return result;
  }
  
  private String getSourceIconName(EClass eClass_p) {
    return UpdateIconsUtil.buildString(eClass_p.getName(), ".gif");
  }
  
  private String getDestinationIconName(EClass eClass_p, EReference containment_p) {
    return UpdateIconsUtil.buildString("Create",
                                       containment_p.getEContainingClass().getName(),
                                       "_",
                                       containment_p.getName(),
                                       "_",
                                       eClass_p.getName(),
                                       ".gif");
  }
  
}
