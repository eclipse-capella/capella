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
package org.polarsys.capella.core.commands.preferences.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.polarsys.capella.core.preferences.commands.exceptions.ItemExistsException;

/**
 * <p>
 * A centralized registry of {@link IItemDescriptor descriptors} for the items that are available in the commands
 * system.
 * </p>
 */
public class PreferencesItemsRegistry {
  /*
   * 
   */
  private static PreferencesItemsRegistry INSTANCE;

  private static IItemDescriptor profileDescriptor;

  public static IItemDescriptor getProfileDescriptor() {
    return profileDescriptor;
  }

  public static void setProfileDescriptor(IItemDescriptor profileDescriptor_p) {
    profileDescriptor = profileDescriptor_p;
  }

  /*
   * 
   */
  private final Map<String, IItemDescriptor> descriptors = new HashMap<String, IItemDescriptor>();

  /*
   * 
   */
  private volatile IItemListener[] itemListeners;

  /**
   * Initializes me.
   */
  private PreferencesItemsRegistry() {
    super();
  }

  /**
   * Obtains the instance of the constraint registry.
   * 
   * @return the <i>Singleton</i> instance
   */
  public static PreferencesItemsRegistry getInstance() {
    if (INSTANCE == null) {

      INSTANCE = new PreferencesItemsRegistry();

    }
    return INSTANCE;
  }

  public boolean isHandled(String commandId) {
    return descriptors.containsKey(commandId);
  }

  /**
   * Obtains the unique constraint descriptor having the specified ID.
   * 
   * @param id
   *          the ID of the constraint descriptor to retrieve (not <code>null</code>)
   * @return the matching constraint descriptor, or <code>null</code> if it does not exist
   */
  public IItemDescriptor getDescriptor(String id) {
    return descriptors.get(id);
  }

  /**
   * Obtains the descriptors for all registered items, in no particular order. Note that all disabled (for whatever
   * reason) items are included in the result.
   * 
   * @return the available constraint descriptors, as an unmodifiable collection
   */
  public Collection<IItemDescriptor> getAllDescriptors() {
    synchronized (descriptors) {
      return new java.util.ArrayList<IItemDescriptor>(descriptors.values());
    }
  }

  /**
   * Registers a constraint descriptor.
   * 
   * @param descriptor
   *          a new constraint descriptor, which must have a unique ID (not <code>null</code>)
   * @throws ItemExistsException
   *           if a different descriptor is already registered under the given <code>descriptor</code>'s ID
   */
  public void register(IItemDescriptor descriptor) throws ItemExistsException {
    try {
      boolean registered;

      synchronized (descriptors) {

        registered = doRegister(descriptor);

      }

      if (registered) {
        broadcastItemChangeEvent(new ItemChangeEvent(descriptor, ItemChangeEventType.REGISTERED));
      }

    } catch (ItemExistsException exception_p) {
      // XXX : add log here ...after RC1 deliver
    }
  }

  /**
   * Registers a constraint descriptor.
   * 
   * @param descriptor
   *          a new constraint descriptor, which must have a unique ID (not <code>null</code>)
   * @throws ItemExistsException
   *           if a different descriptor is already registered under the given <code>descriptor</code>'s ID
   */
  public void registerUserProfile(IItemDescriptor descriptor) throws ItemExistsException {

    Object existing = getProfileDescriptor();

    if (existing == null) {
      profileDescriptor = descriptor;
    } else if (existing != descriptor) {

      throw new ItemExistsException(profileDescriptor.getId());
    }

  }

  /**
   * Unregisters an existing constraint descriptor. This <code>descriptor</code>'s ID will subsequently be available for
   * re-use.
   * 
   * @param descriptor
   *          a constraint descriptor (not <code>null</code>)
   */
  public void unregister(IItemDescriptor descriptor) {
    assert descriptor != null;

    boolean unregistered;

    synchronized (descriptors) {
      unregistered = descriptors.remove(descriptor.getId()) != null;
    }

    if (unregistered) {
      broadcastItemChangeEvent(new ItemChangeEvent(descriptor, ItemChangeEventType.UNREGISTERED));
    }
  }

