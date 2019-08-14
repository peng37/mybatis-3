/**
 *    Copyright 2009-2015 the original author or authors.
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
package org.apache.ibatis.executor.keygen;

import java.sql.Statement;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * @author Clinton Begin
 */
public interface KeyGenerator {
  //在执行 insert 之前执行，设置属性 order ＝ ” BEFORE ”
  void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter);
  //在执行 insert 之后执行，设置属性 order＝“AFTER”

  /**
   *
   * @param executor 执行该操作的执行器
   * @param ms 解析配置XML创建的映射对象
   * @param stmt 响应数据
   * @param parameter 插入操作的响应实体 或 插入操作的入参，用于封装自增响应的ID
   */
  void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

}
