/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.reflection.wrapper;

import java.util.List;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;

/**
 * 对对象级别的元信息的处理
 * @author Clinton Begin
 */
public interface ObjectWrapper {
  //如 ObjectWrapper 中封装的是普通的 Bean 对象 ，则调用相应属性的相应getter方法，
  // 如采封装的是集合类，则获取指定 key 或下标对应的 value 位
  Object get(PropertyTokenizer prop);
  //如果 ObjectWrapper 中封装的是普通的 Bean 对象 ， 则调用相应属性的相应 setter 方法 ，
  //如果封装的是集合类，则设置指定 key 或下标对应的 value 值
  void set(PropertyTokenizer prop, Object value);

  String findProperty(String name, boolean useCamelCaseMapping);

  String[] getGetterNames();

  String[] getSetterNames();

  Class<?> getSetterType(String name);

  Class<?> getGetterType(String name);

  boolean hasSetter(String name);

  boolean hasGetter(String name);

  MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

  boolean isCollection();

  void add(Object element);

  <E> void addAll(List<E> element);

}
