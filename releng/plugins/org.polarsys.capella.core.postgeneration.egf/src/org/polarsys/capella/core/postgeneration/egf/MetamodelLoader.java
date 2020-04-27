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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.operation.IRunnableWithProgress;


/**
 */
@SuppressWarnings("nls")
public class MetamodelLoader implements IRunnableWithProgress {

  private Collection<IFile> _metamodelFiles;
  private boolean _verbose;
  private ResourceSet _resourceSet;
  private Map<EClass, Set<EReference>> _crossRefs;
  
  
  public MetamodelLoader(final Collection<IFile> metamodelFiles_p, boolean verbose_p) {
    Assert.isNotNull(metamodelFiles_p);
    _metamodelFiles = metamodelFiles_p;
    _verbose = verbose_p;
    _resourceSet = null;
    _crossRefs = null;
  }

  public ResourceSet getResultingResourceSet() {
    return _resourceSet;
  }
  
  public Map<EClass, Set<EReference>> getResultingContainmentIncomingReferences() {
    return _crossRefs;
  }
  
  /**
   * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run(IProgressMonitor monitor_p) throws InvocationTargetException,
      InterruptedException {
    try {
      int nb = _metamodelFiles.size();
      monitor_p.beginTask("Loading metamodel fragments...", nb);
      _resourceSet = new ResourceSetImpl();
      for (IFile file : _metamodelFiles) {
        UpdateIconsUtil.getResourceFrom(file, _resourceSet, _verbose);
        monitor_p.worked(1);
        if (monitor_p.isCanceled()) throw new OperationCanceledException();
      }
      monitor_p.beginTask("Computing external cross-references...", 1);
      monitor_p.worked(1);
      _crossRefs = buildContainmentCrossReferences(_resourceSet);
    } finally {
      monitor_p.done();
    }
  }

  Map<EClass, Set<EReference>> buildContainmentCrossReferences(ResourceSet rs_p) {
    Map<EClass, Set<EReference>> result = new HashMap<EClass, Set<EReference>>();
    Iterator<Notifier> it = rs_p.getAllContents();
    while (it.hasNext()) {
      Notifier current = it.next();
      if (current instanceof EReference) {
        EReference ref = (EReference)current;
        if (ref.isContainment()) {
          EClass targetClass = ref.getEReferenceType();
          Set<EReference> value = result.get(targetClass);
          if (value == null) {
            value = new HashSet<EReference>();
            result.put(targetClass, value);
          }
          value.add(ref);
        }
      }
    }
    return result;
  }
  
}
