/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

  public void clear() {
    if (map != null) {
      map.clear();
    }
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

  public HashMapSet(Comparator<V> comparator) {
    super();
    this.comparator = comparator;
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
  public Collection<V> get(K obj) {
    if (map.get(obj) == null) {
      return createInternalSet();
    } else if (!(map.get(obj) instanceof Set)) {
      Collection<V> a = createInternalSet();
      a.add((V) map.get(obj));
      map.put(obj, a);
      return a;
    }

    return (Collection<V>) map.get(obj);
  }

  public boolean contains(K obj) {
    return get(obj).size() != 0;
  }

  public void remove(K obj) {
    if (map.get(obj) != null) {
      map.remove(obj);
    }
  }

  public void remove(K obj, V arg1) {
    if (map.get(obj) != null) {
      if (map.get(obj).equals(arg1)) {
        map.remove(obj);
      } else if (map.get(obj) instanceof Collection) {
        Collection<V> a = ((Collection<V>) map.get(obj));
        a.remove(arg1);

        if (a.size() == 0) {
          map.remove(obj);
        } else if (a.size() == 1) {
          map.remove(obj);
          put(obj, arg1);
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void put(K arg0, V arg1) {
    if (map.get(arg0) == null) {
      map.put(arg0, arg1);
    } else {
      if (!(map.get(arg0) instanceof Set)) {
        Collection<V> a = createInternalSet();
        a.add((V) map.get(arg0));
        map.put(arg0, a);
      }
      ((Collection) map.get(arg0)).add(arg1);
    }
  }

  public void putAll(K arg0, List<V> arg1) {
    for (V v : arg1) {
      put(arg0, v);
    }
  }

}
