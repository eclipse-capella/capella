package org.polarsys.capella.core.model.preferences.helpers;

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.model.preferences.IDataPreferences;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.model.preferences.IDeploymentPreferences;
import org.polarsys.capella.core.model.preferences.IInheritancePreferences;
import org.polarsys.capella.core.model.preferences.IModeAndStateManagementPreferences;
import org.polarsys.capella.core.model.preferences.IReuseComponentsPreferences;
import org.polarsys.capella.core.model.preferences.ISynchronizationPreferences;

public class PreferencesHelper {
    
    PreferencesHelper() {}
    
    /**
     * Get the Confirm Delete current preference value. <br>
     * <br>
     *
     * @deprecated use IDeletePreferences.INSTANCE.isConfirmationRequired()
     * @link {@link IDeletePreferences#PREFERENCE_CONFIRM_DELETE} value <code>true or false</code>
     * @return boolean value
     */
    @Deprecated
    public static boolean isConfirmDeleteAllowed() {
      return AbstractPreferencesInitializer.getBoolean(IDeletePreferences.PREFERENCE_CONFIRM_DELETE,
          IDeletePreferences.PREFERENCE_CONFIRM_DELETE_DEFAULT);
    }

    /**
     * Get the Delete Parts current preference value. <br>
     * <br>
     *
     * @deprecated use IDeletePreferences.INSTANCE.isDeletePartsAllowed()
     * @link {@link IDeletePreferences#PREFERENCE_DELETE_PARTS} value <code>true or false</code>
     * @return boolean value
     */
    @Deprecated
    public static boolean isDeletePartsAllowed() {
      return AbstractPreferencesInitializer.getBoolean(IDeletePreferences.PREFERENCE_DELETE_PARTS,
          IDeletePreferences.PREFERENCE_DELETE_PARTS_DEFAULT);
    }

    /**
     * Get the Allow Primitive Synchronization Strategy current preference value. <br>
     * <br>
     *
     * @link {@link IDataPreferences#PREFS_ALLOW_PRIMITIVE_SYNCHRONIZATION} value <code>true or false</code>
     * @return boolean value
     */
    public static boolean isPrimitiveSynchroAllowed() {
      return AbstractPreferencesInitializer.getBoolean(IDataPreferences.PREFS_ALLOW_PRIMITIVE_SYNCHRONIZATION, true);

    }

    /**
     * Get the Allow Reuse of components Strategy current preference value. <br>
     * <br>
     *
     * @link {@link IReuseComponentsPreferences#PREFS_ALLOW_REUSE_COMPONENTS} value <code>true or false</code>
     * @return boolean value
     */
    public static boolean isReuseOfComponentsAllowed() {
      return AbstractPreferencesInitializer.getBoolean(IReuseComponentsPreferences.PREFS_ALLOW_REUSE_COMPONENTS, true);

    }

    /**
     * Get the Allow Multiple Inheritance Strategy current preference value. <br>
     * <br>
     *
     * @link {@link IInheritancePreferences#PREFS_ALLOW_MULTIPLE_INHERITANCE} value <code>true or false</code>
     * @return boolean value
     */
    public static boolean isMultipleInheritanceAllowed() {
      return AbstractPreferencesInitializer.getBoolean(IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, true);

    }

    /**
     * Get the Allowed Component Non Actor Inheritance Strategy current preference value. <br>
     * <br>
     *
     * @link {@link IInheritancePreferences#PREFS_ALLOW_COMPONENT_NON_ACTOR_INHERITANCE} value <code>true or false</code>
     * @return boolean value
     */
    public static boolean isComponentNonActorInheritanceAllowed() {
      return AbstractPreferencesInitializer
          .getBoolean(IInheritancePreferences.PREFS_ALLOW_COMPONENT_NON_ACTOR_INHERITANCE, true);

    }

    /**
     * Get the Allow Multiple Deployment Strategy current preference value. <br>
     * <br>
     *
     * @link {@link IDeploymentPreferences#PREFS_ALLOW_MULTIPLE_DEPLOYMENT} value <code>true or false</code>
     * @return boolean value
     */
    public static boolean isMultipleDeploymentAllowed() {
      return AbstractPreferencesInitializer.getBoolean(IDeploymentPreferences.PREFS_ALLOW_MULTIPLE_DEPLOYMENT, true);
    }

    /**
     * Get the Allow Synchronization of ComponentPort to FunctionPort current preference value. <br>
     * <br>
     *
     * @link {@link ISynchronizationPreferences#PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT} value
     *       <code>true or false</code>
     * @return boolean value
     */
    public static boolean isSynchronizationOfComponentPortToFunctionPortAllowed() {
      return AbstractPreferencesInitializer
          .getBoolean(ISynchronizationPreferences.PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT, true);
    }

    /**
     * Get the Allow Synchronization of PhysicalPort to ComponentPort on Physical Link current preference value. <br>
     * <br>
     *
     * @link {@link ISynchronizationPreferences#PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK} value
     *       <code>true or false</code>
     * @return boolean value
     */
    public static boolean isSynchronizationOfPhysicalPortToComponentPortOnPhysicalLinkAllowed() {
      return AbstractPreferencesInitializer
          .getBoolean(ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK, true);
    }

    /**
     * Get the Allow Synchronization of PhysicalPort to ComponentPort on Physical Path current preference value. <br>
     * <br>
     *
     * @link {@link ISynchronizationPreferences#PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALPATH} value
     *       <code>true or false</code>
     * @return boolean value
     */
    public static boolean isSynchronizationOfPhysicalPortToComponentPortOnPhysicalPathAllowed() {
      return AbstractPreferencesInitializer
          .getBoolean(ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALPATH, true);
    }

    public static boolean isMixedModeStateAllowed() {
      return AbstractPreferencesInitializer.getBoolean(IModeAndStateManagementPreferences.PREFS_MIXED_MODE_STATE_ALLOWED,
          true);
    }

    /**
     * @return whether it's allowed to change the Physical Component Nature
     */
    public static boolean isChangePhysicalComponentNatureAllowed() {
      return AbstractPreferencesInitializer.getBoolean(IDataPreferences.PREFS_ALLOW_PHYSICAL_COMPONENT_NATURE_CHANGE,
          true);
    }

    /**
     * 
     * @return
     */
    public static boolean isSpecialElementProtectionAllowed() {
      return AbstractPreferencesInitializer.getBoolean(IDeletePreferences.PREFERENCE_DELETE_PROTECTED_ELEMENTS,
          IDeletePreferences.PREFERENCE_DELETE_PREOTECTED_ELEMENTS_DEFAULT);
    }
}
