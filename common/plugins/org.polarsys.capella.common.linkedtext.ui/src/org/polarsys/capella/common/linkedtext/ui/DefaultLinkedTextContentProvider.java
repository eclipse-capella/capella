/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  
  public DefaultLinkedTextContentProvider(Predicate<EObject> includeObject){
    _includeEObject = includeObject;
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
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    /**/
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof EObject){
      Collection<EObject> targets = new ArrayList<EObject>();
      EObject root = EcoreUtil.getRootContainer((EObject) inputElement);
      for (TreeIterator<EObject> it = EcoreUtil.getAllContents(root, true); it.hasNext();){
        EObject next = it.next();
        if (_includeEObject.apply(next)){
          targets.add(next);
        }
      }
      fillAdditionalElements((EObject) inputElement, targets);
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
   * @param result the collection to add to
   */
  protected void fillAdditionalElements(EObject inputElement, Collection<EObject> result){
    /* nop */
  }
  
}
