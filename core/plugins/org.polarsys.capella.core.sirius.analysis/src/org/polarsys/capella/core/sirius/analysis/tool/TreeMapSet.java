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
package org.polarsys.capella.core.sirius.analysis.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * A map with an internal set for each values
 * The master set can be ordered using a comparator
 */
public class TreeMapSet<K, V> {

  Comparator<K> comparator;
  
  TreeMap<K,Object> map;

  public TreeMapSet() {
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
  
  protected Set<V> createInternalSet() {
    return new InternalHashSet();
  }
  
  public TreeMapSet(Comparator<K> comparator_p) {
    super();
    map = new TreeMap<K, Object>(comparator_p);
    comparator=comparator_p;
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
    Set<K> set=keySet();
    for (K key : keys)
      if (set.contains(key))
        subSet.add(key);
    return subSet;
  }

  @SuppressWarnings("unchecked")
  public Collection<V> get(K obj_p) {
    if (map.get(obj_p)==null) {
      return createInternalSet();
    } else if (!(map.get(obj_p) instanceof InternalSet)) {
      Collection<V> a = createInternalSet();
      a.add((V)map.get(obj_p));
      map.put(obj_p, a);
      return a;
    }

    return (Collection<V>)map.get(obj_p);
  }

  public void remove(K obj_p) {
    if (map.get(obj_p)!=null) {
      map.remove(obj_p);
    }
  }

  @SuppressWarnings("unchecked")
  public void put(K arg0_p, V arg1_p) {
    if (map.get(arg0_p)==null) {
      map.put(arg0_p, arg1_p);
    } else {
      if (!(map.get(arg0_p) instanceof InternalSet)) {
        Collection<V> a = createInternalSet();
        a.add((V)map.get(arg0_p));
        map.put(arg0_p, a);
      }
      ((Collection)map.get(arg0_p)).add(arg1_p);
    }
  }

  public void putAll(K arg0_p, List<V> arg1_p) {
    for (V v : arg1_p) {
      put(arg0_p, v);
    }
  }
  
  public Collection<V> values() {
    Collection<V> result = new ArrayList<V>();
    for (K k : keySet()) {
      result.addAll(get(k));
    }
    return result;
  }
  
}
