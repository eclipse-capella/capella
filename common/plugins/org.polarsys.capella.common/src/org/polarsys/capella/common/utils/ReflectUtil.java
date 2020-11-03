/**
 * <copyright>
 *
 * Copyright (c) 2008, 2020-2010 See4sys and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     See4sys - Initial API and implementation
 *
 * </copyright>
 */
package org.polarsys.capella.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * Provides helper methods for manipulating objects reflectively.
 */
public final class ReflectUtil {
  /**
   * Check if the given Class name is assignable from a real Class
   *
   * @param clazz
   *          the real class
   * @param className
   *          the class name
   * @return <code>true</code> if the <code>clazz</code> is assignable from <code>className</code>, else return
   *         <code>false</code>
   */
  public static boolean isAssignableFrom(Class<?> clazz, String className) {
    Assert.isNotNull(clazz);

    // Test if given class directly matches specified class name
    if (clazz.getName().equals(className) || clazz.getSimpleName().equals(className)) {
      return true;
    }

    // Test if superclass of given class matches specified class name
    if (!clazz.isInterface()) {
      Class<?> superClass = clazz.getSuperclass();
      if (superClass != null) {
        if (isAssignableFrom(superClass, className)) {
          return true;
        }
      }
    }

    // Test if super interface of given class matches specified class name
    Class<?>[] interfaces = clazz.getInterfaces();
    for (Class<?> interfaze : interfaces) {
      if (isAssignableFrom(interfaze, className)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Get declared field of the given class by name
   *
   * @param clazz
   *          the owning of the field
   * @param fieldName
   *          the name of the field
   * @return <code>Field</code> of the given <code>class</code> that has the name is the given field name, return
   *         <code>null</code> if there is no such field declared in the given class
   */

  public static Field findDeclaredField(Class<?> clazz, String fieldName) {
    Assert.isNotNull(clazz);

    for (Field declaredField : clazz.getDeclaredFields()) {
      if (declaredField.getName().equals(fieldName)) {
        return declaredField;
      }
    }
    return null;
  }

  /**
   * Get the field of the given Class by name.
   *
   * @param clazz
   *          the class owning the field to find
   * @param fieldName
   *          the name of the field to find
   * @return <code>Field</code> of the given class. This field can be declared in the given class or in SuperClass or
   *         Super Interface of the given class, return <code>null</code> if there isn't such a field in the given class
   *         and its super classes
   */
  public static Field findField(Class<?> clazz, String fieldName) {
    Assert.isNotNull(clazz);

    // Search given class's fields
    Field result = findDeclaredField(clazz, fieldName);
    if (result != null) {
      return result;
    }

    // Search superclass's fields
    if (!clazz.isInterface()) {
      Class<?> superClass = clazz.getSuperclass();
      if (superClass != null) {
        result = findField(superClass, fieldName);
        if (result != null) {
          return result;
        }
      }
    }

    // Search super interfaces' fields
    Class<?>[] interfaces = clazz.getInterfaces();
    for (Class<?> interfaze : interfaces) {
      result = findField(interfaze, fieldName);
      if (result != null) {
        return result;
      }
    }

    return null;
  }

  /**
   * Returns the value of the visible (i.e. public) field with given name on given object. Supports declared and
   * inherited, non-static and static fields.
   *
   * @param object
   *          the owning of the field
   * @param fieldName
   *          the name of the field
   * @return value of the field
   * @throws NoSuchFieldException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  public static Object getFieldValue(Object object, String fieldName)
      throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Assert.isNotNull(object);

    Class<? extends Object> clazz = object instanceof Class<?> ? (Class<?>) object : object.getClass();
    Field field = findField(clazz, fieldName);
    if (field == null) {
      throw new NoSuchFieldException(clazz.getName() + "#" + fieldName); //$NON-NLS-1$
    }

    return field.get(object);
  }

  /**
   * Returns the value of the invisible (i.e. private or protected) field with given name on given object. Supports
   * declared and inherited, non-static and static fields.
   *
   * @param object
   *          the object owning the field
   * @param fieldName
   *          the name of the field to be accessed
   * @return the value of the field
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   */
  public static Object getInvisibleFieldValue(Object object, String fieldName)
      throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Assert.isNotNull(object);

    Class<? extends Object> clazz = object instanceof Class<?> ? (Class<?>) object : object.getClass();
    Field field = findField(clazz, fieldName);
    if (field == null) {
      throw new NoSuchFieldException(clazz.getName() + "#" + fieldName); //$NON-NLS-1$
    }

    Object result;
    boolean oldAccessible = field.isAccessible();
    field.setAccessible(true);
    try {
      result = field.get(object);
    } finally {
      field.setAccessible(oldAccessible);
    }
    return result;
  }

  /**
   * Sets the value of the visible (i.e. public) field with given name on given object. Supports declared and inherited,
   * non-static and static fields.
   *
   * @param object
   *          the object owning the field
   * @param fieldName
   *          the name of the field to be accessed
   * @param value
   *          the value to be set on the field
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   */
  public static void setFieldValue(Object object, String fieldName, Object value)
      throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Assert.isNotNull(object);

    Class<? extends Object> clazz = object instanceof Class<?> ? (Class<?>) object : object.getClass();
    Field field = ReflectUtil.findField(clazz, fieldName);
    if (field == null) {
      throw new NoSuchFieldException(clazz.getName() + "#" + fieldName); //$NON-NLS-1$
    }

    field.set(object, value);
  }

  /**
   * Sets the value of the invisible (i.e. private or protected) field with given name on given object. Supports
   * declared and inherited, non-static and static fields.
   *
   * @param object
   *          the object owning the field
   * @param fieldName
   *          the name of the field to be accessed
   * @param value
   *          the value to be set on the field
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   */
  public static void setInvisibleFieldValue(Object object, String fieldName, Object value)
      throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Assert.isNotNull(object);

    Class<? extends Object> clazz = object instanceof Class<?> ? (Class<?>) object : object.getClass();
    Field field = ReflectUtil.findField(clazz, fieldName);
    if (field == null) {
      throw new NoSuchFieldException(clazz.getName() + "#" + fieldName); //$NON-NLS-1$
    }

    boolean oldAccessible = field.isAccessible();
    field.setAccessible(true);
    try {
      field.set(object, value);
    } finally {
      field.setAccessible(oldAccessible);
    }
  }

