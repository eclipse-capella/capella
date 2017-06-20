/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.Multimap;

public class Stage {

  final static String SOURCE = "capella.move.stage"; //$NON-NLS-1$
  final List<EObject> delegate = new ArrayList<EObject>();

  private TransactionalEditingDomain domain;
  private ResourceSetListener listener;
  private final ListenerList<StageListener> stageListeners;
  private final int CODE_BACKREF = 0;

  /* maps staged elements to their new parent */
  Map<EObject, EObject> plannedParents = new HashMap<EObject, EObject>();

  /**
   * Contains entries [Target]->[Setting], so that target is not staged, and Setting is a setting referencing Target from a staged object
   */
  Multimap<EObject, EStructuralFeature.Setting> backrefs = ArrayListMultimap.create();

  private final Multimap<EObject, Diagnostic> diagnostics = ArrayListMultimap.create();

  public Stage(TransactionalEditingDomain domain){
    this.domain = domain;
    stageListeners = new ListenerList<StageListener>();
    listener = new StageResourceSetListener(this);
    domain.addResourceSetListener(listener);
  }

  public void addStageListener(StageListener l) {
    stageListeners.add(l);
  }

  public void removeStageListener(StageListener l) {
    stageListeners.remove(l);
  }

  /**
   *
   * @param staged
   * @param newParent
   */
  public void setParent(EObject staged, EObject newParent){
    EObject oldParent = null;
    if (newParent == null) {
      oldParent = plannedParents.remove(staged);
    } else {
      oldParent = plannedParents.put(staged, newParent);
    }
    notifyNewParent(staged, oldParent, newParent);
  }


  public EditingDomain getEditingDomain(){
    return domain;
  }

  public Collection<EObject> getElementsWithoutNewParent() {
    return Collections2.filter(getElements(), new Predicate<EObject>() {
      @Override
      public boolean apply(EObject input) {
        return !plannedParents.containsKey(input);
      }
    });
  }

  public Collection<EObject> getElements(){
    return Collections.unmodifiableCollection(delegate);
  }

  public Collection<Diagnostic> getDiagnostics(EObject elem) {
    return diagnostics.get(elem);
  }

  public void addAll(Collection<EObject> elements) {
    for (EObject e : elements) {
      addInternal(e);
    }
    notifyAdd(elements);
  }

  public void add(EObject element) {
    addInternal(element);
    notifyAdd(Collections.singleton(element));
  }

  private void notifyAdd(Collection<EObject> elements) {
    for (StageListener s : stageListeners) {
      s.elementsAdded(elements);
    }
  }

  private void notifyRemoved(Collection<? extends EObject> elements) {
    for (StageListener s : stageListeners) {
      s.elementsRemoved(elements);
    }
  }

  private void notifyNewParent(EObject staged, EObject oldParent, EObject newParent) {
    for (StageListener s : stageListeners) {
      s.parentChanged(staged, oldParent, newParent);
    }
  }

  private void addInternal(EObject e){

    TransactionalEditingDomain eDomain = TransactionUtil.getEditingDomain(e);

    if (eDomain == null) {
      throw new IllegalArgumentException(Messages.Stage_element_has_no_editing_domain);
    } else if (domain != eDomain){
      throw new IllegalArgumentException(Messages.Stage_different_editing_domains_not_allowed);
    }

    // if parent was already added there is nothing to do
    if (EcoreUtil.isAncestor(delegate, e)){
      return;
    }

    // remove staged elements that are descendants of the added element
    for (Iterator<EObject> it = delegate.iterator(); it.hasNext();){
      EObject staged = it.next();
      if (EcoreUtil.isAncestor(e, staged)){
        it.remove();
      }
    }

    delegate.add(e);
    refreshBackRefs();
  }

  private void refreshBackRefs() {
    for (EObject e : delegate) {
      refreshBackRefs(e);
    }
  }

  private void refreshBackRefs(EObject staged) {

    diagnostics.removeAll(staged);
    backrefs.removeAll(staged);
    for (Iterator<EObject> it = EcoreUtil.getAllContents(staged, true); it.hasNext();){
      EObject next = it.next();
      diagnostics.removeAll(next);
      backrefs.removeAll(next);
    }

    addBackrefs(staged);
    for (Iterator<EObject> it = EcoreUtil.getAllContents(staged, true); it.hasNext();){
      addBackrefs(it.next());
    }

  }

