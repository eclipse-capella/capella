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
package org.polarsys.capella.core.transition.common.capellaHelpers;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A map with an internal set for each values
 */
public class HashMapSet<K, V> {

  boolean isOrdonned = false;
  Comparator<V> comparator;

  HashMap<K, Object> map = new HashMap<K, Object>();

  public HashMapSet() {
    super();
  }

  private interface InternalSet {
    //Nothing
  }

  private class InternalHashSet extends HashSet<V> implements InternalSet {
    private static final long serialVersionUID = -1399521010073092265L;

    public InternalHashSet() {
      super();
    }
  }

  private class InternalTreeSet extends TreeSet<V> implements InternalSet {

    private static final long serialVersionUID = 4461070833994342245L;

    public InternalTreeSet() {
      super(comparator);
    }
  }

  protected Set<V> createInternalSet() {
    if (isOrdonned) {
      return new InternalTreeSet();
    }
    return new InternalHashSet();
  }

  public HashMapSet(Comparator<V> comparator_p) {
    super();
    this.comparator = comparator_p;
    isOrdonned = true;
  }

  public Set<K> keySet() {
    return map.keySet();
  }

  public boolean containsKey(K k) {
    return map.keySet().contains(k);
  }

  /**
   * Retrieve the union between the current keySet and the given collection of keys
   * @param keys
   * @return
   */
  public Set<K> subKeySet(Collection<K> keys) {
    Set<K> subSet = new HashSet<K>();
    Set<K> set = keySet();
    for (K key : keys) {
      if (set.contains(key)) {
        subSet.add(key);
      }
    }
    return subSet;
  }

  @SuppressWarnings("unchecked")
  public Collection<V> get(K obj_p) {
    if (map.get(obj_p) == null) {
      return createInternalSet();
    } else if (!(map.get(obj_p) instanceof InternalSet)) {
      Collection<V> a = createInternalSet();
      a.add((V) map.get(obj_p));
      map.put(obj_p, a);
      return a;
    }

    return (Collection<V>) map.get(obj_p);
  }

  public boolean contains(K obj_p) {
    return get(obj_p).size() != 0;
  }

  public void remove(K obj_p) {
    if (map.get(obj_p) != null) {
      map.remove(obj_p);
    }
  }

  public void remove(K obj_p, V arg1_p) {
    if (map.get(obj_p) != null) {
      if (map.get(obj_p).equals(arg1_p)) {
        map.remove(obj_p);
      } else if (map.get(obj_p) instanceof Collection) {
        Collection<V> a = ((Collection<V>) map.get(obj_p));
        a.remove(arg1_p);

        if (a.size() == 0) {
          map.remove(obj_p);
        } else if (a.size() == 1) {
          map.remove(obj_p);
          put(obj_p, arg1_p);
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void put(K arg0_p, V arg1_p) {
    if (map.get(arg0_p) == null) {
      map.put(arg0_p, arg1_p);
    } else {
      if (!(map.get(arg0_p) instanceof InternalSet)) {
        Collection<V> a = createInternalSet();
        a.add((V) map.get(arg0_p));
        map.put(arg0_p, a);
      }
      ((Collection) map.get(arg0_p)).add(arg1_p);
    }
  }

  public void putAll(K arg0_p, List<V> arg1_p) {
    for (V v : arg1_p) {
      put(arg0_p, v);
    }
  }

}