  /**
   * Get declared method of the given class by Name and input parameter types
   *
   * @param clazz
   *          the owning of the method
   * @param methodName
   *          the name of the method declared by the given class
   * @param parameterTypes
   *          input parameter types of the method
   * @return <code>Method</code> that has the given name and given parameter types. Return <code>null</code> if there is
   *         no method like that
   */
  public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
    Assert.isNotNull(clazz);

    for (Method declaredMethod : clazz.getDeclaredMethods()) {
      if (declaredMethod.getName().equals(methodName)
          && compareParameterTypes(declaredMethod.getParameterTypes(), parameterTypes)) {
        return declaredMethod;
      }
    }
    return null;
  }

  /**
   * Find method of the given class by name and parameter types
   *
   * @param clazz
   *          the owning of the method to find
   * @param methodName
   *          the name of the method need to be found
   * @param parameterTypes
   *          input parameter types of the method
   * @return <code>Method</code> that has the given name and given parameter types. Return <code>null</code> if there is
   *         no such method like that
   * @throws NoSuchMethodException
   */
  public static Method findMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes)
      throws NoSuchMethodException {
    Assert.isNotNull(clazz);

    // Search given class's methods
    Method result = findDeclaredMethod(clazz, methodName, parameterTypes);
    if (result != null) {
      return result;
    }

    // Search superclass's methods
    if (!clazz.isInterface()) {
      Class<?> superClass = clazz.getSuperclass();
      if (superClass != null) {
        result = findMethod(superClass, methodName, parameterTypes);
        if (result != null) {
          return result;
        }
      }
    }

    // Search super interfaces' methods
    Class<?>[] interfaces = clazz.getInterfaces();
    for (Class<?> interfaze : interfaces) {
      result = findMethod(interfaze, methodName, parameterTypes);
      if (result != null) {
        return result;
      }
    }

    return null;
  }

  /**
   * Invokes the visible (i.e. public) method with given name and arguments on given object. Supports declared and
   * inherited, non-static and static methods.
   *
   * @param object
   *          the object owning the method
   * @param methodName
   *          the name of the method to be invoked
   * @param args
   *          parameter types of the method
   * @return value returned by the method
   * @throws NoSuchMethodException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static Object invokeMethod(Object object, String methodName, Object... args)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    Assert.isNotNull(object);
    Assert.isNotNull(args);

    List<Class<?>> list = new ArrayList<Class<?>>();
    for (Object arg : args) {
      list.add(arg.getClass());
    }
    Class<?>[] parameterTypes = list.toArray(new Class[list.size()]);

    Class<? extends Object> clazz = object instanceof Class<?> ? (Class<?>) object : object.getClass();
    Method method = findMethod(clazz, methodName, parameterTypes);
    if (method == null) {
      throw new NoSuchMethodException(
          clazz.getName() + "#" + methodName + "(" + convertParameterTypesToString(parameterTypes) + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    return method.invoke(object, args);
  }

  /**
   * Invokes the invisible (i.e. private or protected) method with given name and arguments on given object. Supports
   * declared and inherited, non-static and static methods.
   *
   * @param object
   *          the object owning the method
   * @param methodName
   *          the name of the method to be invoked
   * @param args
   *          the arguments of the method
   * @return the value returned by the method
   * @throws NoSuchMethodException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static Object invokeInvisibleMethod(Object object, String methodName, Object... args)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    Assert.isNotNull(object);
    Assert.isNotNull(args);

    List<Class<?>> list = new ArrayList<Class<?>>();
    for (Object arg : args) {
      list.add(arg.getClass());
    }
    Class<?>[] parameterTypes = list.toArray(new Class[list.size()]);

    Class<? extends Object> clazz = object instanceof Class<?> ? (Class<?>) object : object.getClass();
    Method method = findMethod(clazz, methodName, parameterTypes);
    if (method == null) {
      throw new NoSuchMethodException(
          clazz.getName() + "#" + methodName + "(" + convertParameterTypesToString(parameterTypes) + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    Object result;
    boolean oldAccessible = method.isAccessible();
    method.setAccessible(true);
    try {
      result = method.invoke(object, args);
    } finally {
      method.setAccessible(oldAccessible);
    }
    return result;
  }

  /**
   * Compare 2 given ParamameterTypes arrays.
   *
   * @param parameterTypes1
   * @param parameterTypes2
   * @return <code>TRUE</code> if 2 arrays are the same, else return <code>FALSE</code>.
   */
  private static boolean compareParameterTypes(Class<?>[] parameterTypes1, Class<?>[] parameterTypes2) {
    Assert.isNotNull(parameterTypes1);
    Assert.isNotNull(parameterTypes2);

    if (parameterTypes1.length != parameterTypes2.length) {
      return false;
    }

    for (int i = 0; i < parameterTypes1.length; i++) {
      Class<?> parameterType1 = parameterTypes1[i];
      Class<?> parameterType2 = parameterTypes2[i];
      if (!parameterType1.isAssignableFrom(parameterType2)) {
        if (parameterType1.getName().equals("int") && parameterType2.getName().equals("java.lang.Integer")) { //$NON-NLS-1$ //$NON-NLS-2$
          break;
        } else if (parameterType1.getName().equals("boolean") && parameterType2.getName().equals("java.lang.Boolean")) { //$NON-NLS-1$ //$NON-NLS-2$
          break;
        }
        return false;
      }
    }

    return true;
  }

  /**
   * Convert Parameter Types to String, e.g. "parameterTypeName1, parameterTypeName2"
   *
   * @param parameterTypes
   *          given parameter types
   * @return String of the Parameter Types
   */
  private static String convertParameterTypesToString(Class<?>... parameterTypes) {
    Assert.isNotNull(parameterTypes);

    StringBuilder buf = new StringBuilder();
    for (int i = 0; i < parameterTypes.length; i++) {
      if (i > 0) {
        buf.append(", "); //$NON-NLS-1$
      }
      Class<?> clazz = parameterTypes[i];
      buf.append(clazz == null ? "null" : clazz.getName()); //$NON-NLS-1$
    }
    return buf.toString();
  }

  /**
   * Get simple package name of the given package
   *
   * @param qualifiedPackageName
   *          the given package name
   * @return simple name of the packge
   */
  public static String getSimplePackageName(String qualifiedPackageName) {
    if (qualifiedPackageName != null) {
      int lastSeparatorIndex = qualifiedPackageName.lastIndexOf("."); //$NON-NLS-1$
      if (lastSeparatorIndex != -1) {
        if (lastSeparatorIndex < qualifiedPackageName.length() - 1) {
          return qualifiedPackageName.substring(lastSeparatorIndex + 1);
        }
        return ""; //$NON-NLS-1$
      }
      return qualifiedPackageName;
    }
    return null;
  }

  /**
   * Get super Package name of the given package
   *
   * @param qualifiedPackageName
   *          the given package name
   * @return name of the super package
   */
  public static String getSuperPackageName(String qualifiedPackageName) {
    if (qualifiedPackageName != null) {
      int lastSeparatorIndex = qualifiedPackageName.lastIndexOf("."); //$NON-NLS-1$
      if (lastSeparatorIndex != -1) {
        return qualifiedPackageName.substring(0, lastSeparatorIndex);
      }
    }
    return null;
  }

  /**
   * Clears, i.e. sets to null, all fields of the given object which are available on the class of the object. This
   * includes public, protected, default (package) access, and private fields of the given class or interface, and does
   * include inherited fields.
   *
   * @param object
   *          the object whose fields are to be cleared
   * @throws IllegalAccessException
   */
  public static void clearAllFields(Object object) throws IllegalAccessException {
    clearAllFields(object, new String[0]);
  }

  /**
   * Clears, i.e. sets to null, all fields of the given object which are available on the class of the object except for
   * specified excluded fields. This includes public, protected, default (package) access, and private fields of the
   * given class or interface, and does include inherited fields.
   *
   * @param object
   *          the object whose fields are to be cleared
   * @param excludedFieldNames
   *          the names of the fields that are to be excluded from clearing
   * @throws IllegalAccessException
   */
  public static void clearAllFields(Object object, String[] excludedFieldNames) throws IllegalAccessException {
    Assert.isNotNull(object);

    clearAllFields(object, object.getClass(), excludedFieldNames);
  }

  /**
   * Clears, i.e. sets to null, all fields of the given object which are available on the given class or interface
   * except for specified excluded fields. This includes public, protected, default (package) access, and private fields
   * of the given class or interface, and does include inherited fields.
   *
   * @param object
   *          the object whose fields are to be cleared
   * @param clazz
   *          the class or interface indicating the declared and inherited fields which are to be cleared on the given
   *          object
   * @param excludedFieldNames
   *          the names of the fields that are to be excluded from clearing
   * @throws IllegalAccessException
   */
  private static void clearAllFields(Object object, Class<?> clazz, String[] excludedFieldNames)
      throws IllegalAccessException {
    Assert.isNotNull(object);
    Assert.isNotNull(clazz);

    // Clear given class's fields
    clearDeclaredFields(object, clazz, excludedFieldNames);

    // Clear superclass's fields
    if (!clazz.isInterface()) {
      Class<?> superClass = clazz.getSuperclass();
      if (superClass != null) {
        clearAllFields(object, superClass, excludedFieldNames);
      }
    }

    // Clear super interfaces' fields
    Class<?>[] interfaces = clazz.getInterfaces();
    for (Class<?> interfaze : interfaces) {
      clearAllFields(object, interfaze, excludedFieldNames);
    }
  }

  /**
   * Clears, i.e. sets to null, all fields of the given object which are declared by the given class or interface except
   * for specified excluded fields. This includes public, protected, default (package) access, and private fields of the
   * given class or interface, but excludes inherited fields. Z
   *
   * @param object
   *          the object whose fields are to be cleared
   * @param clazz
   *          the class or interface indicating the declared fields which are to be cleared on the given object
   * @param excludedFieldNames
   *          the names of the fields that are to be excluded from clearing
   * @throws IllegalAccessException
   */
  private static void clearDeclaredFields(Object object, Class<?> clazz, String[] excludedFieldNames)
      throws IllegalAccessException {
    Assert.isNotNull(object);
    Assert.isNotNull(clazz);

    List<String> excludedFieldNameList = excludedFieldNames != null ? Arrays.asList(excludedFieldNames)
        : new ArrayList<String>(0);
    for (Field declaredField : clazz.getDeclaredFields()) {
      if (declaredField.getType().isPrimitive()) {
        continue;
      }
      if ((declaredField.getModifiers() & (Modifier.SYNCHRONIZED | Modifier.STATIC | Modifier.FINAL)) != 0) {
        continue;
      }
      if (excludedFieldNameList.contains(declaredField.getName())) {
        continue;
      }

      boolean oldAccessible = declaredField.isAccessible();
      try {
        declaredField.setAccessible(true);
        declaredField.set(object, null);
      } finally {
        declaredField.setAccessible(oldAccessible);
      }
    }
  }
}