  private void addBackrefs(EObject e){
    EContentsEList.FeatureIterator<?> it = (EContentsEList.FeatureIterator)e.eCrossReferences().iterator();
    while (it.hasNext()){
      EObject referenced = (EObject) it.next();
      EStructuralFeature feature = it.feature();
      if (!feature.isDerived()){


        if (!Iterators.contains(EcoreUtil.getAllContents(delegate), referenced) && !delegate.contains(referenced)){
          // the referenced object is not staged

          if (EcoreUtil.getRootContainer(referenced) == EcoreUtil.getRootContainer(e)) {
            // don't consider cases where the referenced object is in a different project/library
            // FIXME this is probably even more complicated when multiple libraries are involved..

            backrefs.put(referenced, ((InternalEObject) e).eSetting(it.feature()));
            diagnostics.put(e, new BasicDiagnostic(Diagnostic.ERROR, SOURCE, CODE_BACKREF, Messages.Stage_diagnostic_backref, new Object[] {e, referenced, it.feature() }));
          }

        }
      }
    }
  }

  public void refresh() {

    Collection<EObject> keep = new ArrayList<EObject>();
    for (EObject e : delegate) {
      if (TransactionUtil.getEditingDomain(e) != domain) {
        // element was deleted..
        plannedParents.remove(e);
      } else if (e.eContainer() == plannedParents.get(e)){
        // element was already moved..
        plannedParents.remove(e);
      } else {
        EObject plannedParent = plannedParents.get(e);
        if (plannedParent != null && TransactionUtil.getEditingDomain(plannedParent) != domain) {
          // plannedParent was deleted...
          plannedParents.remove(e);
        }
        keep.add(e);
      }
    }


    delegate.clear();
    diagnostics.clear();
    backrefs.clear();

    for (EObject e : keep) {
      addInternal(e);
    }

    for (StageListener l : stageListeners) {
      l.stageChanged(this);
    }

  }

  public boolean hasBackreferences(){
    return backrefs.size() > 0;
  }

  public boolean hasBackreferences(EObject staged){
    for (EStructuralFeature.Setting setting : backrefs.values()){
      if (setting.getEObject() == staged){
        return true;
      }
    }
    return false;
  }

  public EObject getNewParent(EObject staged){
    return plannedParents.get(staged);
  }

  public Multimap<EStructuralFeature, EObject> getBackreferences(EObject staged){
    Multimap<EStructuralFeature, EObject> result = ArrayListMultimap.create();
    for (Map.Entry<EObject, EStructuralFeature.Setting> entry : backrefs.entries()){
      if (entry.getValue().getEObject() == staged){
        result.put(entry.getValue().getEStructuralFeature(), entry.getKey());
      }
    }
    return result;
  }

  public IStatus execute(){

    IStatus result = null;

    // there are still illegal references
    if (!backrefs.isEmpty()){
      result = new Status(IStatus.ERROR, "org.polarsys.capella.core.model.helpers" , Messages.Stage_incomplete_with_backrefs); //$NON-NLS-1$
    }

    // there is at least one element without a new parent
    else if (!plannedParents.keySet().containsAll(getElements())){
      result = new Status(IStatus.ERROR, "org.polarsys.capella.core.model.helpers", Messages.Stage_incomplete_with_orphan); //$NON-NLS-1$
    } else {

      RecordingCommand rc = new RecordingCommand(domain, Messages.Stage_formtitle){

        IStatus resultStatus = Status.OK_STATUS;

        @Override
        protected void doExecute() {
          CompoundCommand c = new CompoundCommand();
          for (EObject staged : getElements()){
            if (!c.appendAndExecute(AddCommand.create(domain, getNewParent(staged), null, Collections.singleton(staged)))){
              resultStatus = new Status(IStatus.ERROR, "org.polarsys.capella.core.model.helpers", NLS.bind("Cannot move {0} to {1}", EObjectLabelProviderHelper.getText(staged), EObjectLabelProviderHelper.getText(getNewParent(staged))));
              throw new OperationCanceledException();
            }
          }
        }

        @Override
        public Collection<?> getResult(){
          return Collections.singleton(resultStatus);
        }

      };

      domain.getCommandStack().execute(rc);
      result = (IStatus) rc.getResult().iterator().next();

    }
    return result;
  }

  public boolean canExecute() {
    return getElements().size() > 0 && getElementsWithoutNewParent().isEmpty() && !hasBackreferences();
  }


  public void dispose() {
    if (domain != null) {
      domain.removeResourceSetListener(listener);
    }
  }


  public void remove(EObject e) {
    removeAll(Collections.singletonList(e));
  }

  public void removeAll(Collection<? extends EObject> elements) {

    for (EObject e : elements ) {
      plannedParents.remove(e);
    }

    if (delegate.removeAll(elements)) {

      // remove backrefs for removed elements
      for (Iterator<Map.Entry<EObject, EStructuralFeature.Setting>> it = backrefs.entries().iterator(); it.hasNext();) {
        Map.Entry<EObject, EStructuralFeature.Setting> next = it.next();
        if (EcoreUtil.isAncestor(elements, next.getValue().getEObject())){
          it.remove();
        }
      }

      // may introduce new backrefs, TODO can be optimized..
      refreshBackRefs();
      notifyRemoved(elements);

    }


  }

}
