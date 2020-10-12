/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.impl.scopes.FilteredModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * A scope where some elements are marked as 'external reference elements'.
 * 
 * This poses a solution to the following problem:
 * 
 * Given are two model scopes, each with a single element A, A', so that A matches A'. A references an element B, and A'
 * references an element B' which matches B. In order to detect this, B and B' must be added to the scopes.
 * 
 * Now, there is a side effect which is sometimes unwanted: Adding B and B' will cause diff merge to also detect
 * differences between those two elements, and there is AFAIU no simple way to configure diffmerge to not do so.
 */
public class SparseModelScope extends FilteredModelScope {

  interface Listener {
    public void scopeChanged();
  }

  public interface AttachHandler {

    /**
     * Attaches the toAttach object into the enclosing EMF model content tree.
     * 
     * @param toAttach
     *          - the object that needs to be attached
     * @param source
     *          - the corresponding object in the opposite scope
     * @param targetRole
     *          - the scope role into which the object is attached
     * @param mapping
     *          - the underlying diffmerge mapping
     */
    public void attachContainment(EObject toAttach, EObject source, Role targetRole, EMapping mapping);

  }

  private final ListenerList listeners = new ListenerList();
  private AttachHandler attachHandler = new NullHandler();

  public SparseModelScope(Collection<? extends EObject> elements) {
    super(new ArrayList<EObject>(elements));
  }

  public void addListener(Listener l) {
    listeners.add(l);
  }

  public void removeListener(Listener l) {
    listeners.remove(l);
  }

  public void setAttachHandler(AttachHandler handler) {
    attachHandler = (handler == null ? new NullHandler() : handler);
  }

  private EMapping mapping;

  public void setMapping(EMapping mapping) {
    this.mapping = mapping;
  }

  private final Collection<EObject> referenceOnlyContent = new HashSet<EObject>();

  public Collection<EObject> getExternalReferenceElements() {
    return referenceOnlyContent;
  }

  @Override
  public boolean add(EObject element) {
    if (element.eContainer() == null) {
      EObject origin = null;
      Role targetRole = Role.REFERENCE;
      EMatch match = mapping.getMatchFor(element, targetRole);
      if (match != null) {
        origin = match.get(Role.TARGET);
      } else {
        targetRole = Role.TARGET;
        match = mapping.getMatchFor(element, targetRole);
        origin = match.get(Role.REFERENCE);
      }
      attachHandler.attachContainment(element, origin, targetRole, mapping);
    }
    boolean result = super.add(element);
    fireChange();
    return result;
  }

  private void fireChange() {
    Object[] l = listeners.getListeners();
    for (int i = 0; i < l.length; ++i) {
      ((Listener) l[i]).scopeChanged();
    }
  }

  @Override
  public boolean add(EObject source, EReference reference, EObject value) {
    boolean result = super.add(source, reference, value);
    if (reference.isContainment()) {
      fireChange();
    }
    return result;
  }

  public void addAll(Collection<? extends EObject> all) {
    for (EObject e : all) {
      add(e);
    }
  }

  // the default attach handler does nothing
  private static class NullHandler implements AttachHandler {
    @Override
    public void attachContainment(EObject toAttach, EObject source, Role role, EMapping mapping) {
      // this does nothing.
    }
  }

}
