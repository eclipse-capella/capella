/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.linkedtext.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.google.common.base.Predicate;

public class DefaultLinkedTextContentProvider implements IStructuredContentProvider {

  private final Predicate<EObject> _includeEObject;
  private final Object[] _empty = new Object[0];
  
  public DefaultLinkedTextContentProvider(Predicate<EObject> includeObject_p){
    _includeEObject = includeObject_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    /**/
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
    /**/
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getElements(Object inputElement_p) {
    if (inputElement_p instanceof EObject){
      Collection<EObject> targets = new ArrayList<EObject>();
      EObject root = EcoreUtil.getRootContainer((EObject) inputElement_p);
      for (TreeIterator<EObject> it = EcoreUtil.getAllContents(root, true); it.hasNext();){
        EObject next = it.next();
        if (_includeEObject.apply(next)){
          targets.add(next);
        }
      }
      fillAdditionalElements((EObject) inputElement_p, targets);
      return targets.toArray();
    }
    return _empty;
  }

  /**
   * @return the predicate that all EObjects in getElements() will hold
   */
  protected final Predicate<EObject> getEObjectPredicate(){
    return _includeEObject;
  }
  
  /**
   * May be overridden by subclasses to include additional elements. Implementors
   * should also assure that additionally added objects hold the predicate returned via
   * getEObjectPredicate().
   * 
   * @param result_p the collection to add to
   */
  protected void fillAdditionalElements(EObject inputElement_p, Collection<EObject> result_p){
    /* nop */
  }
  
}