  /**
   * Adds an <code>IItemListener</code> to receive constraint change events. This method has no effect if the
   * <code>IItemListener
   * </code> is already registered.
   * 
   * @param listener
   *          a new constraint listener
   * @since 1.1
   */
  public synchronized void addItemListener(IItemListener listener) {
    if (indexOf(listener) < 0) {
      if (itemListeners == null) {
        itemListeners = new IItemListener[] { listener };
      } else {
        IItemListener[] newListeners = new IItemListener[itemListeners.length + 1];

        System.arraycopy(itemListeners, 0, newListeners, 0, itemListeners.length);
        newListeners[itemListeners.length] = listener;
        itemListeners = newListeners;
      }

    }
  }

  /**
   * Removes the <code>IItemListener</code> from the list of listeners. This method has no effect if the
   * <code>IItemListener</code> is not currently registered.
   * 
   * @param listener
   *          a constraint listener
   * @since 1.1
   */
  public synchronized void removeItemListener(IItemListener listener) {
    int index = indexOf(listener);

    if (index >= 0) {
      IItemListener[] newListeners = new IItemListener[itemListeners.length - 1];

      System.arraycopy(itemListeners, 0, newListeners, 0, index);
      System.arraycopy(itemListeners, index + 1, newListeners, index, itemListeners.length - index - 1);
      itemListeners = newListeners;

    }
  }

  /**
   * Computes the index of a specified <code>IItemListener</code> in the array of registered listeners.
   * 
   * @param listener
   *          a constraint listener
   * @return the <code>constraint listener</code>'s index, or -1 if it is not in my list
   */
  private int indexOf(IItemListener listener) {
    int result = -1;
    if (itemListeners != null) {
      for (int i = 0; i < itemListeners.length; i++) {
        if (itemListeners[i] == listener) {
          result = i;
          break;
        }
      }
    }

    return result;
  }

  /**
   * Broadcasts the specified <code>ItemChangeEvent</code> to all constraint listeners. This method is used internally
   * by items to send notifications when they have changed.
   * <p>
   * <b>Note</b> that this method should only be invoked by implementation of of the {@link IItemDescriptor} interface.
   * </p>
   * 
   * @param event
   *          a constraint change event to broadcast
   */
  public void broadcastItemChangeEvent(ItemChangeEvent event) {
    // Check if listeners exist
    if (itemListeners == null) {
      return;
    }

    IItemListener[] array = itemListeners; // copy the reference

    for (IItemListener element : array) {
      try {
        element.itemChanged(event);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Implements the registration of a constraint. <b>This method requires that the caller synchronize on the
   * <tt>descriptors</tt> map</b>.
   * 
   * @param descriptor
   *          a descriptor to register
   * @return whether the descriptor was added to the registry or not (<code>false</code> in the case the same descriptor
   *         was already registered, which is OK)
   * @throws ItemExistsException
   *           if a different descriptor was already registered under the same ID
   */
  private boolean doRegister(IItemDescriptor descriptor) throws ItemExistsException {

    boolean result = false;
    String id = descriptor.getId();

    Object existing = descriptors.get(id);

    if (existing == null) {
      result = true;
      descriptors.put(id, descriptor);
    } else if (existing != descriptor) {

      throw new ItemExistsException(id);
    }

    return result;
  }

  /**
   * Performs a bulk registration of items for efficiency.
   * 
   * @param items
   *          the items to register
   * @throws ItemExistsException
   *           if any constraint's ID is already registered under a different descriptor
   */
  void bulkRegister(Collection<? extends IItemDescriptor> items) throws ItemExistsException {
    Collection<IItemDescriptor> registered = new java.util.ArrayList<IItemDescriptor>(items.size());

    synchronized (descriptors) {
      for (IItemDescriptor next : items) {
        if (doRegister(next)) {
          registered.add(next);
        }
      }
    }

    if (!registered.isEmpty()) {
      ItemChangeEvent event = new ItemChangeEvent(null, ItemChangeEventType.REGISTERED);

      for (IItemDescriptor next : registered) {
        event.setItemDescriptor(next);
        broadcastItemChangeEvent(event);
      }
    }
  }
}
